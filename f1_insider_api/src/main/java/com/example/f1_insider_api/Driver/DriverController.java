package com.example.f1_insider_api.Driver;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="api/v1/drivers")
public class DriverController {
	
	@Autowired
	private DriverService driverService;
	
	
	
	@GetMapping
	public List<DriverModel> getAll() throws JsonMappingException, JsonProcessingException{
		return driverService.getDriversOrderedByPosition();
	}
	
	@GetMapping("/driver/position/{driverId}")
    public Object getDriverPos(@PathVariable("driverId") int driverId) throws JsonMappingException, JsonProcessingException {
		Object res = driverService.getDriverPos(driverId);
		System.out.println(res);
		return res;
    }
	
	@GetMapping("/{driverId}")
	public Optional<DriverModel> getDriver(@PathVariable("driverId") Long driverId){
		return driverService.getDriver(driverId);
	}
	
	@PostMapping
	public void saveUpdate(@RequestBody DriverModel driver){
		driverService.saveOrUpdate(driver);
	}
	
	@DeleteMapping("/{driverId}")
	public void saveUpdate(@PathVariable("driverId") Long driverId){
		driverService.delete(driverId);
	}
	
	@GetMapping("/openf1")
    public List<DriverModel> getAndSaveDrivers(){
        // Obtener los conductores de la API externa
        List<DriverModel> drivers = driverService.getDriversFromExternalAPI();
        
        // Guardar los conductores en la base de datos
        for (DriverModel driver : drivers) {
            driverService.saveOrUpdate(driver);
        }
        
        return drivers;
    }
	
}
