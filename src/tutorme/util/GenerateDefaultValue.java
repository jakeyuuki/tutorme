package tutorme.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import tutorme.models.Category;
import tutorme.models.Certification;
import tutorme.models.Certification.CertificationSchema;
import tutorme.models.Course;
import tutorme.models.Course.CourseSchema;
import tutorme.models.Rate;
import tutorme.models.Rate.RateSchema;
import tutorme.models.Tutor;
import tutorme.models.User;
import tutorme.models.Tutor.TutorSchema;
import tutorme.models.TutorManager;
import tutorme.models.User.UserSchema;
import tutorme.models.core.BusinessObjectFactory;
import tutorme.models.core.JUnit;

public class GenerateDefaultValue {
	@Test
	public void testGenerateDefaultValuesInTheMainDB() { // Do not use this method until you want to generate a testing tutor
		JUnit.ON = false;
		
		String testEmail = "test@test.com";
		String testPassword = MD5Hashing.getMD5Hash("test123");
		String tutorEmail = "tutor@tutor.com";
		String tutorPassword = MD5Hashing.getMD5Hash("tutor123");
		String pendingTutorEmail = "tutorpending@tutor.com";
		String pendingTutorPassword = "tutor123";
		String adminEmail = "admin@admin.com";
		String adminPassword = MD5Hashing.getMD5Hash("admin123");
		
		String categoryName = "test";
		
		// Delete existing accounts.
		testDeleteDefaultValuesInTheMainDB();	
		
		// Creating new accounts.
		BusinessObjectFactory factory = new BusinessObjectFactory();
		User user =  factory.create(User.class);
		user.setMandatoryFields("Atif", "Qureshi", testEmail, testPassword);
		
		for (int i = 0; i < 20; i++) {		
			User newUserTutor = factory.create(User.class);
			newUserTutor.setMandatoryFields("Tutor "+i, "A", tutorEmail+i, tutorPassword);
			newUserTutor.setUR_IsTutor(true);
			newUserTutor.setUR_Activated(true);
			Tutor newTutor = TutorManager.activateUserAsTutor(newUserTutor, factory);
			newTutor.setTR_IsApproved(true);
			newTutor.setTR_Summary("Default tutor summary");
			newTutor.setTR_Pic("http://www.themepush.com/demo-kailo/wp-content/uploads/sites/6/edd/2015/03/163_1.jpg");
			newTutor.setTR_Description(getCourseDescription());
		}
		User userTutor = factory.create(User.class);
		userTutor.setMandatoryFields("Tutor", "A", tutorEmail, tutorPassword);
		userTutor.setUR_IsTutor(true);
		userTutor.setUR_Activated(true);
		Tutor tutor = TutorManager.activateUserAsTutor(userTutor, factory);
		tutor.setTR_IsApproved(true);
		tutor.setTR_Pic("http://www.themepush.com/demo-kailo/wp-content/uploads/sites/6/edd/2015/03/163_1.jpg");
		tutor.setTR_Description(getCourseDescription());
		tutor.setTR_Summary("Default tutor summary");
		
		
		User userPending = factory.create(User.class);
		userPending.setMandatoryFields("Tutor", "Pending", pendingTutorEmail, pendingTutorPassword);
		Tutor pending = factory.create(Tutor.class);
		pending.setTR_IsApproved(false);
		pending.setMandatoryFields(userPending.PK, "Pending Tutor Summary");
		
		Certification pendingCert = factory.create(Certification.class);
		pendingCert.setMandatoryFields("CertificationNameHere", "The perfect degree");
		pendingCert.setCN_TR_PK(pending.getPK());

		int randomNum = 1 + (int)(Math.random()*5);
		Rate rate = factory.create(Rate.class);
		rate.setMandatoryFields(tutor.PK, randomNum, user.PK, TutorSchema.TableName);
		
		User admin = factory.create(User.class);
		admin.setMandatoryFields("Admin", "Account", adminEmail, adminPassword);
		admin.setUR_IsAdmin(true);

		
		
		Category category =  factory.create(Category.class);
		category.setMandatoryFields(categoryName);
		
		for (int i = 0; i < 20; i++) {
			Course course = factory.create(Course.class);
			course.setMandatoryFields("Test Course " + (i+1), "<h1>This is a Test Course.</h1>", getCourseDescription(), tutor.PK, new BigDecimal(Math.random() * 100), category.PK);
			course.PK = "test" + i;
			
			randomNum = 1 + (int)(Math.random()*5);
			Rate rate1 = factory.create(Rate.class);
			rate1.setMandatoryFields(course.PK, randomNum, user.PK, CourseSchema.TableName);
		}
		
		factory.save();
	}
	
