package com.example.flightsystem.flightrepository;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.flightsystem.entities.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight , Integer>{
	
	Page<Flight> findAll(Pageable pageable);
	 

}
