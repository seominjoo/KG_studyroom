package mainmenu;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

public class _09paymentLocker {

	private JFrame frame;
	private JTable table;
	LocalDateTime time_now = LocalDateTime.now();
	String time_checkout;
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h시 m분 s초");
 
	_09paymentLocker() {
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

		time_checkout = time_now.plusMonths(1).format(dateTimeFormatter);

		String header[] = {"결제","정보"};
		String contents[][]= {
				{"사물함 번호",_05selectLocker.number},
				{"사용 시작 시간",time_now.format(dateTimeFormatter)},
				{"사용 만료 시간",time_checkout}, 
				{"이용권","1달 이용권"},
				{"결제 금액","25,000"}
		};


		DefaultTableModel model = new DefaultTableModel(contents,header);
		table = new JTable(model);
		table.setBounds(135, 104, 437, 200);
		table.setRowHeight(40);
		p2.add(table);

		Color color = UIManager.getColor("Table.gridColor");
		MatteBorder border = new MatteBorder(1, 1, 0, 0, color);
		table.setBorder(border);
		p2.add(table);

		JRadioButton card_btn = new JRadioButton("카드");
		card_btn.setBounds(361, 332, 121, 23);
		p2.add(card_btn);

		JRadioButton cash_btn = new JRadioButton("현금");
		cash_btn.setBounds(200, 332, 121, 23);
		p2.add(cash_btn);

		ButtonGroup group = new ButtonGroup();
		group.add(cash_btn);
		group.add(card_btn);

		JButton back_btn = new JButton("이전 화면");
		back_btn.setBounds(200, 381, 121, 42);
		p2.add(back_btn);

		JButton pay_btn = new JButton("결제 하기");
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
			if(cash_btn.isSelected()) {// 현금결제
				new _12paycash_locker();

			}

			if(card_btn.isSelected()==true) {//카드 결제
				int result= JOptionPane.showConfirmDialog(null, "카드를 삽입하세요","Message",JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.CLOSED_OPTION) {

				}else if(result==JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog(null,"취소");//취소 메세지
				}else {
		for(int i=0;i<20;i++) {//(예약 완료 버튼 누를시)


			if( _05selectLocker.lockers.get(i).isSelected()&&(_05selectLocker.lockers.get(i).isEnabled()==true)) {
				_05selectLocker.lockers.get(i).setEnabled(false);
				String sql3 = "update locker set Locker_Statement ='사용 중' where Locker_Number= ?";
				pstmt = conn.prepareStatement(sql3);
				pstmt.setInt(1, i+1);
				int row3 = pstmt.executeUpdate();
							
				String sqlt2 = "update locker set l_time_enter =?,l_time_checkout=? where Locker_Number= ?";
				pstmt = conn.prepareStatement(sqlt2);
				pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
				pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(time_now.plusMonths(1)));
				pstmt.setInt(3, i+1);
				int rowt2 = pstmt.executeUpdate();

				//결제테이블에 저장
				String sql_pay = " insert into Payment_Record(Paid_Time,Exit_Time,Locker_Type,Pay_Method,Payment) values(?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql_pay);
				pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
				pstmt.setTimestamp(2, Time.localDateTimeTOTimeStamp(time_now.plusMonths(1)));
				pstmt.setString(3, "1달 이용권");
				pstmt.setString(4, "카드");
		    	pstmt.setInt(5,25000);
				int rowp = pstmt.executeUpdate();

				System.out.printf("%d번 사물함이 예약되었습니다.(%d행 업데이트)\n", i+1,row3);
				System.out.printf("입실/퇴실 시간이 업데이트되었습니다.(%d행 업데이트)\n",rowt2);
				System.out.printf("결제 기록이 업데이트되었습니다.(%d행 업데이트)\n",rowp);
						}
					}
				}
			}

			frame.setVisible(false);

			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (ClassNotFoundException | SQLException e1) { 
			e1.printStackTrace();
		} 
		}	});

		back_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new _05selectLocker();
			}
		}); 
	}
	public static void main(String[] args) {
		_09paymentLocker window = new _09paymentLocker();
		window.frame.setVisible(true);
	}
}
