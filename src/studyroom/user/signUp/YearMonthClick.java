package studyroom.user.signUp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import studyroom.admin.adminmode.SalesManagementPage;
import studyroom.user.findPW.FindPasswordPageUser;

public class YearMonthClick implements ActionListener {

	String time;
	JComboBox year;
	JComboBox month;
	JComboBox day;

	public YearMonthClick(String time, String flag) {
		this.time = time;
		if (flag.equals("ȸ������")) {
			year = SignUpPage.year;
			month = SignUpPage.month;
			day = SignUpPage.day;
		} else if (flag.equals("���ã��")) {
			year = FindPasswordPageUser.year;
			month = FindPasswordPageUser.month;
			day = FindPasswordPageUser.day;
		} else if (flag.equals("�������")) {
			year = SalesManagementPage.year;
			month = SalesManagementPage.month;
			day = SalesManagementPage.day;
		}
	}

	// ���� Ŭ�� �� ��,�� �ʱ�ȭ & �� Ŭ�� �� �� �ʱ�ȭ
	@Override
	public void actionPerformed(ActionEvent e) {

		if (time.equals("year")) {
			month.setSelectedItem("01");
			day.setSelectedItem("01");
		} else {
			int monthselect = 0;
			int yearselect = Integer.parseInt((String) year.getSelectedItem());
			if (month.getSelectedItem().equals(" ")) {
				month.setSelectedItem("01");
			}
			monthselect = Integer.parseInt((String) month.getSelectedItem());

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
