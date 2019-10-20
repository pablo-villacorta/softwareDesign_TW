package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import data.Flight;
import data.FlightType;

public class FlightDetailsPage extends JPanel {

	private Flight flight;
	
	private JPanel leftPanel, rightPanel, topPanel;
	
	private JPanel seatsPanel;
	
	private JButton backButton;
	
	private JButton bookNewSeatButton;
	
	// TODO add book & pay button, combobox with payment methods + confirmation message
	
	public FlightDetailsPage(Flight flight) {
		super();
		this.flight = flight;
		
		this.setLayout(new BorderLayout());
		
		JPanel mainPanel = new JPanel();
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		
		initComponents();
		initPanels();
		addListeners();
		
		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);
		
		this.add(topPanel, BorderLayout.NORTH);
		this.add(mainPanel, BorderLayout.CENTER);
		
	}
	
	private void initPanels() {
		initTopPanel();
		initLeftPanel();
		initRightPanel();
	}
	
	private void initRightPanel() {
		rightPanel = new JPanel(new BorderLayout());
		seatsPanel = new JPanel();
		seatsPanel.setLayout(new BoxLayout(seatsPanel, BoxLayout.Y_AXIS));
		JScrollPane sp = new JScrollPane(seatsPanel);
		seatsPanel.add(new FullSeatPickerPanel());
		rightPanel.add(sp, BorderLayout.CENTER);
	}
	
	private void initTopPanel() {
		topPanel = new JPanel(new BorderLayout());
		topPanel.add(backButton, BorderLayout.WEST);
		topPanel.setBorder(new EmptyBorder(10,10,10,10));
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window.window.setPage(Window.window.searchPage);
			}
		});
	}
	
	private void initLeftPanel() {
		leftPanel = new JPanel();
		JPanel leftPanelContainer = new JPanel(new BorderLayout());
		
		JLabel titleLabel = new JLabel("Flight details: ");
		titleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		JPanel departingPanel = new JPanel(new BorderLayout());
		JLabel departingTitleLabel;
		JPanel departingInfoPanel;
		departingTitleLabel = new JLabel("Departing info");
		departingInfoPanel = new JPanel(new GridLayout(8, 2));
		
		fillWithDepartingDetails(departingInfoPanel);
		
		departingPanel.add(departingTitleLabel, BorderLayout.NORTH);
		departingPanel.add(departingInfoPanel, BorderLayout.CENTER);

		infoPanel.add(departingPanel);
		departingInfoPanel.setBorder(new EmptyBorder(10,10,10,10));
		if (flight.getType() == FlightType.ROUNDTRIP) {
			JPanel returningPanel = new JPanel(new BorderLayout());
			JLabel returningTitleLabel;
			JPanel returningInfoPanel;
			returningTitleLabel = new JLabel("Returning info");
			returningInfoPanel = new JPanel(new GridLayout(8, 2));
			
			fillWithReturningDetails(returningInfoPanel);
			
			returningPanel.add(returningTitleLabel, BorderLayout.NORTH);
			returningPanel.add(returningInfoPanel, BorderLayout.CENTER);
			infoPanel.add(returningPanel);
			returningInfoPanel.setBorder(new EmptyBorder(10,10,10,10));
		}
		
		JPanel bookingButtonPanel = new JPanel(new BorderLayout());
		bookingButtonPanel.add(bookNewSeatButton, BorderLayout.EAST);
		
		leftPanelContainer.add(titleLabel, BorderLayout.NORTH);
		leftPanelContainer.add(infoPanel, BorderLayout.CENTER);
		leftPanelContainer.add(bookingButtonPanel, BorderLayout.SOUTH);
		
		leftPanel.add(leftPanelContainer);
	}
	
	private void fillWithReturningDetails(JPanel panel) {
		panel.add(new JLabel("Airline: "));
		panel.add(new JLabel(flight.getReturningAirline().getName()));
		panel.add(new JLabel("Origin city: "));
		panel.add(new JLabel(flight.getDestinationAirport().getCity()));
		panel.add(new JLabel("Origin airport: "));
		panel.add(new JLabel(flight.getDestinationAirport().getName() + " ("+flight.getDestinationAirport().getCode()+")"));
		panel.add(new JLabel("Destination city: "));
		panel.add(new JLabel(flight.getOriginAirport().getCity()));
		panel.add(new JLabel("Destination airport: "));
		panel.add(new JLabel(flight.getOriginAirport().getName()+" ("+flight.getOriginAirport().getCode()+")"));
		panel.add(new JLabel("Takeoff time: "));
		panel.add(new JLabel(SearchPage.timeFormatter.format(flight.getReturningTakeoffDate())));
		panel.add(new JLabel("Landing time: "));
		panel.add(new JLabel(SearchPage.timeFormatter.format(flight.getReturningLandingDate())));
		panel.add(new JLabel("Cheapest seat price: "));
		panel.add(new JLabel(SearchPage.priceFormatter.format(flight.getTotalPrice()/2)+" $"));
	}

	private void fillWithDepartingDetails(JPanel panel) {
		panel.add(new JLabel("Airline: "));
		panel.add(new JLabel(flight.getDepartingAirline().getName()));
		panel.add(new JLabel("Origin city: "));
		panel.add(new JLabel(flight.getOriginAirport().getCity()));
		panel.add(new JLabel("Origin airport: "));
		panel.add(new JLabel(flight.getOriginAirport().getName() + " ("+flight.getOriginAirport().getCode()+")"));
		panel.add(new JLabel("Destination city: "));
		panel.add(new JLabel(flight.getDestinationAirport().getCity()));
		panel.add(new JLabel("Destination airport: "));
		panel.add(new JLabel(flight.getDestinationAirport().getName()+" ("+flight.getDestinationAirport().getCode()+")"));
		panel.add(new JLabel("Takeoff time: "));
		panel.add(new JLabel(SearchPage.timeFormatter.format(flight.getDepartingTakeoffDate())));
		panel.add(new JLabel("Landing time: "));
		panel.add(new JLabel(SearchPage.timeFormatter.format(flight.getDepartingLandingDate())));
		panel.add(new JLabel("Cheapest seat price: "));
		panel.add(new JLabel(SearchPage.priceFormatter.format(flight.getTotalPrice()/2)+" $"));
	}

	private void initComponents() {
		backButton = new JButton("Back");
		bookNewSeatButton = new JButton("Add a seat");
	}
	
	private void addListeners() {
		bookNewSeatButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seatsPanel.add(new FullSeatPickerPanel());
				seatsPanel.revalidate();
			}
		});
	}
	
	private class FullSeatPickerPanel extends JPanel {
		
		private JButton deleteButton;
		
		public FullSeatPickerPanel() {
			super();
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 350));
			this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			JPanel departingPanel;
			
			departingPanel = new JPanel(new BorderLayout());
			departingPanel.add(new JLabel("Departing flight seat: "), BorderLayout.NORTH);
			departingPanel.add(new SingleSeatPickerPanel(), BorderLayout.CENTER);
			departingPanel.setBorder(new EmptyBorder(10,10,10,10));
			this.add(departingPanel);
			
			if (flight.getType() == FlightType.ROUNDTRIP) {
				JPanel returningPanel = new JPanel(new BorderLayout());
				returningPanel = new JPanel(new BorderLayout());
				returningPanel.add(new JLabel("Returning flight seat: "), BorderLayout.NORTH);
				returningPanel.add(new SingleSeatPickerPanel(), BorderLayout.CENTER);
				returningPanel.setBorder(new EmptyBorder(10,10,10,10));
				this.add(returningPanel);
			}
			
			JPanel buttonPanel = new JPanel(new BorderLayout());
			deleteButton = new JButton("Delete seat");
			JPanel p = new JPanel();
			p.add(deleteButton);
			buttonPanel.add(p, BorderLayout.WEST);
			this.add(buttonPanel);
		}
		
	}
	
	private class SingleSeatPickerPanel extends JPanel {
		
		private JComboBox<String> typeCombo;
		private JCheckBox additionalLuggageCB;
		private JLabel priceLabel;
		
		public SingleSeatPickerPanel() {
			super();
			this.setLayout(new BorderLayout());
			this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
			//this.setBorder(new EmptyBorder(10,10,10,10));
			
			JPanel gridPanel = new JPanel(new GridLayout(3, 2));
			JPanel buttonPanel = new JPanel(new BorderLayout());
			
			initComponents();
			
			gridPanel.add(new JLabel("Seat type: "));
			gridPanel.add(typeCombo);
			gridPanel.add(new JLabel("Additional luggage: "));
			gridPanel.add(additionalLuggageCB);
			gridPanel.add(new JLabel("Price: "));
			gridPanel.add(priceLabel);
			
			this.add(gridPanel, BorderLayout.CENTER);
			this.add(buttonPanel, BorderLayout.SOUTH);
		}
		
		private void initComponents() {
			
			typeCombo = new JComboBox<>();
			typeCombo.addItem("First class");
			typeCombo.addItem("Business class");
			typeCombo.addItem("Economy class");
			
			additionalLuggageCB = new JCheckBox();
			priceLabel = new JLabel(SearchPage.priceFormatter.format(flight.getTotalPrice()/2)+" $");
		}
		
	}
	
}
