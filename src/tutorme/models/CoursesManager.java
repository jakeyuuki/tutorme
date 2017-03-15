package tutorme.models;

import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;

import tutorme.models.Course.CourseSchema;
import tutorme.models.Rate.RateSchema;
import tutorme.models.Tutor.TutorSchema;
import tutorme.models.User.UserSchema;
import tutorme.models.core.BusinessObjectFactory;

public class CoursesManager {
	Hashtable<String, JSONArray> resultsCache = new Hashtable<String, JSONArray>();
	final int maxValue = 1000;
	
	CoursesManager() {
		if(timer != null) {
			timer.cancel();
			timer.purge();
		}
		else {
			timer = new Timer();
		}
		
		timer.schedule(new TimerTask() {
			  @Override
			  public void run() {
				  CoursesManager.destroyInstance();
			  }
			}, 60*60*1000);
	}
	
	static Timer timer;
	static CoursesManager instance;
	
	public static void destroyInstance() {
		if(instance == null) instance = null;
	}
	
	public static CoursesManager getInstance() {
		return instance != null ? instance : (instance = new CoursesManager());
	}
	
	public String getCoursesAsJson(String orderBy){
		if(resultsCache.containsKey(orderBy)) {
			return resultsCache.get(orderBy).toString();
		}
		else {
			BusinessObjectFactory factory = new BusinessObjectFactory();
			String query = getQueryValues();
			JSONArray result = factory.loadParsedData(query, JSONArray.class);
			resultsCache.put(orderBy, result);
			System.out.println("Cached filter by '" + orderBy + "'");
			return result.toString();
		}
	}
	
	/*
	SELECT CE_PK, CE_Name, CE_Cost, CONCAT(UR_FirstName, ' ', UR_LastName) as Name, CAST(CE_DESCRIPTION AS CHAR(100) CHARACTER SET utf8) as Summary, IFNULL(AVG(RE_Value), 0) as Rate
	FROM Course
	LEFT JOIN Rate ON (RE_TR_CE_Reference = CE_PK)
	LEFT JOIN Tutor ON (TR_PK = CE_Author)
	LEFT JOIN User ON (UR_PK = TR_UR_PK)
	GROUP BY CE_PK, CE_Name, CE_Cost, Name, Summary;
	 */
	String getQueryValues() {
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("SELECT %1$s, %2$s, CONCAT(%3$s, ' ', %4$s) as Name, %5$s, %6$s as Summary, IFNULL(AVG(%7$s), 0) as Rate\n",
				CourseSchema.PK, CourseSchema.CE_Name, UserSchema.UR_FirstName, UserSchema.UR_LastName, CourseSchema.CE_Cost, CourseSchema.CE_Summary, RateSchema.RE_Value));
		builder.append(String.format("From %1$s\n", CourseSchema.TableName));
		builder.append(String.format("LEFT JOIN %1$s ON (%2$s = %3$s)\n", RateSchema.TableName, RateSchema.RE_TR_CE_Reference, CourseSchema.PK));
		builder.append(String.format("LEFT JOIN %1$s ON (%2$s = %3$s)\n", TutorSchema.TableName, TutorSchema.PK, CourseSchema.CE_Author));
		builder.append(String.format("LEFT JOIN %1$s ON (%2$s = %3$s)\n", UserSchema.TableName, UserSchema.PK, TutorSchema.TR_UR_PK));
		builder.append("GROUP BY 1, 2 ,3, 4, 5;");
		return builder.toString();
	}
}
