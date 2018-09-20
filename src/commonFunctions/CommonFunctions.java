package commonFunctions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class CommonFunctions {
	public static String encrypt(String unencrypted) {
		return BCrypt.hashpw(unencrypted, BCrypt.gensalt());
	}

	public static boolean chk2Encrypt(String unencrypted, String encrypted) {
		return BCrypt.checkpw(unencrypted, encrypted);
	}

	public static String getCurrentFormatedDate() {
		Date date = (Date) new java.util.Date();
		return formatDate(date);
	}

	public static String getCurrentFormatedDate(Date date) {
		return formatDate(date);
	}

	public static String formatDate(Date date) {
		String fmtDate;
		DateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.US);
		fmtDate = dateFormat.format(date);
		return fmtDate;
	}

	public static Date parseDate(String date) {
		try {
			return (Date) new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date parseDateTime(String dateTime) {
		try {
			return (Date) new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dateTime);
		} catch (ParseException e) {
			return null;
		}
	}

	public static java.sql.Date utilDateToSqlDate(java.util.Date utilDate) {
		LocalDate localDate = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
		return (sqlDate);
	}

}
