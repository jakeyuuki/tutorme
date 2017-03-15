package tutorme.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import tutorme.models.core.LoadableObject;
import tutorme.util.DateUTC;

public class User extends LoadableObject{
	private String UR_FirstName; 			//MANDATORY
	private String UR_LastName;				//MANDATORY
	private String UR_Email;				//MANDATORY
	private String UR_Password;				//MANDATORY
	private String UR_PhoneNumber;
	private String UR_Country;
	private String UR_Address;
	private String UR_Suburb;
	private Integer UR_Postcode;
	private String UR_State;
	private boolean UR_Activated;
	private boolean UR_IsTutor;
	private boolean UR_IsAdmin;
	private boolean UR_IsActive;
	private Date UR_RegisteredTimeUTC; 	//READ-ONLY, Auto-Created
	private Date UR_UpdatedTimeUTC; 	//Auto-Created
	
	private boolean UR_FirstNameUpdated;
	private boolean UR_LastNameUpdated;
	private boolean UR_EmailUpdated;
	private boolean UR_PasswordUpdated;
	private boolean UR_PhoneNumberUpdated;
	private boolean UR_CountryUpdated;
	private boolean UR_AddressUpdated;
	private boolean UR_SuburbUpdated;
	private boolean UR_PostcodeUpdated;
	private boolean UR_StateUpdated;
	private boolean UR_ActivatedUpdated;
	private boolean UR_IsTutorUpdated;

	private boolean UR_IsAdminUpdated;
	private boolean UR_IsActiveUpdated;
	
	public User() {
		super();
	}
	
	public User(ResultSet rs) throws SQLException {
		super(rs);
	}
	
	@Override
	public void load(ResultSet databaseObject) throws SQLException {
		PK = databaseObject.getString(UserSchema.PK);
		UR_FirstName = databaseObject.getString(UserSchema.UR_FirstName);
		UR_LastName = databaseObject.getString(UserSchema.UR_LastName);
		UR_Email = databaseObject.getString(UserSchema.UR_Email);
		UR_Password = databaseObject.getString(UserSchema.UR_Password);
		UR_PhoneNumber = databaseObject.getString(UserSchema.UR_PhoneNumber);
		UR_Country = databaseObject.getString(UserSchema.UR_Country);
		UR_Address = databaseObject.getString(UserSchema.UR_Address);
		UR_Suburb = databaseObject.getString(UserSchema.UR_Suburb);
		UR_Postcode = databaseObject.getInt(UserSchema.UR_Postcode);
		UR_State = databaseObject.getString(UserSchema.UR_State);
		UR_Activated = databaseObject.getBoolean(UserSchema.UR_Activated);

		UR_IsTutor = databaseObject.getBoolean(UserSchema.UR_IsTutor);
		UR_IsAdmin = databaseObject.getBoolean(UserSchema.UR_IsAdmin);
		UR_IsActive = databaseObject.getBoolean(UserSchema.UR_IsActive);
		UR_RegisteredTimeUTC = databaseObject.getTimestamp(UserSchema.UR_RegisteredTimeUTC);
		UR_UpdatedTimeUTC = databaseObject.getTimestamp(UserSchema.UR_UpdatedTimeUTC);
	}
	
	public void setMandatoryFields(String UR_FirstName, String UR_LastName, String UR_Email, String UR_Password) {
		setUR_FirstName(UR_FirstName);
		setUR_LastName(UR_LastName);
		setUR_Email(UR_Email);
		setUR_Password(UR_Password);
	}
	
	@Override
	public int getHierarchyLevel() {
		return 0;
	}
	
	public static String getQueryAuthenticate(String email, String password) {
		return String.format("SELECT * FROM %1$s WHERE %2$s AND %3$s LIMIT 0,1;", UserSchema.TableName, 
				getQueryEqualStatement(UserSchema.UR_Email, email),
				getQueryEqualStatement(UserSchema.UR_Password, password));
	}
	
	
	
	public static String getQueryFindEmail(String email) {
		return String.format("SELECT * FROM %1$s WHERE %2$s LIMIT 0,1;", UserSchema.TableName, 
				getQueryEqualStatement(UserSchema.UR_Email, email));
	}
	
	public String getUR_FirstName(){
		return UR_FirstName;
	}
	
	public void setUR_FirstName(String UR_FirstName){
			isUpdating = UR_FirstNameUpdated = true;
			this.UR_FirstName=UR_FirstName;
		
	}

	public String getUR_LastName(){
		return UR_LastName;
	}

	public void setUR_LastName(String UR_LastName){
			isUpdating = UR_LastNameUpdated = true;
			this.UR_LastName=UR_LastName;
		
	}

	public String getUR_Email(){
		return UR_Email;
	}

