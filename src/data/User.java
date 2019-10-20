package data;

import java.util.ArrayList;

public class User {

	private String fullName, email;
	
	private AuthorizationService loginMethod;
	
	private ArrayList<PaymentMethod> paymentMethods;
	
	public User(String fullName, String email, AuthorizationService loginMethod) {
		this.fullName = fullName;
		this.email = email;
		this.loginMethod = loginMethod;
		
		this.paymentMethods = new ArrayList<>();
	}

	public AuthorizationService getLoginMethod() {
		return loginMethod;
	}

	public void setLoginMethod(AuthorizationService loginMethod) {
		this.loginMethod = loginMethod;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<PaymentMethod> getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(ArrayList<PaymentMethod> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}
		
}
