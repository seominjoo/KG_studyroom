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
	DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy�� M�� d��");
	DateTimeFormatter time = DateTimeFormatter.ofPattern("a h�� m�� ");

	public _01start() {

		new Style(this);
		setBounds(600, 150, 450, 400);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);

		// �޴� ��ư 4��
		JButton seat_btn = new JButton("�¼� �̿��");
		seat_btn.setBounds(5, 95, 208, 121);
		this.add(seat_btn);

		JButton locker_btn = new JButton("�繰�� �̿��");
		locker_btn.setBounds(218, 95, 213, 121);
		this.add(locker_btn);

		JButton room_btn = new JButton("�� �̿��");
		room_btn.setBounds(5, 221, 208, 126);
		this.add(room_btn);

		JButton back_btn = new JButton("���� ȭ��");
		back_btn.setBounds(218, 221, 213, 126);
		this.add(back_btn);

		// ���͵�� ��Ȳǥ
		String header[] = { "1�μ�", "���͵��", "�繰��", "����ð�" };
		String contents[][] = { { "<html>����� 1�μ�<br/>&emsp;&emsp;" + Integer.toString(_00main.count_seat) + " / 20",
				"<html>����� ���͵��<br/>&emsp;&emsp;&emsp;" + Integer.toString(_00main.count_room) + " / 4",
				"<html>����� �繰��<br/>&emsp;&emsp;" + Integer.toString(_00main.count_locker) + " / 20",
				"<html>&emsp;&nbsp;&nbsp;&nbsp;����ð�<br/>" + LocalDate.now().format(date) + "<br/>&nbsp;&nbsp;&nbsp;"
						+ LocalTime.now().format(time) } };

		DefaultTableModel model = new DefaultTableModel(contents, header);
		table = new JTable(model);
		table.setBounds(0, 0, 437, 80);
		table.setRowHeight(80);

		// �׵θ�
		Color color = UIManager.getColor("Table.gridColor");
		MatteBorder border = new MatteBorder(1, 1, 0, 0, color);
		table.setBorder(border);

		// ��Ȳǥ �۾� �߾� ����
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		table.getColumn("1�μ�").setCellRenderer(celAlignCenter);
		table.getColumn("���͵��").setCellRenderer(celAlignCenter);
		table.getColumn("�繰��").setCellRenderer(celAlignCenter);
		table.getColumn("����ð�").setCellRenderer(celAlignCenter);
		this.add(table);

		setVisible(true);

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");

			seat_btn.addActionListener(new UserBtn_Action(seat_btn)); // �¼� �̿�� ������

			room_btn.addActionListener(new UserBtn_Action(room_btn)); // �� �̿�� ������

			locker_btn.addActionListener(new UserBtn_Action(locker_btn)); // �繰�� �̿�� ������

			back_btn.addActionListener(new UserBtn_Action(back_btn)); // ���� ������

		} catch (ClassNotFoundException | SQLException e1) {

			e1.printStackTrace();
		}

	}

}
