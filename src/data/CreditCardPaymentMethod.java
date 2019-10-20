package data;

public class CreditCardPaymentMethod extends PaymentMethod {

	private String cardNumber;
	private int code;
	private int expMonth, expYear;
	
	private String cardType;
	
	public CreditCardPaymentMethod(String name, 
			String cardNumber,
			int code, 
			int expMonth,
			int expYear,
			String cardType) {
		
		super(name);
		this.cardNumber = cardNumber;
		this.code = code;
		this.expMonth = expMonth;
		this.expYear = expYear;
		this.cardType = cardType;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(int expMonth) {
		this.expMonth = expMonth;
	}

	public int getExpYear() {
		return expYear;
	}

	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}
	
}
