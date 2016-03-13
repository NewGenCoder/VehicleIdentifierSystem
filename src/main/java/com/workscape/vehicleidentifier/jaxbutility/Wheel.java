package com.workscape.vehicleidentifier.jaxbutility;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Vivek Singh
 *
 */
@XmlRootElement(name = "wheel")
@XmlAccessorType(XmlAccessType.FIELD)
public class Wheel {
	private String position;
	private String material;

	public Wheel() {
		super();
	}

	public Wheel(String position, String material) {
		super();
		this.position = position;
		this.material = material;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Override
	public String toString() {
		return "Wheel [position=" + position + ", material=" + material + "]";
	}

}
