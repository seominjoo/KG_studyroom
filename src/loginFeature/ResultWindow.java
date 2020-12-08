package loginFeature;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ResultWindow extends JFrame {

	private JPanel gridPanel21;
	private JButton confirmButton;
	private JPanel panelInGrid2;
	private JPanel gridPanel31InGrid1;
	private JLabel failLabel1;
	private JLabel failLabel2;
	private JLabel failLabel3;
	private String error;
	private static String[] errorList;
	private int person_id;
	private String person_name;
	private boolean result = false;

	static {
		errorList = new String[] { "성 함", "생년 월일", "전화 번호", "비밀 번호", "비밀 번호 확인", "약관 동의", "전화 번호 중복" };
	}

	public ResultWindow(int person_id, String person_name, boolean result) {
		this.person_id = person_id;
		this.person_name = person_name;
		this.result = result;
		ResultCalc();
	}

	public ResultWindow(String error) {
		this.error = error;
		ResultCalc();
	}

	void ResultCalc() {
		SwingToolsSubPage.initTestFrame(this);
		getContentPane().setBackground(Color.decode("#404040"));
		setLayout(new BorderLayout(0, 20));
		add(Style.getnewPanel(), BorderLayout.NORTH);

		gridPanel21 = new JPanel();
		gridPanel21.setLayout(new GridLayout(2, 1, 0, 0));
		new Style(gridPanel21);
		add(gridPanel21, BorderLayout.CENTER);

		gridPanel31InGrid1 = new JPanel(new GridLayout(3, 1, 0, 0));
		new Style(gridPanel31InGrid1);

		if (result) {
			failLabel1 = new JLabel("가입 성공", JLabel.CENTER);
			failLabel2 = new JLabel("성 함 : " + this.person_name + " 님", JLabel.CENTER);
			failLabel3 = new JLabel("[ 회원 번호 : " + this.person_id+" ]", JLabel.CENTER);
		} else {
			failLabel1 = new JLabel("가입 실패", JLabel.CENTER);
			failLabel2 = new JLabel("하기 사항을 확인하세요", JLabel.CENTER);
			for (String errorMatch : errorList) {
				if (this.error.equals(errorMatch)) {
					failLabel3 = new JLabel("[ " + this.error + " ]", JLabel.CENTER);
					break;
				}
			}
		}

		new Style(failLabel1);
		gridPanel31InGrid1.add(failLabel1);
		new Style(failLabel2);
		gridPanel31InGrid1.add(failLabel2);
		new Style(failLabel3);
		gridPanel31InGrid1.add(failLabel3);

		gridPanel21.add(gridPanel31InGrid1);

		panelInGrid2 = new JPanel();
		new Style(panelInGrid2);
		gridPanel21.add(panelInGrid2);
		panelInGrid2.setLayout(null);

		confirmButton = new JButton("확인");
		confirmButton.setBounds(121, 35, 110, 30);
		new Style(confirmButton);
		panelInGrid2.add(confirmButton);

		confirmButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1)
					dispose();
			}
		});

	}

}
