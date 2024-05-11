package BootcampJAVA.FlightsAPI.utils;

import BootcampJAVA.FlightsAPI.model.Dolar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DolarUtils {

    DolarUtils dolarUtils;

    @Value("${dollar.apiUrl}")
    private String DOLLAR_API_URL;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public Dolar fetchDolar() {
        RestTemplate restTemplate = restTemplate();
        //String apiUrl = "https://dolarapi.com/v1/dolares/tarjeta";
        return restTemplate.getForObject(DOLLAR_API_URL, Dolar.class);
    }

    public Dolar[] fetchAllDolars() { //traigo array []
        RestTemplate restTemplate = restTemplate();
        String apiUrl = "https://dolarapi.com/v1/dolares";
        return restTemplate.getForEntity(apiUrl, Dolar[].class).getBody(); //del Entity busco. Del body obtengo el array
    }
//puedo pasar param dolarType y concatenar a url para obtener los distintos tipos de dolar

}
