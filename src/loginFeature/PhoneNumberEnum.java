package loginFeature;

public enum PhoneNumberEnum {

	PHONENUMBER1("010",0),
	PHONENUMBER2("",1),
	PHONENUMBER3("",2),;

	String initialStatement;
	int index;
	
	PhoneNumberEnum(String initialStatement, int index) {
		this.initialStatement = initialStatement;
		this.index = index;
	}
	
	
	
}
