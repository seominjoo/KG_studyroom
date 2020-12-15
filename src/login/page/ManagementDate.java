package login.page;

import java.time.LocalDate;

public class ManagementDate {

	String[] yearTable;
	String[] monthTable;
	String[] dayTable;
	String[] monthTable2;//���� ȸ����(��ü����)
	String[] typeTable;//����,���� �̿�� ��Ȳ
	String[] use_statusTable;//�¼�,��,�繰�� �̿� ��Ȳ
	
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
		
		 
		monthTable2 = new String[lastMonth + 1];//�߰�
		monthTable2[0] = "��ü";
		for (int i = 1; i < monthTable2.length ; i++) {
			monthTable2[i] = (i + "").format("%02d", i);
		}
		
		typeTable = new String[3];//�߰�
		typeTable[0]="��ü";
		typeTable[1]="����";
		typeTable[2]="����";
		
		use_statusTable = new String[4];//�߰�
		use_statusTable[0]="��ü";
		use_statusTable[1]="�¼�";
		use_statusTable[2]="��";
		use_statusTable[3]="�繰��";
		 
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
	
	String[] getTypeTable() {//�߰�
		return typeTable;
	}
	
	String[] getuse_statusTable() {//�߰�
		return use_statusTable;
	}
	
}
