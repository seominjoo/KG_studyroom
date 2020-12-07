package mainmenu;

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


public class _06moveSeat2 extends JFrame implements ActionListener{
	 
	private static final long serialVersionUID = 1L;
	boolean selected = false;
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
//	static ArrayList<JCheckBox> lockers = new ArrayList<>(); //1~20�� �繰��
//	{
//		for(int i=1;i<=20;i++) {
//			lockers.add(new JCheckBox(i+"",selected));
//		}
//	}

	int a=0;
	int b=0;
	int c=0;
	int d=0;
	int e=0; 
	public static String number="";
	JLabel label_msg;
	LocalDateTime time_now = LocalDateTime.now();
	String time_checkout;
//	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy�� M�� d�� a h�� m�� s��");
	
	
	 
	
	JPanel p1 = new JPanel();
	_06moveSeat2() {
 
		JButton OK;
		JLabel label = new JLabel("1�μ�");
		JLabel label02 = new JLabel("��");
		label_msg = new JLabel("");
		label.setBounds(10,10,50,30);
		label.setFont(new Font("���� ���", Font.BOLD, 15));
		p1.add(label);
		label02.setBounds(10,100,50,30);
		label02.setFont(new Font("���� ���", Font.BOLD, 15));
		p1.add(label02);
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
			String sqlmt1 = "SELECT seat_number, time_checkout FROM seat "
					+ "WHERE seat_statement ='��� ��'";
			PreparedStatement pstmtt1 = conn.prepareStatement(sqlmt1);
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
	 	 

			
			//�¼� - db���� '��� ��'���� ���� ���� '��� ��'�̸� üũ�ڽ� üũ �� ��Ȱ��ȭ(����� �̹Ƿ� ���� ���ϰ�) 
			String sqlm = "select seat_number from seat where seat_statement='��� ��'";
			PreparedStatement pstmt = conn.prepareStatement(sqlm);
			ResultSet rs = pstmt.executeQuery();
		 
			System.out.print("������� �ڸ� : ");

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
			
			if(rs!=null) rs.close(); 
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		OK = new JButton("�̵� �ϱ�");
		OK.setBounds(230,380,100,50);
		p1.add(OK);
		OK.addActionListener(this);

	 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,500);
		setLocation(0,100);
		setVisible(true);  
		
		p1.setBounds(0, 100, 600, 500);
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

			for(int i=0;i<=19;i++) {//�̵� �� �ڸ� üũ(��Ȱ��ȭ ���ִ°� ����)
				if(seats.get(i).isSelected()&&(seats.get(i).isEnabled()==true)) { 
					msg=i+1+"�� (1�μ�) �ڸ���\n"; 
					number+=i+1+"�� �¼� ";
				}
			}
			
			for(int i=0;i<=3;i++){
				if(room.get(i).isSelected()&&(room.get(i).isEnabled()==true)) {
					msg+=i+101+"ȣ ������\n"; 
					number+=i+101+" ȣ �� ";
				}
			}
				

			msg+="�̵��Ͻðڽ��ϱ�?";
			if(msg.length()<15) {
				msg="�̵��� �ڸ��� �������ּ���";
				JOptionPane.showMessageDialog(this,msg);//������ ������ �ٽü��� �޼��� â ����(�޼��� ���̷� üũ)
			}else {
				//�̵��ϱ� ��ư ���� �� (�̵��Ͻðڽ��ϱ�?)��Ȯ�� -> (â���� or �� or ���)��ư 
				int result= JOptionPane.showConfirmDialog(null, msg,"Message",JOptionPane.YES_NO_OPTION);
				if(result ==JOptionPane.CLOSED_OPTION) { 
					//(�� Ȯ�� â ����) 
				}else if (result ==JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog(this,"���");//��� �޼���
					 
				}else {   
					
					setVisible(false);
					
					// yes��ư -> �̵�Ȯ�� ������
//					new Payment();
					 
					
				}
			} 

			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (ClassNotFoundException | SQLException e1) {

			e1.printStackTrace();
		} 
	} 
	
	
	 
	public static void main(String[] args) {
		new _06moveSeat2(); 
	} 

}
