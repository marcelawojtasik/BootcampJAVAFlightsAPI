package BootcampJAVA.FlightsAPI.controller;


import BootcampJAVA.FlightsAPI.model.Flight;
import BootcampJAVA.FlightsAPI.services.FlightsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired //inyecto el serv
    private FlightsServices flightsServices; //instancia de servicio


    @PostMapping("/createSeveral")
    public void addFlights(@RequestBody List<Flight> flights){
        flightsServices.createSeveralFlights(flights);
    }

    @PostMapping("/create")
    public void createFlight(@RequestBody Flight flight){
        flightsServices.createFlight(flight);
    }

    @GetMapping("/all")
    public List<Flight> getAllFlights(){
        return flightsServices.getAllFlights();
    }

    @GetMapping("/id{id}")
    public Flight findFlightById(@PathVariable Long id){
        return flightsServices.getFlightById(id);
    }

    @PutMapping("/update")
    public void updateFlight(@RequestBody Flight flight){
        flightsServices.updateFlight(flight);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFlight(@PathVariable Long id){
        flightsServices.deleteFlightById(id);
    }

    @DeleteMapping("/delete/all")
    public void deleteAllFlights(){
        flightsServices.deleteAllFlights();
    }



}
