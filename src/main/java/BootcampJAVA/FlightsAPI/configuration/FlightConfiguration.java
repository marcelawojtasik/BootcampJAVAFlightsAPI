package BootcampJAVA.FlightsAPI.configuration;

import BootcampJAVA.FlightsAPI.model.Dolar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class FlightConfiguration {
    //config Rest Template
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate(); //genero nuevo componente
    }

    //quiero traer el objeto Dolar
    public Dolar fetchDolar(){
        RestTemplate restTemplate = restTemplate(); //instancio objeto Rest Template
        String apiUrl = "https://dolarapi.com/v1/dolares/tarjeta";
        return restTemplate.getForObject(apiUrl, Dolar.class);
    } //puedo pasar param dolarType y concatenar a url para obtener los distintos tipos de dolar

    public Dolar[] fetchAllDolars() { //traigo array por eso lo indico []
        RestTemplate restTemplate = restTemplate();
        String apiUrl = "https://dolarapi.com/v1/dolares";
        return restTemplate.getForEntity(apiUrl, Dolar[].class).getBody(); //del Entity solo quiero el array con los datos, que esta en el body de la respuesta
    }
}
