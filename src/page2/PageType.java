package page2;


public enum PageType {

	EMPTY, START, DAILY_WEEKLY, WHAT_HOUR, WHAT_WEEK, CONFIRM, 
	LOCKER, MOVE;
	
	 public Page emptyPage() {
		 Page page = null;
		 return page = new Page() {};	 
	 }
	 
//	 public _01start startPage() {		 
//		 _01start page = null;
//		 return page = new _01start() {};	 	 
//	 }
	 
	 public _02dailyWeekly dailyWeeklyPage() {	 
		 _02dailyWeekly page = null;
		 return page = new _02dailyWeekly() {};	 	 
	 }
	 
	 public _03whatHour whatHourPage() {	 
		 _03whatHour page = null;
		 return page = new _03whatHour() {};	 	 
	 }
	 
	 public _04whatWeek whatWeekPage() {	 
		 _04whatWeek page = null;
		 return page = new _04whatWeek() {};	 	 
	 }
	 
	 public _05confirm confirmPage() {	 
		 _05confirm page = null;
		 return page = new _05confirm() {};	 	 
	 }
	 
	 public _06locker lockerPage() {	 
		 _06locker page = null;
		 return page = new _06locker() {};	 	 
	 }
	 
	 public _07move movePage() {	 
		 _07move page = null;
		 return page = new _07move() {};	 	 
	 }
	
}
