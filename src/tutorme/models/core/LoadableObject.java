package tutorme.models.core;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import tutorme.models.core.interfaces.ILoadableObject;
import tutorme.util.DateUTC;

public abstract class LoadableObject implements ILoadableObject {
	public String PK;
	protected boolean isCreating;
	protected boolean isUpdating;
	protected boolean isDeleting;
	
	public LoadableObject() {
		isUpdating = isDeleting = false;
		isCreating = true;
		PK = UUID.randomUUID().toString();
	}
	
	public LoadableObject(ResultSet databaseObject) throws SQLException {
		isUpdating = isDeleting = isCreating = false;
		load(databaseObject);
	}
	
	public abstract void load(ResultSet databaseObject) throws SQLException;
	
	public void save(Connection connection) throws SQLException {
		PreparedStatement stmt;
		
		if(isCreating)
			stmt = create(connection);
		else if(isDeleting)
			stmt = delete(connection);
		else if(isUpdating)
			stmt = update(connection);
		else
			return;
		
		try {
			stmt.execute();
		} catch (SQLException e) {
			System.out.println(stmt);
			throw e;
		}
	}
	
	public void reload() {
		if(!isCreating && !isDeleting) {
			Connection conn = DBConnection.connect();
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement(String.format("SELECT * FROM %1$s WHERE %2$s LIMIT 0,1;", getTableName(), getReloadWhereStatement()));
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					isUpdating = isDeleting = isCreating = false;
					load(rs);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				// keep the old data.
			}
		}
	}
	
	protected void setSpecialStatement(PreparedStatement ps) throws SQLException { }

	protected String getReloadWhereStatement() {
		return String.format("%1$s = %2$s", getPKSchemaName(), getValue(PK));
	}
	
	public String getPK() {
		return PK;
	}
	
	public void delete() {
		isDeleting = true;
	}
	
	protected PreparedStatement create(Connection connection)throws SQLException {
		String query = String.format("INSERT INTO %1$s (%2$s) VALUES (%3$s);", getTableName(), String.join(", ", getSchemaColumns()), String.join(", ", getValues()));
		PreparedStatement ps = connection.prepareStatement(query);
		setSpecialStatement(ps);
		return ps;
	}
	
	protected PreparedStatement delete(Connection connection)throws SQLException {
		String query = String.format("DELETE FROM %1$s WHERE %2$s;", getTableName(), getQueryWhereParams());
		PreparedStatement ps = connection.prepareStatement(query);
		return ps;
	}
	
	protected PreparedStatement update(Connection connection) throws SQLException {
		String query = String.format("UPDATE %1$s SET %2$s WHERE %3$s;", getTableName(), String.join(", ", getQuerySetParams()), getQueryWhereParams());
		PreparedStatement ps = connection.prepareStatement(query);
		setSpecialStatement(ps);
		return ps;
	}
	
	public abstract int getHierarchyLevel();
	protected abstract String getTableName();
	protected abstract String getPKSchemaName();
	protected abstract List<String> getQuerySetParams();
	protected abstract List<String> getSchemaColumns();
	protected abstract List<String> getValues();
	
	protected String getQueryWhereParams() {
		return getQueryEqualStatement(getPKSchemaName(), PK);
	}
	
	protected static String getQueryEqualQuestionMarkStatement(String field) {
		return getQueryStatement(field, "=", "?");
	}
	
	protected static String getQueryStatement(String field, String operator, Object value) {
		return String.format("%1$s %2$s %3$s", field, operator, value);
	}
	
	protected static String getQueryEqualStatement(String field, String value) {
		return getQueryStatement(field, "=", getValue(value));
	}
	
	protected static String getQueryEqualStatement(String field, char value) {
		return getQueryStatement(field, "=", getValue(value));
	}
	
	protected static String getQueryEqualStatement(String field, boolean value) {
		return getQueryStatement(field, "=", getValue(value));
	}
	
	protected static String getQueryEqualStatement(String field, Integer value) {
		return getQueryStatement(field, "=", getValue(value));
	}
	
	protected static String getQueryEqualStatement(String field, Blob value) {
		return getQueryStatement(field, "=", getValue(value));
	}
	
	protected String getQueryEqualStatement(String field, Date value) {
		return getQueryStatement(field, "=", getValue(value));
	}
	
	protected String getQueryEqualStatement(String field, BigDecimal value) {
		return getQueryStatement(field, "=", getValue(value));
	}
	
	protected static String getValue(BigDecimal value) {
		return value + "";
	}

	protected static String getValue(Date value) {
		return "'" + DateUTC.getDateTimeDBFormat(value) + "'";
	}

	protected static String getValue(boolean value) {
		  return value ? "1" : "0";
	}
	
	protected static String getValue(Blob value) {
		  return value.toString();
	}
	
	protected static String getValue(char value) {
		return "'"+value+"'";
	}
	
	protected static String getValue(String value) {
		return "'"+value+"'";
	}
	
	protected static String getValue(Integer value) {
		return value == null ? "NULL" : value+ "";
	}
}
