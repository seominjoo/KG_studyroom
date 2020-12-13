package login.mainmenu;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import login.design.Style;

public class _11receipt extends JPanel {

	JTable table;
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy�� M�� d�� a h�� m�� s��");

	public _11receipt(LocalDateTime ss, int price) {
		setLayout(null);
		new Style(this);

		JLabel label01 = new JLabel("<html><pre>������</pre>");
		new Style(label01);
		label01.setBounds(183, 0, 440, 80);
		label01.setFont(new Font("Courier", Font.PLAIN, 35));

		// setSize(750,500);
		// setLocation(600,150);
		setVisible(true);

		JPanel p2 = new JPanel();
		new Style(p2);
		p2.setBounds(0, 0, 693, 453);
		add(p2);
		p2.setLayout(null);

		p2.add(label01);

		String header[] = { "����", "����" };
		String contents[][] = { { "��ȣ", _08reservation.number },
				{ "���� �ð�", _09payment.time_now.format(dateTimeFormatter) },
				{ "���(����) ���� �ð�", ss.format(dateTimeFormatter) }, { "�̿��", _08reservation.type11 },
				{ "���� �ݾ�", Integer.toString((_08reservation.price)) + "��" }, { "���� �ݾ�", Integer.toString(price) + "��" },
				{ "�Ž��� ��", Integer.toString(_10paycash.change) + "��" } };

		DefaultTableModel model = new DefaultTableModel(contents, header);

		table = new JTable(model);
		new Style(table);
		table.setBounds(40, 104, 390, 245);
		table.setRowHeight(35);
		
		table.setEnabled(false);

//		Color color = UIManager.getColor("Table.gridColor");
//		MatteBorder border = new MatteBorder(1, 1, 0, 0, color);
//		table.setBorder(border);
		p2.add(table);

	}

}
