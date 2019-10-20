package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import data.CreditCardPaymentMethod;
import data.PaymentMethod;
import data.PaypalPaymentMethod;
import data.User;

public class ProfilePage extends JPanel {

	private User user;

	private JLabel accountTypeLabel; // google, facebook, twitter
	private JLabel emailLabel;
	private JTextField fullNameField;
	
	private JButton backButton;

	private JButton historyButton;
	private JList<PaymentMethod> paymentMethodsList;
	private JButton addCreditCardButton, addPaypalButton, deletePaymentMethodButton;

	private JPanel topPanel, leftPanel, rightPanel;

	public ProfilePage(User user) {
		super();
		this.user = user;

		this.setLayout(new BorderLayout());

		JPanel mainPanel;
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

		initPanels();
		addListeners();

		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);

		this.add(topPanel, BorderLayout.NORTH);
		this.add(mainPanel, BorderLayout.CENTER);
	}

	private void initPanels() {
		initComponents();
		initTopPanel();
		initLeftPanel();
		rightPanel.setLayout(new BorderLayout());
	}
	
	private void initTopPanel() {
		topPanel = new JPanel(new BorderLayout());
		backButton = new JButton("Back");
		topPanel.add(backButton, BorderLayout.WEST);
		topPanel.setBorder(new EmptyBorder(10,10,10,10));
	}

	private void initLeftPanel() {
		leftPanel.setLayout(new BorderLayout());
		leftPanel.setBorder(new EmptyBorder(10,10,10,10));
		initTopLeftPanel();
		initBottomLeftPanel();
	}

	private void initTopLeftPanel() {
		JPanel basicInfoPanel = new JPanel(new BorderLayout());
		JPanel formPanel = new JPanel(new GridLayout(3, 2));
		JPanel historyButtonPanel = new JPanel(new BorderLayout());

		formPanel.add(new JLabel("Full Name: "));
		formPanel.add(fullNameField);
		formPanel.add(new JLabel("e-mail: "));
		formPanel.add(emailLabel);
		formPanel.add(new JLabel("Account type: "));
		formPanel.add(accountTypeLabel);

		historyButtonPanel.add(historyButton, BorderLayout.EAST);

		basicInfoPanel.add(formPanel, BorderLayout.CENTER);
		basicInfoPanel.add(historyButtonPanel, BorderLayout.SOUTH);
		leftPanel.add(basicInfoPanel, BorderLayout.NORTH);
	}

	private void initBottomLeftPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel listPanel = new JPanel(new FlowLayout());
		JPanel buttonsPanel = new JPanel(new FlowLayout());

		listPanel.add(paymentMethodsList);

		buttonsPanel.add(deletePaymentMethodButton);
		buttonsPanel.add(addCreditCardButton);
		buttonsPanel.add(addPaypalButton);

		mainPanel.add(new JLabel("Your payment methods: "), BorderLayout.NORTH);
		mainPanel.add(listPanel, BorderLayout.CENTER);
		mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

		leftPanel.add(mainPanel, BorderLayout.CENTER);
	}

	private void initComponents() {
		fullNameField = new JTextField(this.user.getFullName());
		emailLabel = new JLabel(this.user.getEmail());
		accountTypeLabel = new JLabel(this.user.getLoginMethod().toString());

		historyButton = new JButton("Booking history");

		DefaultListModel<PaymentMethod> listModel = new DefaultListModel<>();
		for (PaymentMethod p: user.getPaymentMethods()) {
			listModel.addElement(p);
		}
		paymentMethodsList = new JList<>();
		paymentMethodsList.setModel(listModel);

		addCreditCardButton = new JButton("Add credit card");
		addPaypalButton = new JButton("Add PayPal account");
		deletePaymentMethodButton = new JButton("Delete payment method");
	}
	
	private void addListeners() {
		paymentMethodsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					PaymentMethod m = paymentMethodsList.getSelectedValue();
					if (m instanceof CreditCardPaymentMethod) {
						rightPanel.removeAll();
						rightPanel.add(new CreditCardPanel((CreditCardPaymentMethod) m));
						rightPanel.revalidate();
					} else {
						rightPanel.removeAll();
						rightPanel.add(new PaypalPanel((PaypalPaymentMethod) m));
						rightPanel.revalidate();
					}
				}
			}
		});
		
		addCreditCardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rightPanel.removeAll();
				rightPanel.add(new CreditCardPanel(null));
				rightPanel.revalidate();
			}
		});
		
		addPaypalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rightPanel.removeAll();
				rightPanel.add(new PaypalPanel(null));
				rightPanel.revalidate();
			}
		});
		
		historyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window.window.setPage(Window.window.historyPage);
			}
		});
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window.window.setPage(Window.window.searchPage);
			}
		});
	}

	private abstract class PaymentMethodPanel extends JPanel {

		protected JPanel infoPanel, buttonPanel;

		public PaymentMethodPanel() {
			super();
			this.setBorder(new EmptyBorder(10,10,10,10));
			this.setLayout(new BorderLayout());
			this.infoPanel = new JPanel();
			this.buttonPanel = new JPanel(new BorderLayout());
			JPanel mainPanel = new JPanel(new BorderLayout());
			mainPanel.add(infoPanel, BorderLayout.NORTH);
			mainPanel.add(buttonPanel, BorderLayout.CENTER);
			this.add(mainPanel, BorderLayout.NORTH);
		}
		
		protected abstract void initComponents();

	}


	private class CreditCardPanel extends PaymentMethodPanel {

		CreditCardPaymentMethod method;

		private JTextField cardTypeField,
		cardNumberField,
		cardCodeField,
		cardDateField,
		methodNameField;

		private JButton modifyAddButton; // add or modify method

		public CreditCardPanel(CreditCardPaymentMethod m) {
			super();
			this.method = m;
			infoPanel.setLayout(new GridLayout(6, 2));

			initComponents();

			infoPanel.add(new JLabel("Payment method name: "));
			infoPanel.add(methodNameField);
			infoPanel.add(new JLabel("Payment method type: "));
			infoPanel.add(new JLabel("Credit Card"));
			infoPanel.add(new JLabel("Card type: "));
			infoPanel.add(cardTypeField);
			infoPanel.add(new JLabel("Card number: "));
			infoPanel.add(cardNumberField);
			infoPanel.add(new JLabel("Card code field: "));
			infoPanel.add(cardCodeField);
			infoPanel.add(new JLabel("Expiration date:"));
			infoPanel.add(cardDateField);

			buttonPanel.add(modifyAddButton, BorderLayout.EAST);
		}

		protected void initComponents() {
			cardTypeField = new JTextField();
			methodNameField = new JTextField();
			cardNumberField = new JTextField();
			cardCodeField = new JTextField();
			cardDateField = new JTextField();
			modifyAddButton = new JButton("Add method");

			if (this.method != null) {
				cardTypeField.setText(this.method.getCardType());
				methodNameField.setText(this.method.getName());
				cardNumberField.setText(this.method.getCardNumber());
				cardCodeField.setText(this.method.getCode()+"");
				cardDateField.setText(this.method.getExpMonth()+"/"+this.method.getExpYear());
				modifyAddButton.setText("Modify method");;
			}
		}

	}

	private class PaypalPanel extends PaymentMethodPanel {

		private PaypalPaymentMethod method;
		
		private JTextField emailField, methodNameField;
		private JPasswordField passwordField;
		
		private JButton modifyAddButton;
		
		public PaypalPanel(PaypalPaymentMethod m) {
			super();
			this.method = m;
			infoPanel.setLayout(new GridLayout(4, 2));
			
			initComponents();
			
			infoPanel.add(new JLabel("Payment method name: "));
			infoPanel.add(methodNameField);
			infoPanel.add(new JLabel("Payment method type: "));
			infoPanel.add(new JLabel("PayPal"));
			infoPanel.add(new JLabel("PayPal e-mail: "));
			infoPanel.add(emailField);
			infoPanel.add(new JLabel("PayPal password: "));
			infoPanel.add(passwordField);
			
			buttonPanel.add(modifyAddButton, BorderLayout.EAST);

		}
		
		protected void initComponents() {
			methodNameField = new JTextField();
			emailField = new JTextField();
			passwordField = new JPasswordField();
			modifyAddButton = new JButton("Add method");
			
			if (this.method != null) {
				methodNameField.setText(this.method.getName());
				emailField.setText(this.method.getName());
				modifyAddButton.setText("Modify method");
			}
		}
		
	}

}
