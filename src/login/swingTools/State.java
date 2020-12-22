package login.swingTools;

import java.awt.CardLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import login.design.Style;
import login.page.MainPage;

public class State extends JPanel{
   public int count_seat;
   public int count_room;
   public int count_locker;
   public static JTable table;

   
   public State() {
      count_seat = 0;
      count_room = 0;
      count_locker = 0;
      new Style(this);
      setLayout(null);
      setBounds(18, 190, 280, 50);
      
      // ������� �¼� �� Ȯ��
               try {
                  Class.forName("oracle.jdbc.driver.OracleDriver");
                  Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");
                  String sql4 = "select seat_number from seat where seat_statement='��� ��'";
                  PreparedStatement pstmt = conn.prepareStatement(sql4);
                  ResultSet rs = pstmt.executeQuery();
                  while (rs.next()) {
                     int sn = rs.getInt("seat_number");
                     if (sn <= 20) {
                        count_seat++;
                     } else if (sn >= 101) {
                        count_room++;
                     }
                  }

                  // ������� �繰�� �� Ȯ��
                  String sql5 = "select locker_number from locker where locker_statement='��� ��'";
                  pstmt = conn.prepareStatement(sql5);
                  rs = pstmt.executeQuery();

                  while (rs.next()) {
                     int sn = rs.getInt("locker_number");
                     count_locker++;
                  }
               } catch (ClassNotFoundException e) {
                  e.printStackTrace();
               } catch (SQLException e) {
                  e.printStackTrace();
               }
               
         // ���͵�� ��Ȳǥ
               String header[] = { "1�μ�", "���͵��", "�繰��" };
               String contents[][] = { { "<html>����� 1�μ�<br/>&emsp;&emsp;" + Integer.toString(count_seat) + " / 20",
                     "<html>����� ���͵��<br/>&emsp;&emsp;&emsp;" + Integer.toString(count_room) + " / 4",
                     "<html>����� �繰��<br/>&emsp;&emsp;" + Integer.toString(count_locker) + " / 20", } };

               DefaultTableModel model = new DefaultTableModel(contents, header);
               table = new JTable(model);

               table.setBounds(0, 0, 280, 50);
               table.setRowHeight(50);

               // �׵θ�
               Color color = UIManager.getColor("Table.gridColor");
               MatteBorder border = new MatteBorder(1, 1, 0, 0, color);
               table.setBorder(border);

               // ��Ȳǥ �۾� �߾� ����
               DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
               celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
               table.getColumn("1�μ�").setCellRenderer(celAlignCenter);
               table.getColumn("���͵��").setCellRenderer(celAlignCenter);
               table.getColumn("�繰��").setCellRenderer(celAlignCenter);

               // ������ ����
               new Style(celAlignCenter);
               celAlignCenter.setForeground(Color.decode("#cfab8b"));
               new Style(table);
               table.setGridColor(Color.decode("#cfab8b")); // ���̺� ���� �� ��
               table.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b")));
               
               this.add(table);
   }
}