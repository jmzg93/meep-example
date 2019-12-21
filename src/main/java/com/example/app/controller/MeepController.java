package com.example.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.example.app.client.Client;
import com.example.app.models.Vehicle;

@Controller
public class MeepController {

	private static final Logger logger = LoggerFactory.getLogger(MeepController.class);

	@Autowired
	Client client;

	private Map<String, Vehicle> vehiclesOld = null;

	@Scheduled(cron = "0 * * * * ?")
	public void saveVehicles() {

		// Aqui la mejor forma seria guardar los resultados en una cache tipo redis para
		// tener acceso a esta informacion mas rapido, tambien se podria guardar
		// en una base de datos para que se tuviera un control de los estados del los
		// vehiculos
		logger.info("Vehiculos antes de recarga " + String.valueOf(vehiclesOld != null ? vehiclesOld.size() : 0));
		Map<String, Vehicle> mapVehicles = client.getVehicles().stream()
				.collect(Collectors.toMap(Vehicle::getId, e -> e));

		logger.info("Recibimos " + mapVehicles.size() +" de vehiculos del api");
		
		// Aqui la logica para que se comprueben si existian, se eliminan o cuales son los nuevos
		if (vehiclesOld != null) {
			List<Vehicle> listSeMantienen = new ArrayList<Vehicle>();
			List<Vehicle> listNuevos = new ArrayList<Vehicle>();
			List<Vehicle> listEliminados = new ArrayList<Vehicle>();

			// Aqui añadimos los que ya estaban
			mapVehicles.forEach((k, v) -> {
				if (vehiclesOld.containsKey(k)) {
					listSeMantienen.add(v);
				} else {
					listNuevos.add(v);
				}

			});

			vehiclesOld.forEach((k,e) -> {
				if(!mapVehicles.containsKey(e.getId())) {
					listEliminados.add(e);
				}
			});

			logger.info("Han entrado " + listNuevos.size() + " vehiculos nuevos");
			logger.info("Han salido de la lista " + listEliminados.size() + " vehiculos");

		}

		// Aqui se deberian actualizar los ya existentes por si cambiara algun
		// parametro, añadir los nuevos que hayan entrado y eliminar los que ya existian
		// si hubiera alguna persistencia, pero para evitar esto se deja el nuevo valor traido del api.
		vehiclesOld = mapVehicles;

	}

}
