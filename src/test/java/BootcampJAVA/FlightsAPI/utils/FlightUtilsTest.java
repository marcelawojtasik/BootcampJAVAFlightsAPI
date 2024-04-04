package BootcampJAVA.FlightsAPI.utils;

import BootcampJAVA.FlightsAPI.model.Flight;
import BootcampJAVA.FlightsAPI.model.FlightDTO;
import BootcampJAVA.FlightsAPI.services.FlightsServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class FlightUtilsTest {

    @Autowired
    FlightUtils flightUtils;

    @BeforeEach
    public void setUp(){
        List<Flight> flightList;
        List<FlightDTO> flightDTOList;
        double dolarPrice = 1100; //puedo hardcodear xq no interesa valor real si no que funcione
    }

    @Test
    void flightsListMapperTest() {
        double dolarPrice = 1100; //no lo toma como param si no
        List<Flight> flightList = new ArrayList<>();//inicializo lista de vuelos
        List<FlightDTO> flightDTOList = new ArrayList<>(); //inicializo lista DTOs

        Flight flight = new Flight(); //Creo vuelo
        //Flight.setId(1L);
        flight.setOrigin("COR");
        flight.setDestiny("EZE");
        flight.setDepartureTime("Salida");
        flight.setArrivalTime("Llegada");
        flight.setPrice(190);
        flight.setFrequency("semanal");

        flightList.add(flight); //agrego el vuelo a la lista


        flightDTOList = flightUtils.DTOsMapper(flightList, dolarPrice);

        FlightDTO flightDTO = flightDTOList.get(0);
        //assertEquals(1, flightDTO.getId());
        assertEquals(flight.getPrice() * dolarPrice, flightDTO.getConvertedPrice());
    }
}