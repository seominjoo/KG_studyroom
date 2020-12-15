package login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import login.findPW.FindPasswordPageUser;
import login.signUp.SignUpPage;

public class YearMonthClick implements ActionListener {

	String time;
	JComboBox year;
	JComboBox month;
	JComboBox day;

	public YearMonthClick(String time, boolean flag) {
		this.time = time;
		if (flag) {
			year = SignUpPage.year;
			month = SignUpPage.month;
			day = SignUpPage.day;
		} else {
			year = FindPasswordPageUser.year;
			month = FindPasswordPageUser.month;
			day = FindPasswordPageUser.day;
		}
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
			day.setSelectedItem("");
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
