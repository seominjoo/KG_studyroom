package studyroom.user.signUp;

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
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import studyroom.user.signUp.window.ResultWindow;
import studyroom.user.usermode.Time;

public class ClickSignUp extends MouseAdapter {

	static int person_id;
	static String person_name;

	static String phoneNumber;

	// È¸¿ø°¡ÀÔ ÆäÀÌÁö¿¡¼­ °¡ÀÔ ¹öÆ° ´­·¶À» ½Ã
	@Override
	public void mouseClicked(MouseEvent e) {
		boolean consentCheck = true;
		JTextField pw;
		JTextField pwConfirm;
		String year = (String) SignUpPage.year.getSelectedItem();
		String month = (String) SignUpPage.month.getSelectedItem();
		String day = (String) SignUpPage.day.getSelectedItem();
		boolean samePhoneNumber = false;
		JTextField phoneNumber1 = SignUpPage.phone_number1;
		JTextField phoneNumber2 = SignUpPage.phone_number2;
		JTextField phoneNumber3 = SignUpPage.phone_number3;
		String text = phoneNumber1.getText() + "-" + phoneNumber2.getText() + "-" + phoneNumber3.getText();

		pw = SignUpEnum.PASSWORD.blindPW;
		pwConfirm = SignUpEnum.PASSWORDCONFIRM.blindPW;

		// °¡ÀÔ Á¶°Ç ÇÊÅÍ¸µ
		if (e.getButton() == MouseEvent.BUTTON1) {
			if (!Pattern.matches("[°¡-ÆR]{2,4}", SignUpEnum.NAME.text.getText())) {
				new ResultWindow("¼º ÇÔ");
				SignUpEnum.NAME.text.setText("");
				;
			} else if (!Pattern.matches("[0-9]{8}", year + month + day)) {
				new ResultWindow("»ý³â ¿ùÀÏ");
			} else if (!(Pattern.matches("01[0-9]", phoneNumber1.getText())
					&& Pattern.matches("[0-9]{4}", phoneNumber2.getText())
					&& Pattern.matches("[0-9]{4}", phoneNumber3.getText()))) {
				new ResultWindow("ÀüÈ­ ¹øÈ£");
				phoneNumber1.setText("");
				phoneNumber2.setText("");
				phoneNumber3.setText("");
			} else if (!Pattern.matches("^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z]).{8,12}$", pw.getText())) {
				new ResultWindow("ºñ¹Ð ¹øÈ£");
				pw.setText("");
				pwConfirm.setText("");
			} else if (!pw.getText().equals(pwConfirm.getText())
					|| pw.getText().equals(SignUpEnum.PASSWORDCONFIRM.labelName)) {
				new ResultWindow("ºñ¹Ð ¹øÈ£ È®ÀÎ");
				pwConfirm.setText("");
			} else {
				for (Entry<JCheckBox, JButton> kv : SignUpPage.consent.entrySet()) {
					if (!kv.getKey().isSelected()) {
						consentCheck = false;
						new ResultWindow("¾à°ü µ¿ÀÇ");
						break;
					}
				}
				// ÇÊÅÍ¸µ °ÅÄ¡°í °É·¯Áø°Ô ¾ø´Ù¸é, ¾Æ·¡ °¡ÀÔDB ½ÇÇà
				if (consentCheck) {
					
					try {

						Class.forName("oracle.jdbc.driver.OracleDriver");

						Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr",
								"1234");

						conn.setAutoCommit(false);
						//
						PreparedStatement read_PhoneNumber = conn
								.prepareStatement("SELECT phone_number FROM person_info");

						ResultSet rs = read_PhoneNumber.executeQuery();

						// ÀüÈ­ ¹øÈ£ Áßº¹ÀÌ¶ó¸é ÇÊÅÍ¸µ
						while (rs.next()) {
							phoneNumber = rs.getString(1);
							if (text.equals(phoneNumber)) {
								new ResultWindow("ÀüÈ­ ¹øÈ£ Áßº¹");
								phoneNumber1.setText("");
								phoneNumber2.setText("");
								phoneNumber3.setText("");
								samePhoneNumber = true;
								break;
							}
						}

						if (rs != null)
							rs.close();
						if (read_PhoneNumber != null)
							read_PhoneNumber.close();

						// ÀüÈ­¹øÈ£µµ Áßº¹ÀÌ ¾Æ´Ï¶ó¸é
						if (!samePhoneNumber) {

							PreparedStatement read_MaxIDNum = conn
									.prepareStatement("select max(person_id) from person_info group by person_id");

							ResultSet rs1 = read_MaxIDNum.executeQuery();

							while (rs1.next()) {
								person_id = rs1.getInt(1);
							}

							PreparedStatement insertPersonInfo = conn.prepareStatement("INSERT INTO Person_Info "
									+ "(Person_Id,Check_Time,Person_Name, person_birth, Phone_Number,PW,Total_Payment)"
									+ " VALUES(?, ?, ?, ?, ?, ?, ?)");

							DateFormat simple = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
							Date now = new Date();

							insertPersonInfo.setInt(1, person_id + 1);
							insertPersonInfo.setString(2, simple.format(now));
							insertPersonInfo.setString(3, SignUpEnum.NAME.text.getText());
							insertPersonInfo.setString(4, year + month + day);
							insertPersonInfo.setString(5, text);
							insertPersonInfo.setString(6, pw.getText());
							insertPersonInfo.setInt(7, 0);

							insertPersonInfo.addBatch();

							int[] rows = insertPersonInfo.executeBatch();

							if (insertPersonInfo != null)
								insertPersonInfo.close();

							PreparedStatement read_name_ID_from_personInfo = conn.prepareStatement(
									"SELECT person_id, person_name FROM person_info where phone_number = ?");

							read_name_ID_from_personInfo.setString(1, text);

							ResultSet rs2 = read_name_ID_from_personInfo.executeQuery();

							while (rs2.next()) {
								person_id = rs2.getInt(1);
								person_name = rs2.getString(2);
							}

							// È¸¿ø¹øÈ£, ¼ºÇÔÀ» °¡ÀÔ¼º°ø ÆäÀÌÁö·Î º¸³»±â
							new ResultWindow(person_id, person_name);

							if (rs1 != null)
								rs1.close();
							if (read_MaxIDNum != null)
								read_MaxIDNum.close();
							if (rs2 != null)
								rs2.close();
							if (read_name_ID_from_personInfo != null)
								read_name_ID_from_personInfo.close();
							if (conn != null)
								conn.close();

							//System.out.println("¼º°ø");
						}

					} catch (SQLException e1) {
						 e1.printStackTrace();
						//System.out.println(e1.toString());

					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
						//System.out.println("[ojdbc] Å¬·¡½º °æ·Î°¡ Æ²·È½À´Ï´Ù.");
					}
				}
			}
		}
	}

}
