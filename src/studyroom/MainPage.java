package studyroom;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.TimerTask;
import java.util.Calendar;
import java.util.Timer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import studyroom.admin.AdminMenuPage;
import studyroom.admin.AdminPage;
import studyroom.design.Conversion_image;
import studyroom.design.Style;
import studyroom.swingTools.Login_SwingTool;
import studyroom.swingTools.State;
import studyroom.user.findPW.FindPasswordPageUser;
import studyroom.user.login.LoginPage;
import studyroom.user.signUp.SignUpPage;
import studyroom.window.MainBtn_Action;

public class MainPage extends JFrame implements Runnable {

   public static int x = new Conversion_image("image/empty.jpg", 4).x; //������ ���� 
   public static int y = new Conversion_image("image/empty.jpg", 4).y; //������ ���� 
   public static int w = 714; //�����г� ����
   public static int h = y+40; //�����г� ����
   
   public static JLabel background; //���ȭ��
   public static JPanel main_page_panel; //���������� 
   public static JPanel user_page_panel; //����ڸ޴�������
   public static JPanel updateTable; //�ǽð� ��Ȳ
   public static JPanel logout; //�α׾ƿ�
   public static JPanel extend; //�ڵ� �α׾ƿ� ����
   public static JPanel changeUser; //�����ھ����� 
   public static JPanel home; //Ȩ 

   public static CardLayout main_cards;
   public static CardLayout user_cards;
   public static CardLayout statecard;
   public static CardLayout logoutcard;
   public static CardLayout extendcard;
   public static CardLayout changeUsercard;
   public static CardLayout homecard;
   public static JButton homebtn;
   public static JButton logoutbtn;
   public static JButton extendbtn;
   public static JButton changeUserbtn;
   public static JButton empty;
   public static JButton empty2;
   public static JButton empty3;
   public static JButton empty4;

   public static LocalDateTime ss;
   public static int price;
   public static String seat_type;
   public static String userToggle;
   public static int extend_cnt=3;//���� Ƚ������
   int min;
   int sec;
   int k;

   Thread thread;
   JLabel clock;

