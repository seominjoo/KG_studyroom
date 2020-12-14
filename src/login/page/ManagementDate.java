package login.page;

import java.time.LocalDate;

public class ManagementDate {

	String[] yearTable;
	String[] monthTable;
	String[] dayTable;
	
	public ManagementDate() {
		int firstYear = LocalDate.now().getYear() - 3;
		int lastYear = LocalDate.now().getYear() + 3;
		yearTable = new String[lastYear - firstYear + 1];
		for (int i = 0; i < lastYear - 2017 + 1; i++) {
			yearTable[i] = (firstYear + "").format("%d", firstYear);
			firstYear++;
		}

		
		int lastMonth = 12;
		monthTable = new String[lastMonth + 1 + 1];
		for (int i = 1; i < monthTable.length - 1; i++) {
			monthTable[i] = (i + "").format("%02d", i);
		}
		monthTable[lastMonth + 1 + 1 - 1] = "연매출";
		
		
		dayTable = new String[32 + 1];
		for (int i = 1; i < dayTable.length - 1; i++) {
			dayTable[i] = (i + "").format("%02d", i);
		}
		dayTable[32 + 1 - 1] = "월매출";
	}
	
	String[] getYearTable() {
		return yearTable;
	}
	
	String[] getMonthTable() {
		return monthTable;
	}
	
	String[] getDayTable() {
		return dayTable;
	}
	
}
