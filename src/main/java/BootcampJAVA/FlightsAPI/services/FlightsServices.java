package BootcampJAVA.FlightsAPI.services;

import BootcampJAVA.FlightsAPI.configuration.FlightConfiguration;
import BootcampJAVA.FlightsAPI.exceptions.ResourceNotFoundException;
import BootcampJAVA.FlightsAPI.model.Company;
import BootcampJAVA.FlightsAPI.model.Dolar;
import BootcampJAVA.FlightsAPI.model.Flight;
import BootcampJAVA.FlightsAPI.model.FlightDTO;
import BootcampJAVA.FlightsAPI.repository.CompanyRepository;
import BootcampJAVA.FlightsAPI.repository.FlightsRepository;
import BootcampJAVA.FlightsAPI.utils.FlightUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
        @Autowired
        FlightUtils flightUtils;
        @Autowired
        CompanyRepository companyRepository;

       // private List<Flight> flightsList = new ArrayList<>();

        //CRUD
        public void createSeveralFlights(List<Flight> flights){
            flightsRepository.saveAll(flights);
        }
        public void createFlight(Flight flight){
            flightsRepository.save(flight);
        }
        
        
        public Flight createFlightWithCompany(Flight flight, Long companyId){
            Company company = companyRepository.findById(companyId)
                    .orElseThrow(()-> new ResourceNotFoundException("flight", "id", companyId));
            flight.setCompany(company);
            return flightsRepository.save(flight);
        }
        public void createSeveralFlightsWithCompany(List<Flight> flights, Long companyId){
            Company company = companyRepository.findById(companyId)
                    .orElseThrow(()-> new IllegalArgumentException("Company not found"));
            for (Flight flight : flights) {
                flight.setCompany(company);
            }
            flightsRepository.saveAll(flights);
        }

//        public List<Flight> getAllFlights() { return flightsRepository.findAll(); }

//        public List<FlightDTO>  getAllDTOs(){
//            List<Flight> flightList = flightsRepository.findAll();
//            return flightList.stream()
//                    .map(flight -> flightUtils.flightMapper(flight, getDolar()))
//                    .collect(Collectors.toList());
//        }

        public List<FlightDTO> findAll(){ //Mapeo lista de vuelos
            double dolarPrice = getDolar();
            //List<Flight> flights = flightsRepository.findAll();
            return flightUtils.DTOsMapper(flightsRepository.findAll(), dolarPrice); //cambie el mapper, VER
        }

        public Flight getFlightById(Long id) {
            return flightsRepository.findById(id).orElse(null);
        }

        public Flight updateFlight(Flight flight){
            flightsRepository.save(flight);
            return flightsRepository.findById(flight.getId()).orElse(null);
        }

        public void deleteFlightById(Long id) throws ResourceNotFoundException {
            Flight flight = flightsRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Flight", "id", id));
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

        //DECLARATIVA "/offers"
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

        //CON STREAM "/getOffers"
        public List<Flight> detectOffers(List<Flight> flights, double offerPrice){
            return flights.stream()
                    .filter(flight -> flight.getPrice() < offerPrice)
                    .collect(Collectors.toList());
        }
        public List<Flight> getOffers(double offerPrice){
            List<Flight> flights = flightsRepository.findAll();
            return detectOffers(flights, offerPrice);
        }

        private double getDolar() { //para realizar conversion
            return flightUtils.fetchDolar().getPromedio();
        }

        public List<Flight> getFlightsByOriginAndPrice(String origin, Integer offerPrice) {
            List<Flight> allFlights = flightsRepository.findAll();
            List<Flight> flightsByOriginAndPrice = new ArrayList<>();

            for(Flight flight: allFlights){
                if( (flight.getOrigin().equals(origin)) && (flight.getPrice() < offerPrice) ){ //ver, antes comp con == equals
                    flightsByOriginAndPrice.add(flight);
                }
            }
            return flightsByOriginAndPrice;
        }

        public List<Dolar> getAllDollars() {
                return List.of(flightUtils.fetchAllDolars()); //List.of transforma Array en Lista
        } //va a traer tambien el promedio, x metodo creado en clase Dolar
}

