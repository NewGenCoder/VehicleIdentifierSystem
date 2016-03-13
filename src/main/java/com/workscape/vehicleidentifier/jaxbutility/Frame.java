package com.workscape.vehicleidentifier.jaxbutility;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Vivek Singh
 *
 */
@XmlRootElement(name = "frame")
@XmlAccessorType(XmlAccessType.FIELD)
public class Frame {
	private String material;

	public Frame() {
	}

	public Frame(String material) {
		super();
		this.material = material;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Override
	public String toString() {
		return "Frame [material=" + material + "]";
	}

}
