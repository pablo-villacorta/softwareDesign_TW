package data;

public class PaypalPaymentMethod extends PaymentMethod {

	private String email;
	
	public PaypalPaymentMethod(String name, String email) {
		super(name);
		this.email = email;
	}
	
}
