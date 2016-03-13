package com.workscape.vehicleidentifier.jaxbutility;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Vivek Singh
 *
 */
@XmlRootElement(name = "vehicles")
@XmlAccessorType(XmlAccessType.FIELD)
public class Vehicles {

	@XmlElement(name = "vehicle")
	private List<Vehicle> vehicles;

	public Vehicles() {
	}

	public Vehicles(List<Vehicle> vehicles) {
		super();
		this.vehicles = vehicles;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	@Override
	public String toString() {
		return "Vehicles [vehicles=" + vehicles + "]";
	}
	
}