   public MainPage() {

      userToggle = "����";

      JPanel fram_panel = new JPanel();
      new Style(fram_panel);
      fram_panel.setLayout(null);
      fram_panel.setBounds(0, 0, x, y);

      JPanel main = new JPanel(new BorderLayout());
      new Style(main);
      JButton touch = new JButton("<html>&nbsp;<br/>��ġ�� �Ͽ�<br/>�̿����ּ���<br/><br/><br/><br/></html>");
      new Style(touch);
      touch.setBorder(null);
      touch.addActionListener(new MainBtn_Action(touch));
      main.add(touch);

      background = new JLabel(new Conversion_image("image/empty.jpg", 4).imageicon_smooth);
      background.setOpaque(false);
      background.setBounds(0, 0, x, y);

      main_page_panel = new JPanel();
      main_cards = new CardLayout();
      main_page_panel.setLayout(main_cards);
      new Style(main_page_panel);
      main_page_panel.setBounds(x-w, 0, w, y);

      user_page_panel = new JPanel();
      user_cards = new CardLayout();
      user_page_panel.setLayout(user_cards);
      new Style(user_page_panel);
      user_page_panel.setBounds(x-w, 0, w, y);
      
      JLabel img = new JLabel(new ImageIcon("image/book.jpg"));
      img.setBounds((x-w)/2-20, 30, 30,30);
      background.add(img);
      
      JLabel kg = new JLabel("KG");
      new Style(kg);
      kg.setFont(new Font("���� ����", Font.BOLD, 30));
      kg.setForeground(Color.decode("#cfab8b"));
      kg.setBounds((x-w)/2-28, 35, 200, 100);
      background.add(kg);
   
      JLabel logo = new JLabel("STUDY ROOM");
      new Style(logo);
      logo.setFont(new Font("���� ����", Font.BOLD, 30));
      logo.setForeground(Color.decode("#cfab8b"));
      logo.setBounds((x-w)/2-102, 70, 250, 100);
      background.add(logo);
      
      updateTable = new JPanel();
      new Style(updateTable);
      statecard = new CardLayout();
      updateTable.setLayout(statecard);
      updateTable.setBounds(18, 185, 280, 50);
      updateTable.add(new State());
      
      JLabel info = new JLabel("�̿�ȳ�", JLabel.LEFT);
      info.setBounds(18,y/2-18, 280, 20);
      info.setFont(new Font("���� ����", Font.BOLD, 16));
      info.setForeground(Color.decode("#cfab8b"));
      background.add(info);
      
      String header[] = {"1", "2"};
      String contents[][] = { {"<html>1.&nbspȸ������","<html>2.&nbsp�α���"},
            {"<html>3.&nbsp�̿��&nbsp����","<html>4.&nbsp���ϴ�&nbsp�ð�&nbsp����"},
            {"<html>5.&nbsp���ϴ�&nbsp�¼�&nbsp����","<html>6.&nbsp�������&nbsp����&nbsp(����/ī��)"},
            {"<html>7.&nbsp������&nbsp�߱�","<html>8.&nbsp�ٷ�&nbsp����"},};

      DefaultTableModel model = new DefaultTableModel(contents, header);
      JTable information = new JTable(model);
      new Style(information);
      information.setBounds(18,y/2-5+16, 280, 100);
      information.getColumnModel().getColumn(1).setPreferredWidth(120);
      information.setRowHeight(25);
      information.setFont(new Font("���� ����", Font.BOLD, 12));
      information.setForeground(Color.decode("#cfab8b"));
      information.setBorder(null);
      information.setLayout(null);
      information.setShowGrid(false);
      information.setOpaque(false);
      ((DefaultTableCellRenderer)information.getDefaultRenderer(Object.class)).setOpaque(false);

      DefaultTableCellRenderer left = new DefaultTableCellRenderer();
      left.setHorizontalAlignment(JLabel.LEFT);
      left.setOpaque(false);
      information.getColumn("1").setCellRenderer(left);
      information.getColumn("2").setCellRenderer(left);
      background.add(information);
      
      JLabel line = new JLabel("�ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�", JLabel.LEFT);
      line.setBounds(18,y*3/4-30, 280, 20);
      line.setFont(new Font("���� ����", Font.BOLD, 16));
      line.setForeground(Color.decode("#cfab8b"));
      background.add(line);
      
      JLabel cau = new JLabel("���ǻ���", JLabel.LEFT);
      cau.setBounds(18,y*3/4-8, 280, 20);
      cau.setFont(new Font("���� ����", Font.BOLD, 16));
      cau.setForeground(Color.decode("#cfab8b"));
      background.add(cau);
      
      String header2[] = {"1"};
      String contents2[][] = { {"<html>1.&nbsp���ϱ�&nbsp����ڴ�&nbsp���&nbspó��&nbsp��&nbsp���Խ�&nbsp�Ұ��մϴ�."},
            {"<html>2.&nbsp�����&nbsp����ڴ�&nbsp�ݵ�ó�����&nbsp��&nbsp���ó��"},
            {"<html>&nbsp&nbsp&nbsp���ֽñ�&nbsp�ٶ��ϴ�."}};
      
      DefaultTableModel model2 = new DefaultTableModel(contents2, header2);
      JTable caution = new JTable(model2);
      new Style(caution);
      caution.setBounds(18,y*3/4+20, 290, 80);
      caution.setRowHeight(25);
      caution.setFont(new Font("���� ����", Font.BOLD, 12));
      caution.setForeground(Color.decode("#cfab8b"));
      caution.setBorder(null);
      caution.setLayout(null);
      caution.setShowGrid(false);
      caution.setOpaque(false);
      ((DefaultTableCellRenderer)caution.getDefaultRenderer(Object.class)).setOpaque(false);

      DefaultTableCellRenderer left2 = new DefaultTableCellRenderer();
      left2.setHorizontalAlignment(JLabel.LEFT);
      left2.setOpaque(false);
      caution.getColumn("1").setCellRenderer(left2);
      background.add(caution);
      
      home = new JPanel();
      new Style(home);
      homecard = new CardLayout();
      home.setLayout(homecard);

      empty4 = new JButton();
      new Style(empty4);
      home.add("1",empty4);

      homebtn = new JButton(new Conversion_image("image/Ȩ��ư.png", 30, 30).imageicon_smooth);
      new Style(homebtn); 
      homebtn.setText("Ȩ");
      homebtn.addActionListener(new MainBtn_Action(homebtn));
      home.add("2",homebtn);
      home.setBounds(x-700,5,40,40);

      logout = new JPanel();
      new Style(logout);
      logoutcard = new CardLayout();
      logout.setLayout(logoutcard);

      empty = new JButton();
      new Style(empty);
      logout.add("1",empty);

      logoutbtn = new JButton("�α׾ƿ�");
      new Style(logoutbtn);
      logoutbtn.setOpaque(true);
      logoutbtn.setFont(new Font("���� ����", Font.BOLD, 13));
      logoutbtn.addActionListener(new MainBtn_Action(logoutbtn));
      logout.add("2",logoutbtn);
      logout.setBounds(x-90, 37, 80, 27);

      extend = new JPanel();
      new Style(extend);
      extendcard = new CardLayout();
      extend.setLayout(extendcard);
      extend.setBounds(x-160,5,150,27);

      empty2 = new JButton();
      new Style(empty2);
      extend.add("1",empty2);

      extendbtn = new JButton(setInterval()+"");
      new Style(extendbtn);  
      extendbtn.setOpaque(true);
      extendbtn.setFont(new Font("���� ����", Font.BOLD, 13));
      extend.add("2",extendbtn);

      MainPage.updateTable.add(new State());
      MainPage.statecard.next(MainPage.updateTable);

      changeUser = new JPanel();
      new Style(changeUser);
      changeUsercard = new CardLayout();
      changeUser.setLayout(changeUsercard);
      changeUser.setBackground(Color.decode("#ede4df"));
      changeUser.setBounds(8, 5, 40, 40);

      empty3 = new JButton();
      new Style(empty3);

      changeUserbtn = new JButton(new Conversion_image("image/������.png", 30, 30).imageicon_smooth);
      new Style(changeUserbtn);
      changeUserbtn.setText("�����ڹ�ư");
      changeUserbtn.addActionListener(new MainBtn_Action(changeUserbtn));
      changeUser.add("1",changeUserbtn);
      changeUser.add("2",empty3);

      main_page_panel.add("����", main);
      main_page_panel.add("�α���", new LoginPage());
      main_page_panel.add("����ڸ޴�", user_page_panel);
      main_page_panel.add("������", new AdminPage());
      main_page_panel.add("�����ڸ޴�", new AdminMenuPage());
      main_page_panel.add("ȸ������", new SignUpPage());
      main_page_panel.add("���ã��", new FindPasswordPageUser());

      clock = new JLabel();
      new Style(clock);
      clock.setForeground(Color.decode("#cfab8b"));
      clock.setHorizontalAlignment(JLabel.CENTER);
      if (thread == null) {
         thread = new Thread(this);
         thread.start();
      }
      clock.setBounds(8, 145, 300, 30);

      background.add(changeUser);
      background.add(clock);
      background.add(updateTable);
      background.add(logout);
      background.add(extend);
      background.add(home);
      fram_panel.add(background);

      Login_SwingTool.initFrame(this);
      add(main_page_panel);
      add(fram_panel);

      extendbtn.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {  
            MainBtn_Action.interval = 300;
            extend_cnt--;
         }
      });

      int delay = 1000;
      int period = 1000;
      Timer time = new Timer(); 
      time.scheduleAtFixedRate(new TimerTask() { 
         public void run() {
            if (MainBtn_Action.interval == 0) {
               try {
                  Class.forName("oracle.jdbc.driver.OracleDriver");
                  Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");
                  MainPage.main_cards.show(MainPage.main_page_panel, "�α���");
                  MainPage.userToggle = "�α���";

                  PreparedStatement pstmt = null;
                  String sql = "update person_info set login_state ='Off'";
                  pstmt = conn.prepareStatement(sql);
                  int row = pstmt.executeUpdate();

                  PreparedStatement pstmt2 = null;
                  String sql2 = "update Admin_Info set Admin_LoginState = 'Off'";
                  pstmt2 = conn.prepareStatement(sql2);
                  int row2 = pstmt2.executeUpdate();

                  if (pstmt2 != null) pstmt.close();
                  if (pstmt != null) pstmt.close();
                  if (conn != null) conn.close();

               // ī�巹�̾ƿ� ǥ�� �Ǵ� ������
  				MainPage.logoutcard.show(MainPage.logout, "1");
  				MainPage.extendcard.show(MainPage.extend, "1");
  				MainPage.homecard.show(MainPage.home, "1");
  				MainPage.changeUsercard.show(MainPage.changeUser, "1");
  				
                  extend_cnt = 3;
                  extendbtn.setEnabled(true);
                  // time.cancel();//Ÿ�̸� ����
                        // ������Ϸ��� ���� ����?�ؾ� �Ѵٰ���
                        MainBtn_Action.interval = -1;//db���� �ݺ� ��� ���� ����
                        
               } catch (ClassNotFoundException | SQLException e1) {
                  e1.printStackTrace();
               }
            } else {
               k = setInterval();
               sec = k % 60;
               min = k / 60;
               extendbtn.setText(
                     "<html><body><center>" + min + "�� " + sec + "�� " + "[����  " + extend_cnt + "ȸ]");
               if (extend_cnt == 0) extendbtn.setEnabled(false);
            }
         }
      }, delay, period);
   }
   private static int setInterval() {          
      return --MainBtn_Action.interval;
   } 

   @Override
   public void run() {
      while (true) {
         Calendar cal = Calendar.getInstance();
         String now = cal.get(Calendar.YEAR) + "�� " + (cal.get(Calendar.MONTH) + 1) + "�� " + cal.get(Calendar.DATE)
         + "�� " + cal.get(Calendar.HOUR) + "�� " + cal.get(Calendar.MINUTE)+ "�� " + cal.get(Calendar.SECOND) + "�� ";
         clock.setText(now);
         try {
            Thread.sleep(1000);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
   }

   public static void main(String[] args) {
      new MainPage();
   }
}