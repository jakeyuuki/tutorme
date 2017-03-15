package tutorme.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tutorme.models.core.LoadableObject;

public class TagLink extends LoadableObject{
	private String TT_HT_PK; 			// Composite Key
	private String TT_TR_CR_Reference;  // Composite Key

	private boolean TT_HT_PKUpdated;
	private boolean TT_TR_CR_ReferenceUpdated;
	
	public TagLink() {
		super();
	}
	
	public TagLink(ResultSet rs) throws SQLException {
		super(rs);
	}
	
	@Override
	public void load(ResultSet databaseObject) throws SQLException {
		TT_HT_PK = databaseObject.getString(TagLinkSchema.TT_HT_PK);
		TT_TR_CR_Reference = databaseObject.getString(TagLinkSchema.TT_TR_CR_Reference);
	}
	
	@Override
	public int getHierarchyLevel() {
		return 4;
	}
	
	public String getTT_HT_PK() {
		return TT_HT_PK;
	}

	public void setTT_HT_PK(String TT_HT_PK) {
		isUpdating = TT_HT_PKUpdated = true;
		this.TT_HT_PK = TT_HT_PK;
	}
	
	public String getTT_TR_CR_Reference() {
		return TT_TR_CR_Reference;
	}

	public void setTT_TR_CR_Reference(String TT_TR_CR_Reference) {
		isUpdating = TT_TR_CR_ReferenceUpdated = true;
		this.TT_TR_CR_Reference = TT_TR_CR_Reference;
	}

	public void setCompiteKey(String HashTag_PK, String Course_Tutor_PK) {
		setTT_HT_PK(HashTag_PK);
		setTT_TR_CR_Reference(Course_Tutor_PK);
	}
	
	@Override
	protected String getReloadWhereStatement() {
		return String.format("%1$s = %2$s AND %3$s = %4$s", TagLinkSchema.TT_HT_PK, TT_HT_PK, TagLinkSchema.TT_TR_CR_Reference, TT_TR_CR_Reference);
	}
	
	@Override
	protected String getTableName() {
		return TagLinkSchema.TableName;
	}
	
	@Override
	protected String getPKSchemaName() {
		return "";
	}
	
	@Override
	protected List<String> getQuerySetParams() {
		List<String> params = new ArrayList<String>();
		
		if(TT_HT_PKUpdated) 		params.add(getQueryEqualStatement(TagLinkSchema.TT_HT_PK, TT_HT_PK));
		if(TT_TR_CR_ReferenceUpdated) 		params.add(getQueryEqualStatement(TagLinkSchema.TT_TR_CR_Reference, TT_TR_CR_Reference));
		
		return params;
	}
	
	@Override
	protected List<String>getSchemaColumns() {
		List<String> params = new ArrayList<String>();
		
		params.add(TagLinkSchema.TT_HT_PK);
		params.add(TagLinkSchema.TT_TR_CR_Reference);
		
		return params;
	}

	@Override
	protected List<String> getValues() {
		List<String> params = new ArrayList<String>();
		
		params.add(getValue(TT_HT_PK));
		params.add(getValue(TT_TR_CR_Reference));
		
		return params;
	}

	public class TagLinkSchema {
		public static final String TableName			= "TagLink";
		public static final String TT_HT_PK 			= "HT_Key";
		public static final String TT_TR_CR_Reference 	= "TT_TR_CR_Reference";
		
	}
}
