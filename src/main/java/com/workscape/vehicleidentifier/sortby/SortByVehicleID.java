package com.workscape.vehicleidentifier.sortby;

import java.util.Comparator;

import com.workscape.vehicleidentifier.jaxbutility.Vehicle;

/**
 * This class used to sort Vehicle objects by it's ID  
 * @author Vivek Singh
 *
 */
public class SortByVehicleID implements Comparator<Vehicle> {

	@Override
	public int compare(Vehicle o1, Vehicle o2) {
		return o1.getId().compareTo(o2.getId());
	}
}
