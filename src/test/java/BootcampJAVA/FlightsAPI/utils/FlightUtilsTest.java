package BootcampJAVA.FlightsAPI.utils;

import BootcampJAVA.FlightsAPI.model.Dolar;
import BootcampJAVA.FlightsAPI.model.Flight;
import BootcampJAVA.FlightsAPI.model.FlightDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
class FlightUtilsTest {

    @Autowired
    FlightUtils flightUtils; //Inyecto dependencia

    @BeforeEach
    public void setUp(){
        List<Flight> flightList;
        List<FlightDTO> flightDTOList;
        double dolarPrice = 1100;
    }

    @Test
    void flightsListMapperTest() {
        double dolarPrice = 1100; //no lo toma como param si no
        List<Flight> flightList = new ArrayList<>();//inicializo lista de vuelos
        List<FlightDTO> flightDTOList = new ArrayList<>(); //inicializo lista DTOs

        Flight flight = new Flight(); //Creo vuelo
        flight.setId(1L);
        flight.setOrigin("COR");
        flight.setDestiny("EZE");
        flight.setDepartureTime("Salida");
        flight.setArrivalTime("Llegada");
        flight.setPrice(190);
        flight.setFrequency("semanal");

        flightList.add(flight); //agrego el vuelo a la lista


        flightDTOList = flightUtils.DTOsMapper(flightList, dolarPrice); //Llamo al metodo a probar

        FlightDTO flightDTO = flightDTOList.get(0);
        assertEquals(1, flightDTO.getId());
        assertEquals(flight.getPrice() * dolarPrice, flightDTO.getConvertedPrice());
    }

    @Test
    void fetchDolarTest(){
        //genero contexto
        //FlightUtils mockedFlightUtils = mock(DolarUtils.class);
        DolarUtils mockedDolarUtils = mock(DolarUtils.class);
        Dolar dummyDolar = new Dolar();

        dummyDolar.setMoneda("USD");
        dummyDolar.setCasa("tarjeta");
        dummyDolar.setNombre("Tarjeta");
        dummyDolar.setVenta(1000.00);
        dummyDolar.setCompra(1200.00);

        //genero Mock
        when(mockedDolarUtils.fetchDolar()).thenReturn(dummyDolar);

        //Llamo a la funcionalidad
        Dolar dolar = mockedDolarUtils.fetchDolar(); //Si tengo otro dolar, comparo entre si

        //Verificaciones
//        assertEquals(dummyDolar.getPromedio(), dolar.getPromedio());
        assertEquals(1100, dolar.getPromedio());
    }

}

