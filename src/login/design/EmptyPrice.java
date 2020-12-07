package login.design;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JTextField;

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
		}
	}
}
