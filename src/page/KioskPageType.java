package page;

public enum KioskPageType {

	EMPTY_PAGE, START_PAGE, EAT_PLACE_PAGE, PAYMENT_PLACE_PAGE, MENU_PAGE, CONFIRM_PAGE, PAYMENT_CARD_PAGE, END_PAGE;

	public KioskPage createMenuStartPage() {
		
		KioskPage kioskPage = null;
		kioskPage = new _MenuStart();
		return kioskPage;
		
	}
	
	
	
//	public KioskPage createPaymentPlacePage() {
//		KioskPage kioskPage = null;
//		kioskPage = new PaymentPlacePage();
//		return kioskPage;
//	}
//	
//	public KioskPage createMenuPage() {
//		KioskPage kioskPage = null;
//		kioskPage = new MenuPage();
//		return kioskPage;
//	}
//	
//	public KioskPage createConfirmPage() {
//		KioskPage kioskPage = null;
//		kioskPage = new PaymentPlacePage();
//		return kioskPage;
//	}
//	
//	
//	public KioskPage createKioskPage() {
//		KioskPage kioskPage = null;
//		if (this == EMPTY_PAGE) {
//			kioskPage = new KioskPage() {};
//		} else if (this == START_PAGE) {
//			kioskPage = new StartPage();
//		} else if (this == EAT_PLACE_PAGE) {
//			kioskPage = new EatPlacePage();
//		} else if (this == PAYMENT_PLACE_PAGE) {
//			kioskPage = new PaymentPlacePage();
//		} else if (this == MENU_PAGE) {
//			kioskPage = new MenuPage();
//		} else if (this == CONFIRM_PAGE) {
//			kioskPage = new ConfirmPage();
//		} else if (this == PAYMENT_CARD_PAGE) {
//			kioskPage = new PaymentCardPage();
//		} else if (this == END_PAGE) {
//			kioskPage = new EndPage();
//		}
//
//		return kioskPage;
//	}


}
