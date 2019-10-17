package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SearchPage extends JPanel {
	
	public SearchPage() {
		super();
		this.setLayout(new BorderLayout());
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(new SearchMenu(), BorderLayout.NORTH);
		JSplitPane splitPane = new JSplitPane(SwingConstants.VERTICAL,
									new FilterMenu(),
									new SearchResultPage());
		mainPanel.add(splitPane, BorderLayout.CENTER);
		this.add(new TopMenu(), BorderLayout.NORTH);
		this.add(mainPanel, BorderLayout.CENTER);
	}
	
	private class SearchResultPage extends JPanel {
		
		public SearchResultPage() {
			super();
			//this.setBackground(Color.green);
			this.add(new JLabel("main content"));
		}
		
	}
	
	private class SearchMenu extends JPanel {
		
		public JRadioButton radioButtonRoundtrip,
							radioButtonOneWay;
		
		public ButtonGroup buttonGroup;
		
		public JComboBox<String> comboOrigin, comboDestination;
		public JTextField tfDeparting, tfReturning;
		public JSpinner spinnerNumPassengers;
		
		public JButton searchButton;
		
		public SearchMenu() {
			super();
			this.setBorder(new EmptyBorder(10,10,10,10));
			//this.setBackground(Color.red);
			this.setLayout(new BorderLayout());	
			
			initComponents();
			
			JPanel topPanel = new JPanel(new BorderLayout());
			JPanel topLeftPanel = new JPanel(new FlowLayout());
			JPanel mainPanel = new JPanel(new GridLayout(2, 5));
			JPanel bottomPanel = new JPanel(new BorderLayout());
			
			topLeftPanel.add(radioButtonRoundtrip);
			topLeftPanel.add(radioButtonOneWay);
			
			topPanel.add(topLeftPanel, BorderLayout.WEST);
			
			mainPanel.setBorder(new EmptyBorder(0,10,10,10));
			
			mainPanel.add(new JLabel("Origin"));
			mainPanel.add(new JLabel("Destination"));
			mainPanel.add(new JLabel("Departing"));
			mainPanel.add(new JLabel("Returning"));
			mainPanel.add(new JLabel("Passengers"));
			
			mainPanel.add(comboOrigin);
			mainPanel.add(comboDestination);
			mainPanel.add(tfDeparting);
			mainPanel.add(tfReturning);
			mainPanel.add(spinnerNumPassengers);
			
			bottomPanel.setBorder(new EmptyBorder(0,10,10,10));
			bottomPanel.add(searchButton, BorderLayout.EAST);
			
			this.add(topPanel, BorderLayout.NORTH);			
			this.add(mainPanel, BorderLayout.CENTER);
			this.add(bottomPanel, BorderLayout.SOUTH);
		}
		
		private void initComponents() {
			radioButtonRoundtrip = new JRadioButton("Roundtrip");
			radioButtonOneWay = new JRadioButton("One way");
			
			buttonGroup = new ButtonGroup();
			buttonGroup.add(radioButtonRoundtrip);
			buttonGroup.add(radioButtonOneWay);
			
			radioButtonRoundtrip.setSelected(true);
			
			comboOrigin = new JComboBox<>();
			comboDestination = new JComboBox<>();
			String[] airports = new String[] { "London", "Paris", "Tokyo", "New York" };
			for (String e : airports) {
				comboOrigin.addItem(e);
				comboDestination.addItem(e);
			}
			
			tfDeparting = new JTextField("YYYY-MM-DD");
			tfReturning = new JTextField("YYYY-MM-DD");
			
			spinnerNumPassengers = new JSpinner();
			spinnerNumPassengers.setValue(1);
			
			searchButton = new JButton("Search flights");
		}
	}
	
	private class FilterMenu extends JPanel {
		
		public JSlider sliderMaxPrice;
		public JSpinner spinnerMaxStopovers;
		public JLabel labelMaxPriceValue;
		
		public FilterMenu() {
			super();
			//this.setBackground(Color.yellow);
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			
			initComponents();
			
			JLabel title = new JLabel("Filters");
			title.setFont(new Font("Arial", Font.PLAIN, 18));
			title.setAlignmentX(LEFT_ALIGNMENT);
			
			JLabel labelMaxPrice = new JLabel("Max. price");
			labelMaxPrice.setAlignmentX(LEFT_ALIGNMENT);
			
			JLabel labelMaxStopovers = new JLabel("Max. stopovers");
			labelMaxStopovers.setAlignmentX(LEFT_ALIGNMENT);
			labelMaxStopovers.setBorder(new EmptyBorder(10,0,10,0));
			
			this.add(title);
			this.add(Box.createVerticalGlue());
			this.add(labelMaxPrice);
			this.add(sliderMaxPrice);
			this.add(labelMaxPriceValue);
			this.add(Box.createVerticalGlue());
			this.add(labelMaxStopovers);
			this.add(spinnerMaxStopovers);
			this.add(Box.createVerticalGlue());
			this.setBorder(new EmptyBorder(10,10,10,10));
		}
		
		private void initComponents() {
			sliderMaxPrice = new JSlider(10, 5000, 100);
			sliderMaxPrice.setPaintTrack(true);
			sliderMaxPrice.setPaintLabels(true);
			sliderMaxPrice.setAlignmentX(LEFT_ALIGNMENT);
			sliderMaxPrice.setBorder(new EmptyBorder(10,0,10,0));
			sliderMaxPrice.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					labelMaxPriceValue.setText(sliderMaxPrice.getValue() + "$");
				}
			});
			
			labelMaxPriceValue = new JLabel(sliderMaxPrice.getValue()+"$");
			labelMaxPriceValue.setAlignmentX(LEFT_ALIGNMENT);
			
			spinnerMaxStopovers = new JSpinner();
			spinnerMaxStopovers.setValue(2);
			spinnerMaxStopovers.setAlignmentX(LEFT_ALIGNMENT);
			spinnerMaxStopovers.setMaximumSize(new Dimension(80, 30));
		}
		
	}
	
	private class TopMenu extends JPanel {
		
		public JButton loginButton, registerButton;
		
		public TopMenu() {
			super();
			this.setBorder(new EmptyBorder(10, 10, 10, 10));
			//this.setBackground(Color.pink);
			this.setLayout(new BorderLayout());
			
			JPanel buttons = new JPanel(new GridLayout(1, 2));
			loginButton = new JButton("Log in");
			registerButton = new JButton("Register");

			buttons.add(loginButton);
			buttons.add(registerButton);
			this.add(buttons, BorderLayout.EAST);
			this.add(new JButton("Back"), BorderLayout.WEST);
		}
		
	}
	
}
