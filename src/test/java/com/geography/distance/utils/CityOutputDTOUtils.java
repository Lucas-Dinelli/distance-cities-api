package com.geography.distance.utils;

import static com.geography.distance.utils.CityUtils.createFakeCityModel;

import org.springframework.data.geo.Point;

import com.geography.distance.dto.CityOutputDTO;

public class CityOutputDTOUtils {
	
	private static final Long ID = createFakeCityModel().getId();
	private static final String NAME = createFakeCityModel().getName();
	private static final String UF = createFakeCityModel().getState().getUf();
	private static final Integer IBGE = createFakeCityModel().getIbge();
	private static final Point LOCATION = createFakeCityModel().getLocation();
	
	public static CityOutputDTO createFakeCityOutputDTO() {
		return CityOutputDTO.builder()
				.id(ID)
				.name(NAME)
				.uf(UF)
				.ibge(IBGE)
				.location(LOCATION)
				.build();
	}

}
