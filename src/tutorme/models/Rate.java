package tutorme.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tutorme.models.core.LoadableObject;

public class Rate extends LoadableObject{
	private String RE_TR_CE_Reference; 	//MANDATORY
	private int RE_Value;			//MANDATORY
	private String RE_Table; 			//MANDATORY
	private String RE_CreatedBy; 		//MANDATORY
	private Date RE_CreatedTimeUTC;		// READ-ONLY, Auto-Created

	private boolean RE_TR_CE_ReferenceUpdated;
	private boolean RE_ValueUpdated;
	private boolean RE_TableUpdated;
	private boolean RE_CreatedByUpdated;
	
	public Rate() {
		super();
	}
	
	public Rate(ResultSet rs) throws SQLException {
		super(rs);
	}
	
	@Override
	public void load(ResultSet databaseObject) throws SQLException {
		PK = databaseObject.getString(RateSchema.PK);
		RE_TR_CE_Reference = databaseObject.getString(RateSchema.RE_TR_CE_Reference);
		RE_Value = databaseObject.getInt(RateSchema.RE_Value);
		RE_Table = databaseObject.getString(RateSchema.RE_Table);
		RE_CreatedBy = databaseObject.getString(RateSchema.RE_CreatedBy);
		RE_CreatedTimeUTC = databaseObject.getTimestamp(RateSchema.RE_CreatedTimeUTC);
	}
	
	@Override
	public int getHierarchyLevel() {
		return 3;
	}
	
	public String getRE_TR_CE_Reference() {
		return RE_TR_CE_Reference;
	}

	public void setRE_TR_CE_Reference(String RE_TR_CE_Reference) {
		isUpdating = RE_TR_CE_ReferenceUpdated  = true;
		this.RE_TR_CE_Reference = RE_TR_CE_Reference;
	}

	public int getRE_Value() {
		return RE_Value;
	}

	public void setRE_Value(int RE_Value) {
		isUpdating = RE_ValueUpdated = true;
		this.RE_Value = RE_Value;
	}
	
	public String getRE_CreatedBy() {
		return RE_CreatedBy;
	}
	
	public void setRE_CreatedBy(String RE_CreatedBy) {
		isUpdating = RE_CreatedByUpdated = true;
		this.RE_CreatedBy = RE_CreatedBy;
	}
	
	public String getRE_Table() {
		return RE_Table;
	}
	
	public void setRE_Table(String RE_Table) {
		isUpdating = RE_TableUpdated = true;
		this.RE_Table = RE_Table;
	}
	
	public Date getCT_CreatedTimeUTC() {
		return RE_CreatedTimeUTC;
	}

	public void setMandatoryFields(String RE_TR_CE_Reference, int RE_Value, String RE_CreatedBy, String RE_Table) {
		setRE_TR_CE_Reference(RE_TR_CE_Reference);
		setRE_Value(RE_Value);
		setRE_CreatedBy(RE_CreatedBy);
		setRE_Table(RE_Table);
	}
	
	@Override
	protected String getTableName() {
		return RateSchema.TableName;
	}
	
	@Override
	protected String getPKSchemaName() {
		return RateSchema.PK;
	}
	
	@Override
	protected List<String> getQuerySetParams() {
		List<String> params = new ArrayList<String>();
		
		if(RE_TR_CE_ReferenceUpdated) 	params.add(getQueryEqualStatement(RateSchema.RE_TR_CE_Reference, RE_TR_CE_Reference));
		if(RE_ValueUpdated) 			params.add(getQueryEqualStatement(RateSchema.RE_Value, RE_Value));
		if(RE_CreatedByUpdated) 		params.add(getQueryEqualStatement(RateSchema.RE_CreatedBy, RE_CreatedBy));
		if(RE_TableUpdated) 			params.add(getQueryEqualStatement(RateSchema.RE_Table, RE_Table));
		
		return params;
	}
	
	@Override
	protected List<String>getSchemaColumns() {
		List<String> params = new ArrayList<String>();
		params.add(RateSchema.PK);
		
		if(RE_TR_CE_ReferenceUpdated) 	params.add(RateSchema.RE_TR_CE_Reference);
		if(RE_ValueUpdated) 			params.add(RateSchema.RE_Value);
		if(RE_CreatedByUpdated) 		params.add(RateSchema.RE_CreatedBy);
		if(RE_TableUpdated) 			params.add(RateSchema.RE_Table);
		
		return params;
	}

	@Override
	protected List<String> getValues() {
		List<String> params = new ArrayList<String>();
		params.add(getValue(PK));
		
		if(RE_TR_CE_ReferenceUpdated) 	params.add(getValue(RE_TR_CE_Reference));
		if(RE_ValueUpdated) 			params.add(getValue(RE_Value));
		if(RE_CreatedByUpdated) 		params.add(getValue(RE_CreatedBy));
		if(RE_TableUpdated) 			params.add(getValue(RE_Table));
		
		return params;
	}

	public class RateSchema {
		public static final String PK 					= "RE_PK";
		public static final String TableName 			= "Rate";
		public static final String RE_TR_CE_Reference 	= "RE_TR_CE_Reference";
		public static final String RE_Value 			= "RE_Value";
		public static final String RE_Table 			= "RE_Table";
		public static final String RE_CreatedBy 		= "RE_CreatedBy";
		public static final String RE_CreatedTimeUTC 	= "RE_CreatedTimeUTC";
	}
}
