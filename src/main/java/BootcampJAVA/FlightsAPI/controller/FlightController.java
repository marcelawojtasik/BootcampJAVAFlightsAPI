package BootcampJAVA.FlightsAPI.controller;


import BootcampJAVA.FlightsAPI.exceptions.ResourceNotFoundException;
import BootcampJAVA.FlightsAPI.model.Company;
import BootcampJAVA.FlightsAPI.model.Dolar;
import BootcampJAVA.FlightsAPI.model.Flight;
import BootcampJAVA.FlightsAPI.model.FlightDTO;
import BootcampJAVA.FlightsAPI.services.FlightsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired //inyecto el serv
    private FlightsServices flightsServices; //instancia de servicio

    //RUTAS PARA CRUD

    @PostMapping("/createSeveral")
    public void addFlights(@RequestBody List<Flight> flights){
        flightsServices.createSeveralFlights(flights);
    }

    @PostMapping("/create")
    public void createFlight(@RequestBody Flight flight){
        flightsServices.createFlight(flight);
    }

    @PostMapping("/createWithCompany{companyId}")
    public Flight createFlightWithCompany(@RequestBody Flight flight, @RequestParam Long companyId){
        return flightsServices.createFlightWithCompany(flight, companyId);
    }

    @PostMapping("/createSeveralWithCompany{companyId}")
    public void addFlightsWithCompany(@RequestBody List<Flight> flights, @RequestParam Long companyId){
        flightsServices.createSeveralFlightsWithCompany(flights, companyId);
    }

//    @GetMapping("/all")
//    public List<Flight> getAllFlights(){
//        return flightsServices.getAllFlights();
//    }
    @CrossOrigin
    @GetMapping("/all")
    public List<FlightDTO> getAllFlights(){
        //return flightsServices.getAllDTOs();
        return flightsServices.findAll();
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
    public String deleteFlight(@PathVariable Long id){
        try{
            flightsServices.deleteFlightById(id);
            return "Vuelo borrado exitosamente";
        } catch(ResourceNotFoundException e){
            System.out.println(e.getMessage());
            return "No se encontró el vuelo solicitado";
        }
    }


    @DeleteMapping("/delete/all")
    public void deleteAllFlights(){
        flightsServices.deleteAllFlights();
    }

    //ENDPOINTS FUNC EXTRA
    @GetMapping("/origin")
    public List<Flight> getByOrigin(@RequestParam String origin){
        return flightsServices.getFlightsByOrigin(origin);
    }

    @GetMapping("/destiny")
    public List<Flight> getByDestiny(@RequestParam String destiny){
        return flightsServices.getFlightsByDestiny(destiny);
    }

    @GetMapping("/origin-destiny")
    public List<Flight> getByOriginAndDestiny(@RequestParam String origin, String destiny){
        return flightsServices.getFlightsByOriginAndDestiny(origin, destiny);
    }

    @GetMapping("/offers")
    public List<Flight> getByPrice(@RequestParam double offerPrice){
        return flightsServices.getFlightsByPrice(offerPrice);
    }

    @GetMapping("/getOffers") //Misma función q endpoint "offers", usa stream
    public List<Flight> getOffers(@RequestParam double offerPrice){
        return flightsServices.getOffers(offerPrice);
    }

    @GetMapping("/origin-offers")
    public List<Flight> getByOriginAndPrice(String origin, Integer offerPrice){
        return flightsServices.getFlightsByOriginAndPrice(origin, offerPrice);
    }

    @GetMapping("/allDollars")
    public List<Dolar> getAllDolars(){
        return flightsServices.getAllDollars();
    }


}








//    @GetMapping("/dolarPrice")
//    public double getDolar(){
//        return flightsServices.getDolar();
//    }


