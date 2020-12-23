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

	// 마이페이지 알림창 프레임
	public MyPage() {

		new Style(this);

		String header[] = { "타입", "번호", "만료기간" };
		String[][] contents = {
				{ "좌석", Mainmenu.seat_chk + "번",
					Time.TimeStampTOlocalDateTime(Mainmenu.time_seat).format(Mainmenu.datetime) + "까지" },
				{ "룸", Mainmenu.room_chk + "호",
						Time.TimeStampTOlocalDateTime(Mainmenu.time_room).format(Mainmenu.datetime) + "까지" },
				{ "사물함", Mainmenu.locker_chk + "번",
							Time.TimeStampTOlocalDateTime(Mainmenu.time_locker).format(Mainmenu.datetime) + "까지" },
				{ "이용권", Mainmenu.type.substring(0,2)} };

		for (String[] str : contents) {
			if (str[1].equals("0번")) {
				str[1] = "없음";
				str[2] = "";
			}else if (str[1].equals("0호")) {
				str[1] = "없음";
				str[2] = "";
			}
		}

		DefaultTableModel model = new DefaultTableModel(contents, header);
		table = new JTable(model);
		table.setBounds(30, 30, 380, 120);
		table.setRowHeight(30);

		// 가로 길이
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(250);
		table.getColumnModel().getColumn(1).setMaxWidth(250);

		// 디자인 적용
		new Style(table);
		table.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		table.setGridColor(Color.decode("#bf2ede9")); // 테이블 내부 선 색
		table.setBorder(BorderFactory.createLineBorder(Color.decode("#bf2ede9")));

		add(table);
		SwingToolsSubPage.initTestFrame(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(550, 300, 450, 220);
		setLocationRelativeTo(null);
	}
}