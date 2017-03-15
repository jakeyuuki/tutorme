package tutorme.models.core;

import java.awt.dnd.InvalidDnDOperationException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;

import tutorme.models.core.interfaces.IBusinessObjectFactory;
import tutorme.util.Convertor;

public class BusinessObjectFactory implements IBusinessObjectFactory {
	ArrayList<LoadableObject> dataAccessObjects = new ArrayList<LoadableObject>();
	
	public BusinessObjectFactory() {}
	
	@SuppressWarnings("unchecked")
	public <T> T loadParsedData(String query, Class<T> type) {
		Connection conn = DBConnection.connect();
		try {
			return (T)loadParsedDataCore(query, type, conn);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if(conn != null ) conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}

	private Object loadParsedDataCore(String query, Class<?> type, Connection conn) throws Exception {
		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		if(type.isAssignableFrom(JSONArray.class))
			return Convertor.convertToJSON(rs);
		throw new InvalidDnDOperationException("Invalid type: " + type);
	}

	public <T extends LoadableObject> List<T> load(String query, Class<T> type) {
		Connection conn = DBConnection.connect();
		try {
			List<T> objects = loadCore(query, type, conn);
			dataAccessObjects.addAll(objects);
			return objects;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if(conn != null ) conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public <T extends LoadableObject> T loadTop1(String query, Class<T> type) {
		Connection conn = DBConnection.connect();
		try {
			T object = loadCoreTop1(query, type, conn);
			if(object != null) dataAccessObjects.add(object);
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if(conn != null ) conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public <T extends LoadableObject> T loadByPK(String PK, Class<T> type) {
		Connection conn = DBConnection.connect();
		try {
			T object = loadByPKCore(PK, type, conn);
			if(object != null) dataAccessObjects.add(object);
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if(conn != null ) conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	private <T extends LoadableObject> List<T> loadCore(String query, Class<T> type, Connection conn) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		List<T> list = new ArrayList<T>();
		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			list.add(type.getDeclaredConstructor(cArg).newInstance(rs));
		}
		return list;
	}
	
	private <T extends LoadableObject> T loadCoreTop1(String query, Class<T> type, Connection conn) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return type.getDeclaredConstructor(cArg).newInstance(rs);
		}
		return null;
	}
	
	private <T extends LoadableObject> T loadByPKCore(String PK, Class<T> type, Connection conn) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		T object = type.newInstance();
		PreparedStatement ps = conn.prepareStatement(String.format("SELECT * from %1$s WHERE %2$s = %3$s LIMIT 0,1;", object.getTableName(), object.getPKSchemaName(), LoadableObject.getValue(PK)));
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			return type.getDeclaredConstructor(cArg).newInstance(rs);
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	Class[] cArg = new Class[] { ResultSet.class };

	@Override
	public <T extends LoadableObject> T create(Class<T> type) {
		try {
			T newObject = type.newInstance();
			dataAccessObjects.add(newObject);
			return newObject;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void save() {
		Connection conn = DBConnection.connect();
		boolean autoCommit = true;
		try {
			autoCommit = conn.getAutoCommit();
			saveCore(conn);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if(conn != null ) conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		finally {
			dataAccessObjects = new ArrayList<LoadableObject>();
			try {
				conn.setAutoCommit(autoCommit);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	void saveCore(Connection conn) throws SQLException {
		conn.setAutoCommit(false);
		Collections.sort(dataAccessObjects, (p1, p2) -> getHierarchyOrder(p1, p2));
		for (LoadableObject loadableObject : dataAccessObjects) {
			loadableObject.save(conn);
		}
		conn.commit();
	}

	private int getHierarchyOrder(LoadableObject p1, LoadableObject p2) {
		return p2.isDeleting && p1.isDeleting ? Integer.compare(p2.getHierarchyLevel(), p1.getHierarchyLevel()) : 
			p2.isCreating && p1.isCreating ? Integer.compare(p1.getHierarchyLevel(), p2.getHierarchyLevel()) :
			0;
	}
	
	public <T extends LoadableObject> int count(Class<T> type) {
		Connection conn = DBConnection.connect();
		try {
			T object = type.newInstance();
			PreparedStatement ps = conn.prepareStatement(String.format("SELECT COUNT(*) FROM %1$s;", object.getTableName()));
			ResultSet rs = ps.executeQuery();
			return rs.next() ? rs.getInt(1) : 0;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if(conn != null ) conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return 0;
	}
	
	public <T extends LoadableObject> int count(String schemaColumnName, Class <T> type, String whereStatement) {
		Connection conn = DBConnection.connect();
		try {
			T object = type.newInstance();
			PreparedStatement ps = conn.prepareStatement(String.format("SELECT COUNT(%1$s) FROM %2$s WHERE %3$s;", schemaColumnName, object.getTableName(), whereStatement));
			ResultSet rs = ps.executeQuery();
			return rs.next() ? rs.getInt(1) : 0;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if(conn != null ) conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return 0;
	}
	
	public <T extends LoadableObject> T delete(String schemaColumnName, String PK, Class<T> type) {
		Connection conn = DBConnection.connect();
		try {
			T object = loadByPKCore(PK, type, conn);
			if(object != null) {
				dataAccessObjects.remove(object);
				//remove from database
				PreparedStatement ps = conn.prepareStatement(String.format("DELETE FROM %1$s WHERE %2$s = '%3$s';", object.getTableName(), schemaColumnName, PK));
				ps.executeUpdate();
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if(conn != null ) conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
	
}
