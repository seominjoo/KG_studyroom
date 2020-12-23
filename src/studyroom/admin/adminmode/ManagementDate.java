package studyroom.admin.adminmode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import studyroom.user.usermode.WhatHour;

public class ManagementDate {

	String[] yearTable;
	String[] monthTable;
	String[] dayTable;
	String[] monthTable2;//월별 회원수(전체포함)
	String[] typeTable;//정기,당일 이용권 현황
	String[] use_statusTable;//좌석,룸,사물함 이용 현황
	String[] passTable;//이용권 타입(가격변경 클래스)
	String[] passTable2;
	String[] passTable3;
	String[] passTable4;

	static ArrayList<String> pass = new ArrayList<>();
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
		monthTable[0] = " ";
		for (int i = 1; i < monthTable.length ; i++) {
			monthTable[i] = (i + "").format("%02d", i);
		}


		dayTable = new String[31 + 1];
		dayTable[0] = " ";
		for (int i = 1; i < dayTable.length ; i++) {
			dayTable[i] = (i + "").format("%02d", i);
		}


		monthTable2 = new String[lastMonth + 1]; 
		monthTable2[0] = "전체";
		for (int i = 1; i < monthTable2.length ; i++) {
			monthTable2[i] = (i + "").format("%02d", i);
		}


		typeTable = new String[3]; 
		typeTable[0]="전체";
		typeTable[1]="정기";
		typeTable[2]="당일";

		use_statusTable = new String[4]; 
		use_statusTable[0]="전체";
		use_statusTable[1]="좌석";
		use_statusTable[2]="룸";
		use_statusTable[3]="사물함";

		try {//이용권 가격 정보 테이블 읽기
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");


			String sql = "select * from seat_price_info,locker_price_info";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			int row=0;
			int loc=0;
			while (rs.next()) {
				if(loc==0) {
					pass.add(rs.getString(3));
					pass.add(Integer.toString(rs.getInt(4)));
					loc=1;
				}
				pass.add(rs.getString(1));
				pass.add(Integer.toString(rs.getInt(2)));
			}

			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (ClassNotFoundException | SQLException e1) { 
			e1.printStackTrace();
		}

		passTable = new String[6]; 
		passTable[0] = "당일 이용권(1인석)";
		for(int i=0; i<passTable.length-1; i++) { 
			passTable[i+1] = pass.get((i+1)*2);
		}

		passTable2 = new String[4]; 
		passTable2[0] = "정기 이용권(1인석)";
		for(int i=0; i<passTable2.length-1; i++) { 
			passTable2[i+1] = pass.get((i+6)*2);
		}

		passTable3 = new String[6]; 
		passTable3[0] = "당일 이용권(룸)";
		for(int i=0; i<passTable3.length-1; i++) { 
			passTable3[i+1] = pass.get((i+9)*2);
		}

		passTable4 = new String[2]; 
		passTable4[0] = "사물함 이용권";
		for(int i=0; i<passTable4.length-1; i++) { 
			passTable4[i+1] = pass.get(i);
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

	String[] getMonthTable2() {//추가
		return monthTable2;
	}

	String[] getTypeTable() { 
		return typeTable;
	}

	String[] getuse_statusTable() { 
		return use_statusTable;
	}

	String[] getcpassTable() { 
		return passTable;
	}
	String[] getcpassTable2() { 
		return passTable2;
	}
	String[] getcpassTable3() { 
		return passTable3;
	}
	String[] getcpassTable4() { 
		return passTable4;
	}

}
