package com.geography.distance.utils;

import java.util.EnumSet;

public enum Conversion {
	
	METER(1.0),						// Default
	KILOMETER(0.001),				// Value to convert from meter to kilometer
	MILE(0.0006213712);				// Value to convert from meter to mile
	
	private Double numberToConversion;
	
	Conversion(Double numberToConversion) {
		this.numberToConversion = numberToConversion;
	}
	
	public Double getNumberToConversion() {
		return this.numberToConversion;
	}
	
	public static String getAllUnits() {
		String allValues = "[";
		for (Conversion unit : EnumSet.allOf(Conversion.class)) {
			allValues = allValues + unit.toString().toLowerCase() + ", ";
		}
		return allValues.substring(0, allValues.lastIndexOf(",")) + "]";
	}

}
