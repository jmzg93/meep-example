package com.example.app.models;

import lombok.Data;

@Data
public class Vehicle {
	
	private String id;
	private String name;
	private int x;
	private int y;
	private String licencePlate;
	private int range;
	private int batteryLevel;
	private int seats;
	private String model;
	private String resourceImageId;
	private Double pricePerMinuteParking;
	private Double pricePerMinuteDriving;
	private Boolean realTimeData;
	private String engineType;
	private String resourceType;
	private int companyZoneId;
	

	
}
