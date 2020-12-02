package loginFeature;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

public class SignUp extends JFrame {

	static ArrayList<JLabel> labelList;
	static ArrayList<JTextField> textList;

	static {
		labelList = new ArrayList<JLabel>();
		textList = new ArrayList<JTextField>();
	}

	public SignUp() {
		// sign.value.text;
		// sign.value.label;
		setBounds(90, 50, 600, 800);
		int componentLocation = getWidth() / 3;
		//

		int view = 0;
		for (SignUpEnum value : SignUpEnum.values()) {
			JLabel label = value.label;
			JTextField text = value.text;

			label.setBounds(componentLocation, view, 200, 50);
			view += 50;
			text.setBounds(componentLocation, view, 200, 50);
			view += 50;

			add(text, BorderLayout.CENTER);
			add(label, BorderLayout.CENTER);
			labelList.add(label);
			textList.add(text);

		}
		for (int i = 0; i < textList.size(); i++)
			textList.get(i).addMouseListener(new ClearTextField(textList.get(i)));

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = 0;
				for (SignUpEnum value : SignUpEnum.values()) {
					if (textList.get(i).getText().equals(""))
						textList.get(i).setText(value.labelName);
					i++;
				}
			}
		});
		

		SwingTools.initTestFrame(this);
	}

	public static void main(String[] args) {
		new SignUp();
	}

}