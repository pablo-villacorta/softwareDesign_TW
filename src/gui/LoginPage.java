package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;

public class LoginPage extends JPanel {

	private JLabel usernameLabel, passwordLabel;
	private JTextField usernameField;
	private JPasswordField passwordField;
	
	private JRadioButton googleRadioBtn,
						 facebookRadioBtn;
	
	private JButton loginButton, registerButton;
	
	public LoginPage() {
		super();
		this.setLayout(new BorderLayout());
		
		initComponents();
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		JPanel loginOptionsPanel = new JPanel(new BorderLayout());
		loginOptionsPanel.add(new JLabel("Log in using: "), BorderLayout.NORTH);
		JPanel radioButtonsPanel = new JPanel(new FlowLayout());
		radioButtonsPanel.add(googleRadioBtn);
		radioButtonsPanel.add(facebookRadioBtn);
		loginOptionsPanel.add(radioButtonsPanel, BorderLayout.CENTER);
		
		// username and password fields here
		
		JPanel loginRegisterOptionsPanel = new JPanel();
		loginRegisterOptionsPanel.setLayout(new BoxLayout(loginRegisterOptionsPanel, BoxLayout.X_AXIS));
		loginRegisterOptionsPanel.add(loginButton);
		loginRegisterOptionsPanel.add(Box.createGlue());
		loginRegisterOptionsPanel.add(new JLabel("You don't have an account?"));
		loginRegisterOptionsPanel.add(registerButton);
		mainPanel.add(loginRegisterOptionsPanel, BorderLayout.SOUTH);
		
		
		mainPanel.add(loginOptionsPanel, BorderLayout.NORTH);
		this.add(mainPanel, BorderLayout.CENTER);
	}
	
	private void initComponents() {
		this.usernameLabel = new JLabel("Username: ");
		this.passwordLabel = new JLabel("Password");
		this.usernameField = new JTextField();
		this.passwordField = new JPasswordField();
		
		this.googleRadioBtn = new JRadioButton("Google");
		this.facebookRadioBtn = new JRadioButton("Facebook");
		ButtonGroup bg = new ButtonGroup();
		bg.add(googleRadioBtn);
		bg.add(facebookRadioBtn);
		googleRadioBtn.setSelected(true);
		
		this.loginButton = new JButton("Log in");
		this.registerButton = new JButton("Sign up");
	}
	
}
