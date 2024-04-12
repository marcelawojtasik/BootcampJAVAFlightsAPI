package BootcampJAVA.FlightsAPI.model;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
public class Dolar {
    private String moneda;
    private String casa;
    private String nombre;
    private double compra;
    private double venta;
    LocalDateTime fechaActualizacion;

    public double getPromedio(){
        return((getCompra()+getVenta())/2);
    }

}