	public void setUR_Email(String UR_Email){
		isUpdating = UR_EmailUpdated = true;
		this.UR_Email=UR_Email;
	}

	public String getUR_Password(){
		return UR_Password;
	}

	public void setUR_Password(String UR_Password){
		isUpdating = UR_PasswordUpdated = true;
		this.UR_Password=UR_Password;
	}

	public String getUR_PhoneNumber(){
		return UR_PhoneNumber;
	}

	public void setUR_PhoneNumber(String UR_PhoneNumber){
		isUpdating = UR_PhoneNumberUpdated = true;
		this.UR_PhoneNumber=UR_PhoneNumber;
	}

	public String getUR_Country(){
		return UR_Country;
	}

	public void setUR_Country(String UR_Country){
		isUpdating = UR_CountryUpdated = true;
		this.UR_Country=UR_Country;
	}

	public String getUR_Address(){
		return UR_Address;
	}

	public void setUR_Address(String UR_Address){
		isUpdating = UR_AddressUpdated = true;
		this.UR_Address=UR_Address;
	}

	public String getUR_Suburb(){
		return UR_Suburb;
	}

	public void setUR_Suburb(String UR_Suburb){
		isUpdating = UR_SuburbUpdated = true;
		this.UR_Suburb=UR_Suburb;
	}
	
	public Integer getUR_Postcode() {
		return UR_Postcode;
	}

	public void setUR_Postcode(Integer UR_Postcode) {
		isUpdating = UR_PostcodeUpdated = true;
		this.UR_Postcode = UR_Postcode;
	}

	public String getUR_State(){
		return UR_State;
	}

	public void setUR_State(String UR_State){
		isUpdating = UR_StateUpdated = true;
		this.UR_State=UR_State;
	}
	
	public boolean getUR_Activated() {
		return UR_Activated;
	}

	public void setUR_Activated(boolean UR_Activated) {
		isUpdating = UR_ActivatedUpdated = true;
		this.UR_Activated = UR_Activated;
	}
	

	public boolean getUR_IsTutor() {
		return UR_IsTutor;
	}

	public void setUR_IsTutor(boolean UR_IsTutor) {
		isUpdating = UR_IsTutorUpdated = true;
		this.UR_IsTutor = UR_IsTutor;
	}

	public boolean getUR_IsAdmin() {
		return UR_IsAdmin;
	}
	
	public void setUR_IsAdmin(boolean UR_IsAdmin) {
		isUpdating = UR_IsAdminUpdated = true;
		this.UR_IsAdmin = UR_IsAdmin;
	}
	
	public boolean getUR_IsActive() {
		return UR_IsActive;
	}
	
	public void setUR_IsActive(boolean UR_IsActive) {
		isUpdating = UR_IsActiveUpdated = true;
		this.UR_IsActive = UR_IsActive;
	}
	
	public Date getUR_RegisteredTimeUTC() {
		return UR_RegisteredTimeUTC;
	}
	
	public Date getUR_UpdatedTimeUTC() {
		return UR_UpdatedTimeUTC;
	}
	
	@Override
	protected String getTableName() {
		return UserSchema.TableName;
	}
	
	@Override
	protected String getPKSchemaName() {
		return UserSchema.PK;
	}
	
	@Override
	protected List<String> getQuerySetParams() {
		List<String> params = new ArrayList<String>();
		
		if(UR_FirstNameUpdated) 	params.add(getQueryEqualStatement(UserSchema.UR_FirstName, UR_FirstName));
		if(UR_LastNameUpdated) 		params.add(getQueryEqualStatement(UserSchema.UR_LastName, UR_LastName));
		if(UR_EmailUpdated) 		params.add(getQueryEqualStatement(UserSchema.UR_Email, UR_Email));
		if(UR_PasswordUpdated) 		params.add(getQueryEqualStatement(UserSchema.UR_Password, UR_Password));
		if(UR_PhoneNumberUpdated) 	params.add(getQueryEqualStatement(UserSchema.UR_PhoneNumber, UR_PhoneNumber));
		if(UR_CountryUpdated) 		params.add(getQueryEqualStatement(UserSchema.UR_Country, UR_Country));
		if(UR_AddressUpdated) 		params.add(getQueryEqualStatement(UserSchema.UR_Address, UR_Address));
		if(UR_SuburbUpdated) 		params.add(getQueryEqualStatement(UserSchema.UR_Suburb, UR_Suburb));
		if(UR_PostcodeUpdated)		params.add(getQueryEqualStatement(UserSchema.UR_Postcode, UR_Postcode));
		if(UR_StateUpdated) 		params.add(getQueryEqualStatement(UserSchema.UR_State, UR_State));
		if(UR_ActivatedUpdated) 	params.add(getQueryEqualStatement(UserSchema.UR_Activated, UR_Activated));
		if(UR_IsTutorUpdated) 		params.add(getQueryEqualStatement(UserSchema.UR_IsTutor, UR_IsTutor));
		if(UR_IsAdminUpdated) 		params.add(getQueryEqualStatement(UserSchema.UR_IsAdmin, UR_IsAdmin));
		if(UR_IsActiveUpdated) 		params.add(getQueryEqualStatement(UserSchema.UR_IsActive, UR_IsActive));
		if(isUpdating) 				params.add(getQueryEqualStatement(UserSchema.UR_UpdatedTimeUTC, (UR_UpdatedTimeUTC = DateUTC.getCurrentDateUTC())));
		
		return params;
	}
	
