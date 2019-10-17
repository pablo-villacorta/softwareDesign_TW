package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import data.Airline;
import data.Airport;
import data.Flight;
import data.FlightType;

public class SearchPage extends JPanel {
	
	private static SimpleDateFormat dateFormatter;
	private static SimpleDateFormat timeFormatter;
	private static DecimalFormat priceFormatter;
	
	static {
		dateFormatter = new SimpleDateFormat("yyyy-mm-dd");
		timeFormatter = new SimpleDateFormat("hh:mm");
		priceFormatter = new DecimalFormat("#");
	}
	
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
		
		private ArrayList<Flight> results;
		
		public SearchResultPage() {
			super();
			this.setLayout(new BorderLayout());
			results = new ArrayList<>();
			
			// some example results here
			for (int i = 0; i < 16; i++) {
				Flight flight = new Flight();
				flight.setType(FlightType.ROUNDTRIP);
				flight.setTotalPrice(10*(i+1));
				flight.setOriginAirport(new Airport("BIO"));
				flight.setDestinationAirport(new Airport("CDG"));
				flight.setDepartingAirline(new Airline("AirFrance"));
				flight.setReturningAirline(new Airline("AirFrance"));
				Date d = new Date();
				flight.setDepartingTakeoffDate(d);
				flight.setDepartingLandingDate(d);
				flight.setReturningTakeoffDate(d);
				flight.setReturningLandingDate(d);
				results.add(flight);
			}
			
			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
			
			for (Flight f : results) {
				FlightResult result = new FlightResult(f);
				result.setAlignmentX(CENTER_ALIGNMENT);
				mainPanel.add(result);
			}
			
			JScrollPane scrollPane = new JScrollPane(mainPanel);
			this.add(scrollPane, BorderLayout.CENTER);
		}
		
	}
	
	private class FlightResult extends JPanel {
		
		private Flight flight;
		
		private JButton detailsButton;
		private JLabel priceLabel;
		
		private JLabel departingTakeoffTimeLabel,
					   departingLandingTimeLabel,
					   departingTakeoffAirportLabel,
					   departingLandingAirportLabel;
		private JLabel returningTakeoffTimeLabel,
					   returningLandingTimeLabel,
					   returningTakeoffAirportLabel,
					   returningLandingAirportLabel;
		
		private JLabel departingAirlineLabel,
					   returningAirlineLabel;
		
		public FlightResult(Flight flight) {
			super();
			this.flight = flight;
			
			Border padding = new EmptyBorder(10,10,10,10);
			Border border = BorderFactory.createLineBorder(Color.GRAY);
			this.setBorder(new CompoundBorder(border, padding));
			this.setLayout(new BorderLayout());
			
			JPanel pricePanel, mainPanel;
			pricePanel = new JPanel();
			pricePanel.setLayout(new BoxLayout(pricePanel, BoxLayout.Y_AXIS));

			int rows = flight.getType() == FlightType.ROUNDTRIP ? 2 : 1;
			mainPanel = new JPanel(new GridLayout(rows, 1));
			mainPanel.setBorder(new EmptyBorder(0,0,0,10));
			
			mainPanel.add(new FlightResultInfo(flight.getDepartingAirline(),
								flight.getDepartingTakeoffDate(), 
								flight.getDepartingLandingDate(), 
								flight.getOriginAirport(), 
								flight.getDestinationAirport()));
			
			initComponents();
			
			pricePanel.add(Box.createGlue());
			pricePanel.add(priceLabel);
			pricePanel.add(detailsButton);
			pricePanel.add(Box.createGlue());
			
			if (flight.getType() == FlightType.ROUNDTRIP) {
				FlightResultInfo returningPanel = new FlightResultInfo(flight.getReturningAirline(),
						flight.getReturningTakeoffDate(), 
						flight.getReturningLandingDate(), 
						flight.getDestinationAirport(), 
						flight.getOriginAirport());
				returningPanel.addSeparatorBorder();
				mainPanel.add(returningPanel);
			}
			
			this.add(mainPanel, BorderLayout.CENTER);
			this.add(pricePanel, BorderLayout.EAST);
		}
		
		private void initComponents() {
			detailsButton = new JButton("See details");
			detailsButton.setAlignmentX(CENTER_ALIGNMENT);
			
			priceLabel = new JLabel(priceFormatter.format(flight.getTotalPrice()) + " $");
			priceLabel.setAlignmentX(CENTER_ALIGNMENT);
		}
	}
	
	private class FlightResultInfo extends JPanel {
		
		private JLabel takeoffTimeLabel,
					   landingTimeLabel,
					   takeoffAirportLabel,
					   landingAirportLabel;
		
		private JLabel airlineLabel;
		
		private Airline airline;
		private Date takeoffTime, landingTime;
		private Airport takeoffAirport, landingAirport;

		public FlightResultInfo(Airline airline,
								Date takeoffTime,
								Date landingTime,
								Airport takeoffAirport,
								Airport landingAirport) {
			super();
			this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			this.setBorder(new EmptyBorder(0,0,10,0));
			
			this.airline = airline;
			this.takeoffTime = takeoffTime;
			this.landingTime = landingTime;
			this.takeoffAirport = takeoffAirport;
			this.landingAirport = landingAirport;
			
			JPanel journeyInfoPanel = new JPanel();
			journeyInfoPanel.setLayout(new BoxLayout(journeyInfoPanel, BoxLayout.X_AXIS));
			JPanel takeoffInfo, landingInfo, arrowPanel;
			
			takeoffInfo = new JPanel(new BorderLayout());
			landingInfo = new JPanel(new BorderLayout());
			arrowPanel = new JPanel(new FlowLayout());
			takeoffInfo.setAlignmentY(CENTER_ALIGNMENT);
			landingInfo.setAlignmentY(CENTER_ALIGNMENT);
			arrowPanel.setAlignmentY(CENTER_ALIGNMENT);
			
			initComponents();
			
			takeoffInfo.add(takeoffTimeLabel, BorderLayout.NORTH);
			takeoffInfo.add(takeoffAirportLabel, BorderLayout.SOUTH);
			landingInfo.add(landingTimeLabel, BorderLayout.NORTH);
			landingInfo.add(landingAirportLabel, BorderLayout.SOUTH);
			
			JLabel arrowLabel = new JLabel();
			//ImageIcon arrowIcon = new ImageIcon("res/arrow.png");
			arrowLabel.setIcon(createArrowIcon(arrowLabel));
			arrowPanel.add(arrowLabel);
			
			journeyInfoPanel.add(takeoffInfo);
			journeyInfoPanel.add(arrowPanel);
			journeyInfoPanel.add(landingInfo);
			journeyInfoPanel.setAlignmentY(CENTER_ALIGNMENT);
			
			this.add(airlineLabel);
			this.add(Box.createGlue());
			this.add(journeyInfoPanel);
		}
		
		private ImageIcon createArrowIcon(JLabel arrowLabel) {
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File("res/arrow.png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			Image dimg = img.getScaledInstance(60, 20, Image.SCALE_SMOOTH);
			return new ImageIcon(dimg);
		}
		
		public void addSeparatorBorder() {
			Border margin = new EmptyBorder(10,0,0,0);
			Border sep = BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY);
			this.setBorder(new CompoundBorder(sep, margin));
		}
		
		private void initComponents() {
			airlineLabel = new JLabel(airline.getName());
			airlineLabel.setAlignmentY(CENTER_ALIGNMENT);
			takeoffAirportLabel = new JLabel(takeoffAirport.getCode(), SwingConstants.CENTER);
			landingAirportLabel = new JLabel(landingAirport.getCode(), SwingConstants.CENTER);
			takeoffTimeLabel = new JLabel(timeFormatter.format(takeoffTime), SwingConstants.CENTER);
			landingTimeLabel = new JLabel(timeFormatter.format(landingTime), SwingConstants.CENTER);
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
			String[] airports = new String[] { "London", "Seoul", "Paris", "Tokyo", "New York", "Barcelona" };
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
			//this.jasetBackground(Color.pink);
			this.setLayout(new BorderLayout());
			
			JPanel buttons = new JPanel(new GridLayout(1, 2));
			loginButton = new JButton("Log in");
			registerButton = new JButton("Register");

			buttons.add(loginButton);
			buttons.add(registerButton);
			this.add(buttons, BorderLayout.EAST);
			//this.add(new JButton("Back"), BorderLayout.WEST);
		}
		
	}
	
}
