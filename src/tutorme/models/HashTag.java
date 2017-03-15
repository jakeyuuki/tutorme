package tutorme.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tutorme.models.core.LoadableObject;

public class HashTag extends LoadableObject{
	private String HT_Key; 	//MANDATORY

	private boolean HT_KeyUpdated;
	
	public HashTag() {
		super();
	}
	
	public HashTag(ResultSet rs) throws SQLException {
		super(rs);
	}
	
	@Override
	public void load(ResultSet databaseObject) throws SQLException {
		PK = databaseObject.getString(HashTagSchema.PK);
		HT_Key = databaseObject.getString(HashTagSchema.HT_Key);
	}
	
	@Override
	public int getHierarchyLevel() {
		return 0;
	}
	
	public String getHT_Key() {
		return HT_Key;
	}

	public void setHT_Key(String HT_Key) {
		isUpdating = HT_KeyUpdated = true;
		this.HT_Key = HT_Key;
	}


	public void setMandatoryFields(String HT_Key) {
		setHT_Key(HT_Key);
	}
	
	@Override
	protected String getTableName() {
		return HashTagSchema.TableName;
	}
	
	@Override
	protected String getPKSchemaName() {
		return HashTagSchema.PK;
	}
	
	@Override
	protected List<String> getQuerySetParams() {
		List<String> params = new ArrayList<String>();
		
		if(HT_KeyUpdated) 		params.add(getQueryEqualStatement(HashTagSchema.HT_Key, HT_Key));
		
		return params;
	}
	
	@Override
	protected List<String>getSchemaColumns() {
		List<String> params = new ArrayList<String>();
		params.add(HashTagSchema.PK);
		
		if(HT_KeyUpdated) 	params.add(HashTagSchema.HT_Key);
		
		return params;
	}

	@Override
	protected List<String> getValues() {
		List<String> params = new ArrayList<String>();
		params.add(getValue(PK));
		
		if(HT_KeyUpdated) 	params.add(getValue(HT_Key));
		
		return params;
	}

	public class HashTagSchema {
		public static final String PK 			= "HT_PK";
		public static final String TableName	= "HashTag";
		public static final String HT_Key 		= "HT_Key";
	}
}
