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
      
      // 사용중인 좌석 수 확인
               try {
                  Class.forName("oracle.jdbc.driver.OracleDriver");
                  Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");
                  String sql4 = "select seat_number from seat where seat_statement='사용 중'";
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

                  // 사용중인 사물함 수 확인
                  String sql5 = "select locker_number from locker where locker_statement='사용 중'";
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
               
         // 스터디룸 상황표
               String header[] = { "1인석", "스터디룸", "사물함" };
               String contents[][] = { { "<html>사용중 1인석<br/>&emsp;&emsp;" + Integer.toString(count_seat) + " / 20",
                     "<html>사용중 스터디룸<br/>&emsp;&emsp;&emsp;" + Integer.toString(count_room) + " / 4",
                     "<html>사용중 사물함<br/>&emsp;&emsp;" + Integer.toString(count_locker) + " / 20", } };

               DefaultTableModel model = new DefaultTableModel(contents, header);
               table = new JTable(model);

               table.setBounds(0, 0, 280, 50);
               table.setRowHeight(50);

               // 테두리
               Color color = UIManager.getColor("Table.gridColor");
               MatteBorder border = new MatteBorder(1, 1, 0, 0, color);
               table.setBorder(border);

               // 상황표 글씨 중앙 정렬
               DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
               celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
               table.getColumn("1인석").setCellRenderer(celAlignCenter);
               table.getColumn("스터디룸").setCellRenderer(celAlignCenter);
               table.getColumn("사물함").setCellRenderer(celAlignCenter);

               // 디자인 적용
               new Style(celAlignCenter);
               celAlignCenter.setForeground(Color.decode("#cfab8b"));
               new Style(table);
               table.setGridColor(Color.decode("#cfab8b")); // 테이블 내부 선 색
               table.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b")));
               
               this.add(table);
   }
}