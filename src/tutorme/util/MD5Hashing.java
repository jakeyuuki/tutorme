package tutorme.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Hashing {
	public static String getMD5Hash(String value) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(value.getBytes());
			byte byteData[] = md.digest();

		    StringBuffer sb = new StringBuffer();
		    for (int i = 0; i < byteData.length; i++)
		        sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		    return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return value;
		}
	}
}
