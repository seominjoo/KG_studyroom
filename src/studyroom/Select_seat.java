package studyroom;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Select_seat extends JFrame implements ActionListener{
	 
	
	boolean selected = false;
	static ArrayList<JCheckBox> seats = new ArrayList<>(); //1~20�� �¼� (1�μ�)
	{
		for(int i=1;i<=20;i++) {
			seats.add(new JCheckBox(i+"",selected));
		}
	}
	static ArrayList<JCheckBox> lockers = new ArrayList<>(); //1~20�� �繰��
	{
		for(int i=1;i<=20;i++) {
			lockers.add(new JCheckBox(i+"",selected));
		}
	}
	static ArrayList<JCheckBox> room = new ArrayList<>(); //101~104ȣ (��)
	{
		for(int i=101;i<=104;i++) {
			room.add(new JCheckBox(i+"ȣ",selected));
		}
	}
	int a=0;
	int b=0;
	int c=0;
	int d=0;
	int e=0; 
	static String number="";
	JLabel label_msg;
	LocalDateTime time_now = LocalDateTime.now();
	String time_checkout;
//	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy�� M�� d�� a h�� m�� s��");
	
	
	 
	
	JPanel p1 = new JPanel();
	Select_seat() {
  
		
		
		JButton OK;
		JLabel label = new JLabel("1�μ�");
		JLabel label02 = new JLabel("��");
		JLabel label03 = new JLabel("�繰��");
		label_msg = new JLabel("");
		label.setBounds(10,10,50,30);
		label.setFont(new Font("���� ���", Font.BOLD, 15));
		p1.add(label);
		label02.setBounds(10,100,50,30);
		label02.setFont(new Font("���� ���", Font.BOLD, 15));
		p1.add(label02);
		label03.setBounds(10,185,50,30);
		label03.setFont(new Font("���� ���", Font.BOLD, 15));
		p1.add(label03);
		label_msg.setBounds(200,310,500,30);
		label_msg.setFont(new Font("���� ���", Font.BOLD, 15));
		p1.add(label_msg); 

		for(int i=0; i<10;i++) { // 1�μ� üũ�ڽ� ��ġ ����
			seats.get(i).setBounds(20+a,40,40,30);
			p1.add(seats.get(i));
			a+=40;
		}
		for(int i=10; i<20;i++) {// 1�μ� üũ�ڽ� ��ġ ����
			seats.get(i).setBounds(20+b,70,40,30);
			p1.add(seats.get(i));
			b+=40;
		}
		for(int i=0; i<10;i++) {// �繰�� üũ�ڽ� ��ġ ����
			lockers.get(i).setBounds(20+c,220,40,30);
			p1.add(lockers.get(i));
			c+=40;
		}
		for(int i=10; i<20;i++) {// �繰�� üũ�ڽ� ��ġ ����
			lockers.get(i).setBounds(20+d,250,40,30);
			p1.add(lockers.get(i));
			d+=40;
		}
		for(int i=0; i<4;i++) {// �� üũ�ڽ� ��ġ ����
			room.get(i).setBounds(20+e,140,100,30);
			p1.add(room.get(i));
			e+=100;
		} 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XEPDB1",
					"hr",
					"1234"
					);
			 
			//�ð� ��
			String sqlmt1 = "select seat_number,time_checkout from seat where seat_statement='��� ��'";
			PreparedStatement pstmtt1 = 
					conn.prepareStatement(sqlmt1);
			ResultSet rst1 = pstmtt1.executeQuery();
		 
			while(rst1.next()) {//������� �¼��� ��ǽð��� ������ ��밡������ ���� 
				int seat_chk = rst1.getInt("seat_number");
				Timestamp time_chk = rst1.getTimestamp("time_checkout");
				if(time_now.isAfter(Time.TimeStampTOlocalDateTime(time_chk))) {
					String change = "update seat set Seat_Statement ='��� ����',time_enter=null,time_checkout=null where Seat_Number= ?";
					PreparedStatement pstmtas = conn.prepareStatement(change);
					pstmtas.setInt(1, seat_chk);
					int row3 = pstmtas.executeUpdate();
				}  
			}
	 	 
			String sqlmt2 = "select Locker_Number,l_time_checkout from locker where Locker_Statement='��� ��'";
			PreparedStatement pstmtt2 = 
					conn.prepareStatement(sqlmt2);
			ResultSet rst2 = pstmtt2.executeQuery();
		 
			while(rst2.next()) {//������� �¼��� ��ǽð��� ������ ��밡������ ���� 
				int locker_chk = rst2.getInt("Locker_Number");
				Timestamp l_time_chk = rst2.getTimestamp("l_time_checkout");
				if(time_now.isAfter(Time.TimeStampTOlocalDateTime(l_time_chk))) {
					String change2 = "update locker set Locker_Statement ='��� ����',l_time_enter=null,l_time_checkout=null where Locker_Number= ?";
					PreparedStatement pstmtas2 = conn.prepareStatement(change2);
					pstmtas2.setInt(1, locker_chk);
					int row4 = pstmtas2.executeUpdate();
				}  
			}
		 
			
			//db���� '��� ��'���� ���� ���� '��� ��'�̸� üũ�ڽ� üũ �� ��Ȱ��ȭ(����� �̹Ƿ� ���� ���ϰ�) -�¼�
			String sqlm = "select seat_number from seat where seat_statement='��� ��'";
			PreparedStatement pstmt = 
					conn.prepareStatement(sqlm);
			ResultSet rs = pstmt.executeQuery();
		 
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
			
			//db���� '��� ��'���� ���� ���� '��� ��'�̸� üũ�ڽ� üũ �� ��Ȱ��ȭ(����� �̹Ƿ� ���� ���ϰ�) -�繰��
			String sqlm2 = "select locker_number from locker where locker_statement='��� ��'";
			PreparedStatement pstmt2 = 
					conn.prepareStatement(sqlm2);
			ResultSet rs2 = pstmt2.executeQuery();
			System.out.println();
			System.out.printf("����� �繰�� : ");
			while(rs2.next()) {
				int sn = rs2.getInt("locker_number");

				System.out.printf("%d�� ",sn);
				lockers.get(sn-1).setSelected(true);
				lockers.get(sn-1).setEnabled(false); 
			}
			System.out.println();

			if(rs!=null) rs.close(); 
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		OK = new JButton("���� �ϱ�");
		OK.setBounds(230,380,100,50);
		p1.add(OK);
		OK.addActionListener(this);

	 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,500);
		setLocation(1250,100);
		setVisible(true);  
		
		p1.setBounds(1250, 100, 600, 500);
		p1.setLayout(null);
		this.add(p1);
		 
	}

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
  
			String msg="";

			for(int i=0;i<=19;i++) {//���� �� �ڸ� üũ(��Ȱ��ȭ ���ִ°� ����)
				if(seats.get(i).isSelected()&&(seats.get(i).isEnabled()==true)) { 
					msg=i+1+"�� (1�μ�) �ڸ�\n"; 
					number+=i+1+"�� �¼� ";
				}
				if(lockers.get(i).isSelected()&&(lockers.get(i).isEnabled()==true)) {
					msg+=i+1+"�� �繰��\n"; 
					number+=i+1+"�� �繰�� ";
				}
			}
			for(int i=0;i<=3;i++){
				if(room.get(i).isSelected()&&(room.get(i).isEnabled()==true)) {
					msg+=i+101+"ȣ ��\n"; 
					number+=i+101+" ȣ �� ";
				}
			}


			msg+="���� �Ͻðڽ��ϱ�?";
			if(msg.length()<15) {
				msg="������ �ڸ� �� �繰���� �������ּ���";
				JOptionPane.showMessageDialog(this,msg);//������ ������ �ٽü��� �޼��� â ����(�޼��� ���̷� üũ)
			}else {
				//�����ϱ� ��ư ���� �� (���� �Ͻðڽ��ϱ�?)��Ȯ�� -> (â���� or �� or ���)��ư 
				int result= JOptionPane.showConfirmDialog(null, msg,"Message",JOptionPane.YES_NO_OPTION);
				if(result ==JOptionPane.CLOSED_OPTION) { 
					//(�� Ȯ�� â ����) 
				}else if (result ==JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog(this,"���");//��� �޼���
					 
				}else {   
					
					setVisible(false);
					
					// yes��ư -> ���������� 
					new Payment();
					 
					
				}
			} 

			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (ClassNotFoundException | SQLException e1) {

			e1.printStackTrace();
		} 
	} 
	
	
	 
	public static void main(String[] args) {
		new Select_seat();
		 
		 
	} 
}
