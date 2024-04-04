package BootcampJAVA.FlightsAPI.utils;

import BootcampJAVA.FlightsAPI.repository.FlightsRepository;
import BootcampJAVA.FlightsAPI.model.Flight;
import BootcampJAVA.FlightsAPI.model.FlightDTO;
import org.springframework.stereotype.Component;

import BootcampJAVA.FlightsAPI.model.Dolar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlightUtils {

    FlightUtils flightUtils;

    public FlightDTO flightMapper(Flight flight, double price){ //el price me lo traigo de la API
        return new FlightDTO(flight.getId(), flight.getOrigin(), flight.getDestiny(), flight.getDepartureTime(), flight.getArrivalTime(), flight.getPrice()*price, flight.getFrequency());
    }

    //para que me traiga lista de DTOs
    public List<FlightDTO> flightsListMapper(List <Flight> flights, double price){
        List <FlightDTO> flightDTOs = new ArrayList<>();
        for(Flight flight : flights){
            flightDTOs.add(new FlightDTO(flight.getId(), flight.getOrigin(), flight.getDestiny(), flight.getDepartureTime(), flight.getArrivalTime(), flight.getPrice()*price, flight.getFrequency()));
        }
        return flightDTOs;
    }

    //con STREAM - EN USO
    public List<FlightDTO>  DTOsMapper(List <Flight> flights, double price){
            List<FlightDTO> flightDTOsListMapper = new ArrayList<>();

            return flights.stream()
                    .map(flight -> new FlightDTO((flight.getId()), flight.getOrigin(), flight.getDestiny(), flight.getDepartureTime(),
                            flight.getArrivalTime(), flight.getPrice()*price, flight.getFrequency()))
                    .collect(Collectors.toList());
    }
}
