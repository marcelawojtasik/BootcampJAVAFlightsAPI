package BootcampJAVA.FlightsAPI.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class FlightTest {

    private static Flight flight;


    @Test
    public void setAndGetOriginTest() {
        //Contexto

        //Funcionalidad
        String testedOrigin = "Ezeiza";
        flight.setOrigin(testedOrigin);
        System.out.println("Se le asigna el origen " + testedOrigin);
        //Comportamiento
        Assertions.assertEquals(flight.getOrigin(),testedOrigin);
    } //si cambio el valor en cualquiera de los testedOrigin, el test NO pasa

    @Test
    public void getPriceTest(){
        double testedPrice = 500;
        flight.setPrice(testedPrice);
        System.out.println("Se le asigna el precio " + testedPrice);
        Assertions.assertEquals(flight.getPrice(), testedPrice);
    }
    

    @BeforeAll
    public static void setUp() {
        flight = new Flight();
        System.out.println("Se está creando el vuelo");
    }
}