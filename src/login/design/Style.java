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
				panel.setOpaque(false); // ��� ����
				panel.setBackground(Color.decode("#404040"));
			}
		else if (cp instanceof JLabel) {
			label = (JLabel)cp;
			label.setOpaque(false); // ��� ����
			if(label.getBackground()==Color.decode("#eeeeee")){
				System.out.println("��� ���� �⺻�� ����");
				label.setBackground(Color.decode("#404040"));
			}
			label.setForeground(Color.decode("#cfab8b"));
		}else if (cp instanceof JButton) {
			btn = (JButton)cp;
			if(btn.getText().length()!=0) {
				btn.setBackground(Color.decode("#cfab8b"));
				btn.setFocusable(false);
				btn.setOpaque(false); // ��� ����
				btn.setFocusPainted(false);
				btn.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // �׵θ�?
				btn.setForeground(Color.decode("#cfab8b"));
			}else {
				btn.setBackground(Color.decode("#cfab8b"));
				btn.setFocusable(false);
				btn.setOpaque(false); // ��� ����
				btn.setFocusPainted(false);
				btn.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // �׵θ�?
				btn.setForeground(Color.decode("#cfab8b"));
				btn.setBorderPainted(false); // �ܰ����� ����
				btn.setContentAreaFilled(false); //���뿵���� ��ä��
				btn.setFocusPainted(false); // ���ý� �׵θ� ��
			}
			
		}else if (cp instanceof JTextField) {
			text = (JTextField) cp;
			text.setOpaque(false); // ��� ����
			text.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // �׵θ�?
			text.setHorizontalAlignment(SwingConstants.CENTER);
			text.setForeground(Color.decode("#cfab8b")); // �۾�ü ����
			text.setCaretColor(Color.decode("#cfab8b")); // Ŀ�� ����
			
		}else if (cp instanceof JPasswordField) {
			pass = (JPasswordField)cp;
			pass.setOpaque(false); // ��� ����
			pass.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // �׵θ�?
			pass.setHorizontalAlignment(SwingConstants.CENTER);
			pass.setForeground(Color.decode("#cfab8b")); // �۾�ü ����
			pass.setCaretColor(Color.decode("#cfab8b")); // Ŀ�� ����
		}
	}
	
	public Style() {
	}

	public static JPanel getnewPanel() {
		panel = new JPanel();
		panel.setOpaque(false); // ��� ����
		panel.setBackground(Color.decode("#404040"));
		return panel;
	}

}
