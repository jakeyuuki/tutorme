package tutorme.models;

import org.json.JSONArray;

import tutorme.models.Certification.CertificationSchema;
import tutorme.models.core.BusinessObjectFactory;

public class CertificationManager {	
	public static String getCertsAsJson(int startIndex){
		BusinessObjectFactory factory = new BusinessObjectFactory();
		String query = String.format("SELECT %1$s FROM %2$s LIMIT 18 OFFSET %3$d;", 
				String.join(", ", new String[] { CertificationSchema.PK, CertificationSchema.CN_Name, CertificationSchema.CN_Description, CertificationSchema.CN_Scan, CertificationSchema.CN_TR_PK}),
				CertificationSchema.TableName,
				startIndex);
		return factory.loadParsedData(query, JSONArray.class).toString();
	}
	
	
	//Get cert by cn_PK
		public static String getSingleCertAsJson(String pk){
			BusinessObjectFactory factory = new BusinessObjectFactory();
			String query = String.format("SELECT %1$s FROM %2$s WHERE %3$s = '%4$s';", 
					String.join(", ", new String[] { CertificationSchema.PK, CertificationSchema.CN_Name, CertificationSchema.CN_Description, CertificationSchema.CN_Scan, CertificationSchema.CN_TR_PK}),
					CertificationSchema.TableName,
					CertificationSchema.CN_TR_PK,
					pk);
			return factory.loadParsedData(query, JSONArray.class).toString();
		}
}
