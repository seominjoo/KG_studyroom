package login.findPW;

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

import login.*;
import login.findPW.window.PassWordSearchResultPage;
import login.page.MainPage;
import login.window.ActionWindow;

public class ClickFindPasswordPage implements ActionListener {

	public static String password;
	static JLabel foundPW;
	String query;
	boolean flag;

	String totalPhoneNumber;
	String totalBirth;

	public ClickFindPasswordPage(JLabel foundPW, String query, boolean flag) {
		this.foundPW = foundPW;
		this.query = query;
		this.flag = flag;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (flag) {
			totalPhoneNumber = FindPasswordPageUser.phone_number1.getText() + "-" + FindPasswordPageUser.phone_number2.getText()
					+ "-" + FindPasswordPageUser.phone_number3.getText();

			totalBirth = (String) FindPasswordPageUser.year.getSelectedItem()
					+ (String) FindPasswordPageUser.month.getSelectedItem()
					+ (String) FindPasswordPageUser.day.getSelectedItem();
		} else {
			totalPhoneNumber = FindPasswordPageAdmin.phone_number1.getText() + "-" + FindPasswordPageAdmin.phone_number2.getText()
					+ "-" + FindPasswordPageAdmin.phone_number3.getText();

			totalBirth = (String) FindPasswordPageAdmin.year.getSelectedItem()
					+ (String) FindPasswordPageAdmin.month.getSelectedItem()
					+ (String) FindPasswordPageAdmin.day.getSelectedItem();
		}

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
			System.out.println(password);

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
			// System.out.println(e1.toString());
			// new SignUpFailWindow(e1);

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			System.out.println("[ojdbc] 클래스 경로가 틀렸습니다.");
		}

	}

}
