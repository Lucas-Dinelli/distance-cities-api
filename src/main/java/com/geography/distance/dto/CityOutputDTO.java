package com.geography.distance.dto;

import org.springframework.data.geo.Point;

import com.geography.distance.model.City;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityOutputDTO {
	
	private Long id;
	
	private String name;
	
	private String uf;
	
	private Integer ibge;
	
	private Point location;
	
	public static CityOutputDTO transform(City city) {
		return CityOutputDTO.builder()
				.id(city.getId())
				.name(city.getName())
				.uf(city.getState().getUf())
				.ibge(city.getIbge())
				.location(city.getLocation())
				.build();
	}

}
