package loginFeature.signUpFails;

import java.awt.BorderLayout;
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

import loginFeature.SignUp;
import loginFeature.SignUpEnum;
import loginFeature.Style;
import loginFeature.SwingToolsSubPage;

public class NameFailWindow extends JFrame {

	private JPanel gridPanel21;
	private JButton confirmButton;
	private JPanel panelInGrid2;
	private JPanel gridPanel31InGrid1;
	private JLabel failLabel1;
	private JLabel failLabel2;
	private JLabel failLabel3;
	private String error;
	private static String[] errorList;
	
	static {
		errorList = new String[] {"�� ��", "���� ����", "��ȭ ��ȣ", "��� ��ȣ", "��� ��ȣ Ȯ��"};
	}
	
	public NameFailWindow(String error) {
		this.error = error;
		
		setLayout(new BorderLayout(0,20));
		add(Style.getnewPanel(),BorderLayout.NORTH);
		
		gridPanel21 = new JPanel();
		gridPanel21.setLayout(new GridLayout(2,1,0,0));
		new Style(gridPanel21);
		add(gridPanel21, BorderLayout.CENTER);
		
		gridPanel31InGrid1 = new JPanel(new GridLayout(3,1,0,0));
		new Style(gridPanel31InGrid1);
		
		failLabel1 = new JLabel("���� ����",JLabel.CENTER);
		new Style(failLabel1);
		gridPanel31InGrid1.add(failLabel1);
		
		failLabel2 = new JLabel("�ϱ� ������ Ȯ���ϼ���",JLabel.CENTER);
		new Style(failLabel2);
		gridPanel31InGrid1.add(failLabel2);
		
		for(String errorMatch : errorList) {
			if(this.error.equals(errorMatch)) {
				failLabel3 = new JLabel("["+this.error+"]",JLabel.CENTER);
				break;
			}
		}
		new Style(failLabel3);
		gridPanel31InGrid1.add(failLabel3);
		
		gridPanel21.add(gridPanel31InGrid1);
		
		panelInGrid2 = new JPanel();
		new Style(panelInGrid2);
		gridPanel21.add(panelInGrid2);
		panelInGrid2.setLayout(null);
		
		confirmButton = new JButton("Ȯ��");
		confirmButton.setBounds(121, 35, 110, 30);
		new Style(confirmButton);
		panelInGrid2.add(confirmButton);
		
		confirmButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
					dispose();
			}
		});

		SwingToolsSubPage.initTestFrame(this);
	}

}
