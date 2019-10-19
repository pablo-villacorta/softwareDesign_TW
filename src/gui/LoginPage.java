package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginPage extends JPanel {

	private JLabel usernameLabel, passwordLabel;
	private JTextField usernameField;
	private JPasswordField passwordField;
	
	private JRadioButton googleRadioBtn,
						 facebookRadioBtn,
						 twitterRadioBtn;
	
	private JButton loginButton, registerButton;
	
	private JButton backButton;
	
	public LoginPage() {
		super();
		this.setLayout(new BorderLayout());
		
		initComponents();
		
		JPanel goBackPanel = new JPanel(new BorderLayout());
		goBackPanel.setBorder(new EmptyBorder(10,10,10,10));
		goBackPanel.add(backButton, BorderLayout.WEST);
		JPanel flowPanel = new JPanel(new FlowLayout());
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		JPanel loginOptionsPanel = new JPanel(new BorderLayout());
		loginOptionsPanel.add(new JLabel("Log in using: "), BorderLayout.NORTH);
		JPanel radioButtonsPanel = new JPanel(new FlowLayout());
		radioButtonsPanel.add(googleRadioBtn);
		radioButtonsPanel.add(facebookRadioBtn);
		radioButtonsPanel.add(twitterRadioBtn);
		loginOptionsPanel.add(radioButtonsPanel, BorderLayout.CENTER);
		
		JPanel credentialsPanel = new JPanel(new GridLayout(2, 1));
		JPanel usernameInputPanel = new JPanel();
		JPanel passwordInputPanel = new JPanel();
		usernameInputPanel.setLayout(new BoxLayout(usernameInputPanel, BoxLayout.X_AXIS));
		passwordInputPanel.setLayout(new BoxLayout(passwordInputPanel, BoxLayout.X_AXIS));
		usernameInputPanel.add(usernameLabel);
		usernameInputPanel.add(usernameField);
		passwordInputPanel.add(passwordLabel);
		passwordInputPanel.add(passwordField);
		usernameInputPanel.setBorder(new EmptyBorder(10,0,10,0));
		passwordInputPanel.setBorder(new EmptyBorder(10,0,10,0));
		credentialsPanel.add(usernameInputPanel);
		credentialsPanel.add(passwordInputPanel);
		mainPanel.add(credentialsPanel, BorderLayout.CENTER);
		
		JPanel loginRegisterOptionsPanel = new JPanel(new GridLayout(2, 1));
		JPanel loginButtonPanel = new JPanel(new BorderLayout());
		loginButtonPanel.add(loginButton, BorderLayout.EAST);
		loginButtonPanel.setBorder(new EmptyBorder(10,0,0,0));
		
		JPanel registerButtonPanel = new JPanel(new BorderLayout());
		registerButtonPanel.add(new JLabel("You don't have an account? "), BorderLayout.CENTER);
		registerButtonPanel.add(registerButton, BorderLayout.EAST);
		registerButtonPanel.setBorder(new EmptyBorder(10,0,0,0));
		
		loginRegisterOptionsPanel.add(loginButtonPanel);
		loginRegisterOptionsPanel.add(registerButtonPanel);
		mainPanel.add(loginRegisterOptionsPanel, BorderLayout.SOUTH);
			
		mainPanel.add(loginOptionsPanel, BorderLayout.NORTH);
		flowPanel.add(mainPanel);
		this.add(goBackPanel, BorderLayout.NORTH);
		this.add(flowPanel, BorderLayout.CENTER);
	}
	
	private void initComponents() {
		this.usernameLabel = new JLabel("Username: "); //email?
		this.passwordLabel = new JLabel("Password: ");
		this.usernameField = new JTextField();
		this.passwordField = new JPasswordField();
		
		this.googleRadioBtn = new JRadioButton("Google");
		this.facebookRadioBtn = new JRadioButton("Facebook");
		this.twitterRadioBtn = new JRadioButton("Twitter");
		ButtonGroup bg = new ButtonGroup();
		bg.add(googleRadioBtn);
		bg.add(facebookRadioBtn);
		bg.add(twitterRadioBtn);
		googleRadioBtn.setSelected(true);
		
		this.loginButton = new JButton("Log in");
		this.registerButton = new JButton("Sign up");
		this.backButton = new JButton("Back");
	}
	
}
