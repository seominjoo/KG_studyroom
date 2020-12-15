package login.page;

import java.time.LocalDate;

public class ManagementDate {

	String[] yearTable;
	String[] monthTable;
	String[] dayTable;
	String[] monthTable2;//월별 회원수(전체포함)
	String[] typeTable;//정기,일일 이용권 현황
	String[] use_statusTable;//좌석,룸,사물함 이용 현황
	
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
		
		 
		monthTable2 = new String[lastMonth + 1];//추가
		monthTable2[0] = "전체";
		for (int i = 1; i < monthTable2.length ; i++) {
			monthTable2[i] = (i + "").format("%02d", i);
		}
		
		typeTable = new String[3];//추가
		typeTable[0]="전체";
		typeTable[1]="정기";
		typeTable[2]="일일";
		
		use_statusTable = new String[4];//추가
		use_statusTable[0]="전체";
		use_statusTable[1]="좌석";
		use_statusTable[2]="룸";
		use_statusTable[3]="사물함";
		 
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
	
	String[] getMonthTable2() {//추가
		return monthTable2;
	}
	
	String[] getTypeTable() {//추가
		return typeTable;
	}
	
	String[] getuse_statusTable() {//추가
		return use_statusTable;
	}
	
}
