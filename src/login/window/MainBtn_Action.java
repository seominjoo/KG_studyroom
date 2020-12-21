package login.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.Format;
import java.util.Calendar;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import login.design.Conversion_image;
import login.design.Style;
import login.loginDataBase.DBLoggedIn;
import login.mainmenu._00main;
import login.page.AdminPage;
import login.page.LoginPage;
import login.page.MainPage;
import login.signUp.SignUpPage;
import login.swingTools.Login_SwingTool;
import login.swingTools.SwingToolsSubPage;

public class MainBtn_Action implements ActionListener {
	public static int interval;
   JButton loginbtns;
   String nextcard = ""; 
   public MainBtn_Action(JButton loginbtns) {
      this.loginbtns = loginbtns;
   } 
   
   @Override
   public void actionPerformed(ActionEvent e) {
      String label1 = null;
      String label2 = null;

      if (loginbtns.getText().equals("회원가입")) {
         MainPage.main_cards.show(MainPage.main_page_panel, "회원가입");
         MainPage.userToggle = "회원가입";
         SignUpPage.passAlert.setText(""); // 회원가입 비번 알림 초기화
         SignUpPage.passConfirmAlert.setText(""); // 회원가입 비번확인 알림 초기화

      } else if (loginbtns.getText().equals("비번찾기")) {
         MainPage.main_cards.show(MainPage.main_page_panel, "비번찾기");
         MainPage.userToggle = "비번찾기";

      } else if (loginbtns.getText().equals("관리자 비번찾기")) {
         MainPage.main_cards.show(MainPage.main_page_panel, "비번찾기");
         MainPage.userToggle = "관리자 비번찾기";

      } else if (loginbtns.getText().equals("관리자버튼")) {
         // 관리자 버튼
         if (MainPage.userToggle.equals("메인") || MainPage.userToggle.equals("로그인")) {
            MainPage.main_cards.show(MainPage.main_page_panel, "관리자");
            MainPage.userToggle = "관리자";
         } else {
            // 첫화면
            MainPage.main_cards.show(MainPage.main_page_panel, "메인");
            MainPage.userToggle = "메인";
         }

      }else if (loginbtns.getText().equals("로그아웃")) {
    	  MainPage.deleteBrown.setOpaque(true);
         //로그아웃 
         try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                  "jdbc:oracle:thin:@localhost:1521/XEPDB1",
                  "hr",
                  "1234"
                  );
            MainPage.main_cards.show(MainPage.main_page_panel, "로그인");
            MainPage.userToggle = "로그인";
            
            PreparedStatement pstmt = null;
            String sql = "update person_info set login_state ='Off'";
            pstmt = conn.prepareStatement(sql);
            
            int row = pstmt.executeUpdate();
            System.out.printf("로그아웃 %d\n",row);
            
            PreparedStatement pstmt2 = null;
            String sql2 = "update Admin_Info set Admin_LoginState = 'Off'";
            pstmt2 = conn.prepareStatement(sql2);
            
            int row2 = pstmt2.executeUpdate();
            System.out.printf("로그아웃 %d\n",row2);
            
            if (pstmt2 != null) pstmt.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
            MainPage.extend_cnt=3;
            MainPage.extendbtn.setEnabled(true);
            MainPage.logoutcard.show(MainPage.logout, "1");
            MainPage.extendcard.show(MainPage.extend, "1");
         } catch (ClassNotFoundException | SQLException e1) { 
            e1.printStackTrace();
         }
       
         
          
         

      } else if (loginbtns.getText().contains("터치")) {
         MainPage.main_cards.show(MainPage.main_page_panel, "로그인");
         MainPage.userToggle = "로그인";

      } else if (loginbtns.getText().equals("관리자 로그인")) {
    	  MainPage.deleteBrown.setOpaque(false);
         String admin_phonenumber = AdminPage.admin_phone_number1.getText() + "-"
               + AdminPage.admin_phone_number2.getText() + "-" + AdminPage.admin_phone_number3.getText();
         String admin_password = String.valueOf(AdminPage.admin_loginpass.getPassword());

         // 관리자 로그인 클릭 시
         new DBLoggedIn(admin_phonenumber, admin_password);

         // 들고온 값이 디비에 있는지 확인
         System.out.println(admin_phonenumber + ", " + admin_password);
         System.out.println(DBLoggedIn.phone_number + ", " + DBLoggedIn.password);

         if (DBLoggedIn.person_name != null && DBLoggedIn.phone_number.equals(admin_phonenumber)
               && DBLoggedIn.password.equals(admin_password)) {
            // 번호와 비번이 일치 하면
            label1 = "회원번호 : " + DBLoggedIn.person_id;
            label2 = DBLoggedIn.person_name + "님 환영합니다 !!";

            String update = "update admin_info set admin_loginstate = 'On' " + "where admin_phonenumber = '"
                  + DBLoggedIn.phone_number + "' and admin_pw = '" + DBLoggedIn.password + "'";
            System.out.println(update);
            DBLoggedIn db = new DBLoggedIn(update);

            interval=10;//자동 로그아웃 카운트 시간
            
            // 버튼 이름
            nextcard = "관리자메뉴";
            new SubWindow(label1, label2, nextcard);

         } else {
            label1 = "가입하지 않은 아이디거나";
            label2 = "잘못된 비밀번호입니다.";
            new SubWindow(label1, label2);
         }

      } else if (loginbtns.getText().equals("로그인")) {
    	 MainPage.deleteBrown.setOpaque(false);
         MainPage.userToggle = "로그인";

         String login_phonenumber = LoginPage.phone_number1.getText() + "-" + LoginPage.phone_number2.getText() + "-"
               + LoginPage.phone_number3.getText();
         String login_password = String.valueOf(LoginPage.loginpass.getPassword());
         // 로그인 클릭 시
         // 닫기 하면 페이지를 넘길까?

         new DBLoggedIn(login_phonenumber, login_password);

         // 들고온 값이 디비에 있는지 확인
         System.out.println(login_phonenumber + ", " + login_password);
         System.out.println(DBLoggedIn.phone_number + ", " + DBLoggedIn.password);

         if (DBLoggedIn.person_name != null && DBLoggedIn.phone_number.equals(login_phonenumber)
               && DBLoggedIn.password.equals(login_password)) {
        	  
            // 번호와 비번이 일치 하면
            label1 = "회원번호 : " + DBLoggedIn.person_id;
            label2 = DBLoggedIn.person_name + "님 환영합니다 !!";

            String update = "update person_info set login_state = 'On' " + "where phone_number = '"
                  + DBLoggedIn.phone_number + "' and pw = '" + DBLoggedIn.password + "'";
            System.out.println(update);
            DBLoggedIn db = new DBLoggedIn(update);
            
            interval=10;//자동 로그아웃 카운트 시간
            
            // 버튼 이름
            nextcard = "사용자메뉴";
            new SubWindow(label1, label2, nextcard);

         } else {
            label1 = "가입하지 않은 아이디거나";
            label2 = "잘못된 비밀번호입니다.";
            new SubWindow(label1, label2);
         }

      } else {
         
         // 페이지 준비중
         
         label1 = "[system] still in maintenance";
         label2 = "페이지 준비 중..";
         new SubWindow(label1, label2);
      }

      //로그인 값들 초기화
//      LoginPage.phone_number1.setText("010");
//      LoginPage.phone_number2.setText("");
//      LoginPage.phone_number3.setText("");
//      LoginPage.loginpass.setText("");
//      AdminPage.admin_phone_number1.setText("010");
//      AdminPage.admin_phone_number2.setText("");
//      AdminPage.admin_phone_number3.setText("");
//      AdminPage.admin_loginpass.setText("");
   }

}