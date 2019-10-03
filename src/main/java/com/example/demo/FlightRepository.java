package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import java.util.ArrayList;
import java.util.Date;

public interface FlightRepository extends CrudRepository<Flight,Long> {
//    ArrayList<Flight> f.(String endAirport);    // Search results
    //ArrayList<Flight> flightArrayList = new ArrayList<>();
    ArrayList<Flight> findFlightByEndAirportContainingIgnoreCase(String endAirport);    // Search results
    ArrayList<Flight> findFlightByDate(Date date);
    ArrayList<Flight> findFlightByPriceContaining(String price);
    ArrayList<Flight> findFlightByAirlineContainingIgnoreCase(String airline);
    ArrayList<Flight> findFlightByDateAndEndAirportIgnoreCase(Date date, String dest);


//    String outStr = "";
//    for (Flight f:flights){
//        outStr += f + ", ";
//    }
}

