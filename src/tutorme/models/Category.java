package tutorme.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tutorme.models.core.LoadableObject;

public class Category extends LoadableObject{
	private String CY_Name; 	//MANDATORY

	private boolean CY_NameUpdated;
	
	public Category() {
		super();
	}
	
	public Category(ResultSet rs) throws SQLException {
		super(rs);
	}
	
	@Override
	public void load(ResultSet databaseObject) throws SQLException {
		PK = databaseObject.getString(CategorySchema.PK);
		CY_Name = databaseObject.getString(CategorySchema.CY_Name);
	}
	
	@Override
	public int getHierarchyLevel() {
		return 0;
	}
	
	public static String getQueryFindCategoryName(String CY_Name) {
		return String.format("SELECT * FROM %1$s WHERE %2$s LIMIT 0,1;", CategorySchema.TableName, 
				getQueryEqualStatement(CategorySchema.CY_Name, CY_Name));
	}
	
	public String getCY_Name() {
		return CY_Name;
	}

	public void setCY_Name(String CY_Name) {
		isUpdating = CY_NameUpdated = true;
		this.CY_Name = CY_Name;
	}


	public void setMandatoryFields(String CY_Name) {
		setCY_Name(CY_Name);
	}
	
	@Override
	protected String getTableName() {
		return CategorySchema.TableName;
	}
	
	@Override
	protected String getPKSchemaName() {
		return CategorySchema.PK;
	}
	
	@Override
	protected List<String> getQuerySetParams() {
		List<String> params = new ArrayList<String>();
		
		if(CY_NameUpdated) 		params.add(getQueryEqualStatement(CategorySchema.CY_Name, CY_Name));
		
		return params;
	}
	
	@Override
	protected List<String>getSchemaColumns() {
		List<String> params = new ArrayList<String>();
		params.add(CategorySchema.PK);
		
		if(CY_NameUpdated) 	params.add(CategorySchema.CY_Name);
		
		return params;
	}

	@Override
	protected List<String> getValues() {
		List<String> params = new ArrayList<String>();
		params.add(getValue(PK));
		
		if(CY_NameUpdated) 	params.add(getValue(CY_Name));
		
		return params;
	}

	public class CategorySchema {
		public static final String PK 			= "CY_PK";
		public static final String TableName	= "Category";
		public static final String CY_Name 		= "CY_Name";
	}
}
