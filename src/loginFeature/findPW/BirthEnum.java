package loginFeature.findPW;

import java.time.LocalDate;

import javax.swing.JComboBox;

public enum BirthEnum {

	YEAR(""), MONTH(""), DAY("");

	JComboBox<String> birthComboBoxYear;
	JComboBox<String> birthComboBoxMonth;
	JComboBox<String> birthComboBoxDay;

	BirthEnum(String string) {
		birthComboBoxYear = new JComboBox<String>(getYearTable());
		birthComboBoxMonth = new JComboBox<String>(getMonthTable());
		birthComboBoxDay = new JComboBox<String>(getDayTable());
	}

	String[] getYearTable() {
		int firstYear = 1930;
		int lastYear = LocalDate.now().getYear();
		String[] yearTable = new String[lastYear - firstYear + 1];
		for (int i = 0; i <= lastYear - 1930; i++) {
			yearTable[i] = (firstYear + "").format("%d", firstYear);
			firstYear++;
		}
		return yearTable;
	}

	String[] getMonthTable() {
		int firstMonth = 1;
		int lastMonth = 12;
		String[] monthTable = new String[lastMonth - firstMonth + 1];
		for (int i = 0; i < lastMonth; i++) {
			monthTable[i] = (firstMonth + "").format("%02d", firstMonth);
			firstMonth++;
		}
		return monthTable;
	}

	String[] getDayTable() {
		int firstDay = 1;

		String[] dayTable = new String[31]; //28
		for (int i = 0; i < dayTable.length; i++) {
			dayTable[i] = (firstDay + "").format("%02d", firstDay);
			firstDay++;
		}
		return dayTable;
	}

}
