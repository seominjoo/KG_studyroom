package login.design;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JTextField;

import login.PhoneNumberEnum;
import login.page.AdminPage;
import login.page.LoginPage;

public class EmptyPrice extends MouseAdapter {
	private JTextField text;

	public EmptyPrice(JTextField text) {
		this.text = text;

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			if (text.getText().equals("비밀번호")) {
				text.setText("");
			}
			for (int i = 0; i < LoginPage.phoneTotal.length; i++) {
				if (LoginPage.phoneTotal[i].getText().equals("")) {
					LoginPage.phoneTotal[i].setText(PhoneNumberEnum.values()[i].labelName);
				}
			}
			for (int i = 0; i < AdminPage.phoneTotal.length; i++) {
				if (AdminPage.phoneTotal[i].getText().equals("")) {
					AdminPage.phoneTotal[i].setText(PhoneNumberEnum.values()[i].labelName);
				}
			}
		}
	}
}