	public void testDeleteDefaultValuesInTheMainDB() { // Do not use this method until you want to generate a testing tutor
		JUnit.ON = false;
		
		String testEmail = "test@test.com";
		String tutorEmail = "tutor@tutor.com";
		String adminEmail = "admin@admin.com";
		String pendingTutorEmail = "tutorpending@tutor.com";
		
		String categoryName = "test";
		
		// Delete existing accounts.
		List<String> userEmails = new ArrayList<String>(Arrays.asList(getEqualsStatement(testEmail), getEqualsStatement(tutorEmail), getEqualsStatement(adminEmail), getEqualsStatement(pendingTutorEmail)));
		for (int i = 0; i < 20; i++) {
			userEmails.add(getEqualsStatement(tutorEmail+i));
		}
		String whereStatement = String.join(" OR ", userEmails);
		String queryUser = String.format("SELECT * FROM %1$s WHERE %2$s;", UserSchema.TableName, whereStatement);
		BusinessObjectFactory factory = new BusinessObjectFactory();
		
		List<Category> categories = factory.load(Category.getQueryFindCategoryName(categoryName), Category.class);
		for (Category category : categories) {
			category.delete();
		}
		
		List<User> users = factory.load(queryUser, User.class);

		for (User user : users) {
			List<Tutor> tutors = factory.load(String.format("Select * from %1$s where %2$s = '%3$s';", TutorSchema.TableName, TutorSchema.TR_UR_PK, user.PK)
					, Tutor.class);
			for (Tutor tutor : tutors) {
				List<Course> courses = factory.load(String.format("Select * from %1$s where %2$s = '%3$s';", CourseSchema.TableName, CourseSchema.CE_Author, tutor.PK)
						, Course.class);
				for (Course course : courses) {
					List<Rate> rates = factory.load(String.format("Select * from %1$s where %2$s = '%3$s';", RateSchema.TableName, RateSchema.RE_TR_CE_Reference, course.PK)
							, Rate.class);
					for (Rate rate : rates) {
						rate.delete();
					}
					course.delete();
				}
				List<Rate> rates = factory.load(String.format("Select * from %1$s where %2$s = '%3$s';", RateSchema.TableName, RateSchema.RE_TR_CE_Reference, tutor.PK)
						, Rate.class);
				for (Rate rate : rates) {
					rate.delete();
				}
				List<Certification> certifications = factory.load(String.format("Select * from %1$s where %2$s = '%3$s';", CertificationSchema.TableName, CertificationSchema.CN_TR_PK, tutor.PK)
						, Certification.class);
				for (Certification certification : certifications) {
					certification.delete();
				}
				tutor.delete();
			}
			user.delete();
		}
		
		factory.save();
	}
	
	String getEqualsStatement(String email) {
		return String.format("%1$s = '%2$s'", UserSchema.UR_Email, email);
	}
	
