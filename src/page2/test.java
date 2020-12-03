package page2;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class test extends Page {

	private enum SelectType {
		TICKET, SEAT;
	}

	public test() {

		addTicketPanel();
		addSeatPanel();
		
	}

	private void addTicketPanel() {
		this.add(createGuidePanel(SelectType.TICKET, null), BorderLayout.NORTH);
	}

	private void addSeatPanel() {
		this.add(createGuidePanel(SelectType.SEAT, null));
	}

	private CommonGuidePanel createGuidePanel(final SelectType type, final String text) {

		final CommonGuidePanel guidePanel = new CommonGuidePanel(text, 0, 2);
		
		switch (type) {

		case TICKET : 
			initTicketPanel(guidePanel);		
			guidePanel.addItem(
					createTicketBtn("좌석 이용권", "image/seat.jpg"),
					createTicketBtn("사물함 이용권", "image/locker.png"));
			break;
			
		case SEAT :
			initSeatPanel(guidePanel);
			guidePanel.addItem(
					createSeatBtn("자리이동", "image/change.png"),
					createSeatBtn("퇴실하기", "image/out.png"));
			break;
		default :
		}
		return guidePanel;
	}


	private void initTicketPanel(final CommonGuidePanel guidePanel) {
		final int width = Display.WINDOWS_HALF_WIDTH * 4 / 5;
		guidePanel.setGuidePanelSize(width, Display.WINDOWS_AVALIABLE_HEIGHT * 11/25);
		guidePanel.setGuidePanelLocation((Display.WINDOWS_HALF_WIDTH - width) / 2,
				Display.WINDOWS_AVALIABLE_HEIGHT * 1/10);
	}

	private ImageTextButton createTicketBtn(final String text, final String imgPath) {
		final ImageTextButton imgTextBtn = new ImageTextButton(text, new ImageIcon(imgPath));
		imgTextBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
				//				KioskPage.getKioskOrderData().setEatPlace(place);

				if (text == "좌석이용권") {

					
					
				} else {

					
					
				}
			}
		});

		return imgTextBtn;
	}

	private void initSeatPanel(final CommonGuidePanel guidePanel) {
		final int width = Display.WINDOWS_HALF_WIDTH * 4 / 5;
		guidePanel.setGuidePanelSize(width, Display.WINDOWS_AVALIABLE_HEIGHT * 17 / 50);
		guidePanel.setGuidePanelLocation((Display.WINDOWS_HALF_WIDTH - width) / 2,
				Display.WINDOWS_AVALIABLE_HEIGHT * 28 / 50);
	}

	private ImageTextButton createSeatBtn(final String text,final String imgPath) {
		final ImageTextButton imgTextBtn = new ImageTextButton(text, new ImageIcon(imgPath));
		imgTextBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {

				if (text == "자리이동") {

				} else {

				}
			}
		});

		return imgTextBtn;
	}
}