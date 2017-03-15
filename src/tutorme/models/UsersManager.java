package tutorme.models;

import org.json.JSONArray;

import tutorme.models.User.UserSchema;
import tutorme.models.core.BusinessObjectFactory;

public class UsersManager {	
	//Get top 100 Users
	public static String getUsersAsJson(int startIndex){
		BusinessObjectFactory factory = new BusinessObjectFactory();
		String query = String.format("SELECT %1$s FROM %2$s LIMIT 100 OFFSET %3$d;", 
				String.join(", ", new String[] { UserSchema.UR_FirstName, UserSchema.UR_LastName, UserSchema.PK, UserSchema.UR_IsTutor}),
				UserSchema.TableName,
				startIndex);
		return factory.loadParsedData(query, JSONArray.class).toString();
	}
	
	public static JSONArray getUsersAsJson(int startIndex, int count){
		BusinessObjectFactory factory = new BusinessObjectFactory();
		String query = String.format("SELECT %1$s FROM %2$s INNER JOIN ( SELECT %3$s FROM %2$s LIMIT %4$d OFFSET %5$d) AS indexResult USING(%3$s);", 
				String.join(", ", new String[] { UserSchema.PK, UserSchema.UR_FirstName, UserSchema.UR_LastName, UserSchema.UR_Email, UserSchema.UR_Activated, UserSchema.UR_IsAdmin, UserSchema.UR_IsTutor, UserSchema.UR_IsActive, 
						UserSchema.UR_PhoneNumber, UserSchema.UR_Address, UserSchema.UR_Suburb, UserSchema.UR_State, UserSchema.UR_Postcode, UserSchema.UR_Country}),
				UserSchema.TableName,
				UserSchema.PK,
				count,
				startIndex);
		return factory.loadParsedData(query, JSONArray.class);
	}
	
	//Get User by User PK
	public static String getSingleUserAsJson(String pk){
		BusinessObjectFactory factory = new BusinessObjectFactory();
		String query = String.format("SELECT %1$s FROM %2$s WHERE %3$s = '%4$s';", 
				String.join(", ", new String[] { UserSchema.UR_FirstName, UserSchema.UR_LastName, UserSchema.UR_Email,UserSchema.UR_IsActive,UserSchema.UR_PhoneNumber,UserSchema.UR_Address,UserSchema.UR_Suburb,UserSchema.UR_State,UserSchema.UR_Postcode,UserSchema.UR_Country}),
				UserSchema.TableName,
				UserSchema.PK,
				pk);
		return factory.loadParsedData(query, JSONArray.class).toString();
	}
	
}
//UserSchema.UR_FirstName, UserSchema.UR_LastName, UserSchema.UR_Email,UserSchema.UR_PhoneNumber,UserSchema.UR_Address,UserSchema.UR_Suburb,UserSchema.UR_State,UserSchema.UR_Postcode,UserSchema.UR_Country