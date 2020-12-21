package login.page;

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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import login.design.Conversion_image;
import login.design.Style;
import login.findPW.FindPasswordPageUser;
import login.signUp.SignUpPage;
import login.swingTools.Login_SwingTool;
import login.swingTools.State;
import login.window.MainBtn_Action;

public class MainPage extends JFrame implements Runnable {

   public static int x = new Conversion_image("image/���ȭ��new.jpg", 4).x; //��ü ������ ���� ����
   public static int y = new Conversion_image("image/���ȭ��new.jpg", 4).y; //��ü ������ ���� ����
   public static int plus = 120;
   public static int w = 560+plus; //���� �г� ���� ����(width)
   public static int h = 562; //���� �г� ���� ����(height)

   public static JPanel main_page_panel;
   public static CardLayout main_cards;
   public static JPanel user_page_panel;
   public static CardLayout user_cards;
   public static JPanel updateTable;
   public static CardLayout statecard;
   public static JLabel logout_background;
   public static JLabel background;
   public static JLabel extendlb;
   public static JPanel logout;
   public static JPanel extend;
   public static JPanel logout_time;
   public static CardLayout logout_timecard;
   public static CardLayout logoutcard;
   public static CardLayout extendcard;
   public static JButton logoutbtn;
   public static JButton empty;
   public static JButton empty2;
   public static JButton extendbtn;
   Thread thread;
   JLabel clock;

   public static String userToggle;
   public static  int extend_cnt=3;//���� Ƚ�� 3������
   int min;
   int sec;
   int k;

   // �����ϱ� �ʿ��� �Ű�����
   public static LocalDateTime ss;
   public static int price;
   public static String seat_type;

   public static JPanel deleteBrown;
   
