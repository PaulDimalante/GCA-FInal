package commonFunctions;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TestDate {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Date javaDate;
		LocalDate localDate;
		LocalDateTime localDateTime;
		java.sql.Date sqlDate = null;

		javaDate = new Date();
		System.out.printf("javaDate=%s %n",new SimpleDateFormat("yyyy-MM-dd.hh:mm:ss").format(javaDate));
		localDate = javaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		localDateTime = javaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		System.out.printf("localDateTime=%s %n",localDateTime.format(DateTimeFormatter.ofPattern("y-M-d.H:m:s")));
		System.out.printf("localDate=%s %n",localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		sqlDate = sqlDate.valueOf(localDate);
		System.out.println("sqlDate=" + sqlDate);
	}

}
