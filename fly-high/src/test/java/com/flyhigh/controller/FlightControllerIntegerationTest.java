package com.flyhigh.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import com.flyhigh.config.JwtTokenUtil;
import com.flyhigh.model.Flight;
import com.flyhigh.service.JwtUserDetailsService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FlightControllerIntegerationTest {

	@LocalServerPort
	private int port;

	@MockBean
	JwtTokenUtil jwtUtil;

	@MockBean
	JwtUserDetailsService jwtUserDetailsService;

	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();

	@Test
	public void testgetFlightDetails() throws Exception {
		HttpEntity<String> entity = setup();

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/flyHigh/flightDetails"),
				HttpMethod.GET, entity, String.class);
		assertTrue(response.getBody().contains("QF400"));
	}

	@Test
	public void testgetFlightDetailsWithAirLineCode() throws Exception {
		HttpEntity<String> entity = setup();

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/flyHigh/flightDetails/EK"),
				HttpMethod.GET, entity, String.class);
		assertTrue(response.getBody().contains("QF400"));
	}

	@Test
	public void testsaveFlight() throws Exception {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));   // This line converts the given date into UTC time zone
		final java.util.Date dateObj = sdf.parse("2020-06-10T10:25:23Z");
		
		Flight flight = new Flight();
		flight.setAirlineCode("s");
		flight.setArrivalPort("d");
		flight.setArrivalTime(dateObj);
		flight.setDeparturePort("g");
		flight.setDepartureTime(dateObj);
		flight.setFlightNumber("34");
		HttpEntity<Flight> entity = setup(flight);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/flyHigh/saveFlightDetails"),
				HttpMethod.GET, entity, String.class);
		assertTrue(response.getStatusCode()==HttpStatus.OK);
	}

	private HttpEntity<String> setup() {
		headers.add("Authorization", "Bearer " + "12345");
		Mockito.when(jwtUtil.validateToken(Mockito.anyString(), Mockito.any())).thenReturn(true);
		Mockito.when(jwtUtil.getUsernameFromToken(Mockito.any())).thenReturn("username");
		UserDetails details = Mockito.mock(UserDetails.class);
		Mockito.when(jwtUserDetailsService.loadUserByUsername(Mockito.any())).thenReturn(details);
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		return entity;
	}

	private HttpEntity<Flight> setup(Flight flight) {
		headers.add("Authorization", "Bearer " + "12345");
		Mockito.when(jwtUtil.validateToken(Mockito.anyString(), Mockito.any())).thenReturn(true);
		Mockito.when(jwtUtil.getUsernameFromToken(Mockito.any())).thenReturn("username");
		UserDetails details = Mockito.mock(UserDetails.class);
		Mockito.when(jwtUserDetailsService.loadUserByUsername(Mockito.any())).thenReturn(details);
		HttpEntity<Flight> entity = new HttpEntity<Flight>(flight, headers);
		return entity;
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
