package com.geography.distance.utils;

import org.springframework.data.geo.Point;

import com.geography.distance.model.City;
import com.geography.distance.model.State;

public class CityUtils {
	
	public static City createFakeCityModel() {
		return City.builder()
				.id(1L)
				.name("Rio de Janeiro")
				//.uf(2L)
				.ibge(3)
				.location(new Point(-22.912901, -43.200298))
				.state(State.builder().uf("RJ").build())
				.build();
	}
	
	public static City createSecondFakeCityModel() {
		return City.builder()
				.id(2L)
				.name("São Paulo")
				//.uf(4L)
				.ibge(5)
				.location(new Point(-23.532900, -46.639500))
				.state(State.builder().uf("MG").build())
				.build();
	}

}
