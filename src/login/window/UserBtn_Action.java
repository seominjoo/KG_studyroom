package login.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.Format;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import login.design.Conversion_image;
import login.design.Style;
import login.loginDataBase.DBLoggedIn;
import login.mainmenu.Time;
import login.mainmenu._00main;
import login.mainmenu._01start;
import login.mainmenu._02dayOrWeek;
import login.mainmenu._02dayRoom;
import login.mainmenu._03whatHour;
import login.mainmenu._05locker;
import login.mainmenu._06move;
import login.mainmenu._07in_seletSeat;
import login.mainmenu._07out;
import login.mainmenu._08reservation;
import login.page.AdminPage;
import login.page.LoginPage;
import login.page.MainPage;
import login.signUp.SignUpPage;
import login.swingTools.SwingToolsSubPage;
import login.window.Login_SwingTool;

public class UserBtn_Action implements ActionListener {

	JButton userBtn;

	public UserBtn_Action(JButton userBtn) {
		this.userBtn = userBtn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String label1 = null;
		String label2 = null;

		if (userBtn.getText().equals("�̿�Ǳ���")) { // �̿�� ������
			MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
			MainPage.user_cards.show(MainPage.user_page_panel, "�̿�Ǳ���");
			MainPage.userToggle = "�̿�Ǳ���";

		} else if (userBtn.getText().equals("�ڸ��̵�")) { // �ڸ��̵� ������
			if ((_00main.seat_chk > 0 && _00main.seat_chk < 21)
					|| (_00main.room_chk >= 101 && _00main.room_chk <= 104)) {
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "�ڸ��̵�");
				MainPage.userToggle = "�ڸ��̵�";
			} else {
				String msg = "��� ���� �¼��� �����ϴ�.";
				JOptionPane.showMessageDialog(null, msg);
			}

		} else if (userBtn.getText().equals("����ϱ�")) { // ��� ������
			MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
			MainPage.user_cards.show(MainPage.user_page_panel, "����ϱ�");
			MainPage.userToggle = "����ϱ�";

		} else if (userBtn.getText().equals("�Խ��ϱ�")) { // �Խ� ������(�����̿�� �̿���)

			if (_00main.seat_chk > 0) {
				String msg = "�̹� �¼��� �ֽ��ϴ�.";
				JOptionPane.showMessageDialog(null, msg);
			} else if (_00main.type.equals("���� �̿��")) {
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "�Խ��ϱ�");
				MainPage.userToggle = "�Խ��ϱ�";
			} else {
				String msg1 = "���� �̿�� �̿��ڸ� �����մϴ�";
				JOptionPane.showMessageDialog(null, msg1);
			}
		}else if (userBtn.getText().equals("���� �̿��")) { // ���� ������(�����̿��)
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "���ϱǰ���ǥ");
				MainPage.userToggle = "���ϱǰ���ǥ";

		} else if (userBtn.getText().equals("2�ð� (3,000��)")) { // ���� ������(2�ð� �̿��)
			MainPage.ss = _03whatHour.time_now.plusHours(2);
			MainPage.price = 3000;
			MainPage.seat_type = "2�ð� �̿�� (1�μ�)";
			MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
			MainPage.user_cards.show(MainPage.user_page_panel, "����������");
			MainPage.userToggle = "����������";

		} else if (userBtn.getText().equals("4�ð� (2,000��)")) { // ���� ������(4�ð� �̿��)
			MainPage.ss = _03whatHour.time_now.plusHours(4);
			MainPage.price = 4500;
			MainPage.seat_type = "4�ð� �̿�� (1�μ�)";
			MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
			MainPage.user_cards.show(MainPage.user_page_panel, "����������");
			MainPage.userToggle = "����������";

		} else if (userBtn.getText().equals("6�ð� (2,000��)")) { // ���� ������(6�ð� �̿��)
			MainPage.ss = _03whatHour.time_now.plusHours(6);
			MainPage.price = 6000;
			MainPage.seat_type = "6�ð� �̿�� (1�μ�)";
			MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
			MainPage.user_cards.show(MainPage.user_page_panel, "����������");
			MainPage.userToggle = "����������";

		} else if (userBtn.getText().equals("8�ð� (2,000��)")) { // ���� ������(8�ð� �̿��)
			MainPage.ss = _03whatHour.time_now.plusHours(8);
			MainPage.price = 7500;
			MainPage.seat_type = "8�ð� �̿�� (1�μ�)";
			MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
			MainPage.user_cards.show(MainPage.user_page_panel, "����������");
			MainPage.userToggle = "����������";

		} else if (userBtn.getText().equals("12�ð� (2,000��)")) { // ���� ������(12�ð� �̿��)
			MainPage.ss = _03whatHour.time_now.plusHours(12);
			MainPage.price = 10000;
			MainPage.seat_type = "12�ð� �̿�� (1�μ�)";
			MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
			MainPage.user_cards.show(MainPage.user_page_panel, "����������");
			MainPage.userToggle = "����������";

		} 
		else if (userBtn.getText().equals("���� �̿��")) { // ���� ������(�����̿��)
			MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
			MainPage.user_cards.show(MainPage.user_page_panel, "����ǰ���ǥ");
			MainPage.userToggle = "����ǰ���ǥ";

		} else if (userBtn.getText().equals("�¼� �̿��")) { // 1�μ� ������
			if (_00main.seat_chk > 0) {
				String msg = "������ �¼��� �̹� �����մϴ�";
				JOptionPane.showMessageDialog(null, msg);
			} else if (_00main.type.equals("���� �̿��")) {
				String msg = "���� �̿�� �̿��ڴ� �Խ��� �̿��ϼ���";
				JOptionPane.showMessageDialog(null, msg);
			} else {
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "�¼��̿��");
				MainPage.userToggle = "�¼��̿��";
			}

		} else if (userBtn.getText().equals("�繰�� �̿��")) { // ��� ������
			try {// �繰�Ը���ð��� �������� ���� �Ұ�
				String sql = "SELECT expiration_locker from person_info where login_state='On'";
				PreparedStatement pstmt = _01start.conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {

					Timestamp time_chk = rs.getTimestamp("expiration_locker");
					if (LocalDateTime.now().isBefore(Time.TimeStampTOlocalDateTime(time_chk))) {
						String msg = "������ �繰���� �̹� �����մϴ�";
						JOptionPane.showMessageDialog(null, msg);
					} else {
						MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
						MainPage.user_cards.show(MainPage.user_page_panel, "�繰���̿��");
						MainPage.userToggle = "�繰���̿��";
					}
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else if (userBtn.getText().equals("�� �̿��")) { // ���̿� ������
			try {// �븸��ð��� �������� ���� �Ұ�
				String sql = "SELECT expiration_room from person_info where login_state='On'";
				PreparedStatement pstmt = _01start.conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					Timestamp time_chk = rs.getTimestamp("expiration_room");
					if (LocalDateTime.now().isBefore(Time.TimeStampTOlocalDateTime(time_chk))) {
						String msg = "������ ���� �̹� �����մϴ�";
						JOptionPane.showMessageDialog(null, msg);
					} else {

						MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
						MainPage.user_cards.show(MainPage.user_page_panel, "���̿��");
						MainPage.userToggle = "���̿��";
					}
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else if (userBtn.getText().equals("2�ð� (12,000��)")) { // ���� ������(2�ð� �̿��)
			MainPage.ss = _03whatHour.time_now.plusHours(2);
			MainPage.price = 12000;
			MainPage.seat_type = "2�ð� �̿�� (��)";
			MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
			MainPage.user_cards.show(MainPage.user_page_panel, "����������");
			MainPage.userToggle = "����������";

		} else if (userBtn.getText().equals("4�ð� (18,000��)")) { // ���� ������(4�ð� �̿��)
			MainPage.ss = _03whatHour.time_now.plusHours(4);
			MainPage.price = 18000;
			MainPage.seat_type = "4�ð� �̿�� (��)";
			MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
			MainPage.user_cards.show(MainPage.user_page_panel, "����������");
			MainPage.userToggle = "����������";

		} else if (userBtn.getText().equals("6�ð� (24,000��)")) { // ���� ������(6�ð� �̿��)
			MainPage.ss = _03whatHour.time_now.plusHours(6);
			MainPage.price = 24000;
			MainPage.seat_type = "6�ð� �̿�� (��)";
			MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
			MainPage.user_cards.show(MainPage.user_page_panel, "����������");
			MainPage.userToggle = "����������";

		} else if (userBtn.getText().equals("8�ð� (30,000��)")) { // ���� ������(8�ð� �̿��)
			MainPage.ss = _03whatHour.time_now.plusHours(8);
			MainPage.price = 30000;
			MainPage.seat_type = "8�ð� �̿�� (��)";
			MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
			MainPage.user_cards.show(MainPage.user_page_panel, "����������");
			MainPage.userToggle = "����������";

		} else if (userBtn.getText().equals("12�ð� (36,000��)")) { // ���� ������(12�ð� �̿��)
			MainPage.ss = _03whatHour.time_now.plusHours(12);
			MainPage.price = 36000;
			MainPage.seat_type = "12�ð� �̿�� (��)";
			MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
			MainPage.user_cards.show(MainPage.user_page_panel, "����������");
			MainPage.userToggle = "����������";

		} else if (userBtn.getText().equals("����")) { // ��� ������

			MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
			MainPage.user_cards.show(MainPage.user_page_panel, "����ϱ�");
			MainPage.userToggle = "����";

		} else if (userBtn.getText().equals("����")) { // ��� ������
			MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
			MainPage.user_cards.show(MainPage.user_page_panel, "����ϱ�");
			MainPage.userToggle = "����";

		} else if (userBtn.getText().equals("���� ȭ��")) { // ���� ������
			System.out.println("���� ȭ�� ���� ���� ȭ�� : " + MainPage.userToggle);
			if (MainPage.userToggle.equals("����������")) { // �¼��϶�
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "�¼��̿��");
				MainPage.userToggle = "�¼��̿��";
			} else if (MainPage.userToggle.equals("�¼��̿��")) {
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "�̿�Ǳ���");
				MainPage.userToggle = "�̿�Ǳ���";
			} else if (MainPage.userToggle.equals("�̿�Ǳ���")) {
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "���θ޴�");
				MainPage.userToggle = "���θ޴�";
			} else if (MainPage.userToggle.equals("�̿�Ǳ���")) {
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "���θ޴�");
				MainPage.userToggle = "���θ޴�";
			} else if (MainPage.userToggle.equals("���̿��")) {
				MainPage.main_cards.show(MainPage.main_page_panel, "����ڸ޴�");
				MainPage.user_cards.show(MainPage.user_page_panel, "�̿�Ǳ���");
				MainPage.userToggle = "�̿�Ǳ���";
			}
		} else {
			// ������ �غ���
			label1 = "[system] still in maintenance";
			label2 = "������ �غ� ��..";
			new SubWindow(label1, label2);
		}
	}

}
