package tutorme.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tutorme.models.core.LoadableObject;

public class UserActivation extends LoadableObject{
	private String UA_UR_PK; 	//MANDATORY

	private boolean UA_UR_PKUpdated;
	
	public UserActivation() {
		super();
	}
	
	public UserActivation(ResultSet rs) throws SQLException {
		super(rs);
	}
	
	@Override
	public void load(ResultSet databaseObject) throws SQLException {
		PK = databaseObject.getString(UserActivationSchema.PK);
		UA_UR_PK = databaseObject.getString(UserActivationSchema.UA_UR_PK);
	}
	
	@Override
	public int getHierarchyLevel() {
		return 1;
	}
	
	public static String getQueryFindByUserId(String UA_UR_PK) {
		return String.format("SELECT * FROM %1$s WHERE %2$s LIMIT 0,1;", UserActivationSchema.TableName, 
				getQueryEqualStatement(UserActivationSchema.UA_UR_PK, UA_UR_PK));
	}
	
	public String getUA_UR_PK() {
		return UA_UR_PK;
	}

	public void setUA_UR_PK(String UA_UR_PK) {
		isUpdating = UA_UR_PKUpdated  = true;
		this.UA_UR_PK = UA_UR_PK;
	}

	public void setMandatoryFields(String UA_UR_PK) {
		setUA_UR_PK(UA_UR_PK);
	}
	
	@Override
	protected String getTableName() {
		return UserActivationSchema.TableName;
	}
	
	@Override
	protected String getPKSchemaName() {
		return UserActivationSchema.PK;
	}
	
	@Override
	protected List<String> getQuerySetParams() {
		List<String> params = new ArrayList<String>();
		
		if(UA_UR_PKUpdated) 	params.add(getQueryEqualStatement(UserActivationSchema.UA_UR_PK, UA_UR_PK));
		
		return params;
	}
	
	@Override
	protected List<String>getSchemaColumns() {
		List<String> params = new ArrayList<String>();
		params.add(UserActivationSchema.PK);
		
		if(UA_UR_PKUpdated) 	params.add(UserActivationSchema.UA_UR_PK);
		
		return params;
	}

	@Override
	protected List<String> getValues() {
		List<String> params = new ArrayList<String>();
		params.add(getValue(PK));
		
		if(UA_UR_PKUpdated) 	params.add(getValue(UA_UR_PK));
		
		return params;
	}

	public class UserActivationSchema {
		public static final String PK 					= "UA_ActivationKey";
		public static final String TableName 			= "UserActivation";
		public static final String UA_UR_PK 			= "UA_UR_PK";
	}
}
