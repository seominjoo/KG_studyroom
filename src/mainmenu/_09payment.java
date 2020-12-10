package mainmenu;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;

public class _09payment {

	private JFrame frame;
	private JTable table;
	static LocalDateTime time_now = LocalDateTime.now();
	String time_checkout;
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy�� M�� d�� a h�� m�� s��");
  
	_09payment(LocalDateTime ss,int seat_price, String seat_type) {
		frame = new JFrame();
		frame.setSize(750,500);
		frame.setLocation(600,150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		frame.setVisible(true);

		JPanel p2 = new JPanel();
		p2.setBounds(12, 10, 706, 453);
		frame.getContentPane().add(p2);
		p2.setLayout(null);


		 
		String header[] = {"����","����"};
		String contents[][]= {
				{"��ȣ",_08reservation.number},
				{"���� �ð�",time_now.format(dateTimeFormatter)},
				{"��� ���� �ð�",ss.format(dateTimeFormatter)}, 
				{"�̿��",_08reservation.type11},
				{"���� �ݾ�",Integer.toString((_08reservation.price))}
		};


		DefaultTableModel model = new DefaultTableModel(contents,header);
		table = new JTable(model);
		table.setBounds(135, 104, 437, 200);
		table.setRowHeight(40);

		Color color = UIManager.getColor("Table.gridColor");
		MatteBorder border = new MatteBorder(1, 1, 0, 0, color);
		table.setBorder(border);
		p2.add(table);

		JRadioButton card_btn = new JRadioButton("ī��");
		card_btn.setBounds(361, 332, 121, 23);
		p2.add(card_btn);

		JRadioButton cash_btn = new JRadioButton("����");
		cash_btn.setBounds(200, 332, 121, 23);
		p2.add(cash_btn);

		ButtonGroup group = new ButtonGroup();
		group.add(cash_btn);
		group.add(card_btn);


		JButton back_btn = new JButton("���ư���");
		back_btn.setBounds(200, 381, 121, 42);
		p2.add(back_btn);

		JButton pay_btn = new JButton("�����ϱ�");
		pay_btn.setBounds(361, 381, 121, 42);
		p2.add(pay_btn);

		pay_btn.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) { 
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection(
							"jdbc:oracle:thin:@localhost:1521/XEPDB1",
							"hr",
							"1234"
							);
					PreparedStatement pstmt = null;

			if(cash_btn.isSelected()) {// ���ݰ���
				new _10paycash(ss);

			}


	if(card_btn.isSelected()==true) {//ī�� ����
		int result= JOptionPane.showConfirmDialog(null, "ī�带 �����ϼ���","Message",JOptionPane.YES_NO_OPTION);
		if(result==JOptionPane.CLOSED_OPTION) {

		}else if(result==JOptionPane.NO_OPTION) {
			JOptionPane.showMessageDialog(null,"���");//��� �޼���
		}else {

		for(int i=0;i<20;i++) {//(ī�� ���� ��ư ������)
			if( _08reservation.seats.get(i).isSelected()&&(_08reservation.seats.get(i).isEnabled()==true)) {//�̹� ������ִ� ��(��Ȱ��ȭ) ���� üũ
				_08reservation.seats.get(i).setEnabled(false);
									
				//��������� db����
				String sql = "update seat set Seat_Statement ='��� ��' where Seat_Number= ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, i+1);
				int row = pstmt.executeUpdate();

				//�Խ�/��ǽð� ����
				String sqlt1 = "update seat set time_enter =?,time_checkout=? where Seat_Number= ?";
				pstmt = conn.prepareStatement(sqlt1);
				pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
				pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(ss));
				pstmt.setInt(3, i+1);
				int rowt1 = pstmt.executeUpdate();

				//�������̺� ����
				String sql_pay = " insert into Payment_Record(Paid_Time,Exit_Time,Seat_Type,Pay_Method,Payment) values(?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql_pay);
				pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
				pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(ss));
			    pstmt.setString(3, _08reservation.type11);
				pstmt.setString(4, "ī��");
				pstmt.setInt(5,_08reservation.price);
				int rowp = pstmt.executeUpdate(); 
				
				//ȸ��info ���̺� ����(�¼���ȣ,�繰�Թ�ȣ,�Խ�)
				sql = "update person_info set seat_number=?,Expiration_seat=? where person_id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, i+1);
				pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(ss));
				pstmt.setInt(3, _00main.id);
				
				int row3 = pstmt.executeUpdate();
				
				
				
				System.out.printf("%d�� �ڸ��� ����Ǿ����ϴ�.(%d�� ������Ʈ)\n", i+1,row);
				System.out.printf("�Խ�/��� �ð��� ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n",rowt1);
				System.out.printf("���� ����� ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n",rowp);
				System.out.printf("ȸ�� ������ ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n",row3);
			}

		} 
		
		for(int i=0;i<4;i++) {
			if(_08reservation.room.get(i).isSelected()&&(_08reservation.room.get(i).isEnabled()==true)) {
				_08reservation.room.get(i).setEnabled(false);
			String sql2 = "update seat set Seat_Statement ='��� ��' where Seat_Number= ?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, i+101);
			int row2 = pstmt.executeUpdate();

	     	String sqlt3 = "update seat set time_enter =?,time_checkout=? where Seat_Number= ?";
			pstmt = conn.prepareStatement(sqlt3);
			pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
			pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(ss));
			pstmt.setInt(3, i+101);
			int rowt3 = pstmt.executeUpdate();
									
			//�������̺� ����
			String sql_pay = " insert into Payment_Record(Paid_Time,Exit_Time,Seat_Type,Pay_Method,Payment) values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql_pay);
			pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
			pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(ss));
			pstmt.setString(3, _08reservation.type11);
			pstmt.setString(4, "ī��");
			pstmt.setInt(5,_08reservation.price);
			int rowp = pstmt.executeUpdate();
									
//			ȸ��info ���̺� ����(�¼���ȣ,�繰�Թ�ȣ,�Խ�)
			sql2 = "update person_info set room_number=?,Expiration_room=? where person_id=?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, i+101);
			pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(ss));
			pstmt.setInt(3, _00main.id);
			int row5 = pstmt.executeUpdate();
			
