package BootcampJAVA.FlightsAPI.repository;


import BootcampJAVA.FlightsAPI.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightsRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByOrigin(String origin);
    List<Flight> findByDestiny(String destiny);
    List<Flight> findByOriginAndDestiny(String origin, String destiny);
}

//Una vez que agrego la interfaz, puedo usar los metodos q extiende JPA Repo (para el CRUD)
//https://spring.io/guides/gs/accessing-data-mysql al app.properties
