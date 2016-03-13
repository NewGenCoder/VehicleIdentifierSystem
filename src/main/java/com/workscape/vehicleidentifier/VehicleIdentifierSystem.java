package com.workscape.vehicleidentifier;

import com.workscape.vehicleidentifier.service.VehicleIdentifierService;

/**
 * 
 * This class is entry point for Vehicle Identifier System
 * 
 * @author Vivek Singh <vivekrajsurya@gmail.com>
 *
 */
public class VehicleIdentifierSystem {

	public static void main(String[] args) {
		
		VehicleIdentifierService service = new VehicleIdentifierService();

		service.loadVehicles("VehicleDetails.xml");
		
		service.getVehicleType();
		
		service.generateReport();
		
	}
}