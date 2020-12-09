package login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class YearMonthClick implements ActionListener {

	String time;
	JComboBox year;
	JComboBox month;
	JComboBox day;

	public YearMonthClick(String time) {
		this.time = time;
		year = BirthEnum.YEAR.birthComboBoxYear;
		month = BirthEnum.MONTH.birthComboBoxMonth;
		day = BirthEnum.DAY.birthComboBoxDay;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (time.equals("year")) {
			month.setSelectedItem("01");
			day.setSelectedItem("01");
		} else {
			int yearselect = Integer.parseInt((String) year.getSelectedItem());
			int monthselect = Integer.parseInt((String) month.getSelectedItem());
			System.out.println(yearselect + ", " + monthselect);
			DefaultComboBoxModel<String> realDay = new DefaultComboBoxModel<>(BirthEnum.DAY.dayTable);
			day.setModel(realDay);
			int[] monthFordayCount = { 4, 6, 9, 11 };
			if (monthselect == 2) {
				if (yearselect % 4 == 0) {
					day.removeItem("30");
					day.removeItem("31");
				} else {
					day.removeItem("29");
					day.removeItem("30");
					day.removeItem("31");
				}
			} else {
				for (int i : monthFordayCount) {
					if (i == monthselect) {
						day.removeItem("31");
						break;
					}

				}
			}
		}
	}

}
