package tutorme.models;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tutorme.models.core.LoadableObject;
import tutorme.util.DateUTC;

public class Course extends LoadableObject{
	private String CE_Name;				// MANDATORY
	private String CE_Summary; 			// MANDATORY
	private String CE_Description; 		// MANDATORY
	private String CE_Author;			// MANDATORY
	private BigDecimal CE_Cost;			// MANDATORY
	private String CE_CY_PK; 			// MANDATORY
	private Date CE_CreatedTimeUTC;		// Auto-Created
	private Date CE_UpdatedTimeUTC;		// Auto-Created

	private boolean CE_NameUpdated;
	private boolean CE_SummaryUpdated;
	private boolean CE_DescriptionUpdated;
	private boolean CE_AuthorUpdated;
	private boolean CE_CostUpdated;
	private boolean CE_CY_PKUpdated;
	
	public Course() {
		super();
	}
	
	public Course(ResultSet rs) throws SQLException {
		super(rs);
	}
	
	@Override
	public void load(ResultSet databaseObject) throws SQLException {
		PK = databaseObject.getString(CourseSchema.PK);
		CE_Summary = databaseObject.getString(CourseSchema.CE_Summary);
		CE_Description = databaseObject.getString(CourseSchema.CE_Description);
		CE_Author = databaseObject.getString(CourseSchema.CE_Author);
		CE_Cost = databaseObject.getBigDecimal(CourseSchema.CE_Cost);
		CE_CY_PK = databaseObject.getString(CourseSchema.CE_CY_PK);
		CE_CreatedTimeUTC = databaseObject.getTimestamp(CourseSchema.CE_CreatedTimeUTC);
		CE_UpdatedTimeUTC = databaseObject.getTimestamp(CourseSchema.CE_UpdatedTimeUTC);
	}
	
	@Override
	public int getHierarchyLevel() {
		return 2;
	}
	
	public String getCE_Name() {
		return CE_Name;
	}

	public void setCE_Name(String CE_Name) {
		isUpdating = CE_NameUpdated = true;
		this.CE_Name = CE_Name;
	}
	
	public String getCE_Summary() {
		return CE_Summary;
	}

	public void setCE_Summary(String CE_Summary) {
		isUpdating = CE_SummaryUpdated = true;
		this.CE_Summary = CE_Summary;
	}
	
	public String getCE_Description() {
		return CE_Description;
	}

	public void setCE_Description(String CE_Description) {
		isUpdating = CE_DescriptionUpdated = true;
		this.CE_Description = CE_Description;
	}

	public String getCE_Author() {
		return CE_Author;
	}

	public void setCE_Author(String CE_Author) {
		isUpdating = CE_AuthorUpdated = true;
		this.CE_Author = CE_Author;
	}
	
	public BigDecimal getCE_Cost() {
		return CE_Cost;
	}

	public void setCE_Cost(BigDecimal CE_Cost) {
		isUpdating = CE_CostUpdated = true;
		this.CE_Cost = CE_Cost;
	}
	
	public String getCE_CY_PK() {
		return CE_CY_PK;
	}

	public void setCE_CY_PK(String CE_CY_PK) {
		isUpdating = CE_CY_PKUpdated = true;
		this.CE_CY_PK = CE_CY_PK;
	}
	
	public Date getCE_CreatedTimeUTC() {
		return CE_CreatedTimeUTC;
	}
	
	public Date getCE_UpdatedTimeUTC() {
		return CE_UpdatedTimeUTC;
	}

	public void setMandatoryFields(String CE_Name, String CE_Summary, String CE_Description, String CE_Author, BigDecimal CE_Cost, String CE_CY_PK) {
		setCE_Name(CE_Name);
		setCE_Summary(CE_Summary);
		setCE_Description(CE_Description);
		setCE_Author(CE_Author);
		setCE_Cost(CE_Cost);
		setCE_CY_PK(CE_CY_PK);
	}
	
