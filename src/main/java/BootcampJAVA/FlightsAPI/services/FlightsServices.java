package BootcampJAVA.FlightsAPI.services;

import BootcampJAVA.FlightsAPI.model.Flight;
import BootcampJAVA.FlightsAPI.repository.FlightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightsServices {

        @Autowired
        private FlightsRepository flightsRepository;

       // private List<Flight> flightsList = new ArrayList<>();

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
}

