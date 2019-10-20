package models;

import javax.swing.AbstractListModel;

import data.PaymentMethod;
import data.User;

public class PaymentMethodListModel extends AbstractListModel<PaymentMethod> {

	private User user;
	
	public PaymentMethodListModel(User u) {
		super();
	}
	
	@Override
	public int getSize() {
		return this.user.getPaymentMethods().size();
	}

	@Override
	public PaymentMethod getElementAt(int index) {
		return this.user.getPaymentMethods().get(index);
	}
	
}
