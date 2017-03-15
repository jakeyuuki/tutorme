package tutorme.util;

import java.util.UUID;

public class GenerateUUID {
	public static long generateUUID() {
		UUID uuidOne = UUID.randomUUID();
		long keyValues = uuidOne.getMostSignificantBits();
		return keyValues;
	}
}
