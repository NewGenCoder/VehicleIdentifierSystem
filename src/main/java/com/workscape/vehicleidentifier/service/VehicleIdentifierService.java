package com.workscape.vehicleidentifier.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.Validate;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.workscape.vehicleidentifier.constants.FrameType;
import com.workscape.vehicleidentifier.constants.PowertrainType;
import com.workscape.vehicleidentifier.constants.VehicleType;
import com.workscape.vehicleidentifier.jaxbutility.Vehicle;
import com.workscape.vehicleidentifier.jaxbutility.Vehicles;
import com.workscape.vehicleidentifier.sortby.SortByVehicleID;

/**
 * 
 * This class is responsible to provide the service for Vehicle Identifier
 * System
 * 
 * @author Vivek Singh
 * 
 *
 */
public class VehicleIdentifierService {

	/*
	 * This property cached the all vehicle object
	 */
	public static Set<Vehicle> vehicles = new TreeSet(new SortByVehicleID());

	/*
	 * This method accept the xml file convert it to java object and store in
	 * TreeSet.
	 */
	public void loadVehicles(final String fileName) {

		Validate.notEmpty(fileName);
		File file = new File(fileName);
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Vehicles.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Vehicles vehicles = (Vehicles) jaxbUnmarshaller.unmarshal(file);

			for (Vehicle vehicle : vehicles.getVehicles()) {
				VehicleIdentifierService.vehicles.add(vehicle);
			}

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * This method return the map of vehicle number and type.
	 */
	public Map getVehicleType() {

		Map<String, Enum<VehicleType>> vehicleIDAndType = new HashMap<>();

		Iterator<Vehicle> iterator = vehicles.iterator();

		while (iterator.hasNext()) {
			Vehicle vehicle = (Vehicle) iterator.next();

			if (vehicle.getFrame().getMaterial().equals(FrameType.plastic.name())) {
				if (vehicle.getWheels().getWheels() == null) {
					vehicleIDAndType.put(vehicle.getId(), VehicleType.HangGlider);
				} else {
					vehicleIDAndType.put(vehicle.getId(), VehicleType.BigWheel);
				}
			} else {
				if (vehicle.getWheels().getWheels().size() == 4) {
					vehicleIDAndType.put(vehicle.getId(), VehicleType.Car);
				} else {
					if (vehicle.getPowertrain().equals(PowertrainType.Human.name())) {
						vehicleIDAndType.put(vehicle.getId(), VehicleType.Bicycle);
					} else {
						vehicleIDAndType.put(vehicle.getId(), VehicleType.Motorcycle);
					}
				}
			}
		}

		return vehicleIDAndType;
	}

	/*
	 * This method generate report
	 */
	public void generateReport() {
		try {

			OutputStream file = new FileOutputStream(new File("VehicleIdentity_Report.pdf"));
			Document document = new Document();
			PdfWriter.getInstance(document, file);

			document.open();
			document.add(createTableForVehicleType());
			document.add(createTableForVehicleCount());
			document.close();
			file.close();

		} catch (IOException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private PdfPTable createTableForVehicleType() {
		PdfPTable vehicleType = new PdfPTable(2);
		PdfPCell cell = new PdfPCell(new Paragraph("ADP Vehicle Identity System"));

		cell.setColspan(3);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(10.0f);

		vehicleType.addCell(cell);

		vehicleType.addCell("VehicleID");
		vehicleType.addCell("Vehicle Type");

		Map<String, Enum<VehicleType>> vehicleIDAndType = getVehicleType();

		for (Entry<String, Enum<VehicleType>> vehicle : vehicleIDAndType.entrySet()) {

			vehicleType.addCell(vehicle.getKey());
			vehicleType.addCell(vehicle.getValue().name());
		}

		vehicleType.setSpacingBefore(30.0f);
		vehicleType.setSpacingAfter(30.0f);
		return vehicleType;

	}

	private PdfPTable createTableForVehicleCount() {
		int bigWheel = 0, bicycle = 0, motorcycle = 0, hangGlider = 0, car = 0;

		PdfPTable vehicleType = new PdfPTable(2);
		PdfPCell cell = new PdfPCell(new Paragraph(" Vehicle Types and their count"));

		cell.setColspan(3);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(10.0f);

		vehicleType.addCell(cell);

		vehicleType.addCell("Vehicle Type");
		vehicleType.addCell("Count");

		Map<String, Enum<VehicleType>> vehicleIDAndType = getVehicleType();

		for (Entry<String, Enum<VehicleType>> vehicle : vehicleIDAndType.entrySet()) {

			if (vehicle.getValue().name().equals(VehicleType.BigWheel.name()))
				++bigWheel;

			if (vehicle.getValue().name().equals(VehicleType.Bicycle.name()))
				++bicycle;

			if (vehicle.getValue().name().equals(VehicleType.Motorcycle.name()))
				++motorcycle;

			if (vehicle.getValue().name().equals(VehicleType.HangGlider.name()))
				++hangGlider;

			if (vehicle.getValue().name().equals(VehicleType.Car.name())) {
				++car;
			}

		}

		vehicleType.addCell(VehicleType.BigWheel.name());
		vehicleType.addCell((bigWheel + ""));
		vehicleType.addCell(VehicleType.Bicycle.name());
		vehicleType.addCell((bicycle + ""));
		vehicleType.addCell(VehicleType.Motorcycle.name());
		vehicleType.addCell((motorcycle + ""));
		vehicleType.addCell(VehicleType.HangGlider.name());
		vehicleType.addCell((hangGlider + ""));
		vehicleType.addCell(VehicleType.Car.name());
		vehicleType.addCell((car + ""));

		vehicleType.setSpacingBefore(30.0f);
		vehicleType.setSpacingAfter(30.0f);
		return vehicleType;

	}

}
