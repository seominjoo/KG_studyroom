package loginFeature;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class ClickSignUp extends MouseAdapter {

	static int person_id;
	static String person_name;
	static String pw;
	static String pwConfirm;
	boolean consentFlag = true;
//
//	Container card_panel;
//	
//	public ClickSignUp(Container card_panel) {
//		this.card_panel = card_panel;
//	}

	@Override
	public void mouseClicked(MouseEvent e) {

		pw = SignUp.textList.get(SignUpEnum.PASSWORD.index).getText();
		pwConfirm = SignUp.textList.get(SignUpEnum.PASSWORDCONFIRM.index).getText();

		if (e.getButton() == MouseEvent.BUTTON1) {
			if(!Pattern.matches("[°¡-ÆR]{2,4}", SignUp.textList.get(SignUpEnum.NAME.index).getText())) {
				
			}
			
			if (!pw.equals(pwConfirm) || pw.equals(SignUpEnum.PASSWORDCONFIRM.labelName)) {
				new PWnoMatch();
			} else {
				// ¾à°ü Ã¼Å©
				for (Entry<JCheckBox, JButton> kv : SignUp.consent.entrySet()) {
					if (!kv.getKey().isSelected()) {
						new ConsentFailWindow();
						consentFlag = false;
						break;
					}
					else
						consentFlag = true;
				}

				if (consentFlag) {

					try {
						// ³»ÀÏ ²À Á¤±ÔÇ¥Çö½ÄÀ¸·Î °Å¸£±â
						// ÀÌ¸§, »ý³â¿ùÀÏ, ÈÞ´ëÆù, ºñ¹ø,
						
					
						
						Class.forName("oracle.jdbc.driver.OracleDriver");

						Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr",
								"1234");
						
						conn.setAutoCommit(false);
						
						PreparedStatement insertPersonInfo = conn.prepareStatement("INSERT INTO Person_Info "
								+ "(Person_Id,Check_Time,Person_Name, person_birth, Phone_Number,PW,Total_Payment)"
								+ " VALUES(SignUpSeq.nextval, ?, ?, ?, ?, ?, ?)");

						// Batch : ÀÏ°ýÃ³¸®
						DateFormat simple = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
						Date now = new Date();

						insertPersonInfo.setString(1, simple.format(now));
						insertPersonInfo.setString(2, SignUp.textList.get(0).getText());
						insertPersonInfo.setString(3, SignUp.textList.get(1).getText());
						insertPersonInfo.setString(4, SignUp.textList.get(2).getText());
						insertPersonInfo.setString(5, pw);
						insertPersonInfo.setInt(6, 0);
						insertPersonInfo.addBatch();

						int[] rows = insertPersonInfo.executeBatch();

						if (insertPersonInfo != null)
							insertPersonInfo.close();

						PreparedStatement read_name_ID_from_personInfo = conn
								.prepareStatement("SELECT person_id, person_name FROM person_info");

						ResultSet rs = read_name_ID_from_personInfo.executeQuery();

						while (rs.next()) {

							person_id = rs.getInt(1);
							person_name = rs.getString(2);
						}

						new SignUpSuccessWindow(person_id, person_name);

						if (rs != null)
							rs.close();
						if (read_name_ID_from_personInfo != null)
							read_name_ID_from_personInfo.close();
						if (conn != null)
							conn.close();

						System.out.println("¼º°ø");

					} catch (SQLException e1) {
						// e1.printStackTrace();
						System.out.println(e1.toString());
						new SignUpFailWindow(e1);
	
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
						System.out.println("[ojdbc] Å¬·¡½º °æ·Î°¡ Æ²·È½À´Ï´Ù.");
					}
				}
			}
		}
	}

}
