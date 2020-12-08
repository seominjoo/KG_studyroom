package studyroom;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Time {

	
	static Timestamp localDateTimeTOTimeStamp(LocalDateTime ldt) {
		return Timestamp.valueOf(ldt);
	}
	
	static LocalDateTime TimeStampTOlocalDateTime(Timestamp ts) {
		return ts.toLocalDateTime();
	}
}
