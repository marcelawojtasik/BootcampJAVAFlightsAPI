package BootcampJAVA.FlightsAPI.model;

import lombok.Data;

@Data
public class FlightDTO {
    private Long id;
    private String origin;
    private String destiny;
    private String departureTime;
    private String arrivalTime;
    private double convertedPrice;
    private String frequency;

    public FlightDTO(Long id, String origin, String destiny, String departureTime, String arrivalTime, double convertedPrice, String frequency) {
        this.id = id;
        this.origin = origin;
        this.destiny = destiny;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.convertedPrice = convertedPrice;
        this.frequency = frequency;
    }

}


