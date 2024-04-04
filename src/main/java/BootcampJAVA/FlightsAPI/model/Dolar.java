package BootcampJAVA.FlightsAPI.model;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Data
public class Dolar {
    private String moneda;
    private String casa;
    private String nombre;
    private double compra;
    private double venta;
    LocalDateTime fechaActualizacion;

    public Dolar(String moneda, String casa, String nombre, double compra, double venta, LocalDateTime fechaActualizacion) {
        this.moneda = moneda;
        this.casa = casa;
        this.nombre = nombre;
        this.compra = compra;
        this.venta = venta;
        this.fechaActualizacion = fechaActualizacion;
    }

    public double getPromedio(){
        return((getCompra()+getVenta())/2);
    }

    public double getCompra() {
        return compra;
    }

    public double getVenta() {
        return venta;
    }

}
