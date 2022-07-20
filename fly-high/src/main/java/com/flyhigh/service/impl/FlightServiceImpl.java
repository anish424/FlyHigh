package com.flyhigh.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flyhigh.dao.DAOFlight;
import com.flyhigh.model.Flight;
import com.flyhigh.repo.FlightRepository;
import com.flyhigh.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

	private static final Logger logger = LoggerFactory.getLogger(FlightServiceImpl.class);

	@Autowired
	private FlightRepository repo;

	@Override
	public List<DAOFlight> getFlightDetails() throws ParseException {
		Date before = new Date(new java.util.Date().getTime());
		before.setHours(0);
		before.setSeconds(0);
		before.setMinutes(0);
		Date after = new Date(before.getTime() + (1000 * 60 * 60 * 24));
		logger.info("beforeTime {} afterTime {}", before, after);
		return repo.findByArrivalTimeBetween(before, after);
	}

	@Override
	public List<DAOFlight> getFlightDetails(String airlineCode) {
		return repo.findByAirlineCode(airlineCode);
	}

	@Override
	public void save(Flight flight) {
		DAOFlight dao = new DAOFlight(flight);
		repo.save(dao);
	}

}
