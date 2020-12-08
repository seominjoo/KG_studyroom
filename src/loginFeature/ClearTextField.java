package loginFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class ClearTextField extends MouseAdapter {
	
	SignUpEnum value;
	
	public ClearTextField(SignUpEnum value) {
		this.value = value;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1 && value.text.getText().contains(value.labelName)) {
			value.text.setText("");
			value.blindPW.setText("");
			for(SignUpEnum other : SignUpEnum.values()) {
				if(!other.equals(value) && (other.text.getText().equals("") 
						&& String.valueOf(other.blindPW.getPassword()).equals(""))) {
					other.text.setText(other.labelName);
					other.blindPW.setText(other.labelName);
				}
			}
			for(PhoneNumberEnum value : PhoneNumberEnum.values()) {
				if(value.text.getText().equals("")) {
					value.text.setText(value.labelName);
				}				
			}
		}
			//clickCnt++;
			// 최초 입력시 초기화 
			
//			for (SignUpEnum value : SignUpEnum.values()) {
//				if (clickCnt <= 1 || textList.get(listIndex).getText().equals(value.labelName))
//					textList.get(listIndex).setText("");
//
//			}
			// 텍스트 눌럿을 때 다른 빈텍스트 기본값으로 표시

	}

}
