package studyroom.user.usermode;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Time {//db에 넣을 때와 자바로 불러올 때 필요한 타입 변경 클래스
 
	public static Timestamp localDateTimeTOTimeStamp(LocalDateTime ldt) {
		return Timestamp.valueOf(ldt);
	}
	
	public static LocalDateTime TimeStampTOlocalDateTime(Timestamp ts) {
		return ts.toLocalDateTime();
	}
}

