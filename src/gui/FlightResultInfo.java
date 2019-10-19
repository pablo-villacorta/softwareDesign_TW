package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import data.Airline;
import data.Airport;

public class FlightResultInfo extends JPanel {
		
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
			takeoffTimeLabel = new JLabel(SearchPage.timeFormatter.format(takeoffTime), SwingConstants.CENTER);
			landingTimeLabel = new JLabel(SearchPage.timeFormatter.format(landingTime), SwingConstants.CENTER);
		}
		
	}