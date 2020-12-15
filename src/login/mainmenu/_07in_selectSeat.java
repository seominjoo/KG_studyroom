package login.mainmenu;

import java.awt.Color;
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
import login.page.MainPage;


public class _07in_selectSeat extends JPanel implements ActionListener{
 
	 
	static ArrayList<JButton> seats_btn = new ArrayList<>(); //1~20�� �¼� (1�μ�) ��ư
	{
		for(int i=0;i<20;i++) {
			seats_btn.add(new JButton());
		}
	 }
	static ArrayList<JButton> room_btn = new ArrayList<>(); //1~20�� �¼� (1�μ�) ��ư
	{
		for(int i=0;i<4;i++) {//0~3
			room_btn.add(new JButton()); 
		}
	}
	static ArrayList<JButton> locker_btn = new ArrayList<>(); //1~20�� �¼� (1�μ�) ��ư
	{
		for(int i=0;i<20;i++) {
			locker_btn.add(new JButton()); 
		}
	}

	int f=0;
	int g=0;
	int c=0;
	int d=0;
	int e=0; 
	int a=0;
	JLabel label_msg;
	static LocalDateTime time_now = LocalDateTime.now();
	String time_checkout;
	 
	
	public _07in_selectSeat() {
		 
		new Style(this);
		JButton OK;
		JButton back;
//		JLabel label = new JLabel("1�μ�");
//		JLabel label02 = new JLabel("��");
//		label_msg = new JLabel("");
//		label.setBounds(10,10,50,30);
//		label.setFont(new Font("���� ���", Font.BOLD, 15));
//		this.add(label);
//		label02.setBounds(10,100,50,30);
//		label02.setFont(new Font("���� ���", Font.BOLD, 15));
//	 
//		label_msg.setBounds(200,310,500,30);
//		label_msg.setFont(new Font("���� ���", Font.BOLD, 15));
//		this.add(label_msg); 
//		this.add(label02);
		
		JLabel label03 = new JLabel("�繰��");
	 
		label03.setBounds(15,320,50,30);
		label03.setFont(new Font("���� ���", Font.BOLD, 15));
		this.add(label03); 
		new Style(label03);
		
		JLabel label04 = new JLabel("�ްԽ�");
		label04.setOpaque(true);
		label04.setBackground(Color.gray);
		label04.setBounds(250,225,180,85);
		label04.setFont(new Font("���� ���", Font.BOLD, 15));
		label04.setHorizontalAlignment(JLabel.CENTER);
		this.add(label04);
		
		JLabel label05 = new JLabel("���� ����");
		label05.setOpaque(true);
		label05.setBackground(Color.black);
		label05.setForeground(Color.orange);
		label05.setHorizontalAlignment(JLabel.CENTER);
		label05.setBounds(40,435,100,30);
		this.add(label05);
		
		JLabel label06 = new JLabel("���� �Ұ�");
		label06.setOpaque(true);
		label06.setBackground(Color.black);
		label06.setForeground(Color.gray);
		label06.setHorizontalAlignment(JLabel.CENTER);
		label06.setBounds(40,470,100,30);
		this.add(label06);

		for(int i=0;i<3;i++) {// 1�μ� ��ư ��ġ ����

			seats_btn.get(i).setBackground(Color.BLACK);
			seats_btn.get(i).setText(i+1+"��");
			seats_btn.get(i).setForeground(Color.orange);
			this.add(seats_btn.get(i));
			seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i)));
			seats_btn.get(i).setBounds(30+f,10,60,60); 
			f+=60;  
		}

		for(int i=3;i<6;i++) {// 1�μ� ��ư ��ġ ����

			seats_btn.get(i).setBackground(Color.BLACK);
			seats_btn.get(i).setText(i+1+"��");
			seats_btn.get(i).setForeground(Color.orange);
			this.add(seats_btn.get(i));
			seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i)));
			seats_btn.get(i).setBounds(70+f,10,60,60); 
			f+=60;  
		}
		for(int i=6;i<11;i++) {// 1�μ� ��ư ��ġ ����

			seats_btn.get(i).setBackground(Color.BLACK);
			seats_btn.get(i).setText(i+1+"��");
			seats_btn.get(i).setForeground(Color.orange);
			this.add(seats_btn.get(i));
			seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i)));
			seats_btn.get(i).setBounds(120+f,10+a,60,60); 
			a+=60;
		}
		for(int i=11; i<14;i++) { // 1�μ� ��ư ��ġ ����
			seats_btn.add(new JButton()); 
			seats_btn.get(i).setBackground(Color.BLACK);
			seats_btn.get(i).setText(i+1+"��");
			seats_btn.get(i).setForeground(Color.orange);
			this.add(seats_btn.get(i));
			seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i)));
			seats_btn.get(i).setBounds(30+g,70,60,60);
			g+=60;  
		}
		for(int i=14; i<17;i++) { // 1�μ� ��ư ��ġ ����
			seats_btn.add(new JButton()); 
			seats_btn.get(i).setBackground(Color.BLACK);
			seats_btn.get(i).setText(i+1+"��");
			seats_btn.get(i).setForeground(Color.orange);
			this.add(seats_btn.get(i));
			seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i)));
			seats_btn.get(i).setBounds(70+g,70,60,60);
			g+=60;  
		}
		g-=180;
		for(int i=17; i<20;i++) { // 1�μ� ��ư ��ġ ����
			seats_btn.add(new JButton()); 
			seats_btn.get(i).setBackground(Color.BLACK);
			seats_btn.get(i).setText(i+1+"��");
			seats_btn.get(i).setForeground(Color.orange);
			this.add(seats_btn.get(i));
			seats_btn.get(i).addActionListener(new ActionBtn_select(seats_btn.get(i)));
			seats_btn.get(i).setBounds(70+g,160,60,60);
			g+=60;  
		}

		for(int i=0;i<2;i++) {//0~3
			room_btn.get(i).setBackground(Color.BLACK);
			room_btn.get(i).setText(i+101+"ȣ");
			room_btn.get(i).setForeground(Color.orange);
			this.add(room_btn.get(i));
			room_btn.get(i).addActionListener(new ActionBtn_select(room_btn.get(i))); 
			room_btn.get(i).setBounds(30+e,160,90,75);
			e+=90; 
			room_btn.get(i).setEnabled(false);
		}
		for(int i=2;i<4;i++) {//0~3
			room_btn.get(i).setBackground(Color.BLACK);
			room_btn.get(i).setText(i+101+"ȣ");
			room_btn.get(i).setForeground(Color.orange);
			this.add(room_btn.get(i));
			room_btn.get(i).addActionListener(new ActionBtn_select(room_btn.get(i))); 
			room_btn.get(i).setBounds(30+d,235,90,75);
			d+=90; 
			room_btn.get(i).setEnabled(false);
		}
		for(int i=0;i<10;i++) {// �繰�� ��ư ��ġ ����
			locker_btn.get(i).setBackground(Color.BLACK);
			locker_btn.get(i).setText(i+1+"��");
			locker_btn.get(i).setForeground(Color.orange);
			this.add(locker_btn.get(i));
			locker_btn.get(i).addActionListener(new ActionBtn_select( locker_btn.get(i)));
			locker_btn.get(i).setBounds(10+c,350,60,30);
			c+=55;
			locker_btn.get(i).setEnabled(false);
		}	
		d=0;
		for(int i=10; i<20;i++) { // �繰�� ��ư ��ġ ����

			locker_btn.get(i).setBackground(Color.BLACK);
			locker_btn.get(i).setText(i+1+"��");
			locker_btn.get(i).setForeground(Color.orange);
			this.add(locker_btn.get(i));
			locker_btn.get(i).addActionListener(new ActionBtn_select( locker_btn.get(i)));
			locker_btn.get(i).setBounds(10+d,380,60,30);
			d+=55;   
			locker_btn.get(i).setEnabled(false);
		}

		
		ActionListener back_btn = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				for(int i=0;i<20;i++) {
					seats_btn.get(i).setSelected(false);
				} 
				
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "���θ޴�");
				MainPage.userToggle = "���θ޴�";
				
			}
		};
		
		back = new JButton("���� ȭ��");
		back.setBounds(200,430,150,80);
		this.add(back);
		back.addActionListener(back_btn);
		new Style(back);
	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XEPDB1",
					"hr",
					"1234"
					);
		
			//������� �¼��̸� üũ�ڽ� üũ �� ��Ȱ��ȭ 
			String sql = "select seat_number from seat where seat_statement='��� ��'";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery(); 
			 
			while(rs.next()) { 
				int sn = rs.getInt("seat_number"); 
				if(sn<=20) {
					System.out.printf("%d�� ",sn); 
					seats_btn.get(sn-1).setSelected(true);
					seats_btn.get(sn-1).setEnabled(false);
				}else if (sn>=101) {
					System.out.printf("[%dȣ] ",sn); 
					room_btn.get(sn-101).setSelected(true);
					room_btn.get(sn-101).setEnabled(false);
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
							locker_btn.get(sn-1).setSelected(true);
							locker_btn.get(sn-1).setEnabled(false); 
							 
						}
			
			if(rs!=null) rs.close(); 
			if (pstm != null) pstm.close();
			if (conn != null) conn.close();
		} catch (ClassNotFoundException | SQLException e1) { 
			e1.printStackTrace();
		}
		
		OK = new JButton("�¼� ����(�Խ�)");
		OK.setBounds(360,430,150,80);
		this.add(OK);
		OK.addActionListener(this);
		new Style(OK);
		setSize(600,500);
		setLocation(600,150);
		setVisible(true);  
		
		this.setBounds(0, 100, 600, 500);
		this.setLayout(null);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) { 
		int count_only=0; 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XEPDB1",
					"hr",
					"1234"
					);
			PreparedStatement pstmt = null;

			String msg=""; 
			for(int i=0;i<=19;i++) {//�ڸ� üũ(��Ȱ��ȭ ���ִ°� ����)
				if(seats_btn.get(i).isSelected()&&(seats_btn.get(i).isEnabled()==true)) { 
					msg=i+1+"�� (1�μ�) �ڸ�\n";  
					count_only++; 
			}
		} 
			msg+="�����Ͻðڽ��ϱ�?";
			if(count_only==0) {
				msg="������ �ڸ��� �������ּ���";
				JOptionPane.showMessageDialog(this,msg);//������ ������ �ٽü��� �޼��� â ����(�޼��� ���̷� üũ)
			}else if(count_only>1) {
				String warning="1�� 1���� ����";
				JOptionPane.showMessageDialog(this,warning); 
			}else {
				// (â���� or �� or ���)��ư 
				int result= JOptionPane.showConfirmDialog(null, msg,"Message",JOptionPane.YES_NO_OPTION);
				if(result ==JOptionPane.CLOSED_OPTION) { 
					//(�� Ȯ�� â ����) 
				}else if (result ==JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog(this,"���");//��� �޼���
				}else {   
					 
			 // yes��ư -> �¼�����
						
		for(int i=0;i<20;i++) {
			if(seats_btn.get(i).isSelected()&&(seats_btn.get(i).isEnabled()==true)) {//�̹� ������ִ� ��(��Ȱ��ȭ) ���� üũ
				seats_btn.get(i).setEnabled(false);
													
				//��������� db����
				String sql = "update seat set Seat_Statement ='��� ��' where Seat_Number= ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, i+1);
				int row = pstmt.executeUpdate();

				//�Խ�/��ǽð� ����
				String sqlt1 = "update seat set time_enter =?,time_checkout=? where Seat_Number= ?";
				pstmt = conn.prepareStatement(sqlt1);
				pstmt.setTimestamp(1, Time.localDateTimeTOTimeStamp(time_now));
		 		pstmt.setTimestamp(2, _00main.time_seat);
				pstmt.setInt(3, i+1);
				int rowt1 = pstmt.executeUpdate();
								
				//ȸ��info ���̺� ����(�¼���ȣ,�繰�Թ�ȣ,�Խ�)
				sql = "update person_info set seat_number=? where person_id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, i+1);
				pstmt.setInt(2, _00main.id);
								
				int row3 = pstmt.executeUpdate();
								
				System.out.printf("%d�� �ڸ��� ����Ǿ����ϴ�.(%d�� ������Ʈ)\n", i+1,row);
				System.out.printf("�Խ�/��� �ð��� ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n",rowt1); 
				System.out.printf("ȸ�� ������ ������Ʈ�Ǿ����ϴ�.(%d�� ������Ʈ)\n",row3); 
			}
		}
		setVisible(false);
					  
					 
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