   public MainPage() {

      userToggle = "����";
      
      // ���ȭ��
      JPanel fram_panel = new JPanel();
      new Style(fram_panel);
      fram_panel.setLayout(null);
      fram_panel.setBounds(0, 0, x+plus, y);//x=1000, y=562
      
      // �α��� �� ������ ���� �κ� ���ֱ�
      deleteBrown = new JPanel();
      deleteBrown.setBackground(Color.decode("#ede4df"));
      deleteBrown.setBounds(1000, 0, 120, 560);
      add(deleteBrown);
      
      // ��� �̹���
      background = new JLabel(new Conversion_image("image/���ȭ��new.jpg", 4).imageicon_smooth);
      background.setOpaque(false);
      background.setBounds(0, 0, x+plus, y);//x=1000, y=562
      
      // ���� ī�������� �г�
      main_page_panel = new JPanel();
      main_cards = new CardLayout();
      main_page_panel.setLayout(main_cards);
      new Style(main_page_panel);
      main_page_panel.setBounds(x-w, 0, w, h); //w=683, h=562   

      // ����ڸ޴� ī�������� �г�
      user_page_panel = new JPanel();
      user_cards = new CardLayout();
      user_page_panel.setLayout(user_cards);
      new Style(user_page_panel);
      user_page_panel.setBounds(x-w, 0, w, h); //w=683, h=562
      
      // �¼���Ȳ �г�
      updateTable = new JPanel();
      new Style(updateTable);
      statecard = new CardLayout();
      updateTable.setLayout(statecard);
      updateTable.setBounds(38, 190, 280, 50);
      updateTable.add(new State());
      
      // �α׾ƿ� �г�
      logout = new JPanel();
      new Style(logout);
      logoutcard = new CardLayout();
      logout.setLayout(logoutcard);
      logout.setBounds(x-11, 5, 107, 50); //x=1000
      logout.setForeground(Color.decode("#805b38"));
      
      // ��ȭ��
      empty = new JButton();
      new Style(empty);
      empty.setBounds(0, 0, 100, 30);
      logout.add("1",empty);
      
      // �α׾ƿ���ư
      logoutbtn = new JButton("�α׾ƿ�");
      new Style(logoutbtn);
      logoutbtn.setFont(new Font("���� ���", Font.BOLD, 13));
      logoutbtn.addActionListener(new MainBtn_Action(logoutbtn));
      logoutbtn.setForeground(Color.decode("#cfab8b"));
      logout.add("2",logoutbtn);
                 
      //�����ϱ� �г�
      extend = new JPanel();
      new Style(extend);
      extendcard = new CardLayout();
      extend.setLayout(extendcard);
      extend.setBounds(x-11,58,107,y-65);
      
      //��ȭ��2
      empty2 = new JButton();
      new Style(empty2);
      extend.add("1",empty2);
      
      //�����ϱ� ��ư
      extendbtn = new JButton(setInterval()+"");
      new Style(extendbtn);  
      extendbtn.setForeground(Color.decode("#cfab8b"));
      extend.add("2",extendbtn);
      
      background.add(extend);
      
      MainPage.updateTable.add(new State());
      MainPage.statecard.next(MainPage.updateTable);

      JPanel main = new JPanel(new BorderLayout());
      new Style(main);
      JButton touch = new JButton("<html>��ġ�� �Ͽ�<br/>�̿����ּ���</html>");
      new Style(touch);
      touch.setBorder(null);
      touch.addActionListener(new MainBtn_Action(touch));
      main.add(touch);   
      
//      JLabel rightside = new JLabel(new ImageIcon("image/rightside.jpg"));
//      rightside.setSize(30,80);
//      rightside.setLocation(0,0);
//      add(rightside);

      // ���� ������ �߰� �۾�
      main_page_panel.add("����", main);
      main_page_panel.add("�α���", new LoginPage());
      main_page_panel.add("����ڸ޴�", user_page_panel);
      main_page_panel.add("������", new AdminPage());
      main_page_panel.add("�����ڸ޴�", new AdminMenuPage());
      main_page_panel.add("ȸ������", new SignUpPage());
      main_page_panel.add("���ã��", new FindPasswordPageUser());

        // ����� �޴� ������ �߰� �۾�
//      user_page_panel.add("���θ޴�", new _00main()); // �޴�������
//      user_page_panel.add("�̿�Ǳ���", new _01start()); // �̿�Ǳ��� ������
//      user_page_panel.add("�¼��̿��", new _02dayOrWeek()); // �¼� �̿�� ������
//      user_page_panel.add("���̿��", new _02dayRoom());
//      user_page_panel.add("���ϱǰ���ǥ(�¼�)", new _03whatHour());
//      user_page_panel.add("����ǰ���ǥ", new _03whatWeek());
//      user_page_panel.add("���ϱǰ���ǥ(��)", new _03whatHourRoom());
//      user_page_panel.add("�繰���̿��", new _05locker());
//      user_page_panel.add("�ڸ��̵�", new _06move());
//      user_page_panel.add("�Խ�������", new _07in_seletSeat());
//      user_page_panel.add("���������", new _07out());
//      user_page_panel.add("����������", new _08reservation(ss,price,seat_type));
//      user_page_panel.add("�ڸ�������", new _06move_selectSeat());
//      user_page_panel.add("����������", new _09payment(ss, price, seat_type));
//      user_page_panel.add("�����˸�â", new _10paycash(ss));

        // �������� Ȯ�� ��ư(or ����ٷΰ��� or�α׾ƿ�off)
//      user_page_panel.add("������", new _11receipt(ss, price));

      JButton changeUser = new JButton(new Conversion_image("image/������.png", 30, 30).imageicon_smooth);
      new Style(changeUser);
      changeUser.setText("�����ڹ�ư");
      changeUser.setBounds(5, 5, 40, 40);
      changeUser.addActionListener(new MainBtn_Action(changeUser));


      // ���� �ð�
      clock = new JLabel();
      new Style(clock);
      clock.setForeground(Color.decode("#dec5ae"));
      clock.setHorizontalAlignment(JLabel.CENTER);
      if (thread == null) {
         thread = new Thread(this);
         thread.start();
      }
      clock.setBounds(8, 150, 300, 30);

      background.add(logout);
      background.add(changeUser);
      background.add(clock);
      background.add(updateTable);
      
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
      
   // �ڵ��α׾ƿ� �ð�
      int delay = 1000;
      int period = 1000;
      Timer time = new Timer(); 
      time.scheduleAtFixedRate(new TimerTask() { 
          public void run() {
             
              if (MainBtn_Action.interval == 0) {
                   try {
                	   deleteBrown.setOpaque(true);
                      Class.forName("oracle.jdbc.driver.OracleDriver");
                        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr",
                              "1234");
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
                        System.out.printf("�α׾ƿ� %d\n", row);
                        System.out.printf("�α׾ƿ� %d\n", row2);
                        if (pstmt2 != null)
                           pstmt.close();
                        if (pstmt != null)
                           pstmt.close();
                        if (conn != null)
                           conn.close();
                        MainPage.logoutcard.show(MainPage.logout, "1");
                        MainPage.extendcard.show(MainPage.extend, "1");
                        extend_cnt = 3;
                        extendbtn.setEnabled(true);
                        // time.cancel();//Ÿ�̸� ����
                        // ����� �Ϸ��� ���� ����?�ؾ� �Ѵٰ���
                        MainBtn_Action.interval = 1000;// db���� �ݺ��� �����ϱ� ���� �ӽ÷� �ð� �÷���
                     } catch (ClassNotFoundException | SQLException e1) {
                        e1.printStackTrace();
                     }

                  } else {
                     k = setInterval();
                     sec = k % 60;
                     min = k / 60;
                     extendbtn.setText(
                           "<html><body><center>" + min + "��" + sec + "��<br>" + "����  " + extend_cnt + "ȸ");
                     if (extend_cnt == 0) {
                        extendbtn.setEnabled(false);
                     }
                  }
               }
            }, delay, period);

         
             
         }
         private static int setInterval() {          
             return --MainBtn_Action.interval;
         } 
         
         
         @Override
         public void run() {
//            while (true) {
//               Calendar cal = Calendar.getInstance();
//               String now = cal.get(Calendar.YEAR) + "�� " + (cal.get(Calendar.MONTH) + 1) + "�� " + cal.get(Calendar.DATE)
//                     + "�� " + cal.get(Calendar.HOUR) + "�� " + cal.get(Calendar.MINUTE)+ "�� " + cal.get(Calendar.SECOND)
//                     + "�� ";
//               
//               clock.setText(now);
//               try {
//                  Thread.sleep(1000);
//               } catch (InterruptedException e) {
//                  e.printStackTrace();
//               }
//            }
         }

         public static void main(String[] args) {
            new MainPage();
         }
      }