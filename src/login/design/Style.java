package login.design;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Scrollbar;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.table.DefaultTableCellRenderer;

import login.signUp.SignUpPage;

public class Style {

	private static JPanel panel;
	private JFrame frame;
	private JOptionPane optionpane;
	private JLabel label;
	private JButton btn;
	private JTextField text;
	private JPasswordField pass;
	private JComboBox<?> jcombo;
	private JScrollPane jscroll;
	private JCheckBox jcheck;
	private JTable jtable;
	private JRadioButton jradio;
	private DefaultTableCellRenderer renderer;
	private JComponent cp;
	private int size;
	static BufferedImage image;

	// ������Ʈ ��Ÿ��
	public Style(JComponent cp) {
		this.cp = cp;
//      .getViewport().setBackground(Color.decode("#404040")); ���� ����
		if (cp instanceof JPanel) {
			panel = (JPanel) cp;
			panel.setOpaque(false); // ��� ����
			panel.setBackground(Color.decode("#fffcfa"));
//         panel.setBorder(BorderFactory.createLineBorder(Color.decode("#805b38")));
		} else if (cp instanceof JLabel) {
			label = (JLabel) cp;
			label.setOpaque(false); // ��� ����
			if (label.getBackground() == Color.decode("#eeeeee")) {
				System.out.println("��� ���� �⺻�� ����");
				label.setBackground(Color.decode("#fffcfa"));
			} else if (label.getText().equals("-")) {
				label.setFont(new Font("���� ���", Font.PLAIN, 12));
			} else if (label.getText().equals("")) {
				label.setFont(new Font("���� ���", Font.BOLD, 16));
			} else {
				label.setFont(new Font("���� ���", Font.BOLD, 15));
			}
			label.setForeground(Color.decode("#805b38"));
		} else if (cp instanceof JButton) {
			btn = (JButton) cp;
			if (btn.getText().length() != 0) {
				btn.setBackground(Color.decode("#fffcfa"));
				btn.setFocusable(false);
				btn.setOpaque(false); // ��� ����
				btn.setFocusPainted(false);
				btn.setBorder(BorderFactory.createLineBorder(Color.decode("#805b38"))); // �׵θ�?
				btn.setForeground(Color.decode("#805b38"));
				btn.setFont(new Font("���� ���", Font.BOLD, 15));
			} else {
				btn.setBackground(Color.decode("#fffcfa"));
				btn.setFocusable(false);
				btn.setOpaque(false); // ��� ����
				btn.setFocusPainted(false);
				btn.setBorder(BorderFactory.createLineBorder(Color.decode("#805b38"))); // �׵θ�?
				btn.setForeground(Color.decode("#805b38"));
				btn.setBorderPainted(false); // �ܰ����� ����
				btn.setContentAreaFilled(false); // ���뿵���� ��ä��
				btn.setFocusPainted(false); // ���ý� �׵θ� ��
			}
		} else if (cp instanceof JTextField) {
			text = (JTextField) cp;
			text.setOpaque(false); // ��� ����
			text.setBorder(BorderFactory.createLineBorder(Color.decode("#805b38"))); // �׵θ�?
			text.setHorizontalAlignment(SwingConstants.CENTER);
			text.setForeground(Color.decode("#805b38"));
			text.setFont(new Font("���� ���", Font.BOLD, 13));
			text.setCaretColor(Color.decode("#805b38")); // Ŀ�� ����
		} else if (cp instanceof JPasswordField) {
			pass = (JPasswordField) cp;
			pass.setOpaque(false); // ��� ����
			pass.setBorder(BorderFactory.createLineBorder(Color.decode("#805b38"))); // �׵θ�?
			pass.setHorizontalAlignment(SwingConstants.CENTER);
			pass.setForeground(Color.decode("#805b38"));
			pass.setFont(new Font("���� ���", Font.PLAIN, 17));
			text.setCaretColor(Color.decode("#805b38")); // Ŀ�� ����
		} else if (cp instanceof JComboBox<?>) {
			jcombo = (JComboBox<?>) cp;
			jcombo.setOpaque(false); // ��� ����
			jcombo.setBorder(BorderFactory.createLineBorder(Color.decode("#805b38"))); // �׵θ�?
			jcombo.setForeground(Color.decode("#805b38"));
			jcombo.setBackground(Color.decode("#fffcfa"));
			jcombo.setUI(new BasicComboBoxUI() {
				@Override
				protected ComboPopup createPopup() {
					BasicComboPopup basicComboPopup = new BasicComboPopup(comboBox);
					basicComboPopup.setBorder(new LineBorder(Color.decode("#805b38")));
					return basicComboPopup;
				}
			});
		} else if (cp instanceof JScrollPane) {
			jscroll = (JScrollPane) cp;
			jscroll.setOpaque(false); // ��� ����
			jscroll.getViewport().setBackground(Color.decode("#fffcfa"));
			jscroll.getVerticalScrollBar().setBackground(Color.decode("#805b38"));
			// jscroll.getHorizontalScrollBar().setBackground(Color.decode("#805b38"));
			jscroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
				@Override
				protected void configureScrollBarColors() {
					this.thumbColor = Color.decode("#fffcfa");
				}

				@Override
				protected JButton createIncreaseButton(int orientation) {
					JButton button = new BasicArrowButton(orientation);
					button.setBackground(Color.decode("#fffcfa"));
					button.setForeground(Color.decode("#805b38"));
					return button;
				}

				@Override
				protected JButton createDecreaseButton(int orientation) {
					JButton button = new BasicArrowButton(orientation);
					button.setBackground(Color.decode("#fffcfa"));
					button.setForeground(Color.decode("#805b38"));
					return button;
				}
				
			});

		} else if (cp instanceof JCheckBox) {
			jcheck = (JCheckBox) cp;
			jcheck.setForeground(Color.decode("#805b38"));
			jcheck.setOpaque(false);
			jcheck.setFont(new Font("���� ���", Font.BOLD, 13));
		} else if (cp instanceof JTable) {
			jtable = (JTable) cp;
			// �Ʒ� �ΰ��� ǥ ���θ� ����ȭ�� �� �ݵ�� �ʿ�
			jtable.setOpaque(false);
			((DefaultTableCellRenderer) jtable.getDefaultRenderer(Object.class)).setOpaque(false);
			jtable.setFont(new Font("���� ���", Font.PLAIN, 10));
			jtable.setForeground(Color.decode("#805b38"));
			jtable.setGridColor(Color.decode("#805b38")); // ���̺� ���� �� ��
			jtable.setBorder(BorderFactory.createLineBorder(Color.decode("#805b38")));
			jtable.getTableHeader().setBackground(Color.decode("#fffcfa"));
			jtable.getTableHeader().setForeground(Color.decode("#805b38"));
			jtable.setEnabled(false); // ����ڰ� ���̺��� Ŭ���ϰų� �����ϴ� ���� ����
		} else if (cp instanceof JRadioButton) {
			jradio = (JRadioButton) cp;
			jradio.setOpaque(false);
			jradio.setFont(new Font("���� ���", Font.BOLD, 13));
			jradio.setForeground(Color.decode("#805b38"));
		} else if (cp instanceof DefaultTableCellRenderer) {
			renderer = (DefaultTableCellRenderer) cp;
			renderer.setOpaque(false);
		}
	}

	// ������ ��Ÿ��
	public Style(JFrame cp) {
		frame = (JFrame) cp;

		try {
			image = ImageIO.read(new File("image\\�ΰ�.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.setIconImage(image);
		frame.getContentPane().setBackground(Color.decode("#fffcfa"));
	}

	// �ؽ�Ʈ ĭ����
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

	// ���ο� �г� �ޱ����� ������
	public Style() {
	}

	public static JPanel getnewPanel() {
		panel = new JPanel();
		panel.setOpaque(false); // ��� ����
		panel.setBackground(Color.decode("#fffcfa"));
		return panel;
	}

}