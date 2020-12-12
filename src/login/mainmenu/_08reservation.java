package login.mainmenu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import login.design.Style;


public class _08reservation extends JPanel implements ActionListener{
	 static LocalDateTime time11;
	 static int price11; 
	 static int price; 
	 static int price_r; 
	 static String type11;
	 
	static boolean selected = false;
	static ArrayList<JCheckBox> seats = new ArrayList<>(); //1~20�� �¼� (1�μ�)
	{
		for(int i=1;i<=20;i++) {
			seats.add(new JCheckBox(i+"",selected));
		}
	}
	static ArrayList<JCheckBox> room = new ArrayList<>(); //101~104ȣ (��)
	{
		for(int i=101;i<=104;i++) {
			room.add(new JCheckBox(i+"ȣ",selected));
		}
	}
	static ArrayList<JCheckBox> lockers = new ArrayList<>(); //1~20�� �繰��
	{
		for(int i=1;i<=20;i++) {
			lockers.add(new JCheckBox(i+"",selected));
		}
	}

	int a=0;
	int b=0;
	int c=0;
	int d=0;
	int e=0; 
	public static String number="";
	JLabel label_msg;
	static LocalDateTime time_now = LocalDateTime.now();
	String time_checkout;
	 
	
	public _08reservation(LocalDateTime ss,int price,String seat_type) {
		 
		new Style(this);
		JButton OK;
		JButton back;
		JLabel label = new JLabel("1�μ�");
		JLabel label02 = new JLabel("��");
		label_msg = new JLabel("");
		label.setBounds(10,10,50,30);
		label.setFont(new Font("���� ���", Font.BOLD, 15));
		this.add(label);
		label02.setBounds(10,100,50,30);
		label02.setFont(new Font("���� ���", Font.BOLD, 15));
	 
		label_msg.setBounds(200,310,500,30);
		label_msg.setFont(new Font("���� ���", Font.BOLD, 15));
		this.add(label_msg); 
		this.add(label02);
		
		JLabel label03 = new JLabel("�繰��");
		label_msg = new JLabel("");
		label03.setBounds(10,175,50,30);
		label03.setFont(new Font("���� ���", Font.BOLD, 15));
		this.add(label03);
		label_msg.setBounds(200,310,500,30);
		label_msg.setFont(new Font("���� ���", Font.BOLD, 15));
		this.add(label_msg); 
		
		for(int i=0; i<10;i++) { // 1�μ� üũ�ڽ� ��ġ ����
			seats.get(i).setBounds(20+a,40,40,30);
			this.add(seats.get(i));
			a+=40;
			if(!(price>=3000&&price<=10000||price>=90000)) {
				seats.get(i).setEnabled(false);
			}
		}
		for(int i=10; i<20;i++) {// 1�μ� üũ�ڽ� ��ġ ����
			seats.get(i).setBounds(20+b,70,40,30);
			this.add(seats.get(i));
			b+=40;
			if(!(price>=3000&&price<=10000||price>=90000)) {
				seats.get(i).setEnabled(false);
			}
		}
		 
		for(int i=0; i<4;i++) {// �� üũ�ڽ� ��ġ ����
			room.get(i).setBounds(20+e,140,100,30);
			this.add(room.get(i));
			e+=100;
			if(!(price>=12000&&price<=40000&&price!=25000))
				room.get(i).setEnabled(false); 
		}
		for(int i=0; i<10;i++) {// �繰�� üũ�ڽ� ��ġ ����
			lockers.get(i).setBounds(20+c,200,40,30);
			this.add(lockers.get(i));
			c+=40;
			if(price!=25000) {
				lockers.get(i).setEnabled(false);
			}
		}
		for(int i=10; i<20;i++) {// �繰�� üũ�ڽ� ��ġ ����
			lockers.get(i).setBounds(60+d,230,40,30);
			this.add(lockers.get(i));
			d+=40;
			if(price!=25000) {
				lockers.get(i).setEnabled(false);
			}
		}
		
		

		ActionListener back_btn = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				for(int i=0;i<20;i++) {
					seats.get(i).setSelected(false);
				} 
				for(int i=0;i<4;i++) {
					room.get(i).setSelected(false);
				}
				for(int i=0;i<20;i++) {
					lockers.get(i).setSelected(false);
				} 
				_01start frame = new _01start();
				frame.setVisible(true);
				 number="";
			}
		};
		
		back = new JButton("���� ȭ��");
		back.setBounds(180,380,100,50);
		this.add(back);
		back.addActionListener(back_btn);
	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XEPDB1",
					"hr",
					"1234"
					);
//			//�ð� ��
//			String sql = "SELECT seat_number, time_checkout FROM seat "
//					+ "WHERE seat_statement ='��� ��'";
//			PreparedStatement pstm = conn.prepareStatement(sql);
//			ResultSet rs = pstm.executeQuery();
//		 
//			while(rs.next()) {//��� �ð� ���� �¼� ���ó��
//				int seat_chk = rs.getInt("seat_number");
//				Timestamp time_chk = rs.getTimestamp("time_checkout");
//				if(time_now.isAfter(Time.TimeStampTOlocalDateTime(time_chk))) {
//					String change = "update seat set Seat_Statement ='��� ����',time_enter=null,time_checkout=null where Seat_Number= ?";
//					PreparedStatement pstmtas = conn.prepareStatement(change);
//					pstmtas.setInt(1, seat_chk);
//					int row3 = pstmtas.executeUpdate();
//				}  
//			} 
			
			//������� �¼��̸� üũ�ڽ� üũ �� ��Ȱ��ȭ 
			String sql = "select seat_number from seat where seat_statement='��� ��'";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery(); 
			System.out.print("����� �ڸ� : ");

			while(rs.next()) { 
				int sn = rs.getInt("seat_number"); 
				if(sn<=20) {
					System.out.printf("%d�� ",sn); 
					seats.get(sn-1).setSelected(true);
					seats.get(sn-1).setEnabled(false);
				}else if (sn>=101) {
					System.out.printf("[%dȣ] ",sn); 
					room.get(sn-101).setSelected(true);
					room.get(sn-101).setEnabled(false);
				}
			}
			
