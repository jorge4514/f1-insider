package com.example.f1_insider_api.Radio;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="api/v1/team_radio")
public class RadioController {

    private final RestTemplate restTemplate;

    public RadioController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{driverNumber}")
    public String getLapsInfo(@PathVariable int driverNumber) {
        // Construir la URL con el driver_number proporcionado
        String apiUrl = "https://api.openf1.org/v1/team_radio?meeting_key=latest&session_key=latest&driver_number=" + driverNumber;

        // Realizar solicitud HTTP GET y obtener la respuesta como una cadena
        String response = restTemplate.getForObject(apiUrl, String.class);

        return response;
    }
}
