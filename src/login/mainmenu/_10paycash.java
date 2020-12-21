package login.mainmenu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import login.design.Style;
import login.page.MainPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class _10paycash extends JFrame {

	public static int change;
	LocalDateTime time_now = LocalDateTime.now();

	public _10paycash(LocalDateTime ss) {

		JFrame frame = this;
		new Style(this);
		setLayout(null);
		setBounds(400, 200, 380, 250);
		setVisible(true);
		
		String price = Integer.toString((_08reservation.price11));
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 420, 322);
		new Style(panel);
		add(panel);
		panel.setLayout(null);

		JLabel payment = new JLabel("���� �ݾ�  :  " + price + "��");
		new Style(payment);
		payment.setBounds(73, 23, 236, 39);
		panel.add(payment);

		JLabel deposit = new JLabel("�Ա� �ݾ�  :  ");
		new Style(deposit);
		deposit.setBounds(73, 73, 120, 39);
		panel.add(deposit);

		JTextField cash = new JTextField();
		new Style(cash);
		cash.setBounds(165, 73, 120, 40);
		panel.add(cash);
		cash.setColumns(10);

		JButton back;
		back = new JButton("���");
		ActionListener back_btn = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "����������");
				MainPage.userToggle = "����������";
				frame.setVisible(false);
			}
		};
		  this.add(back);
	      new Style(back);
	      back.setBounds(73, 135, 90, 40);
	      back.addActionListener(back_btn);

	      JButton pay_btn = new JButton("����");
	      new Style(pay_btn);
	      pay_btn.setBounds(198, 135, 90, 40);
	      this.add(pay_btn);

		pay_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Integer.parseInt(price) <= Integer.parseInt(cash.getText())) {

				change = Integer.parseInt(cash.getText()) - Integer.parseInt(price);
				String msg = "���� �Ϸ�";
				JOptionPane.showMessageDialog(null, msg);

				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");
					PreparedStatement pstmt = null;

				for (int i = 0; i < 20; i++) {
					if (_08reservation.seats_btn.get(i).isSelected() && (_08reservation.seats_btn.get(i).isEnabled() == true)) {// �̹� ������ִ� ��(��Ȱ��ȭ) ���� üũ
						_08reservation.seats_btn.get(i).setEnabled(false);

						String sql = "update seat set Seat_Statement ='��� ��' where Seat_Number= ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, i + 1);
						int row = pstmt.executeUpdate();

						// �Խ�/��ǽð� ����
						String sqlt1 = "update seat set time_enter =?,time_checkout=? where Seat_Number= ?";
						pstmt = conn.prepareStatement(sqlt1);
						pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
						pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(_08reservation.time11));
						pstmt.setInt(3, i + 1);
						int rowt1 = pstmt.executeUpdate();

						// �������̺� ����
						String sql_pay = " insert into Payment_Record(Paid_Time,Exit_Time,person_id,Seat_Type,Pay_Method,Payment) values(?,?,?,?,?,?)";
						pstmt = conn.prepareStatement(sql_pay);
						pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
						pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(_08reservation.time11));
						pstmt.setInt(3, _00main.id);
						pstmt.setString(4, _08reservation.type11);
						pstmt.setString(5, "����");
						pstmt.setInt(6, _08reservation.price11);
						int rowp = pstmt.executeUpdate();

						// ȸ��info ���̺� ����(�¼���ȣ,�繰�Թ�ȣ,�Խ�)
						sql = "update person_info set seat_number=?,Expiration_seat=?,seat_type=? where person_id=?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, i + 1);
						pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(ss));
						if (_08reservation.price >= 90000) {
							pstmt.setString(3, "���� �̿��");
						} else {
							pstmt.setString(3, "���� �̿��");
						}
							pstmt.setInt(4, _00main.id);

						int row3 = pstmt.executeUpdate();

						System.out.printf("%d�� �ڸ��� ����Ǿ����ϴ�.(%d�� ������Ʈ)\n", i + 1, row);
						System.out.printf("�Խ�/��� �ð��� ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n", rowt1);
						System.out.printf("���� ����� ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n", rowp);
						System.out.printf("ȸ�� ������ ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n", row3);
						}
					}
				
				for (int i = 0; i < 4; i++) {
					if (_08reservation.room_btn.get(i).isSelected() && (_08reservation.room_btn.get(i).isEnabled() == true)) {
						_08reservation.room_btn.get(i).setEnabled(false);
								
						String sql2 = "update seat set Seat_Statement ='��� ��' where Seat_Number= ?";
						pstmt = conn.prepareStatement(sql2);
						pstmt.setInt(1, i + 101);
						int row2 = pstmt.executeUpdate();

						String sqlt3 = "update seat set time_enter =?,time_checkout=? where Seat_Number= ?";
						pstmt = conn.prepareStatement(sqlt3);
						pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
						pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(_08reservation.time11));
						pstmt.setInt(3, i + 101);
						int rowt3 = pstmt.executeUpdate();

						String sql_pay = " insert into Payment_Record(Paid_Time,Exit_Time,person_id,Seat_Type,Pay_Method,Payment) values(?,?,?,?,?,?)";
						pstmt = conn.prepareStatement(sql_pay);
						pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
						pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(_08reservation.time11));
						pstmt.setInt(3, _00main.id);
						pstmt.setString(4, _08reservation.type11);
						pstmt.setString(5, "����");
						pstmt.setInt(6, _08reservation.price11);
						int rowp = pstmt.executeUpdate();

						//ȸ��info ���̺� ����(�¼���ȣ,�繰�Թ�ȣ,�Խ�)
						sql2 = "update person_info set room_number=? where person_id=?";
						pstmt = conn.prepareStatement(sql2);
						pstmt.setInt(1, i + 101);
						pstmt.setInt(2, _00main.id);
						int row5 = pstmt.executeUpdate();

						System.out.printf("%dȣ ���� ����Ǿ����ϴ�.(%d�� ������Ʈ)\n", i + 101, row2);
						System.out.printf("�Խ�/��� �ð��� ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n", rowt3);
						System.out.printf("���� ����� ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n", rowp);
						System.out.printf("ȸ�������� ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n", row5);

						}
					}
				for (int i = 0; i < 20; i++) {

					if (_08reservation.locker_btn.get(i).isSelected() && (_08reservation.locker_btn.get(i).isEnabled() == true)) {
						_08reservation.locker_btn.get(i).setEnabled(false);
						
						String sql3 = "update locker set Locker_Statement ='��� ��' where Locker_Number= ?";
						pstmt = conn.prepareStatement(sql3);
						pstmt.setInt(1, i + 1);
						int row3 = pstmt.executeUpdate();

						String sqlt2 = "update locker set l_time_enter =?,l_time_checkout=? where Locker_Number= ?";
						pstmt = conn.prepareStatement(sqlt2);
						pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
						pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(time_now.plusMonths(1)));
						pstmt.setInt(3, i + 1);
						int rowt2 = pstmt.executeUpdate();

						// �������̺� ����
						String sql_pay = " insert into Payment_Record(Paid_Time,Exit_Time,person_id,Locker_Type,Pay_Method,Payment) values(?,?,?,?,?,?)";
						pstmt = conn.prepareStatement(sql_pay);
						pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
						pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(time_now.plusMonths(1)));
						pstmt.setInt(3, _00main.id);
						pstmt.setString(4, _08reservation.type11);
						pstmt.setString(5, "����");
						pstmt.setInt(6, _08reservation.price11);
						int rowp = pstmt.executeUpdate();

						// ȸ��info ���̺� ����(�¼���ȣ,�繰�Թ�ȣ,�Խ�)
						sql3 = "update person_info set locker_number=? where person_id=?";
						pstmt = conn.prepareStatement(sql3);
						pstmt.setInt(1, i + 1);
						pstmt.setInt(2, _00main.id);
						int row1 = pstmt.executeUpdate();

						System.out.printf("%d�� �繰���� ����Ǿ����ϴ�.(%d�� ������Ʈ)\n", i + 1, row3);
						System.out.printf("�Խ�/��� �ð��� ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n", rowt2);
						System.out.printf("���� ����� ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n", rowp);
						System.out.printf("ȸ�� ������ ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n", row1);

						}
					}

						MainPage.user_page_panel.add("������", new _11receipt(ss, Integer.parseInt(cash.getText())));
						MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
						MainPage.user_cards.show(MainPage.user_page_panel, "������");
						MainPage.userToggle = "������";

						if (pstmt != null) pstmt.close();
						if (conn != null) conn.close();
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}

				} else {
					int change2 = Integer.parseInt(price) - Integer.parseInt(cash.getText());
					String msg2 = Integer.toString(change2) + "���� �����մϴ�";
					JOptionPane.showMessageDialog(null, msg2);
				}
				frame.setVisible(false);
			}
		});
	}
}