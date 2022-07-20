package com.flyhigh.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.flyhigh.dao.DAOFlight;
import com.flyhigh.model.Flight;
import com.flyhigh.repo.FlightRepository;
import com.flyhigh.service.impl.FlightServiceImpl;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class FlightServiceTest {

	@Mock
	private FlightRepository repo;
	
	@InjectMocks
	private FlightServiceImpl service;
	
	@Test
	public void getFlightDetailsTest() throws ParseException {
		List<DAOFlight> flights = new ArrayList<>();
		Mockito.when(repo.findByArrivalTimeBetween(Mockito.any(),Mockito.any())).thenReturn(flights);
		Assert.assertEquals(flights, service.getFlightDetails());
	}
	
	@Test
	public void getFlightDetailsTestException() throws ParseException {
		List<DAOFlight> flights = new ArrayList<>();
		Mockito.when(repo.findByArrivalTimeBetween(Mockito.any(),Mockito.any())).thenThrow(ParseException.class);
		Assert.assertEquals(flights, service.getFlightDetails());
	}
	
	@Test
	public void getFlightDetailsWithAirlineCodeTest() throws ParseException {
		List<DAOFlight> flights = new ArrayList<>();
		Mockito.when(repo.findByAirlineCode(Mockito.any())).thenReturn(flights);
		Assert.assertEquals(flights, service.getFlightDetails(""));
	}
	
	@Test
	public void getSaveTest() throws ParseException {
		Flight flight = new Flight();
		flight.setAirlineCode("s");
		flight.setArrivalPort("d");
		flight.setArrivalTime(new Date());
		flight.setDeparturePort("g");
		flight.setDepartureTime(new Date());
		flight.setFlightNumber("34");
		Mockito.when(repo.save(Mockito.any())).thenReturn(Mockito.any());
		service.save(flight);
	}

}
