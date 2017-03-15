package tutorme.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tutorme.models.core.LoadableObject;

public class Certification extends LoadableObject{
	private String CN_TR_PK; 			//MANDATORY
	private String CN_Name;				//MANDATORY
	private String CN_Description; 		//MANDATORY
	private String CN_Scan; 		//MANDATORY

	private boolean CN_ScanUpdated;

	private boolean CN_TR_PKUpdated;
	private boolean CN_NameUpdated;
	private boolean CN_DescriptionUpdated;
	
	public Certification() {
		super();
	}
	
	public Certification(ResultSet rs) throws SQLException {
		super(rs);
	}
	
	@Override
	public void load(ResultSet databaseObject) throws SQLException {
		PK = databaseObject.getString(CertificationSchema.PK);
		CN_TR_PK = databaseObject.getString(CertificationSchema.CN_TR_PK);
		CN_Name = databaseObject.getString(CertificationSchema.CN_Name);
		CN_Description = databaseObject.getString(CertificationSchema.CN_Description);
		CN_Scan = databaseObject.getString(CertificationSchema.CN_Scan);

	}
	
	public static String getQueryFindCertificationsByTutorPK(String tutorPK) {
		return String.format("SELECT * FROM %1$s WHERE %2$s = '%3$s';", 
				CertificationSchema.TableName, 
				CertificationSchema.CN_TR_PK, 
				tutorPK);
	}
	
	@Override
	public int getHierarchyLevel() {
		return 2;
	}
	
	public String getCN_TR_PK() {
		return CN_TR_PK;
	}

	public void setCN_TR_PK(String CN_TR_PK) {
		isUpdating = CN_TR_PKUpdated = true;
		this.CN_TR_PK = CN_TR_PK;
	}

	public String getCN_Name() {
		return CN_Name;
	}

	public void setCN_Name(String CN_Name) {
		isUpdating = CN_NameUpdated = true;
		this.CN_Name = CN_Name;
	}
	
	public String getCN_Scan() {
		return CN_Scan;
	}

	public void setCN_Scan(String CN_Scan) {
		isUpdating = CN_ScanUpdated = true;
		this.CN_Scan = CN_Scan;
	}
	
	public String getCN_Description() {
		return CN_Description;
	}

	public void setCN_Description(String CN_Description) {
		isUpdating = CN_DescriptionUpdated = true;
		this.CN_Description = CN_Description;
	}
	

	public void setMandatoryFields(String CN_Name, String CN_Description) {
		setCN_Name(CN_Name);
		setCN_Description(CN_Description);
	}
	
	@Override
	protected String getTableName() {
		return CertificationSchema.TableName;
	}
	
	@Override
	protected String getPKSchemaName() {
		return CertificationSchema.PK;
	}
	
	@Override
	protected List<String> getQuerySetParams() {
		List<String> params = new ArrayList<String>();
		
		if(CN_TR_PKUpdated)			params.add(getQueryEqualStatement(CertificationSchema.CN_TR_PK, CN_TR_PK));
		if(CN_NameUpdated) 			params.add(getQueryEqualStatement(CertificationSchema.CN_Name, CN_Name));
		if(CN_DescriptionUpdated) 	params.add(getQueryEqualStatement(CertificationSchema.CN_Description, CN_Description));
		if(CN_ScanUpdated)			params.add(getQueryEqualStatement(CertificationSchema.CN_Scan, CN_Scan));
		
		return params;
	}
	
	@Override
	protected List<String>getSchemaColumns() {
		List<String> params = new ArrayList<String>();
		params.add(CertificationSchema.PK);

		if(CN_TR_PKUpdated)			params.add(CertificationSchema.CN_TR_PK);
		if(CN_NameUpdated) 			params.add(CertificationSchema.CN_Name);
		if(CN_DescriptionUpdated) 	params.add(CertificationSchema.CN_Description);
		if(CN_ScanUpdated)			params.add(CertificationSchema.CN_Scan);
		
		return params;
	}

	@Override
	protected List<String> getValues() {
		List<String> params = new ArrayList<String>();
		params.add(getValue(PK));

		if(CN_TR_PKUpdated)			params.add(getValue(CN_TR_PK));
		if(CN_NameUpdated) 			params.add(getValue(CN_Name));
		if(CN_DescriptionUpdated) 	params.add(getValue(CN_Description));
		if(CN_ScanUpdated) 			params.add(getValue(CN_Scan));
		
		return params;
	}

	public class CertificationSchema {
		public static final String PK 				= "CN_PK";
		public static final String TableName 		= "Certification";
		public static final String CN_TR_PK 		= "CN_TR_PK";
		public static final String CN_Name 			= "CN_Name";
		public static final String CN_Scan 			= "CN_Scan";

		public static final String CN_Description 	= "CN_Description";
	}
}
