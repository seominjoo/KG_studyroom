package studyroom.admin;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import studyroom.MainPage;
import studyroom.design.ClearTextBackGround;
import studyroom.design.Conversion_image;
import studyroom.design.EmptyPrice;
import studyroom.design.PhoneNumberClearTextField;
import studyroom.design.Style;
import studyroom.user.signUp.PhoneNumberEnum;
import studyroom.window.MainBtn_Action;

public class AdminPage extends JPanel{
   
   public static JTextField admin_phone_number1;
   public static JTextField admin_phone_number2;
   public static JTextField admin_phone_number3;
   public static JPasswordField admin_loginpass;
   public static JTextField[] phoneTotal;

   public AdminPage() {
      
      this.setLayout(null);
      new Style(this);
      
      JLabel title = new JLabel("Log - In");
      title.setBounds(MainPage.w/2-34, 150, 90, 20);
      add(title);
      new Style(title);

      admin_phone_number1 = new JTextField("010");
      new Style(admin_phone_number1, 3);
      admin_phone_number1.setBounds(255, 200, 55, 40);
      this.add(admin_phone_number1);

      JLabel hyphen1 = new JLabel("-", JLabel.CENTER);
      new Style(hyphen1);
      hyphen1.setBounds(310, 200, 15, 40);
      this.add(hyphen1);

      admin_phone_number2 = new JTextField("");
      new Style(admin_phone_number2, 4);
      admin_phone_number2.setBounds(325, 200, 55, 40);
      this.add(admin_phone_number2);

      JLabel hyphen2 = new JLabel("-", JLabel.CENTER);
      new Style(hyphen2);
      hyphen2.setBounds(380, 200, 15, 40);
      this.add(hyphen2);

      admin_phone_number3 = new JTextField("");
      new Style(admin_phone_number3, 4);
      admin_phone_number3.setBounds(395, 200, 55, 40);
      this.add(admin_phone_number3);
      this.add(admin_phone_number3);

      admin_loginpass = new JPasswordField("");
      admin_loginpass.addMouseListener(new EmptyPrice(admin_loginpass));
      new Style(admin_loginpass, 12);
      admin_loginpass.setBounds(255, 250, 195, 40);
      this.add(admin_loginpass);

      JButton admin = new JButton("관리자 로그인");
      new Style(admin);
      admin.setBounds(255, 300, 195, 40);
      admin.addActionListener(new MainBtn_Action(admin));
      this.add(admin);

      JButton admin_find_PW1 = new JButton(new Conversion_image("image/PW찾기(진한).png", 40, 40).imageicon_smooth);
      new Style(admin_find_PW1);
      admin_find_PW1.setText("관리자 비번찾기");
      admin_find_PW1.setBounds(390, 354, 50, 50);
      admin_find_PW1.addActionListener(new MainBtn_Action(admin_find_PW1));
      this.add(admin_find_PW1);
      
      phoneTotal = new JTextField[] { admin_phone_number1, admin_phone_number2, admin_phone_number3 };
      for (int i = 0; i < phoneTotal.length; i++) {
         phoneTotal[i].addMouseListener(new PhoneNumberClearTextField(phoneTotal[i], "관리자"));
         addMouseListener(new ClearTextBackGround(phoneTotal[i], PhoneNumberEnum.values()[i]));
      }
      admin_loginpass.addMouseListener(new EmptyPrice(admin_loginpass));
   }
}