package com.flyhigh.service;

import java.text.ParseException;
import java.util.List;

import com.flyhigh.dao.DAOFlight;
import com.flyhigh.model.Flight;

public interface FlightService {
	public List<DAOFlight> getFlightDetails() throws ParseException;
	public List<DAOFlight> getFlightDetails(String airlineCode);
	public void save(Flight flight);
}
