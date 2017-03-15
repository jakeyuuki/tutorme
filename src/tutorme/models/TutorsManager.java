package tutorme.models;

import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;

import tutorme.models.Certification.CertificationSchema;
import tutorme.models.Course.CourseSchema;
import tutorme.models.Rate.RateSchema;
import tutorme.models.Tutor.TutorSchema;
import tutorme.models.User.UserSchema;
import tutorme.models.core.BusinessObjectFactory;

public class TutorsManager {	
	Hashtable<String, JSONArray> resultsCache = new Hashtable<String, JSONArray>();
	final int maxValue = 1000;
	
	TutorsManager() {
		if(timer != null) {
			timer.cancel();
			timer.purge();
		}
		
		timer = new Timer();
		
		timer.schedule(new TimerTask() {
			  @Override
			  public void run() {
				  CoursesManager.destroyInstance();
			  }
			}, 60*60*1000);
	}
	
	static Timer timer;
	static TutorsManager instance;
	
	public static void destroyInstance() {
		if(instance != null) instance = null;
	}
	
	public static TutorsManager getInstance() {
		return instance != null ? instance : (instance = new TutorsManager());
	}
	
	public String getCoursesAsJson(String orderBy, String name){
		if(name == null || name.equals("null")) name = "";
		String key = orderBy + "|" + name;
		if(resultsCache.containsKey(key)) {
			return resultsCache.get(key).toString();
		}
		else {
			BusinessObjectFactory factory = new BusinessObjectFactory();
			String query = getQueryValues(name);
			JSONArray result = factory.loadParsedData(query, JSONArray.class);
			if(resultsCache.size() > 10) {
				String firstKey = (String) resultsCache.keySet().toArray()[0];
				resultsCache.remove(firstKey.charAt(firstKey.length()-1) == '|' ? 
						resultsCache.keySet().toArray()[1] : firstKey);
			}
			resultsCache.put(key, result);
			System.out.println("Cached filter by '" + key + "'");
			return result.toString();
		}
	}
	
