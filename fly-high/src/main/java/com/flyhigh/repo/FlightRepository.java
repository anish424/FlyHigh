package com.flyhigh.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.flyhigh.dao.DAOFlight;

@Repository
public interface FlightRepository extends CrudRepository<DAOFlight, Integer> {
	
	//@Query("select flight from DAOFlight flight where flight.arrivalTime like %?1")
	List<DAOFlight> findByArrivalTimeBetween(Date before, Date after);
	
	//@Query("select flight from DAOFlight flight where flight.airlineCode = ?1")
	List<DAOFlight> findByAirlineCode(String airlineCode);
}
