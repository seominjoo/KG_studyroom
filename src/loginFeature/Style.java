package loginFeature;


import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
	private JComboBox<?> jcombo;
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
			else if(label.getText().equals("-")) {
			}
			else if(label.getText().equals("")) {
				label.setFont(new Font("���� ���", Font.BOLD, 16));
			}
			else {
			label.setFont(new Font("���� ���", Font.BOLD, 17));
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
				btn.setFont(new Font("���� ���", Font.BOLD, 17));
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
			text.setForeground(Color.decode("#cfab8b"));
			if(!(text.getText().equals("010") || text.getText().equals("")))
				text.setFont(new Font("���� ���", Font.BOLD, 17));
		}else if (cp instanceof JPasswordField) {
			pass = (JPasswordField)cp;
			pass.setOpaque(false); // ��� ����
			pass.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // �׵θ�?
			pass.setHorizontalAlignment(SwingConstants.CENTER);
			pass.setForeground(Color.decode("#cfab8b"));
			pass.setFont(new Font("���� ���", Font.PLAIN, 17));
		}
		else if (cp instanceof JComboBox<?>) {
			jcombo = (JComboBox<?>) cp;
			jcombo.setOpaque(false); // ��� ����
			jcombo.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // �׵θ�?
			jcombo.setForeground(Color.decode("#cfab8b"));
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
