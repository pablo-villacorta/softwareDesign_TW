package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.*;

import javax.swing.UIManager.*;

public class Window extends JFrame {

	private Container cp;
	
	public SearchPage searchPage;
	public LoginPage loginPage;
	public RegisterPage registerPage;
	
	public static Window window;
	
	public Window() {
		super("EasyBooking");
		
		initPages();
		
		this.cp = this.getContentPane();
		//cp.add(searchPage);
		
		cp.add(new RegisterPage()); // todo delete
		
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
	}
	
	// I don't know why but this does not work
	private void setLookAndFeel() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {}
	}	
}
