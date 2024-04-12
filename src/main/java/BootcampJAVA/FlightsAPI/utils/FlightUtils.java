package BootcampJAVA.FlightsAPI.utils;

import BootcampJAVA.FlightsAPI.model.Dolar;
import BootcampJAVA.FlightsAPI.model.Flight;
import BootcampJAVA.FlightsAPI.model.FlightDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlightUtils {

    FlightUtils flightUtils;

    @Bean//copiado de configuration 08/04
    public RestTemplate restTemplate() {
        return new RestTemplate(); //genero nuevo componente de conexión con API externa
    }

    public FlightDTO flightMapper(Flight flight, double price) { //Trae price de API
        return new FlightDTO(flight.getId(), flight.getOrigin(), flight.getDestiny(), flight.getDepartureTime(), flight.getArrivalTime(), flight.getPrice() * price, flight.getFrequency());
    }

    //Traer lista de DTOs
    public List<FlightDTO> flightsListMapper(List<Flight> flights, double price) {
        List<FlightDTO> flightDTOs = new ArrayList<>();
        for (Flight flight : flights) {
            flightDTOs.add(new FlightDTO(flight.getId(), flight.getOrigin(), flight.getDestiny(), flight.getDepartureTime(), flight.getArrivalTime(), flight.getPrice() * price, flight.getFrequency()));
        }
        return flightDTOs;
    }

    //DTOs con STREAM - EN USO
    public List<FlightDTO> DTOsMapper(List<Flight> flights, double price) {
        List<FlightDTO> flightDTOsListMapper = new ArrayList<>();

        return flights.stream()
                .map(flight -> new FlightDTO((flight.getId()), flight.getOrigin(), flight.getDestiny(), flight.getDepartureTime(),
                        flight.getArrivalTime(), flight.getPrice() * price, flight.getFrequency()))
                .collect(Collectors.toList());
    }

    public Dolar fetchDolar() {
        RestTemplate restTemplate1 = restTemplate();
        String apiUrl = "https://dolarapi.com/v1/dolares/tarjeta";
        return restTemplate1.getForObject(apiUrl, Dolar.class);
    }

    public Dolar[] fetchAllDolars() { //traigo array []
        RestTemplate restTemplate = restTemplate();
        String apiUrl = "https://dolarapi.com/v1/dolares";
        return restTemplate.getForEntity(apiUrl, Dolar[].class).getBody();
    }

}