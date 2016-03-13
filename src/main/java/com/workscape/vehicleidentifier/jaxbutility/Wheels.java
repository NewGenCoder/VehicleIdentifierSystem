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
@XmlRootElement(name ="wheels")
@XmlAccessorType(XmlAccessType.FIELD)
public class Wheels {
	
	@XmlElement(name = "wheel")
	private List<Wheel> wheels;
	
	public Wheels() {
		super();
	}

	public Wheels(List<Wheel> wheels) {
		super();
		this.wheels = wheels;
	}
	
	public List<Wheel> getWheels() {
		return wheels;
	}

	public void setWheels(List<Wheel> wheels) {
		this.wheels = wheels;
	}

	@Override
	public String toString() {
		return "Wheels [wheels=" + wheels + "]";
	}
}
