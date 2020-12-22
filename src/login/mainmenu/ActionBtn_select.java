package login.mainmenu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

import login.page.MainPage;

public class ActionBtn_select extends JFrame implements ActionListener {

	JButton btn;
	int on = 0;
	String type;

	public ActionBtn_select(JButton btn, String type) {
		this.btn = btn;
		this.type = type;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (on++ % 2 == 0) {
			if (type.equals("����")) {
				for (JButton seat_btn : _08reservation.seats_btn) {
					if (seat_btn.isSelected() == true) { // �̹� �ִ� �¼��� ����
						seat_btn.setBackground(Color.BLACK);
						seat_btn.setForeground(Color.orange);
						seat_btn.setSelected(false);
					}
				}
				for (JButton room_btn : _08reservation.room_btn) {
					if (room_btn.isSelected() == true) { // �̹� �ִ� �¼��� ����
						room_btn.setBackground(Color.BLACK);
						room_btn.setForeground(Color.orange);
						room_btn.setSelected(false);
					}
				}
				for (JButton locker_btn : _08reservation.locker_btn) {
					if (locker_btn.isSelected() == true) { // �̹� �ִ� �¼��� ����
						locker_btn.setBackground(Color.BLACK);
						locker_btn.setForeground(Color.orange);
						locker_btn.setSelected(false);
					}
				}
			} else if (type.equals("�̵�")) {
				for (JButton seats_btn : _06move_selectSeat.seats_btn) {
					if (seats_btn.isSelected() == true) { // �̹� �ִ� �¼��� ����
						seats_btn.setBackground(Color.BLACK);
						seats_btn.setForeground(Color.orange);
						seats_btn.setSelected(false);
					}
				}
				for (JButton room_btn : _06move_selectSeat.room_btn) {
					if (room_btn.isSelected() == true) { // �̹� �ִ� �¼��� ����
						room_btn.setBackground(Color.BLACK);
						room_btn.setForeground(Color.orange);
						room_btn.setSelected(false);
					}
				}
			} else if (type.equals("����")) {
				for (JButton seats_btn : _07in_selectSeat.seats_btn) {
					if (seats_btn.isSelected() == true) { // �̹� �ִ� �¼��� ����
						seats_btn.setBackground(Color.BLACK);
						seats_btn.setForeground(Color.orange);
						seats_btn.setSelected(false);
					}
				}
			}
			btn.setBackground(Color.orange);
			btn.setForeground(Color.BLACK);
			btn.setSelected(true);
			System.out.println();

			on = 0;
		}
//      else {
//         btn.setBackground(Color.BLACK);
//         btn.setForeground(Color.orange);
//         btn.setSelected(false);
//
//      }
		System.out.println(on);
	}

}