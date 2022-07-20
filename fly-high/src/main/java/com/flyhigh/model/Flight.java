package com.flyhigh.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Flight {

	private String flightNumber;
	private String departurePort;
	private String arrivalPort;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-ddTHH:mm:ssZ", timezone = "UTC")
	private Date departureTime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-ddTHH:mm:ssZ", timezone = "UTC")
	private Date arrivalTime;
	private String airlineCode;

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getDeparturePort() {
		return departurePort;
	}

	public void setDeparturePort(String departurePort) {
		this.departurePort = departurePort;
	}

	public String getArrivalPort() {
		return arrivalPort;
	}

	public void setArrivalPort(String arrivalPort) {
		this.arrivalPort = arrivalPort;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getAirlineCode() {
		return airlineCode;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}

	@Override
	public String toString() {
		return "Flight [flightNumber=" + flightNumber + ", departurePort=" + departurePort + ", arrivalPort="
				+ arrivalPort + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", airlineCode="
				+ airlineCode + "]";
	}

}
