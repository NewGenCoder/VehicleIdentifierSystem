package com.workscape.vehicleidentifier.jaxbutility;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 
 * @author Vivek Singh
 *
 */
@XmlRootElement(name = "vehicle")
@XmlAccessorType(XmlAccessType.FIELD)
public class Vehicle {

	private String id;
	private Frame frame;
	private String powertrain;
	@XmlElement(name = "wheels")
	private Wheels wheels;
	
	public Vehicle(String id, Frame frame, String powertrain, Wheels wheels) {
		super();
		this.id = id;
		this.frame = frame;
		this.powertrain = powertrain;
		this.wheels = wheels;
	}
	
	public Vehicle() {
	}

	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPowertrain() {
		return powertrain;
	}

	public void setPowertrain(String powertrain) {
		this.powertrain = powertrain;
	}
	
	public Wheels getWheels() {
		return wheels;
	}

	public void setWheels(Wheels wheels) {
		this.wheels = wheels;
	}
	
	@Override
	public boolean equals(Object obj) {
		Vehicle vehicle = (Vehicle) obj;
		return this.id.equals(vehicle.getId());
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", frame=" + frame + ", powertrain="
				+ powertrain + ", wheels=" + wheels + "]";
	}
	
	@Override
	public int hashCode() {
		int hashcode = new HashCodeBuilder().append(this.id).toHashCode();
		return hashcode;
	}
	
}
