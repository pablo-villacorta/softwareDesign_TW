package data;

import java.util.Date;

public class Flight {
	
	private Airport originAirport, destinationAirport;
	private Date departingTakeoffDate,
				 departingLandingDate,
				 returningTakeoffDate,
				 returningLandingDate;
	
	private Airline departingAirline,
					returningAirline;

	private float totalPrice;
	
	private FlightType type;

	// Getters & Setters
	public Airport getOriginAirport() {
		return originAirport;
	}

	public void setOriginAirport(Airport originAirport) {
		this.originAirport = originAirport;
	}

	public Airport getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public Date getDepartingTakeoffDate() {
		return departingTakeoffDate;
	}

	public void setDepartingTakeoffDate(Date departingTakeoffDate) {
		this.departingTakeoffDate = departingTakeoffDate;
	}

	public Date getDepartingLandingDate() {
		return departingLandingDate;
	}

	public void setDepartingLandingDate(Date departingLandingDate) {
		this.departingLandingDate = departingLandingDate;
	}

	public Date getReturningTakeoffDate() {
		return returningTakeoffDate;
	}

	public void setReturningTakeoffDate(Date returningTakeoffDate) {
		this.returningTakeoffDate = returningTakeoffDate;
	}

	public Date getReturningLandingDate() {
		return returningLandingDate;
	}

	public void setReturningLandingDate(Date returningLandingDate) {
		this.returningLandingDate = returningLandingDate;
	}

	public Airline getDepartingAirline() {
		return departingAirline;
	}

	public void setDepartingAirline(Airline departingAirline) {
		this.departingAirline = departingAirline;
	}

	public Airline getReturningAirline() {
		return returningAirline;
	}

	public void setReturningAirline(Airline returningAirline) {
		this.returningAirline = returningAirline;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}	
	
	public FlightType getType() {
		return type;
	}

	public void setType(FlightType type) {
		this.type = type;
	}
}
