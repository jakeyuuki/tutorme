package tutorme.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tutorme.models.core.LoadableObject;

public class Comment extends LoadableObject{
	private String CT_TR_CE_Reference; 	//MANDATORY
	private String CT_Comment;			//MANDATORY
	private String CT_Table; 			//MANDATORY
	private String CT_CreatedBy; 		//MANDATORY
	private Date CT_CreatedTimeUTC;		// READ-ONLY, Auto-Created

	private boolean CT_TR_CE_ReferenceUpdated;
	private boolean CT_CommentUpdated;
	private boolean CT_TableUpdated;
	private boolean CT_CreatedByUpdated;
	
	public Comment() {
		super();
	}
	
	public Comment(ResultSet rs) throws SQLException {
		super(rs);
	}
	
	@Override
	public void load(ResultSet databaseObject) throws SQLException {
		PK = databaseObject.getString(CommentSchema.PK);
		CT_TR_CE_Reference = databaseObject.getString(CommentSchema.CT_TR_CE_Reference);
		CT_Comment = databaseObject.getString(CommentSchema.CT_Comment);
		CT_Table = databaseObject.getString(CommentSchema.CT_Table);
		CT_CreatedBy = databaseObject.getString(CommentSchema.CT_CreatedBy);
		CT_CreatedTimeUTC = databaseObject.getTimestamp(CommentSchema.CT_CreatedTimeUTC);
	}
	
	@Override
	public int getHierarchyLevel() {
		return 3;
	}
	
	public String getCT_TR_CE_Reference() {
		return CT_TR_CE_Reference;
	}

	public void setCT_TR_CE_Reference(String CT_TR_CE_Reference) {
		isUpdating = CT_TR_CE_ReferenceUpdated  = true;
		this.CT_TR_CE_Reference = CT_TR_CE_Reference;
	}

	public String getCT_Comment() {
		return CT_Comment;
	}

	public void setCT_Comment(String CT_Comment) {
		isUpdating = CT_CommentUpdated = true;
		this.CT_Comment = CT_Comment;
	}
	
	public String getCT_CreatedBy() {
		return CT_CreatedBy;
	}
	
	public void setCT_CreatedBy(String CT_CreatedBy) {
		isUpdating = CT_CreatedByUpdated = true;
		this.CT_CreatedBy = CT_CreatedBy;
	}
	
	public String getCT_Table() {
		return CT_Table;
	}
	
	public void setCT_Table(String CT_Table) {
		isUpdating = CT_TableUpdated = true;
		this.CT_Table = CT_Table;
	}
	
	public Date getCT_CreatedTimeUTC() {
		return CT_CreatedTimeUTC;
	}

	public void setMandatoryFields(String CT_TR_CE_Reference, String CT_Comment, String CT_CreatedBy, String CT_Table) {
		setCT_TR_CE_Reference(CT_TR_CE_Reference);
		setCT_Comment(CT_Comment);
		setCT_CreatedBy(CT_CreatedBy);
		setCT_Table(CT_Table);
	}
	
	@Override
	protected String getTableName() {
		return CommentSchema.TableName;
	}
	
	@Override
	protected String getPKSchemaName() {
		return CommentSchema.PK;
	}
	
	@Override
	protected List<String> getQuerySetParams() {
		List<String> params = new ArrayList<String>();
		
		if(CT_TR_CE_ReferenceUpdated) 		params.add(getQueryEqualStatement(CommentSchema.CT_TR_CE_Reference, CT_TR_CE_Reference));
		if(CT_CommentUpdated) 			params.add(getQueryEqualStatement(CommentSchema.CT_Comment, CT_Comment));
		if(CT_CreatedByUpdated) 	params.add(getQueryEqualStatement(CommentSchema.CT_CreatedBy, CT_CreatedBy));
		if(CT_TableUpdated) 	params.add(getQueryEqualStatement(CommentSchema.CT_Table, CT_Table));
		
		return params;
	}
	
	@Override
	protected List<String>getSchemaColumns() {
		List<String> params = new ArrayList<String>();
		params.add(CommentSchema.PK);
		
		if(CT_TR_CE_ReferenceUpdated) 	params.add(CommentSchema.CT_TR_CE_Reference);
		if(CT_CommentUpdated) 			params.add(CommentSchema.CT_Comment);
		if(CT_CreatedByUpdated) 		params.add(CommentSchema.CT_CreatedBy);
		if(CT_TableUpdated) 			params.add(CommentSchema.CT_Table);
		
		return params;
	}

	@Override
	protected List<String> getValues() {
		List<String> params = new ArrayList<String>();
		params.add(getValue(PK));
		
		if(CT_TR_CE_ReferenceUpdated) 	params.add(getValue(CT_TR_CE_Reference));
		if(CT_CommentUpdated) 			params.add(getValue(CT_Comment));
		if(CT_CreatedByUpdated) 		params.add(getValue(CT_CreatedBy));
		if(CT_TableUpdated) 			params.add(getValue(CT_Table));
		
		return params;
	}

	public class CommentSchema {
		public static final String PK 					= "CT_PK";
		public static final String TableName 			= "Comment";
		public static final String CT_TR_CE_Reference 	= "CT_TR_CE_Reference";
		public static final String CT_Comment 			= "CT_Comment";
		public static final String CT_Table 			= "CT_Table";
		public static final String CT_CreatedBy 		= "CT_CreatedBy";
		public static final String CT_CreatedTimeUTC 	= "CT_CreatedTimeUTC";
	}
}
