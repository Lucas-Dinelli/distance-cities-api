package com.geography.distance.utils;

import static com.geography.distance.utils.CityUtils.createFakeCityModel;
import static com.geography.distance.utils.CityUtils.createSecondFakeCityModel;

import com.geography.distance.dto.DistanceOutputDTO;

public class DistanceOutputDTOUtils {
	
	private static final String FROM = createFakeCityModel().getName();
	private static final String TO = createSecondFakeCityModel().getName();
	private static final String UNIT = "kilometer";
	private static final Double DISTANCE = 358.52;
	
	public static DistanceOutputDTO createFakeDistanceOutputDTO() {
		return DistanceOutputDTO.builder()
				.from(FROM)
				.to(TO)
				.unit(UNIT)
				.distance(DISTANCE)
				.build();
	}

}