//			// ������� �繰�� �� �̿�Ⱓ�� ������ ��밡������ ������Ʈ
//						sql = "SELECT Locker_Number,l_time_checkout FROM locker "
//								+ "WHERE Locker_Statement='��� ��'";
//						 pstm = conn.prepareStatement(sql);
//						 rs = pstm.executeQuery();
//					 
//						while(rs.next()) { 
//							int locker_chk = rs.getInt("Locker_Number");
//							Timestamp l_time_chk = rs.getTimestamp("l_time_checkout");
//							if(time_now.isAfter(Time.TimeStampTOlocalDateTime(l_time_chk))) {
//								String change = "update locker set Locker_Statement ='��� ����',l_time_enter=null,l_time_checkout=null where Locker_Number= ?";
//								PreparedStatement pstm2 = conn.prepareStatement(change);
//								pstm2.setInt(1, locker_chk);
//								int row4 = pstm2.executeUpdate();
//							}  
//						}
					 
							
						// ������� �繰���̸� üũ�ڽ� üũ �� ��Ȱ��ȭ 
						sql = "select locker_number from locker where locker_statement='��� ��'";
						pstm = conn.prepareStatement(sql);
						rs = pstm.executeQuery();
						System.out.println();
						System.out.printf("����� �繰�� : ");
						while(rs.next()) {
							int sn = rs.getInt("locker_number");
							System.out.printf("%d�� ",sn);
							lockers.get(sn-1).setSelected(true);
							lockers.get(sn-1).setEnabled(false); 
							 
						}
			
			if(rs!=null) rs.close(); 
			if (pstm != null) pstm.close();
			if (conn != null) conn.close();
		} catch (ClassNotFoundException | SQLException e1) { 
			e1.printStackTrace();
		}
		
		OK = new JButton("�����ϱ�");
		OK.setBounds(290,380,100,50);
		this.add(OK);
		OK.addActionListener(this);

		setSize(600,500);
		setLocation(600,150);
		
		this.setBounds(0, 100, 600, 500);
		this.setLayout(null);
		time11  = ss;
		price11 = price; 
		type11 = seat_type;
	}

	@Override
	public void actionPerformed(ActionEvent e) { 
		int count_only=0;
		int count_only2=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XEPDB1",
					"hr",
					"1234"
					);
			PreparedStatement pstmt = null;

			String msg="";
			 
			price=0;
			if((price11>=3000&&price11<=10000||price11>=90000)) {
			for(int i=0;i<=19;i++) {//�ڸ� üũ(��Ȱ��ȭ ���ִ°� ����)
				if(seats.get(i).isSelected()&&(seats.get(i).isEnabled()==true)) { 
					msg=i+1+"�� (1�μ�) �ڸ�\n"; 
					number+=i+1+"�� �¼� ";
					 
					price+=price11;
					count_only++;
				}
			}
		}
			if(price11>=12000&&price11<=40000&&price11!=25000) {
			for(int i=0;i<=3;i++){
				if(room.get(i).isSelected()&&(room.get(i).isEnabled()==true)) {
					msg=i+101+"ȣ ��\n"; 
					number+=i+101+" ȣ �� ";
					 
					price+=price11;
					count_only++;
					 
				}
			}
		}
			if(price11==25000) {
			for(int i=0;i<=19;i++) {
				if(lockers.get(i).isSelected()&&(lockers.get(i).isEnabled()==true)) {
					msg=i+1+"�� �繰��\n"; 
					number+=i+1+"�� �繰�� ";
					price+=price11;
					count_only++;
				} 
			}
		}
			 
			msg+="�����Ͻðڽ��ϱ�?";
			if(count_only==0) {
				msg="������ �ڸ��� �������ּ���";
				JOptionPane.showMessageDialog(this,msg);//������ ������ �ٽü��� �޼��� â ����(�޼��� ���̷� üũ)
			}else if(count_only>1) {
				String warning="   1�� 1���� ����";
				JOptionPane.showMessageDialog(this,warning);
				number="";
			}else {
				// (â���� or �� or ���)��ư 
			
				int result= JOptionPane.showConfirmDialog(null, msg,"Message",JOptionPane.YES_NO_OPTION);
				if(result ==JOptionPane.CLOSED_OPTION) { 
					//(�� Ȯ�� â ����) 
				}else if (result ==JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog(this,"���");//��� �޼���
				}else {   
					setVisible(false);
					// yes��ư -> ���� ������
					 
					new _09payment(time11,price,type11);
					 
				}
			} 
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		} 
	} 
	
	
	 
	public static void main(String[] args) {
		 
	} 

}

