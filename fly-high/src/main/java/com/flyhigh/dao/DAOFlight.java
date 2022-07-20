package com.flyhigh.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.flyhigh.model.Flight;

@Entity
@Table(name = "flight")
public class DAOFlight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "flight_number")
	private String flightNumber;
	@Column(name = "departure_port")
	private String departurePort;
	@Column(name = "arrival_port")
	private String arrivalPort;
	@Column(name = "departure_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date departureTime;
	@Column(name = "arrival_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date arrivalTime;
	@Column(name = "airline_code")
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

	public DAOFlight() {
		super();
	}

	public DAOFlight(Flight flight) {
		super();
		this.flightNumber = flight.getFlightNumber();
		this.departurePort = flight.getDeparturePort();
		this.arrivalPort = flight.getArrivalPort();
		this.departureTime = flight.getDepartureTime();
		this.arrivalTime = flight.getArrivalTime();
		this.airlineCode = flight.getAirlineCode();
	}

}
