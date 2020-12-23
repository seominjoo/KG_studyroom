package studyroom.admin;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import studyroom.admin.adminmode.MemberManagementPage;
import studyroom.admin.adminmode.PassChangePage;
import studyroom.admin.adminmode.SalesManagementPage;
import studyroom.admin.adminmode.StoreManagementPage;
import studyroom.design.Style;
import studyroom.user.MainPage;

public class AdminMenuPage extends JPanel{
    
   public AdminMenuPage() {

      this.setLayout(null);
      new Style(this);

      JLabel page_Title = new JLabel("관리자 페이지", JLabel.RIGHT);
      new Style(page_Title);
      page_Title.setFont(new Font("맑은 고딕", Font.BOLD, 13));
      page_Title.setBounds(MainPage.w/2+120, MainPage.h/2+125, 100, 30);
      this.add(page_Title);
      
      String admin_Name = "서민주";
      JLabel page_Subtitle = new JLabel("관리자_"+admin_Name+"", JLabel.RIGHT);
      new Style(page_Subtitle);
      page_Subtitle.setFont(new Font("맑은 고딕", Font.BOLD, 13));
      page_Subtitle.setBounds(MainPage.w/2+120, MainPage.h/2+145, 100, 30);
      this.add(page_Subtitle);
      
      JButton store_Management = new JButton("매장관리");
      new Style(store_Management);
      store_Management.setOpaque(true);
      store_Management.setBounds(MainPage.w/2-230, MainPage.h/2-160, 220, 130);
      this.add(store_Management);
      
      store_Management.addActionListener(new StoreManagementPage());
      
      JButton sales_Management = new JButton("매출관리");
      new Style(sales_Management);
      sales_Management.setOpaque(true);
      sales_Management.setBounds(MainPage.w/2-230, MainPage.h/2-10, 220, 130);
      this.add(sales_Management);
      
      sales_Management.addActionListener(new SalesManagementPage());
      
      JButton member_Management = new JButton("회원관리");
      new Style(member_Management);
      member_Management.setOpaque(true);
      member_Management.setBounds(MainPage.w/2+10, MainPage.h/2-160, 220, 130);
      this.add(member_Management);
      
      member_Management.addActionListener(new MemberManagementPage());
      
      JButton price_change = new JButton("이용권 가격 조정");
      new Style(price_change);
      price_change.setOpaque(true);
      price_change.setBounds(MainPage.w/2+10, MainPage.h/2-10, 220, 130);
      this.add(price_change);
      
      price_change.addActionListener(new PassChangePage());

   }
}