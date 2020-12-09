package loginFeature;

import java.time.LocalDate;

import javax.swing.JComboBox;

public enum BirthEnum {

	YEAR(""), MONTH(""), DAY("");

	public JComboBox<String> birthComboBoxYear;
	public JComboBox<String> birthComboBoxMonth;
	public JComboBox<String> birthComboBoxDay;
	public String[] dayTable;
	
	BirthEnum(String string) {
		birthComboBoxYear = new JComboBox<String>(getYearTable());
		birthComboBoxMonth = new JComboBox<String>(getMonthTable());
		birthComboBoxDay = new JComboBox<String>(getDayTable());
		dayTable = getDayTable();
		birthComboBoxYear.setSelectedItem("2000");
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
		int lastMonth = 12;
		String[] monthTable = new String[lastMonth + 1];
		for (int i = 1; i < monthTable.length; i++) {
			monthTable[i] = (i + "").format("%02d", i);
		}
		return monthTable;
	}

	String[] getDayTable() {
		String[] dayTable = new String[32];
		for (int i = 1; i < dayTable.length; i++) {
			dayTable[i] = (i + "").format("%02d", i);
		}
		return dayTable;
	}

}
