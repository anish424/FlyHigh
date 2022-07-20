package com.flyhigh.controller;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.flyhigh.dao.DAOFlight;
import com.flyhigh.model.Flight;
import com.flyhigh.service.FlightService;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class FlightControllerTest {

	@Mock
	private FlightService service;

	@InjectMocks
	private FlightController controller;

	@Test
	public void getFlightDetailsTest() throws ParseException {
		List<DAOFlight> flights = new ArrayList<>();
		Mockito.when(service.getFlightDetails()).thenReturn(flights);
		ResponseEntity entity = controller.getFlightDetails();
		Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

	@Test
	public void getFlightDetailsTestException() throws ParseException {
		Mockito.when(service.getFlightDetails()).thenThrow(ParseException.class);
		ResponseEntity entity = controller.getFlightDetails();
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, entity.getStatusCode());
	}

	@Test
	public void getFlightDetailsWithAirlineCodeTest() throws ParseException {
		List<DAOFlight> flights = new ArrayList<>();
		Mockito.when(service.getFlightDetails()).thenReturn(flights);
		ResponseEntity entity = controller.getFlightDetails("UK");
		Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

	@Test
	public void getFlightDetailsWithAirlineCodeTestException() throws ParseException {
		Mockito.when(service.getFlightDetails(Mockito.anyString())).thenThrow(NullPointerException.class);
		ResponseEntity entity = controller.getFlightDetails("UK");
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, entity.getStatusCode());
	}

	@Test
	public void saveFlightDetailsTest() throws ParseException {
		Flight flight = new Flight();
		flight.setAirlineCode("s");
		flight.setArrivalPort("d");
		flight.setArrivalTime(new Date());
		flight.setDeparturePort("g");
		flight.setDepartureTime(new Date());
		flight.setFlightNumber("34");
		Mockito.doNothing().when(service).save(flight);
		ResponseEntity entity = controller.saveFlightDetails(flight);
		Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

	@Test
	public void saveFlightDetailsTestException() throws ParseException {
		Flight flight = Mockito.mock(Flight.class);
		Mockito.doNothing().when(service).save(flight);
		ResponseEntity entity = controller.saveFlightDetails(flight);
		Mockito.when(flight.getAirlineCode()).thenReturn(null);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
	}

	@Test
	public void saveFlightDetailsTestException2() throws ParseException {
		Flight flight = Mockito.mock(Flight.class);
		Mockito.doNothing().when(service).save(flight);
		ResponseEntity entity = controller.saveFlightDetails(flight);
		Mockito.when(flight.getAirlineCode()).thenReturn("1");
		Mockito.when(flight.getArrivalPort()).thenReturn(null);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
	}

	@Test
	public void saveFlightDetailsTestException3() throws ParseException {
		Flight flight = Mockito.mock(Flight.class);
		Mockito.doNothing().when(service).save(flight);
		ResponseEntity entity = controller.saveFlightDetails(flight);
		Mockito.when(flight.getAirlineCode()).thenReturn("1");
		Mockito.when(flight.getArrivalPort()).thenReturn("2");
		Mockito.when(flight.getDeparturePort()).thenReturn(null);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
	}

	@Test
	public void saveFlightDetailsTestException4() throws ParseException {
		Flight flight = Mockito.mock(Flight.class);
		Mockito.doNothing().when(service).save(flight);
		ResponseEntity entity = controller.saveFlightDetails(flight);
		Mockito.when(flight.getAirlineCode()).thenReturn("1");
		Mockito.when(flight.getArrivalPort()).thenReturn("2");
		Mockito.when(flight.getDeparturePort()).thenReturn("5");
		Mockito.when(flight.getFlightNumber()).thenReturn(null);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
	}
}