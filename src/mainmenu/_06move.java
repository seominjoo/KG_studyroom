package mainmenu;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class _06move extends JFrame {

	private JPanel contentPane;
	static int num_seat;
	static int num_room;
	String type;
	String sql;
	PreparedStatement pstmt;
	ResultSet rs;

	public _06move() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 150, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0,3, 0, 0));
		
		JButton move_seat = new JButton("�¼� �̵��ϱ�");
		contentPane.add(move_seat);
		JButton move_room = new JButton("�� �̵��ϱ�");
		contentPane.add(move_room);

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
						  setVisible(false);
				           _06move_selectSeat frame = new _06move_selectSeat();
				           frame.setVisible(true);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}	
				}
			});

			move_room.addActionListener(new ActionListener() {//��
				@Override
				public void actionPerformed(ActionEvent e) { 

					//ȸ���� ���� ���ȣ 
					sql = "SELECT room_number FROM person_info "
							+ "WHERE login_state = 'On'";
					try {
						pstmt = conn.prepareStatement(sql);
						rs = pstmt.executeQuery();
						while(rs.next()) { 
							num_room = rs.getInt("room_number");
						}
						if(num_room==0) {
							String msg= "������ ���� �����ϴ�";
							JOptionPane.showMessageDialog(null,msg); 
						}else {
						System.out.println("�̵��� ��: "+num_seat+"ȣ");
						  setVisible(false);
				           _06move_selectSeat frame = new _06move_selectSeat();
				           frame.setVisible(true);
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
		contentPane.add(back_btn);
		back_btn.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				_00main frame = new _00main();
				frame.setVisible(true);
			}
		}); 
	}

	public static void main(String[] args) {
		_06move frame = new _06move();
		frame.setVisible(true);
	}
}
