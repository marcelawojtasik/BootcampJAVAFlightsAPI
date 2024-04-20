package BootcampJAVA.FlightsAPI.utils;

import BootcampJAVA.FlightsAPI.model.Flight;
import BootcampJAVA.FlightsAPI.model.FlightDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlightUtils {

    FlightUtils flightUtils;

    public FlightDTO flightMapper(Flight flight, double price) { //Trae price de API
        return new FlightDTO(flight.getId(), flight.getOrigin(), flight.getDestiny(), flight.getDepartureTime(), flight.getArrivalTime(), flight.getPrice() * price, flight.getFrequency(), flight.getCompany());
    }

    //Traer lista de DTOs
    public List<FlightDTO> flightsListMapper(List<Flight> flights, double price) {
        List<FlightDTO> flightDTOs = new ArrayList<>();
        for (Flight flight : flights) {
            flightDTOs.add(new FlightDTO(flight.getId(), flight.getOrigin(), flight.getDestiny(), flight.getDepartureTime(), flight.getArrivalTime(), flight.getPrice() * price, flight.getFrequency(), flight.getCompany()));
        }
        return flightDTOs;
    }

    //DTOs con STREAM - EN USO
    public List<FlightDTO> DTOsMapper(List<Flight> flights, double price) {
        List<FlightDTO> flightDTOsListMapper = new ArrayList<>();

        return flights.stream()
                .map(flight -> new FlightDTO((flight.getId()), flight.getOrigin(), flight.getDestiny(), flight.getDepartureTime(),
                        flight.getArrivalTime(), flight.getPrice() * price, flight.getFrequency(), flight.getCompany()))
                .collect(Collectors.toList());
    }

}