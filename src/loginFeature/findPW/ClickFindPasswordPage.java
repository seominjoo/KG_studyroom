package loginFeature.findPW;

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

import loginFeature.PhoneNumberEnum;
import loginFeature.SignUpEnum;
import loginFeature.SignUpSuccessWindow;


public class ClickFindPasswordPage implements ActionListener{

	static String password;
	JLabel foundPW;
	
	public ClickFindPasswordPage(JLabel foundPW) {
		this.foundPW = foundPW;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		String totalPhoneNumber = PhoneNumberEnum.PHONENUMBER1.text.getText()
				+"-"+PhoneNumberEnum.PHONENUMBER2.text.getText()+"-"
				+PhoneNumberEnum.PHONENUMBER3.text.getText();
		
		String totalBirth = (String) BirthEnum.YEAR.birthComboBoxYear.getSelectedItem()
				+ (String) BirthEnum.MONTH.birthComboBoxMonth.getSelectedItem()
				+ (String) BirthEnum.DAY.birthComboBoxDay.getSelectedItem();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr",
					"1234");
			
			conn.setAutoCommit(false);

			PreparedStatement read_name_ID_from_personInfo = conn
					.prepareStatement("SELECT pw FROM person_info where phone_number = ?"
							+ "and person_birth = ?");

			read_name_ID_from_personInfo.setString(1, totalPhoneNumber);
			read_name_ID_from_personInfo.setString(2, totalBirth);
			
			password = null;
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
			 //e1.printStackTrace();
			System.out.println(e1.toString());
			//new SignUpFailWindow(e1);

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			System.out.println("[ojdbc] 클래스 경로가 틀렸습니다.");
		}
		
	}

	
}
