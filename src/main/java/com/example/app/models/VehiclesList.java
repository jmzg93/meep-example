package com.example.app.models;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VehiclesList {

	private List<Vehicle> vehicles;

}
