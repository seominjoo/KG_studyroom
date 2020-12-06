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

import loginFeature.signUpFails.BirthFailWindow;
import loginFeature.signUpFails.ConsentFailWindow;
import loginFeature.signUpFails.NameFailWindow;
import loginFeature.signUpFails.PWnoMatch;
import loginFeature.signUpFails.PassWordFailWindow;
import loginFeature.signUpFails.PhoneNumberFailWindow;
import loginFeature.signUpFails.SamePhoneNumberFail;

public class ClickSignUp extends MouseAdapter {

	static int person_id;
	static String person_name;
	static String pw;
	static String pwConfirm;
	static String phoneNumber;

	boolean consentFlag = true;
//
//	Container card_panel;
//	
//	public ClickSignUp(Container card_panel) {
//		this.card_panel = card_panel;
//	}
	static String year;
	static String month;
	static String day;

	public ClickSignUp(Object year, Object month, Object day) {
		this.year = year.toString();
		this.month = month.toString();
		this.day = day.toString();
	}

	public ClickSignUp() {

	}
	// ���~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	@Override
	public void mouseClicked(MouseEvent e) {
		boolean samePhoneNumber = false;
		String text = PhoneNumberEnum.PHONENUMBER1.text.getText() + "-" + PhoneNumberEnum.PHONENUMBER2.text.getText()
				+ "-" + PhoneNumberEnum.PHONENUMBER3.text.getText();

		pw = SignUpEnum.PASSWORD.blindPW.getText();
		pwConfirm = SignUpEnum.PASSWORDCONFIRM.blindPW.getText();

		if (e.getButton() == MouseEvent.BUTTON1) {
			if (!Pattern.matches("[��-�R]{2,4}", SignUpEnum.NAME.text.getText()))
				new NameFailWindow();
			else if (!Pattern.matches("[0-9]{6,8}", year + month + day)) {
				new BirthFailWindow();
			} else if (!(Pattern.matches("01[0-9]", PhoneNumberEnum.PHONENUMBER1.text.getText())
					&& Pattern.matches("[0-9]{4}", PhoneNumberEnum.PHONENUMBER2.text.getText())
					&& Pattern.matches("[0-9]{4}", PhoneNumberEnum.PHONENUMBER3.text.getText())))
				new PhoneNumberFailWindow();
			else if (!Pattern.matches("^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z]).{8,12}$", pw))
				new PassWordFailWindow();
			else if (!pw.equals(pwConfirm) || pw.equals(SignUpEnum.PASSWORDCONFIRM.labelName))
				new PWnoMatch();
			else {
				// ��� üũ
				for (Entry<JCheckBox, JButton> kv : SignUp.consent.entrySet()) {
					if (!kv.getKey().isSelected()) {
						new ConsentFailWindow();
						consentFlag = false;
						break;
					} else
						consentFlag = true;
				}

				if (consentFlag) {

					try {
						// ���� �� ����ǥ�������� �Ÿ���
						// �̸�, �������, �޴���, ���,

						Class.forName("oracle.jdbc.driver.OracleDriver");

						Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "hr",
								"1234");

						conn.setAutoCommit(false);
						//
						PreparedStatement read_PhoneNumber = conn
								.prepareStatement("SELECT phone_number FROM person_info");

						ResultSet rs = read_PhoneNumber.executeQuery();

						while (rs.next()) {
							phoneNumber = rs.getString("phone_number");
							if (phoneNumber.equals(text)) {
								new SamePhoneNumberFail();

								samePhoneNumber = true;
								break;
							}
						}

						if (rs != null)
							rs.close();
						if (read_PhoneNumber != null)
							read_PhoneNumber.close();

						if (!samePhoneNumber) {
							PreparedStatement insertPersonInfo = conn.prepareStatement("INSERT INTO Person_Info "
									+ "(Person_Id,Check_Time,Person_Name, person_birth, Phone_Number,PW,Total_Payment)"
									+ " VALUES(SignUpSeq.nextval, ?, ?, ?, ?, ?, ?)");

							// Batch : �ϰ�ó��
							DateFormat simple = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
							Date now = new Date();

							insertPersonInfo.setString(1, simple.format(now));
							insertPersonInfo.setString(2, SignUpEnum.NAME.text.getText());
							insertPersonInfo.setString(3, year + month + day);
							insertPersonInfo.setString(4, text);
							insertPersonInfo.setString(5, pw);
							insertPersonInfo.setInt(6, 0);
							insertPersonInfo.addBatch();

							int[] rows = insertPersonInfo.executeBatch();

							if (insertPersonInfo != null)
								insertPersonInfo.close();

							PreparedStatement read_name_ID_from_personInfo = conn.prepareStatement(
									"SELECT person_id, person_name FROM person_info order by check_time");

							ResultSet rs2 = read_name_ID_from_personInfo.executeQuery();

							while (rs2.next()) {
								person_id = rs2.getInt(1);
								person_name = rs2.getString(2);
							}

							new SignUpSuccessWindow(person_id, person_name);

							if (rs2 != null)
								rs2.close();
							if (read_name_ID_from_personInfo != null)
								read_name_ID_from_personInfo.close();
							if (conn != null)
								conn.close();

							System.out.println("����");
						}

					} catch (SQLException e1) {
						// e1.printStackTrace();
						System.out.println(e1.toString());
						// new SignUpFailWindow(e1);

					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
						System.out.println("[ojdbc] Ŭ���� ��ΰ� Ʋ�Ƚ��ϴ�.");
					}
				}
			}
		}
	}

}
