package login.mainmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import login.design.Style;
import login.page.MainPage;

public class _06move extends JPanel {

	static int num_seat;
	static int num_room;
	String type;
	String sql;
	PreparedStatement pstmt;
	ResultSet rs;
	static int chk=0;
	
	public _06move() {
		
		new Style(this);
		setLayout(null);
		
		JButton move_seat = new JButton("�¼� �̵��ϱ�");
		this.add(move_seat);
		new Style(move_seat);
		move_seat.setBounds(160,100,300,100);
		
		JButton move_room = new JButton("�� �̵��ϱ�");
		this.add(move_room);
		new Style(move_room);
		move_room.setBounds(160,220,300,100);

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XEPDB1",
					"hr",
					"1234"
					);
			
			move_seat.addActionListener(new ActionListener() {//1�μ�
				@Override
				public void actionPerformed(ActionEvent e) {
					chk=1;
					//ȸ���� ���� �¼���ȣ 
					sql = "SELECT seat_number,room_number FROM person_info "
							+ "WHERE login_state = 'On'";
					try {
						pstmt = conn.prepareStatement(sql);
						rs = pstmt.executeQuery();
						while(rs.next()) { 
							num_seat = rs.getInt("seat_number");
							num_room = rs.getInt("room_number");
						}if(num_seat==0) {
							String msg= "������ �¼��� �����ϴ�";
							JOptionPane.showMessageDialog(null,msg); 
						}else {
						System.out.println("�̵��� �¼�: "+num_seat+"��");
						MainPage.user_page_panel.add("�ڸ�������", new _06move_selectSeat());
							MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
							MainPage.user_cards.show(MainPage.user_page_panel, "�ڸ�������");
							MainPage.userToggle = "�ڸ�������";
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}	
				}
			});

			move_room.addActionListener(new ActionListener() {//��
				@Override
				public void actionPerformed(ActionEvent e) { 
					 chk=2;	
					//ȸ���� ���� ���ȣ 
					 sql = "SELECT seat_number,room_number FROM person_info "
								+ "WHERE login_state = 'On'";
					try {
						pstmt = conn.prepareStatement(sql);
						rs = pstmt.executeQuery();
						while(rs.next()) { 
							num_seat = rs.getInt("seat_number");
							num_room = rs.getInt("room_number");
						}
						if(num_room==0) {
							String msg= "������ ���� �����ϴ�";
							JOptionPane.showMessageDialog(null,msg); 
						}else {
						System.out.println("�̵��� ��: "+num_room+"ȣ");
						MainPage.user_page_panel.add("�ڸ�������", new _06move_selectSeat());
						MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
						MainPage.user_cards.show(MainPage.user_page_panel, "�ڸ�������");
						MainPage.userToggle = "�ڸ�������";
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});

		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}

		JButton back_btn = new JButton("���� ȭ��");
		this.add(back_btn);
		new Style(back_btn);
		back_btn.setBounds(160,340,300,100);
		back_btn.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "���θ޴�");
				MainPage.userToggle = "���θ޴�";
			}
		}); 
	}
}
