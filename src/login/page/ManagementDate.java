package login.page;

import java.time.LocalDate;

public class ManagementDate {

	public ManagementDate() {
		int firstYear = 1930;
		int lastYear = LocalDate.now().getYear();
		String[] yearTable = new String[lastYear - firstYear + 1];
		for (int i = 0; i <= lastYear - 1930; i++) {
			yearTable[i] = (firstYear + "").format("%d", firstYear);
			firstYear++;
		}
		
		int lastMonth = 12;
		String[] monthTable = new String[lastMonth + 1];
		//monthTable[0] = "";
		for (int i = 1; i < monthTable.length; i++) {
			monthTable[i] = (i + "").format("%02d", i);
		}
		
		String[] dayTable = new String[32];
		//dayTable[0] = "";
		for (int i = 1; i < dayTable.length; i++) {
			dayTable[i] = (i + "").format("%02d", i);
		}
		
	}
	
}
