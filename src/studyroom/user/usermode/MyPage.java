package studyroom.user.usermode;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import studyroom.MainPage;
import studyroom.design.Style;
import studyroom.swingTools.SwingToolsSubPage;

public class MyPage extends JFrame {

	JTable table;
	static BufferedImage image;

	// ���������� �˸�â ������
	public MyPage() {

		new Style(this);

		String header[] = { "Ÿ��", "��ȣ", "����Ⱓ" };
		String[][] contents = {
				{ "�¼�", Mainmenu.seat_chk + "��",
					Time.TimeStampTOlocalDateTime(Mainmenu.time_seat).format(Mainmenu.datetime) + "����" },
				{ "��", Mainmenu.room_chk + "ȣ",
						Time.TimeStampTOlocalDateTime(Mainmenu.time_room).format(Mainmenu.datetime) + "����" },
				{ "�繰��", Mainmenu.locker_chk + "��",
							Time.TimeStampTOlocalDateTime(Mainmenu.time_locker).format(Mainmenu.datetime) + "����" },
				{ "�̿��", Mainmenu.type.substring(0,2)} };

		for (String[] str : contents) {
			if (str[1].equals("0��")) {
				str[1] = "����";
				str[2] = "";
			}else if (str[1].equals("0ȣ")) {
				str[1] = "����";
				str[2] = "";
			}
		}

		DefaultTableModel model = new DefaultTableModel(contents, header);
		table = new JTable(model);
		table.setBounds(30, 30, 380, 120);
		table.setRowHeight(30);

		// ���� ����
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(250);
		table.getColumnModel().getColumn(1).setMaxWidth(250);

		// ������ ����
		new Style(table);
		table.setFont(new Font("���� ���", Font.BOLD, 13));
		table.setGridColor(Color.decode("#bf2ede9")); // ���̺� ���� �� ��
		table.setBorder(BorderFactory.createLineBorder(Color.decode("#bf2ede9")));

		add(table);
		SwingToolsSubPage.initTestFrame(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(550, 300, 450, 220);
		setLocationRelativeTo(null);
	}
}