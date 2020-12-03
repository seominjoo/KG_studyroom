package page2;
import java.awt.Menu;
import java.util.ArrayList;
import java.util.List;

public class KioskOrderData {

	private final List<Menu> orderMenuList = new ArrayList<>();

	private class Calculator {

		private int getOrderQuantity() {
			return orderMenuList.size();
		}


		private final Calculator calculator = new Calculator();

		private _02dailyWeeklyEnum dailyWeekly;
		private _03whatHourEnum whatHour;

		public _02dailyWeeklyEnum dailyWeekly() {
			return dailyWeekly;
		}

		public void setDailyWeekly ( _02dailyWeeklyEnum a ) {
			dailyWeekly = a ;
		}

		public _03whatHourEnum whatHour() {
			return whatHour;
		}

		public void setPaymentPlace(_03whatHourEnum hour ) {
			whatHour = hour;
		}


		public void addMenu(Menu menu) {
			orderMenuList.add(menu);
		}

		public void clearMenu() {
			orderMenuList.clear();
		}

		public void emptyOrder() {
			orderMenuList.clear();
			dailyWeekly = null;
			whatHour = null;
		}

		public Menu[] getOrderMenuArray() {
			return orderMenuList.toArray(new Menu[orderMenuList.size()]);
		}

	}	
}
