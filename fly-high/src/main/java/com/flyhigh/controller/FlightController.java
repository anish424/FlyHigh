package com.flyhigh.controller;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flyhigh.dao.DAOFlight;
import com.flyhigh.model.Flight;
import com.flyhigh.service.FlightService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "flyHigh", description = "Operations pertaining to flights")
public class FlightController {
	
	private static final Logger logger = LoggerFactory.getLogger(FlightController.class);

	@Autowired
	private FlightService flightService;

	@ApiOperation(value = "View a list of all of today's flights", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/flightDetails")
	public ResponseEntity getFlightDetails() {
		try {
			logger.info("inside getFlightDetails");
			List<DAOFlight> list = flightService.getFlightDetails();
			return new ResponseEntity(list, HttpStatus.OK);
		} catch (ParseException e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "View a list of all flights based on airlineCode", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/flightDetails/{airlineCode}")
	public ResponseEntity getFlightDetails(@PathVariable(required = false, name = "airlineCode") String airlineCode) {
		try {
			logger.info("inside getFlightDetails airlineCode {}",airlineCode);
			if (StringUtils.isNotBlank(airlineCode))
				return new ResponseEntity(flightService.getFlightDetails(airlineCode), HttpStatus.OK);
			return new ResponseEntity("AirlineCode can't be blank", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Save a new Flight", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully saved a new flight"),
			@ApiResponse(code = 401, message = "You are not authorized to save"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/flightDetails")
	public ResponseEntity saveFlightDetails(@RequestBody Flight flight) {
		try {
			logger.info("inside saveFlightDetails flight {}",flight);
			if (StringUtils.isBlank(flight.getAirlineCode()))
				return new ResponseEntity("AirlineCode can't be blank", HttpStatus.BAD_REQUEST);
			else if (StringUtils.isBlank(flight.getArrivalPort()))
				return new ResponseEntity("ArrivalPort can't be blank", HttpStatus.BAD_REQUEST);
			else if (StringUtils.isBlank(flight.getDeparturePort()))
				return new ResponseEntity("DeparturePort can't be blank", HttpStatus.BAD_REQUEST);
			else if (StringUtils.isBlank(flight.getFlightNumber()))
				return new ResponseEntity("FlightNumber can't be blank", HttpStatus.BAD_REQUEST);
			flightService.save(flight);
			return new ResponseEntity("Flight saved", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
