package com.example.f1_insider_api.Driver;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DriverService {
	
	@Autowired
	DriverRepository driverRepository;
	
	private final String API_URL = "https://api.openf1.org/v1/drivers";
	
	public List<DriverModel> getDrivers() {
        List<DriverModel> drivers = driverRepository.findAll();
        return drivers;
    }
	
	public List<DriverModel> getDriversOrderedByPosition() throws JsonMappingException, JsonProcessingException {
	    List<DriverModel> drivers = driverRepository.findAll();
	    String driversPosition = getDriversPos();

	    // Convertir la cadena JSON de driversPosition a un array de objetos JsonNode
	    ObjectMapper objectMapper = new ObjectMapper();
	    JsonNode positionArray = objectMapper.readTree(driversPosition);

	    // Crear un mapa para mapear el número del conductor con su posición
	    Map<Integer, Integer> driverPositionMap = new HashMap<>();
	    for (JsonNode positionObject : positionArray) {
	        int driverNumber = positionObject.get("driver_number").asInt();
	        int position = positionObject.get("position").asInt();
	        driverPositionMap.put(driverNumber, position);
	    }

	    // Ordenar los conductores según la posición
	    Collections.sort(drivers, new Comparator<DriverModel>() {
	        @Override
	        public int compare(DriverModel driver1, DriverModel driver2) {
	            // Obtener el número de conductor de cada conductor
	            int driverNumber1 = driver1.getDriver_number();
	            int driverNumber2 = driver2.getDriver_number();

	            // Obtener las posiciones de los conductores del mapa
	            Integer position1 = driverPositionMap.getOrDefault(driverNumber1, Integer.MAX_VALUE);
	            Integer position2 = driverPositionMap.getOrDefault(driverNumber2, Integer.MAX_VALUE);

	            // Comparar las posiciones de los conductores
	            return Integer.compare(position1, position2);
	        }
	    });

	    return drivers;
	}
	
	public String getDriversPos() {
		String apiUrl = "https://api.openf1.org/v1/position?meeting_key=latest&session_key=latest";
		RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
	    
	    return response.getBody();
	}
	
	public Optional<DriverModel> getDriver(Long id) {
		return driverRepository.findById(id);
	}
	
	public void saveOrUpdate(DriverModel driver) {
		driverRepository.save(driver);
	}
	
	public void delete(Long id) {
		driverRepository.deleteById(id);
	}
	
	
	
	public int getDriverPos(int id) throws JsonProcessingException {
	    String apiUrl = "https://api.openf1.org/v1/position?meeting_key=latest&session_key=latest&driver_number=" + id;
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

	    String responseBody = response.getBody();
	    if (responseBody != null) {
	        ObjectMapper objectMapper = new ObjectMapper();
	        List<Map<String, Object>> objects = objectMapper.readValue(responseBody, new TypeReference<List<Map<String, Object>>>(){});
	        if (!objects.isEmpty()) {
	            Map<String, Object> lastObject = objects.get(objects.size() - 1);
	            // Verificar si la posición es un número
	            if (lastObject.get("position") instanceof Number) {
	                // Convertir el valor de posición a un entero
	                return ((Number) lastObject.get("position")).intValue();
	            } else {
	                throw new RuntimeException("La posición no es un número");
	            }
	        }
	    }
	    throw new RuntimeException("No se pudo obtener la posición del conductor");
	}
	
	public List<DriverModel> getDriversFromExternalAPI() {
		RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<String> response = restTemplate.getForEntity(API_URL, String.class);
	    String responseBody = response.getBody();
	    
	    ObjectMapper objectMapper = new ObjectMapper();
	    
	    try {
	        // Convertir la respuesta JSON a una lista de objetos DriverModel
	        List<DriverModel> drivers = objectMapper.readValue(responseBody, new TypeReference<List<DriverModel>>(){});
	        
	        // Obtener los primeros 20 elementos de la lista
	        List<DriverModel> first20Drivers = drivers.subList(0, Math.min(drivers.size(), 20));
	        
	        System.out.println(first20Drivers);
	        
	        // Imprimir los detalles de los primeros 20 drivers
	        for (DriverModel driver : first20Drivers) {
	            System.out.println("Driver Number: " + driver.getDriver_number());
	            System.out.println("Broadcast Name: " + driver.getBroadcast_name());
	            System.out.println("Full Name: " + driver.getFull_name());
	            // Imprimir más detalles si es necesario...
	        }
	        
	        return first20Drivers; // Devolver la lista de los primeros 20 drivers
	    } catch (JsonProcessingException e) {
	        // Manejar excepciones en caso de problemas al parsear la respuesta JSON
	        e.printStackTrace();
	        return Collections.emptyList();
	    }
    }
	
	
}