	/*
SELECT TR_PK, Name, Cost, Summary, IFNULL(AVG(RE_Value), 0) as Rate, TR_UpdatedTimeUTC, TR_Pic
FROM (
	SELECT TR_PK, CONCAT(UR_FirstName, ' ' , UR_LastName) as Name, IFNULL(AVG(CE_Cost), 0) as Cost, CE_Summary as Summary, TR_UpdatedTimeUTC, TR_Pic
	FROM Tutor
	LEFT JOIN Course ON (TR_PK = CE_Author)
	LEFT JOIN User ON (UR_PK = TR_UR_PK)
	WHERE TR_IsApproved = 1
	AND UPPER(CONCAT(UR_FirstName, ' ' , UR_LastName)) LIKE UPPER('%%')
	AND UR_IsActive = 1
	GROUP BY 1, 2, 4, 5, 6
	) as myalias
LEFT JOIN Rate ON (RE_TR_CE_Reference = TR_PK)
GROUP BY 1, 2, 3, 4, 6, 7 ORDER BY DATE(TR_UpdatedTimeUTC) desc, TR_UpdatedTimeUTC desc, Name desc;
	 */
	String getQueryValues(String name) {
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("SELECT %1$s, Name, Cost, Summary, IFNULL(AVG(%2$s), 0) as Rate, %3$s, %4$s\n", TutorSchema.PK, RateSchema.RE_Value, TutorSchema.TR_UpdatedTimeUTC, TutorSchema.TR_Pic));
		builder.append(String.format("FROM ( SELECT %1$s, CONCAT(%2$s, ' ' , %3$s) as Name, IFNULL(AVG(%4$s), 0) as Cost, %5$s as Summary, %6$s, %7$s\n", 
				TutorSchema.PK, UserSchema.UR_FirstName, UserSchema.UR_LastName, CourseSchema.CE_Cost, TutorSchema.TR_Summary, TutorSchema.TR_UpdatedTimeUTC, TutorSchema.TR_Pic));
		builder.append(String.format("FROM Tutor\nLEFT JOIN %1$s ON (%2$s = %3$s)\n", CourseSchema.TableName, TutorSchema.PK, CourseSchema.CE_Author));
		builder.append(String.format("LEFT JOIN %1$s ON (%2$s = %3$s)\n", UserSchema.TableName, UserSchema.PK, TutorSchema.TR_UR_PK));
		builder.append(String.format("WHERE %1$s = 1\n", TutorSchema.TR_IsApproved));
		builder.append(String.format("AND UPPER(CONCAT(%1$s, ' ' , %2$s)) LIKE UPPER('%%%3$s%%')\n", UserSchema.UR_FirstName, UserSchema.UR_LastName, name));
		builder.append(String.format("AND %1$s = 1\n", UserSchema.UR_IsActive));
		builder.append("GROUP BY 1, 2, 4, 5, 6\n");
		builder.append(") as myalias\n");
		builder.append(String.format("LEFT JOIN %1$s ON (%2$s = %3$s)\n", RateSchema.TableName, RateSchema.RE_TR_CE_Reference, TutorSchema.PK));
		builder.append(String.format("GROUP BY 1, 2, 3, 4, 6, 7 ORDER BY DATE(%1$s) desc, %1$s desc, Name desc;", TutorSchema.TR_UpdatedTimeUTC));
		return builder.toString();
	}
	
	//Get User PK from TutorPK
	public static String getSingleUserAsJson(String pk){
		BusinessObjectFactory factory = new BusinessObjectFactory();
		String query = String.format("SELECT %1$s FROM %2$s WHERE %3$s = '%4$s';", 
				String.join(", ", new String[] { TutorSchema.PK, TutorSchema.TR_Description, TutorSchema.TR_IsApproved, TutorSchema.TR_UR_PK}),
				TutorSchema.TableName,
				TutorSchema.PK,
				pk);
		return factory.loadParsedData(query, JSONArray.class).toString();
	}
	
	public static String getPendingTutorsAsJsonString(int startIndex){
		BusinessObjectFactory factory = new BusinessObjectFactory();
		String query = String.format("SELECT %1$s FROM %2$s LEFT JOIN %3$s ON %4$s = %5$s LEFT JOIN %6$s ON %7$s = %8$s WHERE %9$s = %10$s ORDER BY %11$s; ", 
				String.join(", ", new String[] { TutorSchema.PK, CertificationSchema.CN_TR_PK,CertificationSchema.CN_Scan, CertificationSchema.CN_Name, CertificationSchema.CN_Description, UserSchema.UR_FirstName, UserSchema.UR_LastName, UserSchema.UR_Email, UserSchema.PK, TutorSchema.TR_IsApproved}),
				TutorSchema.TableName,
				UserSchema.TableName,
				UserSchema.PK,
				TutorSchema.TR_UR_PK,
				CertificationSchema.TableName,
				CertificationSchema.CN_TR_PK,
				TutorSchema.PK,
				TutorSchema.TR_IsApproved,
				'0',
				UserSchema.UR_FirstName,
				startIndex);
		return factory.loadParsedData(query, JSONArray.class).toString();
	}
		
	public static JSONArray getPendingTutorsAsJson(int startIndex, int count){
		BusinessObjectFactory factory = new BusinessObjectFactory();
		String query = String.format("SELECT %1$s FROM %2$s LEFT JOIN %3$s ON %4$s = %5$s LEFT JOIN %6$s ON %7$s = %8$s WHERE %9$s = %10$s ORDER BY %11$s; ", 
				String.join(", ", new String[] { TutorSchema.PK, CertificationSchema.CN_TR_PK, CertificationSchema.CN_Scan, CertificationSchema.CN_Name, CertificationSchema.CN_Description, UserSchema.UR_FirstName, UserSchema.UR_LastName, UserSchema.UR_Email, UserSchema.PK, TutorSchema.TR_IsApproved}),
				TutorSchema.TableName,
				UserSchema.TableName,
				UserSchema.PK,
				TutorSchema.TR_UR_PK,
				CertificationSchema.TableName,
				CertificationSchema.CN_TR_PK,
				TutorSchema.PK,
				TutorSchema.TR_IsApproved,
				'0',
				UserSchema.UR_FirstName,
				startIndex);
		return factory.loadParsedData(query, JSONArray.class);
	}

}
