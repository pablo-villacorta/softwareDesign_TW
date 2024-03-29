package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.*;

import javax.swing.UIManager.*;

import data.AuthorizationService;
import data.CreditCardPaymentMethod;
import data.PaypalPaymentMethod;
import data.User;

public class Window extends JFrame {

	private Container cp;
	
	public SearchPage searchPage;
	public LoginPage loginPage;
	public RegisterPage registerPage;
	public HistoryPage historyPage;
	public ProfilePage profilePage;
	
	public User user;
	
	public static Window window;
	
	public Window() {
		super("EasyBooking");
		setLookAndFeel();
		
		//example user
		user = new User("John Doe", "john@doe.com", AuthorizationService.GOOGLE);
		for (int i = 0; i < 10; i++) {
			CreditCardPaymentMethod p = new CreditCardPaymentMethod("method "+i, "xxxx xxxx xxxx xxxx", 333, 2, 22, "VISA");
			user.getPaymentMethods().add(p);
		}
		for (int i = 0; i < 3; i++) {
			PaypalPaymentMethod p = new PaypalPaymentMethod("method "+i, "example"+i+"@gmail.com");
			user.getPaymentMethods().add(p);
		}
		
		initPages();
		
		this.cp = this.getContentPane();
		cp.add(searchPage);
		
	    this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	protected void setPage(JPanel page) {
		Window.window.setContentPane(page);
		Window.window.revalidate();
	}
	
	private void initPages() {
		searchPage = new SearchPage();
		loginPage = new LoginPage();
		registerPage = new RegisterPage();
		historyPage = new HistoryPage();
		profilePage = new ProfilePage(user);
	}
	
	private void setLookAndFeel() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
