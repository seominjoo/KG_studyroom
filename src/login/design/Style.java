package login.design;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.table.DefaultTableCellRenderer;

import login.signUp.SignUpPage;

public class Style {

	private static JPanel panel;
	private JLabel label;
	private JButton btn;
	private JTextField text;
	private JPasswordField pass;
	private JComboBox<?> jcombo;
	private JScrollPane jscroll;
	private JCheckBox jcheck;
	private JTable jtable;
	private JRadioButton jradio;
	private JComponent cp;
	private int size;
	
	
	public Style(JComponent cp) {
		this.cp = cp;
		if (cp instanceof JPanel) {
			panel = (JPanel) cp;
			panel.setOpaque(false); // ��� ����
			panel.setBackground(Color.decode("#404040"));
//			panel.setBackground(Color.decode("#f6f1ed")); ���� ����
		} else if (cp instanceof JLabel) {
			label = (JLabel) cp;
			label.setOpaque(false); // ��� ����
			if (label.getBackground() == Color.decode("#eeeeee")) {
				System.out.println("��� ���� �⺻�� ����");
				label.setBackground(Color.decode("#404040"));
			} else if (label.getText().equals("-")) {
				label.setFont(new Font("���� ���", Font.PLAIN, 12));
			} else if (label.getText().equals("")) {
				label.setFont(new Font("���� ���", Font.BOLD, 16));
			} else {
				label.setFont(new Font("���� ���", Font.BOLD, 15));
			}
			label.setForeground(Color.decode("#cfab8b"));
		} else if (cp instanceof JButton) {
			btn = (JButton) cp;
			if (btn.getText().length() != 0) {
				btn.setBackground(Color.decode("#404040"));
				btn.setFocusable(false);
				btn.setOpaque(false); // ��� ����
				btn.setFocusPainted(false);
				btn.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // �׵θ�?
				btn.setForeground(Color.decode("#cfab8b"));
				btn.setFont(new Font("���� ���", Font.BOLD, 15));
			} else {
				btn.setBackground(Color.decode("#cfab8b"));
				btn.setFocusable(false);
				btn.setOpaque(false); // ��� ����
				btn.setFocusPainted(false);
				btn.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // �׵θ�?
				btn.setForeground(Color.decode("#cfab8b"));
				btn.setBorderPainted(false); // �ܰ����� ����
				btn.setContentAreaFilled(false); // ���뿵���� ��ä��
				btn.setFocusPainted(false); // ���ý� �׵θ� ��
			}
		} else if (cp instanceof JTextField) {
			text = (JTextField) cp;
			text.setOpaque(false); // ��� ����
			text.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // �׵θ�?
			text.setHorizontalAlignment(SwingConstants.CENTER);
			text.setForeground(Color.decode("#cfab8b"));
			if (!(text.getText().equals("010") || text.getText().equals("")))
				text.setFont(new Font("���� ���", Font.BOLD, 13));
			text.setCaretColor(Color.decode("#cfab8b")); // Ŀ�� ����
		} else if (cp instanceof JPasswordField) {
			pass = (JPasswordField) cp;
			pass.setOpaque(false); // ��� ����
			pass.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // �׵θ�?
			pass.setHorizontalAlignment(SwingConstants.CENTER);
			pass.setForeground(Color.decode("#cfab8b"));
			pass.setFont(new Font("���� ���", Font.PLAIN, 17));
			text.setCaretColor(Color.decode("#cfab8b")); // Ŀ�� ����
		} else if (cp instanceof JComboBox<?>) {
			jcombo = (JComboBox<?>) cp;
			jcombo.setOpaque(false); // ��� ����
			jcombo.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // �׵θ�?
			jcombo.setForeground(Color.decode("#cfab8b"));
			jcombo.setBackground(Color.decode("#404040"));
			jcombo.setUI(new BasicComboBoxUI() {
				@Override
				protected ComboPopup createPopup() {
					BasicComboPopup basicComboPopup = new BasicComboPopup(comboBox);
					basicComboPopup.setBorder(new LineBorder(Color.decode("#cfab8b")));
					return basicComboPopup;
				}
			});
		} else if (cp instanceof JScrollPane) {
			jscroll = (JScrollPane) cp;
			jscroll.setOpaque(false); // ��� ����
			jscroll.getViewport().setBackground(Color.decode("#404040"));
		}
		else if (cp instanceof JCheckBox) {
			jcheck = (JCheckBox) cp;
			jcheck.setForeground(Color.decode("#cfab8b"));
			jcheck.setOpaque(false);
			jcheck.setFont(new Font("���� ���", Font.BOLD, 13));
		}
		else if (cp instanceof JTable) {
			jtable = (JTable) cp;
			// �Ʒ� �ΰ��� ǥ ���θ� ����ȭ�� �� �ݵ�� �ʿ�
			jtable.setOpaque(false);
			((DefaultTableCellRenderer)jtable.getDefaultRenderer(Object.class)).setOpaque(false);
			//
			jtable.setFont(new Font("���� ���", Font.PLAIN, 10));
			jtable.setForeground(Color.decode("#cfab8b"));
			jtable.setGridColor(Color.decode("#cfab8b")); // ���̺� ���� �� ��
			jtable.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b")));
			jtable.setEnabled(false); // ����ڰ� ���̺��� Ŭ���ϰų� �����ϴ� ���� ����
		}
		else if(cp instanceof JRadioButton) {
			jradio = (JRadioButton) cp;
			jradio.setOpaque(false);
			jradio.setFont(new Font("���� ���", Font.BOLD, 13));
			jradio.setForeground(Color.decode("#cfab8b"));
		}
	}

	public Style(JTextField cp, int size) {
		new Style(cp);
		cp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				JTextField src = (JTextField) e.getSource();
				if (src.getText().length() >= size)
					e.consume();
			}
		});
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
