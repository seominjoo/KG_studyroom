package login.signUp;

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

import login.BirthEnum;
import login.PhoneNumberEnum;
import login.mainmenu.Time;
import login.signUp.window.ResultWindow;


public class ClickSignUp extends MouseAdapter {

	static int person_id;
	static String person_name;

	static String phoneNumber;
//	 java.sql.Timestamp timestamp 
//     = java.sql.Timestamp.valueOf(LocalDateTime.of(
//              2020,
//              01,
//              01,
//              00,
//              00,
//              00,
//              000000
//           ));

	   

//
//	Container card_panel;
//	
//	public ClickSignUp(Container card_panel) {
//		this.card_panel = card_panel;
//	}

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
		String text = phoneNumber1.getText() + "-" + phoneNumber2.getText()
				+ "-" + phoneNumber3.getText();

		pw = SignUpEnum.PASSWORD.blindPW;
		pwConfirm = SignUpEnum.PASSWORDCONFIRM.blindPW;

		if (e.getButton() == MouseEvent.BUTTON1) {
			if (!Pattern.matches("[��-�R]{2,4}", SignUpEnum.NAME.text.getText())) {
				new ResultWindow("�� ��");
				SignUpEnum.NAME.text.setText("");;
			}
			else if (!Pattern.matches("[0-9]{8}", year + month + day)) {
				new ResultWindow("���� ����");
			} 
			else if (!(Pattern.matches("01[0-9]", phoneNumber1.getText())
					&& Pattern.matches("[0-9]{4}", phoneNumber2.getText())
					&& Pattern.matches("[0-9]{4}", phoneNumber3.getText()))) {
				new ResultWindow("��ȭ ��ȣ");
				phoneNumber1.setText("");
				phoneNumber2.setText("");
				phoneNumber3.setText("");				
			}
			else if (!Pattern.matches("^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z]).{8,12}$", pw.getText())) {
				new ResultWindow("��� ��ȣ");
				pw.setText("");
				pwConfirm.setText("");
			}
			else if (!pw.getText().equals(pwConfirm.getText()) || pw.getText().equals(SignUpEnum.PASSWORDCONFIRM.labelName)) {
				new ResultWindow("��� ��ȣ Ȯ��");
				pwConfirm.setText("");
			}
			else {
				// ��� üũ
				for (Entry<JCheckBox, JButton> kv : SignUpPage.consent.entrySet()) {
					if (!kv.getKey().isSelected()) {
						consentCheck = false;
						new ResultWindow("��� ����");
						break;
					} 
				}

				if (consentCheck) {

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
							phoneNumber = rs.getString(1);
							if (text.equals(phoneNumber)) {
								new ResultWindow("��ȭ ��ȣ �ߺ�");
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
							insertPersonInfo.setString(5, pw.getText());
							insertPersonInfo.setInt(6, 0);
//							insertPersonInfo.setTimestamp(7, timestamp);
//							insertPersonInfo.setTimestamp(8, timestamp);
//							insertPersonInfo.setTimestamp(9,timestamp);
//							insertPersonInfo.setString(10, "x");
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

							new ResultWindow(person_id,person_name);

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
