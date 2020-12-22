package login.page;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import login.PhoneNumberEnum;
import login.clearText.ClearTextBackGround;
import login.clearText.PhoneNumberClearTextField;
import login.design.Conversion_image;
import login.design.EmptyPrice;
import login.design.Style;
import login.swingTools.State;
import login.window.MainBtn_Action;

public class LoginPage extends JPanel {

   public static JTextField phone_number1;
   public static JTextField phone_number2;
   public static JTextField phone_number3;
   public static JTextField phone_number4;
   public static JTextField phone_number5;
   public static JTextField phone_number6;
   public static JTextField[] phoneTotal;
   
   public static JPasswordField loginpass;
   public static JPasswordField admin_loginpass1;

   public LoginPage() {
      
      MainPage.updateTable.add(new State());
      MainPage.statecard.next(MainPage.updateTable);

      this.setLayout(null);
      new Style(this);
      
      JLabel title = new JLabel("Log - In");
      title.setBounds(MainPage.w/2-34, 150, 90, 20);
      add(title);
      new Style(title);

      JPanel admin_panel = new JPanel();
      admin_panel.setLayout(null);
      new Style(admin_panel);

      phone_number1 = new JTextField("010");
      new Style(phone_number1, 3);
      phone_number1.setBounds(255, 200, 55, 40);
      this.add(phone_number1);

      JLabel hyphen1 = new JLabel("-", JLabel.CENTER);
      new Style(hyphen1);
      hyphen1.setBounds(310, 200, 15, 40);
      this.add(hyphen1);

      phone_number2 = new JTextField("1111");
      new Style(phone_number2, 4);
      phone_number2.setBounds(325, 200, 55, 40);
      this.add(phone_number2);

      JLabel hyphen2 = new JLabel("-", JLabel.CENTER);
      new Style(hyphen2);
      hyphen2.setBounds(380, 200, 15, 40);
      this.add(hyphen2);

      phone_number3 = new JTextField("1111");
      new Style(phone_number3, 4);
      phone_number3.setBounds(395, 200, 55, 40);
      this.add(phone_number3);

      loginpass = new JPasswordField("111111Aa");
      loginpass.addMouseListener(new EmptyPrice(loginpass));
      new Style(loginpass, 12);
      loginpass.setBounds(255, 250, 195, 40);
      this.add(loginpass);

      JButton login = new JButton("�α���");
      new Style(login);
      login.setBounds(255, 300, 195, 40);
      login.addActionListener(new MainBtn_Action(login));
      this.add(login);

      JButton find_PW = new JButton(new Conversion_image("image/PWã��(����).png", 40, 40).imageicon_smooth);
      new Style(find_PW);
      find_PW.setText("���ã��");
      find_PW.setBounds(280, 362, 50, 50);
      find_PW.addActionListener(new MainBtn_Action(find_PW));
      this.add(find_PW);

      JButton signup = new JButton(new Conversion_image("image/ȸ������(����).png", 40, 40).imageicon_smooth);
      new Style(signup);
      signup.setText("ȸ������");
      signup.setBounds(385, 360, 50, 50);
      signup.addActionListener(new MainBtn_Action(signup));
      this.add(signup);

      phoneTotal = new JTextField[] { phone_number1, phone_number2, phone_number3 };
      // ���� �ؽ�Ʈ ���콺�� ���� ��
      for (int i = 0; i < phoneTotal.length; i++) {
         phoneTotal[i].addMouseListener(new PhoneNumberClearTextField(phoneTotal[i], "�α���"));
         addMouseListener(new ClearTextBackGround(phoneTotal[i], PhoneNumberEnum.values()[i]));
      }

   }

}