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
	String[] monthTable2;//���� ȸ����(��ü����)
	String[] typeTable;//����,���� �̿�� ��Ȳ
	String[] use_statusTable;//�¼�,��,�繰�� �̿� ��Ȳ
	String[] passTable;//�̿�� Ÿ��(���ݺ��� Ŭ����)
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
		monthTable2[0] = "��ü";
		for (int i = 1; i < monthTable2.length ; i++) {
			monthTable2[i] = (i + "").format("%02d", i);
		}


		typeTable = new String[3]; 
		typeTable[0]="��ü";
		typeTable[1]="����";
		typeTable[2]="����";

		use_statusTable = new String[4]; 
		use_statusTable[0]="��ü";
		use_statusTable[1]="�¼�";
		use_statusTable[2]="��";
		use_statusTable[3]="�繰��";

		try {//�̿�� ���� ���� ���̺� �б�
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
		passTable[0] = "���� �̿��(1�μ�)";
		for(int i=0; i<passTable.length-1; i++) { 
			passTable[i+1] = pass.get((i+1)*2);
		}

		passTable2 = new String[4]; 
		passTable2[0] = "���� �̿��(1�μ�)";
		for(int i=0; i<passTable2.length-1; i++) { 
			passTable2[i+1] = pass.get((i+6)*2);
		}

		passTable3 = new String[6]; 
		passTable3[0] = "���� �̿��(��)";
		for(int i=0; i<passTable3.length-1; i++) { 
			passTable3[i+1] = pass.get((i+9)*2);
		}

		passTable4 = new String[2]; 
		passTable4[0] = "�繰�� �̿��";
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

	String[] getMonthTable2() {//�߰�
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
