package com.example.app.client.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.app.client.Client;
import com.example.app.models.Vehicle;

@Service
public class ClientImpl implements Client {

	@Value("${applications.client.url}")
	private String url;

	@Override
	public List<Vehicle> getVehicles() {
		try {
			
			//Aqui se podrian poner parametros para recuperar mas especificamente los vehiculos de una zona, pero en este caso he usado la url que se me entrego
			RestTemplate plantilla = new RestTemplate();
			List<Vehicle> resultado = Arrays.asList(plantilla.getForObject(url, Vehicle[].class));
			

			return resultado;
		} catch (Exception e) {
			return null;
		}

	}

}
