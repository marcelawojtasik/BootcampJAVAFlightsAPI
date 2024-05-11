package BootcampJAVA.FlightsAPI.services;

import BootcampJAVA.FlightsAPI.configuration.FlightConfiguration;
import BootcampJAVA.FlightsAPI.exceptions.ResourceNotFoundException;
import BootcampJAVA.FlightsAPI.model.Company;
import BootcampJAVA.FlightsAPI.model.Dolar;
import BootcampJAVA.FlightsAPI.model.Flight;
import BootcampJAVA.FlightsAPI.model.FlightDTO;
import BootcampJAVA.FlightsAPI.repository.CompanyRepository;
import BootcampJAVA.FlightsAPI.repository.FlightsRepository;
import BootcampJAVA.FlightsAPI.utils.DolarUtils;
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
        DolarUtils dolarUtils;
        @Autowired
        CompanyRepository companyRepository;


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
            return flightUtils.DTOsMapper(flightsRepository.findAll(), dolarPrice);
        }

        public FlightDTO getFlightDTOById(Long id) {
            double dolarPrice = getDolar();
            Flight flight = flightsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe el vuelo identificado con el id "+id));
            return flightUtils.flightMapper(flight,dolarPrice);
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

        public List<FlightDTO> getFlightsByOrigin(String origin) {
            List<FlightDTO> byOrigin = new ArrayList<>();
            double dolarPrice = getDolar();
            return flightUtils.DTOsMapper(flightsRepository.findByOrigin(origin), dolarPrice);
        }

        public List<FlightDTO> getFlightsByDestiny(String destiny){
            double dolarPrice = getDolar();
            return flightUtils.DTOsMapper((flightsRepository.findByDestiny(destiny)), dolarPrice);
        }

        public List<FlightDTO> getFlightsByOriginAndDestiny(String origin, String destiny){
            double dolarPrice = getDolar();
            return flightUtils.DTOsMapper(flightsRepository.findByOriginAndDestiny(origin, destiny), dolarPrice);
        }

        //DECLARATIVA "/offers"
        public List<FlightDTO> getFlightsByPrice(double offerPrice){
            List<Flight> allFlights = flightsRepository.findAll();
            List<Flight> flightsByPrice = new ArrayList<>();
            double dolarPrice = getDolar();

            for(Flight flight: allFlights){
                if(flight.getPrice() < offerPrice){
                    flightsByPrice.add(flight);
                }
            }
            return flightUtils.flightsListMapper(flightsByPrice, dolarPrice);
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

        public List<Flight> detectFlightsByOriginAndPrice(List<Flight> flights, String origin, Integer offerPrice) {
            return flights.stream()
                    .filter((flight -> flight.getPrice() < offerPrice & flight.getOrigin().equals(origin)))
                    .collect(Collectors.toList());
        }
        public List<FlightDTO> getFlightsDTOByOAP(String origin, Integer offerPrice){
            List<Flight> flights = flightsRepository.findAll();
            double dolarPrice = getDolar();
            return flightUtils.DTOsMapper(detectFlightsByOriginAndPrice(flights, origin, offerPrice), dolarPrice);
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

        private double getDolar() {
            return dolarUtils.fetchDolar().getPromedio();
        }
        public List<Dolar> getAllDollars() {
                return List.of(dolarUtils.fetchAllDolars()); //List.of transforma Array en Lista
        } //trae promedio, x metodo en clase Dolar
}




//        public List<FlightDTO> getFlightsByOrigin(String origin) { //Ver como hacer que devuelva mensaje o lista, se puede?
//            List<FlightDTO> byOrigin = new ArrayList<>();
//            long quantity = byOrigin.size(); //SIEMPRE DA CERO
//            System.out.println(quantity);
//            double dolarPrice = getDolar();
//            if (quantity>0){
//                System.out.println("Lista de vuelos");
//            } else {
//                System.out.println("No hay vuelos de origen " + origin);
//            }
//            return flightUtils.DTOsMapper(flightsRepository.findByOrigin(origin), dolarPrice);
//        }