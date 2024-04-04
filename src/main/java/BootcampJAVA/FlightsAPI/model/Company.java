package BootcampJAVA.FlightsAPI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Company {
    @Id
    Long id;
    String name;
    String banner;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
