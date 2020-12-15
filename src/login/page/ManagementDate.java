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
		monthTable = new String[lastMonth + 1];
		for (int i = 1; i < monthTable.length ; i++) {
			monthTable[i] = (i + "").format("%02d", i);
		}
		
		
		dayTable = new String[31 + 1];
		for (int i = 1; i < dayTable.length ; i++) {
			dayTable[i] = (i + "").format("%02d", i);
		}
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
