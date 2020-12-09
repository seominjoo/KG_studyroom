package mainmenu;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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
import javax.swing.border.EmptyBorder;

public class _05selectLocker extends JFrame implements ActionListener{
	
	private JPanel p1;
	boolean selected = false;
	 
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
	LocalDateTime time_now = LocalDateTime.now();
	String time_checkout;
	
 
	
	_05selectLocker() {
		
		p1 = new JPanel();
		 
		JLabel label03 = new JLabel("�繰��");
		label_msg = new JLabel("");
		label03.setBounds(60,120,50,30);
		label03.setFont(new Font("���� ���", Font.BOLD, 15));
		p1.add(label03);
		label_msg.setBounds(200,310,500,30);
		label_msg.setFont(new Font("���� ���", Font.BOLD, 15));
		p1.add(label_msg); 
		

		for(int i=0; i<10;i++) {// �繰�� üũ�ڽ� ��ġ ����
			lockers.get(i).setBounds(60+c,170,40,30);
			p1.add(lockers.get(i));
			c+=40;
		}
		for(int i=10; i<20;i++) {// �繰�� üũ�ڽ� ��ġ ����
			lockers.get(i).setBounds(60+d,200,40,30);
			p1.add(lockers.get(i));
			d+=40;
		}
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XEPDB1",
					"hr",
					"1234"
					);
			 
			// ������� �繰�� �� �̿�Ⱓ�� ������ ��밡������ ������Ʈ
			String sql = "SELECT Locker_Number,l_time_checkout FROM locker "
					+ "WHERE Locker_Statement='��� ��'";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
		 
			while(rs.next()) { 
				int locker_chk = rs.getInt("Locker_Number");
				Timestamp l_time_chk = rs.getTimestamp("l_time_checkout");
				if(time_now.isAfter(Time.TimeStampTOlocalDateTime(l_time_chk))) {
					String change = "update locker set Locker_Statement ='��� ����',l_time_enter=null,l_time_checkout=null where Locker_Number= ?";
					PreparedStatement pstm2 = conn.prepareStatement(change);
					pstm2.setInt(1, locker_chk);
					int row4 = pstm2.executeUpdate();
				}  
			}
		 
				
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
			System.out.println();

			if(rs!=null) rs.close(); 
			if (pstm != null) pstm.close();
			if (conn != null) conn.close();
		} catch (ClassNotFoundException | SQLException e1) { 
			e1.printStackTrace();
		}
		 
		JButton btn_pay = new JButton("�����ϱ�");
		btn_pay.setBounds(310,380,100,50);
		p1.add(btn_pay);
		btn_pay.addActionListener(this);
 
		 
		
		JButton btn_back = new JButton("���� ȭ��");
		btn_back.setBounds(160,380,100,50);
		p1.add(btn_back);
		btn_back.addActionListener(new ActionListener() { //���� ������
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				_01start frame = new _01start();
				frame.setVisible(true);
			}
		});
	 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,500);
		setLocation(600,150);
		setVisible(true);  
		
		p1.setBounds(0, 100, 600, 500);
		p1.setLayout(null);
		this.add(p1); 
	}

	@Override
	public void actionPerformed(ActionEvent e) { 
			
			String msg="";
			
			for(int i=0;i<=19;i++) {
				if(lockers.get(i).isSelected()&&(lockers.get(i).isEnabled()==true)) {
					msg+=i+1+"�� �繰��\n"; 
					number+=i+1+"�� �繰�� ";
				}
			}
			
			msg+="���� �Ͻðڽ��ϱ�?";
			if(msg.length()<15) {
				msg="������ �繰���� �������ּ���";
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
					new _09paymentLocker(); 
				}
			}  
	} 
 
	public static void main(String[] args) {
		_05selectLocker frame = new _05selectLocker();
		frame.setVisible(true);
	}  
}