	String getCourseDescription() {
		StringBuilder builder = new StringBuilder();
		builder.append("<h1><img alt=\"Saturn V carrying Apollo 11\" class=\"right\" src=\"http://c.cksource.com/a/1/img/sample.jpg\" /> Apollo 11</h1>");
		builder.append("<p><strong>Apollo 11</strong> was the spaceflight that landed the first humans, Americans <a href=\"http://en.wikipedia.org/wiki/Neil_Armstrong\">Neil Armstrong</a> and <a href=\"http://en.wikipedia.org/wiki/Buzz_Aldrin\">Buzz Aldrin</a>, on the Moon on July 20, 1969, at 20:18 UTC. Armstrong became the first to step onto the lunar surface 6 hours later on July 21 at 02:56 UTC.</p>");
		builder.append("<p>Armstrong spent about <s>three and a half</s> two and a half hours outside the spacecraft, Aldrin slightly less; and together they collected 47.5 pounds (21.5&nbsp;kg) of lunar material for return to Earth. A third member of the mission, <a href=\"http://en.wikipedia.org/wiki/Michael_Collins_(astronaut)\">Michael Collins</a>, piloted the <a href=\"http://en.wikipedia.org/wiki/Apollo_Command/Service_Module\">command</a> spacecraft alone in lunar orbit until Armstrong and Aldrin returned to it for the trip back to Earth.</p>");
		builder.append("<h2>Broadcasting and <em>quotes</em> <a id=\"quotes\" name=\"quotes\"></a></h2>");
		builder.append("<p>Broadcast on live TV to a world-wide audience, Armstrong stepped onto the lunar surface and described the event as:</p>");
		builder.append("<blockquote>");
		builder.append("<p>One small step for [a] man, one giant leap for mankind.</p>");
		builder.append("</blockquote>");
		builder.append("<p>Apollo 11 effectively ended the <a href=\"http://en.wikipedia.org/wiki/Space_Race\">Space Race</a> and fulfilled a national goal proposed in 1961 by the late U.S. President <a href=\"http://en.wikipedia.org/wiki/John_F._Kennedy\">John F. Kennedy</a> in a speech before the United States Congress:</p>");
		builder.append("<blockquote>");
		builder.append("<p>[...] before this decade is out, of landing a man on the Moon and returning him safely to the Earth.</p>");
		builder.append("</blockquote>");
		builder.append("<h2>Technical details <a id=\"tech-details\" name=\"tech-details\"></a></h2>");
		builder.append("<table align=\"right\" border=\"1\" bordercolor=\"#ccc\" cellpadding=\"5\" cellspacing=\"0\" style=\"border-collapse:collapse\">");
		builder.append("<caption><strong>Mission crew</strong></caption>");
		builder.append("<thead>");
		builder.append("<tr>");
		builder.append("<th scope=\"col\">Position</th>");
		builder.append("<th scope=\"col\">Astronaut</th>");
		builder.append("</tr>");
		builder.append("</thead>");
		builder.append("<tbody>");
		builder.append("<tr>");
		builder.append("<td>Commander</td>");
		builder.append("<td>Neil A. Armstrong</td>");
		builder.append("</tr>");
		builder.append("<tr>");
		builder.append("<td>Command Module Pilot</td>");
		builder.append("<td>Michael Collins</td>");
		builder.append("</tr>");
		builder.append("<tr>");
		builder.append("<td>Lunar Module Pilot</td>");
		builder.append("<td>Edwin &quot;Buzz&quot; E. Aldrin, Jr.</td>");
		builder.append("</tr>");
		builder.append("</tbody>");
		builder.append("</table>");
		builder.append("<p>Launched by a <strong>Saturn V</strong> rocket from <a href=\"http://en.wikipedia.org/wiki/Kennedy_Space_Center\">Kennedy Space Center</a> in Merritt Island, Florida on July 16, Apollo 11 was the fifth manned mission of <a href=\"http://en.wikipedia.org/wiki/NASA\">NASA</a>&#39;s Apollo program. The Apollo spacecraft had three parts:</p>");
		builder.append("<ol>");
		builder.append("<li><strong>Command Module</strong> with a cabin for the three astronauts which was the only part which landed back on Earth</li>");
		builder.append("<li><strong>Service Module</strong> which supported the Command Module with propulsion, electrical power, oxygen and water</li>");
		builder.append("<li><strong>Lunar Module</strong> for landing on the Moon.</li>");
		builder.append("</ol>");
		builder.append("<p>After being sent to the Moon by the Saturn V&#39;s upper stage, the astronauts separated the spacecraft from it and travelled for three days until they entered into lunar orbit. Armstrong and Aldrin then moved into the Lunar Module and landed in the <a href=\"http://en.wikipedia.org/wiki/Mare_Tranquillitatis\">Sea of Tranquility</a>. They stayed a total of about 21 and a half hours on the lunar surface. After lifting off in the upper part of the Lunar Module and rejoining Collins in the Command Module, they returned to Earth and landed in the <a href=\"http://en.wikipedia.org/wiki/Pacific_Ocean\">Pacific Ocean</a> on July 24.</p>");
		builder.append("<hr />");
		builder.append("<p><small>Source: <a href=\"http://en.wikipedia.org/wiki/Apollo_11\">Wikipedia.org</a></small></p>");
		return builder.toString();
	}
}
