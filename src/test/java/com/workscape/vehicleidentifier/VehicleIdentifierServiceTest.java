package com.workscape.vehicleidentifier;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

import com.workscape.vehicleidentifier.constants.FrameType;
import com.workscape.vehicleidentifier.constants.PowertrainType;
import com.workscape.vehicleidentifier.constants.VehicleType;
import com.workscape.vehicleidentifier.jaxbutility.Vehicle;
import com.workscape.vehicleidentifier.service.VehicleIdentifierService;

/**
 * 
 * This class test the methods of VehicleIdentifierService class.
 * 
 * @author Vivek Singh
 *
 */
public class VehicleIdentifierServiceTest {

	/*
	 * If null passes to method loadNullAsFile of class VehicleIdentifierService
	 * then it throws exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void loadNullAsFileNameTest() {
		VehicleIdentifierService service = new VehicleIdentifierService();
		service.loadVehicles(null);
		Assert.fail();
	}

	/*
	 * In input file there are 5 vehicle object then map also have same count.
	 */
	@Test
	public void validateXmlDataTest() {
		VehicleIdentifierService service = new VehicleIdentifierService();
		service.loadVehicles("TestVehicleDetails.xml");

		Set<Vehicle> vehicles = service.vehicles;

		for (Vehicle vehicle : vehicles) {
			if (vehicle.getId().equals("vehicle101")) {
				Assert.assertEquals(vehicle.getPowertrain(), PowertrainType.Human.name());
				Assert.assertEquals(vehicle.getFrame().getMaterial(), FrameType.plastic.name());
				Assert.assertEquals(vehicle.getWheels().getWheels().size(), 3);
			}
			if (vehicle.getId().equals("vehicle102")) {
				Assert.assertEquals(vehicle.getPowertrain(), PowertrainType.Human.name());
				Assert.assertEquals(vehicle.getFrame().getMaterial(), FrameType.metal.name());
				Assert.assertEquals(vehicle.getWheels().getWheels().size(), 2);
			}
			if (vehicle.getId().equals("vehicle103")) {
				Assert.assertEquals(vehicle.getPowertrain(), PowertrainType.InternalCombustion.name());
				Assert.assertEquals(vehicle.getFrame().getMaterial(), FrameType.metal.name());
				Assert.assertEquals(vehicle.getWheels().getWheels().size(), 2);
			}

			if (vehicle.getId().equals("vehicle104")) {
				Assert.assertEquals(vehicle.getPowertrain(), PowertrainType.Bernoulli.name());
				Assert.assertEquals(vehicle.getFrame().getMaterial(), FrameType.plastic.name());
				Assert.assertTrue(vehicle.getWheels().getWheels() == null);
			}

			if (vehicle.getId().equals("vehicle105")) {
				Assert.assertEquals(vehicle.getPowertrain(), PowertrainType.InternalCombustion.name());
				Assert.assertEquals(vehicle.getFrame().getMaterial(), "");
				Assert.assertEquals(vehicle.getWheels().getWheels().size(), 4);
			}

		}

		Assert.assertEquals(VehicleIdentifierService.vehicles.size(), 5);

	}

	@Test
	public void getVehicleTypeTest() {
		VehicleIdentifierService service = new VehicleIdentifierService();
		service.loadVehicles("TestVehicleDetails.xml");

		Map<String, Enum<VehicleType>> vehicleIDAndType = service.getVehicleType();

		for (Entry<String, Enum<VehicleType>> vehicle : vehicleIDAndType.entrySet()) {
			if (vehicle.getKey().equals("vehicle101")) {
				Assert.assertEquals(vehicle.getValue().name(), VehicleType.BigWheel.name());
			}
			if (vehicle.getKey().equals("vehicle102")) {
				Assert.assertEquals(vehicle.getValue().name(), VehicleType.Bicycle.name());
			}
			if (vehicle.getKey().equals("vehicle103")) {
				Assert.assertEquals(vehicle.getValue().name(), VehicleType.Motorcycle.name());
			}
			if (vehicle.getKey().equals("vehicle104")) {
				Assert.assertEquals(vehicle.getValue().name(), VehicleType.HangGlider.name());
			}

			if (vehicle.getKey().equals("vehicle105")) {
				Assert.assertEquals(vehicle.getValue().name(), VehicleType.Car.name());
			}

		}

	}

}