	@Override
	protected List<String>getSchemaColumns() {
		List<String> params = new ArrayList<String>();
		params.add(UserSchema.PK);
		
		if(UR_FirstNameUpdated) 	params.add(UserSchema.UR_FirstName);
		if(UR_LastNameUpdated) 		params.add(UserSchema.UR_LastName);
		if(UR_EmailUpdated) 		params.add(UserSchema.UR_Email);
		if(UR_PasswordUpdated) 		params.add(UserSchema.UR_Password);
		if(UR_PhoneNumberUpdated) 	params.add(UserSchema.UR_PhoneNumber);
		if(UR_CountryUpdated) 		params.add(UserSchema.UR_Country);
		if(UR_AddressUpdated) 		params.add(UserSchema.UR_Address);
		if(UR_SuburbUpdated) 		params.add(UserSchema.UR_Suburb);
		if(UR_PostcodeUpdated)		params.add(UserSchema.UR_Postcode);
		if(UR_StateUpdated) 		params.add(UserSchema.UR_State);
		if(UR_ActivatedUpdated) 	params.add(UserSchema.UR_Activated);

		if(UR_IsTutorUpdated) 		params.add(UserSchema.UR_IsTutor);
		if(UR_IsAdminUpdated) 		params.add(UserSchema.UR_IsAdmin);
		if(UR_IsActiveUpdated) 		params.add(UserSchema.UR_IsActive);
		if(isUpdating) 				params.add(UserSchema.UR_UpdatedTimeUTC);
		
		return params;
	}

	@Override
	protected List<String> getValues() {
		List<String> params = new ArrayList<String>();
		params.add(getValue(PK));
		
		if(UR_FirstNameUpdated) 	params.add(getValue(UR_FirstName));
		if(UR_LastNameUpdated) 		params.add(getValue(UR_LastName));
		if(UR_EmailUpdated) 		params.add(getValue(UR_Email));
		if(UR_PasswordUpdated) 		params.add(getValue(UR_Password));
		if(UR_PhoneNumberUpdated) 	params.add(getValue(UR_PhoneNumber));
		if(UR_CountryUpdated) 		params.add(getValue(UR_Country));
		if(UR_AddressUpdated) 		params.add(getValue(UR_Address));
		if(UR_SuburbUpdated) 		params.add(getValue(UR_Suburb));
		if(UR_PostcodeUpdated)		params.add(getValue(UR_Postcode));
		if(UR_StateUpdated) 		params.add(getValue(UR_State));
		if(UR_ActivatedUpdated) 	params.add(getValue(UR_Activated));
		if(UR_IsTutorUpdated) 		params.add(getValue(UR_IsTutor));
		if(UR_IsAdminUpdated) 		params.add(getValue(UR_IsAdmin));
		if(UR_IsActiveUpdated) 		params.add(getValue(UR_IsActive));
		if(isUpdating)				params.add(getValue(UR_UpdatedTimeUTC = DateUTC.getCurrentDateUTC()));
		
		return params;
	}

	public class UserSchema {
		public static final String PK 					= "UR_PK";
		public static final String TableName 			= "User";
		public static final String UR_FirstName 		= "UR_FirstName";
		public static final String UR_LastName 			= "UR_LastName";
		public static final String UR_Email 			= "UR_Email";
		public static final String UR_Password 			= "UR_Password";
		public static final String UR_PhoneNumber 		= "UR_PhoneNumber";
		public static final String UR_Country 			= "UR_Country";
		public static final String UR_Address 			= "UR_Address";
		public static final String UR_Suburb 			= "UR_Suburb";
		public static final String UR_Postcode 			= "UR_Postcode";
		public static final String UR_State 			= "UR_State";
		public static final String UR_Activated 		= "UR_Activated";
		public static final String UR_IsTutor 			= "UR_IsTutor";
		public static final String UR_IsAdmin 			= "UR_IsAdmin";
		public static final String UR_IsActive 			= "UR_IsActive";
		public static final String UR_RegisteredTimeUTC	= "UR_RegisteredTimeUTC";
		public static final String UR_UpdatedTimeUTC	= "UR_UpdatedTimeUTC";
	}
}
