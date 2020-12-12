package login.mainmenu;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

public class _11receipt extends JPanel{

	JTable table;
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h시 m분 s초");
	public static LocalDateTime ss = LocalDateTime.now();
	public static int price = 0;
	
	public _11receipt() {
		JLabel label01 = new JLabel("<html><pre>		 영수증</pre>");
		label01.setBounds(0,0,440,80);
		label01.setFont(new Font("Courier", Font.PLAIN, 35));
		

		setSize(750,500);
		setLocation(600,150);
		setVisible(true); 
		
		JPanel p2 = new JPanel();
		p2.setBounds(12, 10, 706, 453);
		add(p2);
		p2.setLayout(null);
		
		p2.add(label01);
		
		String header[] = {"결제","정보"};
		String contents[][]= {
				{"번호",_08reservation.number},
				{"결제 시간",_09payment.time_now.format(dateTimeFormatter)},
				{"퇴실(만료) 예정 시간",ss.format(dateTimeFormatter)}, 
				{"이용권",_08reservation.type11},
				{"결제 금액",Integer.toString((_08reservation.price))+"원"},
				{"받은 금액",Integer.toString(price)+"원"},
				{"거스름 돈", Integer.toString(_10paycash.change)+"원"}
		};
		
		DefaultTableModel model = new DefaultTableModel(contents,header);
		table = new JTable(model);
		table.setBounds(135, 104, 437, 280);
		table.setRowHeight(40);

		Color color = UIManager.getColor("Table.gridColor");
		MatteBorder border = new MatteBorder(1, 1, 0, 0, color);
		table.setBorder(border);
		p2.add(table);
		
	}
	public static void main(String[] args) {
		 
	}
}
