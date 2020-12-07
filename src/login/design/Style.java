package login.design;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class Style {
	
	private static JPanel panel;
	private JLabel label;
	private JButton btn;
	private JTextField text;
	private JPasswordField pass;
	private JComponent cp;
	
	public Style(JComponent cp) {
		this.cp = cp;
			if (cp instanceof JPanel) {
				panel = (JPanel)cp;
				panel.setOpaque(false); // 배경 투명
				panel.setBackground(Color.decode("#404040"));
			}
		else if (cp instanceof JLabel) {
			label = (JLabel)cp;
			label.setOpaque(false); // 배경 투명
			if(label.getBackground()==Color.decode("#eeeeee")){
				System.out.println("배경 없음 기본색 적용");
				label.setBackground(Color.decode("#404040"));
			}
			label.setForeground(Color.decode("#cfab8b"));
		}else if (cp instanceof JButton) {
			btn = (JButton)cp;
			if(btn.getText().length()!=0) {
				btn.setBackground(Color.decode("#cfab8b"));
				btn.setFocusable(false);
				btn.setOpaque(false); // 배경 투명
				btn.setFocusPainted(false);
				btn.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // 테두리?
				btn.setForeground(Color.decode("#cfab8b"));
			}else {
				btn.setBackground(Color.decode("#cfab8b"));
				btn.setFocusable(false);
				btn.setOpaque(false); // 배경 투명
				btn.setFocusPainted(false);
				btn.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // 테두리?
				btn.setForeground(Color.decode("#cfab8b"));
				btn.setBorderPainted(false); // 외곽선을 없앰
				btn.setContentAreaFilled(false); //내용영역을 안채움
				btn.setFocusPainted(false); // 선택시 테두리 끔
			}
			
		}else if (cp instanceof JTextField) {
			text = (JTextField) cp;
			text.setOpaque(false); // 배경 투명
			text.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // 테두리?
			text.setHorizontalAlignment(SwingConstants.CENTER);
			text.setForeground(Color.decode("#cfab8b")); // 글씨체 색상
			text.setCaretColor(Color.decode("#cfab8b")); // 커서 색상
			
		}else if (cp instanceof JPasswordField) {
			pass = (JPasswordField)cp;
			pass.setOpaque(false); // 배경 투명
			pass.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // 테두리?
			pass.setHorizontalAlignment(SwingConstants.CENTER);
			pass.setForeground(Color.decode("#cfab8b")); // 글씨체 색상
			pass.setCaretColor(Color.decode("#cfab8b")); // 커서 색상
		}
	}
	
	public Style() {
	}

	public static JPanel getnewPanel() {
		panel = new JPanel();
		panel.setOpaque(false); // 배경 투명
		panel.setBackground(Color.decode("#404040"));
		return panel;
	}

}
