package BootcampJAVA.FlightsAPI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //para que se genere automaticamente distintos ids numericos
    private Long id;
    private String origin;
    private String destiny;
    private String departureTime;
    private String arrivalTime;
    private double price;
    private String frequency;

//    public Flight(Long id, String origin, String destiny, String departureTime, String arrivalTime, double price, String frequency) {
//        this.id = id;
//        this.origin = origin;
//        this.destiny = destiny;
//        this.departureTime = departureTime;
//        this.arrivalTime = arrivalTime;
//        this.price = price;
//        this.frequency = frequency;
//    }

    public Flight(String origin, String destiny, String departureTime, String arrivalTime, double price, String frequency) {
        this.origin = origin;
        this.destiny = destiny;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.frequency = frequency;
    }

//
//    public void setDepartureTime(String departureTime) {
//        this.departureTime = departureTime;
//    }
//
//    public void setArrivalTime(String arrivalTime) {
//        this.arrivalTime = arrivalTime;
//    }
//
//    public void setFrequency(String frequency) {
//        this.frequency = frequency;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setOrigin(String origin) {
//        this.origin = origin;
//    }
//
//    public void setDestiny(String destiny) {
//        this.destiny = destiny;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public String getOrigin() {
//        return origin;
//    }
//
//    public String getDestiny() {
//        return destiny;
//    }
//
//    public String getDepartureTime() {
//        return departureTime;
//    }
//
//    public String getArrivalTime() {
//        return arrivalTime;
//    }
//
//    public String getFrequency() {
//        return frequency;
//    }
}
