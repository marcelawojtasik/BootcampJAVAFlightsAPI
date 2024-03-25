package BootcampJAVA.FlightsAPI.services;

import BootcampJAVA.FlightsAPI.configuration.FlightConfiguration;
import BootcampJAVA.FlightsAPI.model.Dolar;
import BootcampJAVA.FlightsAPI.model.Flight;
import BootcampJAVA.FlightsAPI.repository.FlightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightsServices {

        @Autowired
        private FlightsRepository flightsRepository;
        @Autowired
        FlightConfiguration flightConfiguration;

       // private List<Flight> flightsList = new ArrayList<>();
        //CRUD

        public void createSeveralFlights(List<Flight> flights){
            flightsRepository.saveAll(flights);
        }

        public void createFlight(Flight flight){
            flightsRepository.save(flight);
        }

        public List<Flight> getAllFlights() {
            return flightsRepository.findAll();
        }

        public Flight getFlightById(Long id) {
            return flightsRepository.findById(id).orElse(null);
        }

        public Flight updateFlight(Flight flight){
            flightsRepository.save(flight);
            return flightsRepository.findById(flight.getId()).orElse(null);
        }

        public void deleteFlightById(Long id) {
            flightsRepository.deleteById(id);
        }

         public void deleteAllFlights() {
            flightsRepository.deleteAll();
        }

        //FUNCIONALIDADES EXTRA
        public List<Flight> getFlightsByOrigin(String origin){
            return flightsRepository.findByOrigin(origin);
        }

        public List<Flight> getFlightsByDestiny(String destiny){
            return flightsRepository.findByDestiny(destiny);
        }

        public List<Flight> getFlightsByOriginAndDestiny(String origin, String destiny){
            return flightsRepository.findByOriginAndDestiny(origin, destiny);
        }

        public List<Flight> getFlightsByPrice(double offerPrice){
            List<Flight> allFlights = flightsRepository.findAll();
            List<Flight> flightsByPrice = new ArrayList<>();

            for(Flight flight: allFlights){
                if(flight.getPrice() < offerPrice){
                    flightsByPrice.add(flight);
                }
            }
            return flightsByPrice;
        }

        //CON STREAM
        public List<Flight> detectOffers(List<Flight> flights, double offerPrice){
            return flights.stream()
                    .filter(flight -> flight.getPrice() < offerPrice)
                    .collect(Collectors.toList());
        }
        public List<Flight> getOffers(double offerPrice){
            List<Flight> flights = flightsRepository.findAll();
            return detectOffers(flights, offerPrice);
        }

//        public Dolar getDolar() {
//            return flightConfiguration.fetchDolar();
//        }

        public double getDolar() {
            return flightConfiguration.fetchDolar().getPromedio();
        }


//NO FUNCIONA, ME TRAE ARRAY VACIO
    public List<Flight> getFlightsByOriginAndPrice(String origin, Integer offerPrice) {
        List<Flight> allFlights = flightsRepository.findAll();
        List<Flight> flightsByOriginAndPrice = new ArrayList<>();

        for(Flight flight: allFlights){
            if( (flight.getOrigin()==origin) && (flight.getPrice() < offerPrice) ){
                flightsByOriginAndPrice.add(flight);
            }
        }
        //return flightsByOriginAndPrice;
        return flightsRepository.findByOriginAndPrice(origin, offerPrice);
    }


}

