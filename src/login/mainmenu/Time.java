package login.mainmenu;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Time {

	
	public static Timestamp localDateTimeTOTimeStamp(LocalDateTime ldt) {
		return Timestamp.valueOf(ldt);
	}
	
	public static LocalDateTime TimeStampTOlocalDateTime(Timestamp ts) {
		return ts.toLocalDateTime();
	}
}

