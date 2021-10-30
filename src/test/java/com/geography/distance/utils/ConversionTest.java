package com.geography.distance.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConversionTest {
	
	@Test
	@DisplayName("The method getAllUnits returns a string with the units of measure when successful")
	public void getAllUnits_ReturnsStringWithTheUnits_WhenSuccessful() {
		String allUnits = Conversion.getAllUnits();
		assertEquals("[meter, kilometer, mile]", allUnits);
	}

}
