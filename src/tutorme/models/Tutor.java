package tutorme.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tutorme.models.core.LoadableObject;
import tutorme.util.DateUTC;

public class Tutor extends LoadableObject{
	private String TR_Summary;				//MANDATORY
	private String TR_Description; 			//MANDATORY
	private String TR_UR_PK;				//MANDATORY
	private boolean TR_IsApproved;
	private String TR_Pic;

	
	private Date TR_RegisteredTimeUTC; 	//READ-ONLY, Auto-Created
	private Date TR_UpdatedTimeUTC;		// Auto-Created

	private boolean TR_PicUpdated;
	private boolean TR_SummaryUpdated;
	private boolean TR_IsApprovedUpdated;
	private boolean TR_DescriptionUpdated;
	private boolean TR_UR_PKUpdated;
	
	public Tutor() {
		super();
	}
	
	public Tutor(ResultSet rs) throws SQLException {
		super(rs);
	}
	
	@Override
	public void load(ResultSet databaseObject) throws SQLException {
		PK = databaseObject.getString(TutorSchema.PK);
		TR_UR_PK = databaseObject.getString(TutorSchema.TR_UR_PK);
		TR_Summary = databaseObject.getString(TutorSchema.TR_Summary);
		TR_Description = databaseObject.getString(TutorSchema.TR_Description);
		TR_IsApproved = databaseObject.getBoolean(TutorSchema.TR_IsApproved);
		TR_Pic = databaseObject.getString(TutorSchema.TR_Pic);
		TR_RegisteredTimeUTC = databaseObject.getTimestamp(TutorSchema.TR_RegisteredTimeUTC);
		TR_UpdatedTimeUTC = databaseObject.getTimestamp(TutorSchema.TR_UpdatedTimeUTC);
	}
	
	@Override
	public int getHierarchyLevel() {
		return 1;
	}
	
	public static String getQueryFindUserPK(String userPK) {
		return String.format("SELECT * FROM %1$s WHERE %2$s = '%3$s';", 
				TutorSchema.TableName, 
				TutorSchema.TR_UR_PK, 
				userPK);
	}
	
	public String getTR_Summary() {
		return TR_Summary;
	}

	public void setTR_Summary(String TR_Summary) {
		isUpdating = TR_SummaryUpdated = true;
		this.TR_Summary = TR_Summary;
	}
	
	public String getTR_Description() {
		return TR_Description;
	}

	public void setTR_Description(String TR_Description) {
		isUpdating = TR_DescriptionUpdated = true;
		this.TR_Description = TR_Description;
	}
	public String getTR_Pic() {
		return TR_Pic;
	}

	public void setTR_Pic(String TR_Pic) {
		isUpdating = TR_PicUpdated = true;
		this.TR_Pic = TR_Pic;
	}

	public String getTR_UR_PK() {
		return TR_UR_PK;
	}

	public void setTR_IsApproved(boolean TR_IsApproved) {
		isUpdating = TR_IsApprovedUpdated = true;
		this.TR_IsApproved = TR_IsApproved;
	}
	
	public boolean getTR_IsApproved() {
		return TR_IsApproved;
	}

	public void setTR_UR_PK(String TR_UR_PK) {
		isUpdating = TR_UR_PKUpdated = true;
		this.TR_UR_PK = TR_UR_PK;
	}
	
	public Date getTR_RegisteredTimeUTC() {
		return TR_RegisteredTimeUTC;
	}
	
	public Date getTR_UpdatedTimeUTC() {
		return TR_UpdatedTimeUTC;
	}

	public void setMandatoryFields(String TR_UR_PK, String TR_Summary) {
		setTR_UR_PK(TR_UR_PK);
		setTR_Summary(TR_Summary);
	}
	
	@Override
	protected String getTableName() {
		return TutorSchema.TableName;
	}
	
	@Override
	protected String getPKSchemaName() {
		return TutorSchema.PK;
	}
	
	@Override
	protected List<String> getQuerySetParams() {
		List<String> params = new ArrayList<String>();
		
		if(TR_DescriptionUpdated) 	params.add(getQueryEqualQuestionMarkStatement(TutorSchema.TR_Description));
		if(TR_IsApprovedUpdated) 	params.add(getQueryEqualStatement(TutorSchema.TR_IsApproved, TR_IsApproved));
		if(TR_SummaryUpdated) 		params.add(getQueryEqualStatement(TutorSchema.TR_Summary, TR_Summary));
		if(TR_UR_PKUpdated) 		params.add(getQueryEqualStatement(TutorSchema.TR_UR_PK, TR_Description));
		if(TR_PicUpdated) 			params.add(getQueryEqualStatement(TutorSchema.TR_Pic, TR_Pic));
		if(isUpdating) 				params.add(getQueryEqualStatement(TutorSchema.TR_UpdatedTimeUTC, (TR_UpdatedTimeUTC = DateUTC.getCurrentDateUTC())));
		
		if(isUpdating) TutorsManager.destroyInstance();
		return params;
	}
	
	@Override
	protected List<String>getSchemaColumns() {
		List<String> params = new ArrayList<String>();
		params.add(TutorSchema.PK);
		
		if(TR_DescriptionUpdated) 	params.add(TutorSchema.TR_Description);
		if(TR_IsApprovedUpdated)	params.add(TutorSchema.TR_IsApproved);
		if(TR_SummaryUpdated) 		params.add(TutorSchema.TR_Summary);
		if(TR_UR_PKUpdated) 		params.add(TutorSchema.TR_UR_PK);
		if(TR_PicUpdated) 			params.add(TutorSchema.TR_Pic);
		if(isUpdating)				params.add(TutorSchema.TR_UpdatedTimeUTC);
		
		if(isUpdating) TutorsManager.destroyInstance();
		return params;
	}
	
	@Override
	protected List<String> getValues() {
		List<String> params = new ArrayList<String>();
		params.add(getValue(PK));
		
		if(TR_DescriptionUpdated) 	params.add("?");
		if(TR_IsApprovedUpdated)	params.add(getValue(TR_IsApproved));
		if(TR_SummaryUpdated) 		params.add(getValue(TR_Summary));
		if(TR_UR_PKUpdated) 		params.add(getValue(TR_UR_PK));
		if(TR_PicUpdated) 			params.add(getValue(TR_Pic));
		if(isUpdating)				params.add(getValue((TR_UpdatedTimeUTC = DateUTC.getCurrentDateUTC())));
		
		if(isUpdating) TutorsManager.destroyInstance();
		return params;
	}
	
	@Override
	protected void setSpecialStatement(PreparedStatement ps) throws SQLException {
		if(TR_DescriptionUpdated) ps.setString(1, TR_Description);
	}

	public class TutorSchema {
		public static final String PK 					= "TR_PK";
		public static final String TableName 			= "Tutor";
		public static final String TR_UR_PK 			= "TR_UR_PK";
		public static final String TR_IsApproved 		= "TR_IsApproved";
		public static final String TR_Pic 				= "TR_Pic";
		public static final String TR_Summary 			= "TR_Summary";
		public static final String TR_Description 		= "TR_Description";
		public static final String TR_RegisteredTimeUTC = "TR_RegisteredTimeUTC";
		public static final String TR_UpdatedTimeUTC 	= "TR_UpdatedTimeUTC";
	}
}