	public void setMandatoryFields(String CE_Name, String CE_Summary, BigDecimal CE_Cost, String CE_CY_PK) {
		setCE_Name(CE_Name);
		setCE_Summary(CE_Summary);
		setCE_Cost(CE_Cost);
		setCE_CY_PK(CE_CY_PK);
	}
	
	@Override
	protected String getTableName() {
		return CourseSchema.TableName;
	}
	
	@Override
	protected String getPKSchemaName() {
		return CourseSchema.PK;
	}
	
	@Override
	protected List<String> getQuerySetParams() {
		List<String> params = new ArrayList<String>();
		
		if(CE_NameUpdated)			params.add(getQueryEqualStatement(CourseSchema.CE_Name, CE_Name));
		if(CE_SummaryUpdated) 		params.add(getQueryEqualStatement(CourseSchema.CE_Summary, CE_Summary));
		if(CE_DescriptionUpdated) 	params.add(getQueryEqualStatement(CourseSchema.CE_Description, CE_Description));
		if(CE_AuthorUpdated) 		params.add(getQueryEqualStatement(CourseSchema.CE_Author, CE_Description));
		if(CE_CY_PKUpdated) 		params.add(getQueryEqualStatement(CourseSchema.CE_CY_PK, CE_CY_PK));
		if(CE_CostUpdated) 			params.add(getQueryEqualStatement(CourseSchema.CE_Cost, CE_Cost));
		if(isUpdating) 				params.add(getQueryEqualStatement(CourseSchema.CE_UpdatedTimeUTC, (CE_UpdatedTimeUTC = DateUTC.getCurrentDateUTC())));
		
		return params;
	}

	@Override
	protected String getQueryWhereParams() {
		return getQueryEqualStatement(CourseSchema.PK, PK);
	}
	
	@Override
	protected List<String>getSchemaColumns() {
		List<String> params = new ArrayList<String>();
		params.add(CourseSchema.PK);
		
		if(CE_NameUpdated)			params.add(CourseSchema.CE_Name);
		if(CE_SummaryUpdated) 		params.add(CourseSchema.CE_Summary);
		if(CE_DescriptionUpdated) 	params.add(CourseSchema.CE_Description);
		if(CE_AuthorUpdated) 		params.add(CourseSchema.CE_Author);
		if(CE_CostUpdated) 			params.add(CourseSchema.CE_Cost);
		if(CE_CY_PKUpdated) 		params.add(CourseSchema.CE_CY_PK);
		if(isUpdating)				params.add(CourseSchema.CE_UpdatedTimeUTC);
		
		return params;
	}

	@Override
	protected List<String> getValues() {
		List<String> params = new ArrayList<String>();
		params.add(getValue(PK));
		
		if(CE_NameUpdated)			params.add(getValue(CE_Name));
		if(CE_SummaryUpdated) 		params.add(getValue(CE_Summary));
		if(CE_DescriptionUpdated) 	params.add(getValue(CE_Description));
		if(CE_AuthorUpdated) 		params.add(getValue(CE_Author));
		if(CE_CostUpdated) 			params.add(getValue(CE_Cost));
		if(CE_CY_PKUpdated) 		params.add(getValue(CE_CY_PK));
		if(isUpdating) 				params.add(getValue((CE_UpdatedTimeUTC = DateUTC.getCurrentDateUTC())));
		
		return params;
	}

	public class CourseSchema {
		public static final String PK 					= "CE_PK";
		public static final String TableName 			= "Course";
		public static final String CE_Name 				= "CE_Name";
		public static final String CE_Summary 			= "CE_Summary";
		public static final String CE_Description 		= "CE_Description";
		public static final String CE_Author 			= "CE_Author";
		public static final String CE_Cost 				= "CE_Cost";
		public static final String CE_CY_PK 			= "CE_CY_PK";
		public static final String CE_CreatedTimeUTC 	= "CE_CreatedTimeUTC";
		public static final String CE_UpdatedTimeUTC 	= "CE_UpdatedTimeUTC";
	}
}