			System.out.printf("%dȣ ���� ����Ǿ����ϴ�.(%d�� ������Ʈ)\n", i+101,row2);
			System.out.printf("�Խ�/��� �ð��� ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n",rowt3); 
			System.out.printf("���� ����� ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n",rowp);
			System.out.printf("ȸ�������� ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n",row5);
		}	
	}
		for(int i=0;i<20;i++) {//(���� �Ϸ� ��ư ������)


			if( _08reservation.lockers.get(i).isSelected()&&(_08reservation.lockers.get(i).isEnabled()==true)) {
				_08reservation.lockers.get(i).setEnabled(false);
				String sql3 = "update locker set Locker_Statement ='��� ��' where Locker_Number= ?";
				pstmt = conn.prepareStatement(sql3);
				pstmt.setInt(1, i+1);
				int row3 = pstmt.executeUpdate();
							
				String sqlt2 = "update locker set l_time_enter =?,l_time_checkout=? where Locker_Number= ?";
				pstmt = conn.prepareStatement(sqlt2);
				pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
				pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(time_now.plusMonths(1)));
				pstmt.setInt(3, i+1);
				int rowt2 = pstmt.executeUpdate();

				//�������̺� ����
				String sql_pay = " insert into Payment_Record(Paid_Time,Exit_Time,Locker_Type,Pay_Method,Payment) values(?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql_pay);
				pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
				pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(time_now.plusMonths(1)));
				pstmt.setString(3, "1�� �̿��");
				pstmt.setString(4, "ī��");
		    	pstmt.setInt(5,25000);
				int rowp = pstmt.executeUpdate();
				
				//ȸ��info ���̺� ����(�¼���ȣ,�繰�Թ�ȣ,�Խ�)
				sql3 = "update person_info set locker_number=?,Expiration_locker=? where person_id=?";
				pstmt = conn.prepareStatement(sql3);
				pstmt.setInt(1, i+1);
				pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(time_now.plusMonths(1)));
				pstmt.setInt(3, _00main.id);
				int row1 = pstmt.executeUpdate();

				System.out.printf("%d�� �繰���� ����Ǿ����ϴ�.(%d�� ������Ʈ)\n", i+1,row3);
				System.out.printf("�Խ�/��� �ð��� ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n",rowt2);
				System.out.printf("���� ����� ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n",rowp);
 				System.out.printf("ȸ�� ������ ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n",row1);
				
				 
						}
					}
			
			JOptionPane.showMessageDialog(null,"���� �Ϸ�"); 
			frame.setVisible(false);
			new _11receipt(ss,_08reservation.price);

			}
		}
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (ClassNotFoundException | SQLException e1) { 
			e1.printStackTrace();
				} 

			}
		}); 
		
		back_btn.addActionListener(new ActionListener() { 
	@Override
	public void actionPerformed(ActionEvent e) {
		frame.setVisible(false);
		_08reservation.number="";
		for(int i=0;i<20;i++) {
			_08reservation.seats.get(i).setSelected(false);
		} 
		for(int i=0;i<4;i++) {
			_08reservation.room.get(i).setSelected(false);
		}
		for(int i=0;i<20;i++) {
			_08reservation.lockers.get(i).setSelected(false);
		} 
		new _08reservation(_08reservation.time11,
				_08reservation.price11,
				_08reservation.type11);
		}
	
	
		}); 
	} 
	public static void main(String[] args) {
		_09payment window = new _09payment(_08reservation.time11,_08reservation.price11,_08reservation.type11);
		window.frame.setVisible(true);
	}
 
}
