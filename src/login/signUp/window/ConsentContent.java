package login.signUp.window;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import login.design.Style;
import login.signUp.SignUpPage;
import login.swingTools.SwingToolsSubPage;

public class ConsentContent extends JFrame implements ActionListener {

	private JPanel panel;
	private JButton confirmButton;
	private JScrollPane scrollPane;
	private JLabel contentLabel;
	private int consentNum;
	private String content;
	int i;
	int x;

	public ConsentContent(int consentNum) {
		this.consentNum = consentNum;
		scrollPane = new JScrollPane();
		confirmButton = new JButton("동의");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		new Style(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(50, 50, 1215, 840);

		panel = new JPanel();

		panel.setLayout(null);
		new Style(panel);

		add(panel);

		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		scrollPane.setBounds(50, 36, 1100, 700);
		new Style(scrollPane);
		panel.add(scrollPane);

		if (consentNum == 1) {
			content = "<html>회원 이용약관\r\n"
					+ "1. 제이에스 스터디 라운지(JS STUDY LOUNGE)내의 스터디카페와 스터디룸 고객 등 라운지를 이용하는 분에게 본 약관을 준용합니다.\r\n" + "\r\n"
					+ "2. CCTV 작동중이나 개인 소지품은 잘 보관하시기 바라며, 퇴장 시에는 놓고 가시는 소지품이 없도록 주의하여 주시기 바랍니다. 습득물 및 개인사물함 수거물품 중 1개월 이상 장기 보관물품은 7일간 공고 후 일괄 폐기합니다.\r\n"
					+ "\r\n" + "3. 스터디카페는 회원 전용으로 회원 본인만 사용가능합니다. 가족이나 친구의 입장은 불가하며 타인에게 양도 또는 대여할 수 없습니다.\r\n" + "\r\n"
					+ "4. 스터디카페는 1인 1좌석제입니다. 친구의 좌석을 맡아 놓을 수 없으며, 자리를 이동하실 때에는 소지품도 함께 옮겨야 합니다.\r\n" + "\r\n"
					+ "5. 휴지와 쓰레기는 휴지통에 버려주시고 사용한 컵은 컵 놓는 곳에 놓아 주시기 바랍니다. 화장실은 라운지 전용으로 깨끗이 사용하여 주시기 바랍니다.\r\n" + "\r\n"
					+ "6. 책상 위 낙서 및 칼 사용은 금지되며, 시설물 훼손 시 원상복구에 필요한 배상책임이 있습니다.\r\n" + "\r\n"
					+ "7. 소란 및 외부 음식물 반입 등으로 타인에게 피해를 주는 행위를 금합니다.\r\n" + "가. 음료를 제외한 외부 음식물은 반입이 금지됩니다.\r\n"
					+ "나. 음주 후 입실을 금하며, 건물에서는 금주, 금연입니다.\r\n" + "(학생의 경우 관련기관 통보 및 강제퇴실 조치합니다.)\r\n"
					+ "다. 휴대전화는 무음모드로 하고 통화(대화)는 외부에서 하여야 합니다.\r\n" + "\r\n"
					+ "8. 기타 라운지 운영에 관한 사항은 본 라운지에서 정하는 바에 따라야 합니다.\r\n" + "\r\n"
					+ "9. 회원이 다음 각 호의 사유에 해당하는 경우, 회원자격을 상실할 수 있습니다.\r\n" + "\r\n" + "가. 부정한 방법이나 허위의 사실로 회원 등록한 경우\r\n"
					+ "나. 다른 회원의 이용을 방해하거나 공공질서와 선량한 풍속을 해할 우려가 있는 경우\r\n" + "다. 회원으로서의 자격을 지속시키는 것이 부적절하다고 판단되는 경우\r\n"
					+ "라. 본 약관에 위배되는 경우\r\n"
					+ "10. 회원 등록 후 회원 자격의 상실 또는 본인의 의사로 스터디카페 사용을 포기한 경우에는 아래의 반환기준에 따라 계산 후 잔액이 있을 경우 반환하여 드립니다.\r\n"
					+ "\r\n" + "구분    반환사유 발생일    반환 금액    비고\r\n" + "1개월 이내    사용 전    납부한 이용요금 전액\r\n"
					+ "이용 대상 일수 1/2 경과 전    시간권 이용요금(일 최대이용시간)으로 일할계산 후 잔액반환\r\n" + "이용 대상 일수 1/2 경과 후    반환하지 않음\r\n"
					+ "1개월 초과    사용 전    납부한 이용요금 전액\r\n" + "사용 중    월회원 정상요금으로 월할계산 후 잔액반환\r\n"
					+ "– 반환사유가 발생한 해당 월은 “1개월 이내”인 경우의 기준에 따라 산출\r\n"
					+ "* 할인요금은 장기사용에 따른 서비스 차원에서 제공되는 것으로 본인의사로 중도 포기한 경우에는 할인요금이 아닌 정상요금으로 월할 계산합니다.</html>";
		}

		String replaced1 = content.replace("\r\n", "<br/> ");
		//String replaced2 = replaced1.replace(".", ".<br/> ");
		String replaced3 = replaced1.replace("(", "<br/> (");
		String replaced4 = replaced3.replace(")", ")<br/> ");
		String replaced5 = replaced4.replace(",", ",<br/> ");

		contentLabel = new JLabel(replaced5);
		new Style(contentLabel);
		contentLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		scrollPane.setViewportView(contentLabel);

		
		confirmButton.setBounds(550, 755, 145, 23);
		new Style(confirmButton);
		confirmButton.setEnabled(false);
		panel.add(confirmButton);

		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {

				if (scrollPane.getVerticalScrollBar().getValue() < 323) {
					confirmButton.setEnabled(false);
				} else {
					confirmButton.setEnabled(true);
					confirmButton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if (e.getButton() == MouseEvent.BUTTON1) {
								if (scrollPane.getVerticalScrollBar().getValue() >= 323) {
									SignUpPage.consentBox.setEnabled(true);
									SignUpPage.consentBox.setSelected(true);
									dispose();
								}
							}
						}
					});
				}

			}
		});

	}

}