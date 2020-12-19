package login.page;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import login.design.Style;

public class AdminMenuPage extends JPanel{
    
   public AdminMenuPage() {

      this.setLayout(null);
      new Style(this);
      
//    page_Subtitle.setBorder(BorderFactory.createLineBorder(Color.decode("#cfab8b"))); // �׵θ�?
      
      JLabel page_Title = new JLabel("������ ������", JLabel.RIGHT);
      new Style(page_Title);
      page_Title.setFont(new Font("���� ���", Font.BOLD, 13));
      page_Title.setBounds(374, 395, 100, 30);
      this.add(page_Title);
      
      String admin_Name = "������";
      JLabel page_Subtitle = new JLabel("������_"+admin_Name+"", JLabel.RIGHT);
      new Style(page_Subtitle);
      page_Subtitle.setFont(new Font("���� ���", Font.BOLD, 13));
      page_Subtitle.setBounds(374, 418, 100, 30);
      this.add(page_Subtitle);
      
      JButton store_Management = new JButton("�������");
      new Style(store_Management);
      store_Management.setOpaque(true);
      store_Management.setBounds(39,175,100,100);
      this.add(store_Management);
      
      store_Management.addActionListener(new StoreManagementPage());
      
      JButton sales_Management = new JButton("�������");
      new Style(sales_Management);
      sales_Management.setOpaque(true);
      sales_Management.setBounds(185,175,100,100);
      this.add(sales_Management);
      
      sales_Management.addActionListener(new SalesManagementPage());
      
      JButton member_Management = new JButton("ȸ������");
      new Style(member_Management);
      member_Management.setOpaque(true);
      member_Management.setBounds(331,175,100,100);
      this.add(member_Management);
      
      member_Management.addActionListener(new MemberManagementPage());
      
      JButton price_change = new JButton("�̿�� ���� ����");
      new Style(price_change);
      price_change.setOpaque(true);
      price_change.setBounds(185,300,150,50);
      this.add(price_change);
      
      price_change.addActionListener(new PassChange());

   }

}