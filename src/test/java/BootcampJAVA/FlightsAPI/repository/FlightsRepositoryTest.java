package BootcampJAVA.FlightsAPI.repository;

import BootcampJAVA.FlightsAPI.model.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest //le indico testeo repo
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //debo quitar la autoconfiguracion
public class FlightsRepositoryTest {

    //inyecto el repo para poder testearlo y debo tener objeto del tipo
    @Autowired
    private FlightsRepository flightsRepository;

    private Flight flight;

    @BeforeEach
    void setUp(){ //inicializo el vuelo
        flight = new Flight("Buenos Aires", "Bariloche", "2024-12-21 09:00", "2024-12-21 14:15", 220, "semanal");
    } //conf previa de save

    @Test
    void saveFlightTest(){ //1er metodo del repo para el CRUD
        //funcionalidad
        Flight flightBD = flightsRepository.save(flight);
        //comportamiento
        assertThat(flightBD).isNotNull(); //si digo isNull el test NO pasa
        assertThat(flightBD.getId()).isGreaterThan(0);
    }

    @Test
    void flightFindByIdTest(){
        //config previa: tener guardado el vuelo
        flightsRepository.save(flight);
        //llamo a la funcionalidad
        Flight flight1 = flightsRepository.findById(flight.getId()).get();
        assertThat(flight1).isNotNull();
    }

    @Test
    void flightsFindAllTest(){
        Flight flight2 = new Flight("Buenos Aires", "Cordoba", "2024-12-21 19:00", "2024-12-21 23:15", 190, "diaria");
        flightsRepository.save(flight);
        flightsRepository.save(flight2);

        List<Flight> flightsList = flightsRepository.findAll();

        assertThat(flightsList).isNotNull();
        assertThat(flightsList.size()).isEqualTo(2); // 69???
    }

    @Test
    void flightDeleteById(){
        flightsRepository.save(flight);
        flightsRepository.deleteById(flight.getId());
        Optional<Flight> deletedFlight= flightsRepository.findById(flight.getId());
        assertThat(deletedFlight).isEmpty();
    }

    @Test
    void flightUpdateTest(){
        flightsRepository.save(flight);
        Flight flightBD = flightsRepository.findById(flight.getId()).get();
        flightBD.setOrigin("Mendoza");
        flightBD.setDestiny("Rosario");
        Flight flightUpdated = flightsRepository.save(flightBD);
        assertThat(flightUpdated.getOrigin()).isEqualTo("Mendoza");
        assertThat(flightUpdated.getDestiny()).isEqualTo("Rosario");
    }

    @Test
    void flightFindByOriginTest(){
        Flight flight2 = new Flight("Buenos Aires", "Cordoba", "2024-12-21 09:00", "2024-12-21 14:15", 220, "semanal");
        Flight flight3 = new Flight("Bariloche", "Rosario", "2024-12-21 09:00", "2024-12-21 14:15", 220, "semanal");

        flightsRepository.save(flight);
        flightsRepository.save(flight2);
        flightsRepository.save(flight3);

        List<Flight> flights = flightsRepository.findByOrigin("Buenos Aires");
//        List<Flight> flights = flightsRepository.findByOrigin(flight.getOrigin());
        assertThat(flights.stream()
                .filter(flight -> flight.getOrigin() == "Buenos Aires")
                .collect(Collectors.toList()).size()).isEqualTo(2);
        //assertThat(flights.size()).isEqualTo(2); //3?


    }
}