package loginFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class PhoneNumberClearTextField extends MouseAdapter/* implements MouseListener */ {

	int clickCnt = 0;

	int listIndex;
	ArrayList<JTextField> textList;

	public PhoneNumberClearTextField(ArrayList<JTextField> textList, int listIndex) {
		this.textList = textList;
		this.listIndex = listIndex;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for (PhoneNumberEnum value : PhoneNumberEnum.values()) {
		if (e.getButton() == MouseEvent.BUTTON1 && textList.get(listIndex).getText().contains(value.initialStatement)) {
			textList.get(listIndex).setText("");
		}
			//clickCnt++;
			// ���� �Է½� �ʱ�ȭ 
			
//			for (SignUpEnum value : SignUpEnum.values()) {
//				if (clickCnt <= 1 || textList.get(listIndex).getText().equals(value.labelName))
//					textList.get(listIndex).setText("");
//
//			}
			// �ؽ�Ʈ ������ �� �ٸ� ���ؽ�Ʈ �⺻������ ǥ��
			for (int i = 0; i < textList.size(); i++) {
				if (textList.get(i).getText().equals("")) {
					if (i != listIndex) {
						textList.get(i).setText(PhoneNumberEnum.values()[i].initialStatement);
					}
				}
			}

		}
	}

}
