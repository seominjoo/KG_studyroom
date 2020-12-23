package studyroom.user.findPW;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

import studyroom.*;
import studyroom.user.findPW.window.PassWordSearchResultPage;
import studyroom.window.MainBtn_Action;

public class ClickFindPasswordPage implements ActionListener {

	public static String password;
	static JLabel foundPW;

	String totalPhoneNumber;
	String totalBirth;

	String query;

	public ClickFindPasswordPage(JLabel foundPW) {
		this.foundPW = foundPW;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// 사용자 and 관리자 비번찾기 sql 구분 
		if(MainPage.userToggle.equals("비번찾기")) {
			query = "SELECT pw FROM person_info where phone_number = ?" + "and person_birth = ?";
		}
		else if(MainPage.userToggle.equals("관리자 비번찾기")) {
			query = "select admin_pw from admin_info where admin_phonenumber = ? and admin_birth = ?";
		}

		totalPhoneNumber = FindPasswordPageUser.phone_number1.getText() + "-"
				+ FindPasswordPageUser.phone_number2.getText() + "-" + FindPasswordPageUser.phone_number3.getText();

		totalBirth = (String) FindPasswordPageUser.year.getSelectedItem()
				+ (String) FindPasswordPageUser.month.getSelectedItem()
				+ (String) FindPasswordPageUser.day.getSelectedItem();

		// 비번찾기 위한 DB
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr", "1234");

			conn.setAutoCommit(false);

			PreparedStatement read_name_ID_from_personInfo = conn.prepareStatement(query);

			read_name_ID_from_personInfo.setString(1, totalPhoneNumber);
			read_name_ID_from_personInfo.setString(2, totalBirth);

			password = "";
			ResultSet rs2 = read_name_ID_from_personInfo.executeQuery();

			while (rs2.next()) {
				password = rs2.getString(1);
			}

			new PassWordSearchResultPage(foundPW, password);

			if (rs2 != null)
				rs2.close();
			if (read_name_ID_from_personInfo != null)
				read_name_ID_from_personInfo.close();
			if (conn != null)
				conn.close();

			System.out.println("성공");

		} catch (SQLException e1) {
			e1.printStackTrace();

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			//System.out.println("[ojdbc] 클래스 경로가 틀렸습니다.");
		}

	}

}
