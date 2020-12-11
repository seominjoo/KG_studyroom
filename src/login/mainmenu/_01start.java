package login.mainmenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import login.design.Style;
import login.window.UserBtn_Action;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.Action;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Panel;

import javax.swing.ImageIcon;

public class _01start extends JPanel {

	JTable table;
	public static Connection conn;

//	private JPanel this;
	static Timestamp time_chk_seat;
	DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy년 M월 d일");
	DateTimeFormatter time = DateTimeFormatter.ofPattern("a h시 m분 ");

	public _01start() {

		new Style(this);
		setBounds(600, 150, 450, 400);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);

		// 메뉴 버튼 4개
		JButton seat_btn = new JButton("좌석 이용권");
		seat_btn.setBounds(5, 95, 208, 121);
		this.add(seat_btn);

		JButton locker_btn = new JButton("사물함 이용권");
		locker_btn.setBounds(218, 95, 213, 121);
		this.add(locker_btn);

		JButton room_btn = new JButton("룸 이용권");
		room_btn.setBounds(5, 221, 208, 126);
		this.add(room_btn);

		JButton back_btn = new JButton("이전 화면");
		back_btn.setBounds(218, 221, 213, 126);
		this.add(back_btn);

		// 스터디룸 상황표
		String header[] = { "1인석", "스터디룸", "사물함", "현재시간" };
		String contents[][] = { { "<html>사용중 1인석<br/>&emsp;&emsp;" + Integer.toString(_00main.count_seat) + " / 20",
				"<html>사용중 스터디룸<br/>&emsp;&emsp;&emsp;" + Integer.toString(_00main.count_room) + " / 4",
				"<html>사용중 사물함<br/>&emsp;&emsp;" + Integer.toString(_00main.count_locker) + " / 20",
				"<html>&emsp;&nbsp;&nbsp;&nbsp;현재시간<br/>" + LocalDate.now().format(date) + "<br/>&nbsp;&nbsp;&nbsp;"
						+ LocalTime.now().format(time) } };

		DefaultTableModel model = new DefaultTableModel(contents, header);
		table = new JTable(model);
		table.setBounds(0, 0, 437, 80);
		table.setRowHeight(80);

		// 테두리
		Color color = UIManager.getColor("Table.gridColor");
		MatteBorder border = new MatteBorder(1, 1, 0, 0, color);
		table.setBorder(border);

		// 상황표 글씨 중앙 정렬
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		table.getColumn("1인석").setCellRenderer(celAlignCenter);
		table.getColumn("스터디룸").setCellRenderer(celAlignCenter);
		table.getColumn("사물함").setCellRenderer(celAlignCenter);
		table.getColumn("현재시간").setCellRenderer(celAlignCenter);
		this.add(table);

		setVisible(true);

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");

			seat_btn.addActionListener(new UserBtn_Action(seat_btn)); // 좌석 이용권 페이지

			room_btn.addActionListener(new UserBtn_Action(room_btn)); // 룸 이용권 페이지

			locker_btn.addActionListener(new UserBtn_Action(locker_btn)); // 사물함 이용권 페이지

			back_btn.addActionListener(new UserBtn_Action(back_btn)); // 이전 페이지

		} catch (ClassNotFoundException | SQLException e1) {

			e1.printStackTrace();
		}

	}

}
