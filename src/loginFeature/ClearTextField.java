package loginFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class ClearTextField extends MouseAdapter/* implements MouseListener */ {

	int clickCnt = 0;
	
	JTextField text;

	public ClearTextField(JTextField text) {
		this.text = text;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			clickCnt++;
			for(SignUpEnum value : SignUpEnum.values()) {
			if (clickCnt <= 1 || text.getText().equals(value.labelName))
				text.setText("");
			}
		}
	}
//§! �� �� �ȵ��͵� ���ðŸ� ���ü��ְ� !!��������

}
