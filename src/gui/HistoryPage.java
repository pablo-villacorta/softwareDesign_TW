package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import data.Airline;
import data.Airport;
import data.Flight;
import data.FlightType;

public class HistoryPage extends JPanel {

	private JButton backButton;
	
	public HistoryPage() {
		super();
		this.setLayout(new BorderLayout());
		JPanel topPanel = new JPanel(new BorderLayout());
		
		initComponents();
		
		topPanel.add(backButton, BorderLayout.WEST);
		topPanel.setBorder(new EmptyBorder(10,10,10,10));
		
		JPanel mainPanel = new SearchResultPage();
		mainPanel.setBorder(new EmptyBorder(10,10,10,10));
		
		this.add(topPanel, BorderLayout.NORTH);
		this.add(mainPanel, BorderLayout.CENTER);
	}
	
	private void initComponents() {
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window.window.setPage(Window.window.profilePage);
			}
		});
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

		public FlightResult(Flight flight) {
			super();

			Border padding = new EmptyBorder(10,10,10,10);
			Border border = BorderFactory.createLineBorder(Color.GRAY);
			this.setBorder(new CompoundBorder(border, padding));
			this.setLayout(new BorderLayout());

			JPanel datePanel, mainPanel;
			datePanel = new JPanel();
			datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.X_AXIS));

			int rows = flight.getType() == FlightType.ROUNDTRIP ? 2 : 1;
			mainPanel = new JPanel(new GridLayout(rows, 1));
			mainPanel.setBorder(new EmptyBorder(0,0,0,10));

			mainPanel.add(new FlightResultInfo(flight.getDepartingAirline(),
					flight.getDepartingTakeoffDate(), 
					flight.getDepartingLandingDate(), 
					flight.getOriginAirport(), 
					flight.getDestinationAirport()));

			datePanel.add(new JLabel("Booking date: "));
			datePanel.add(new JLabel(SearchPage.dateFormatter.format(new Date())));
			datePanel.setBorder(new EmptyBorder(0,0,0,20));

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
			this.add(datePanel, BorderLayout.WEST);
		}
	}

}
