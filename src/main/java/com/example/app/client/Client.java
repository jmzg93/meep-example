package com.example.app.client;

import java.util.List;

import com.example.app.models.Vehicle;
import com.example.app.models.VehiclesList;

public interface Client {
	
	List<Vehicle> getVehicles(); 
	

}
