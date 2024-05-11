package BootcampJAVA.FlightsAPI.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //
    private Long id;
    private String origin;
    private String destiny;
    private String departureTime;
    private String arrivalTime;
    private double price;
    private String frequency;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public Flight(String origin, String destiny, String departureTime, String arrivalTime, double price, String frequency) {
        this.origin = origin;
        this.destiny = destiny;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.frequency = frequency;
    }

    public Flight(String origin, String destiny, String departureTime, String arrivalTime, double price, String frequency, Company company) {
        this.origin = origin;
        this.destiny = destiny;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.frequency = frequency;
        this.company = company;
    }
}
