package com.geography.distance.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
public class DistanceOutputDTO {
	
	private String from;
	
	private String to;
	
	private String unit;
	
	private Double distance;
	
	public DistanceOutputDTO(String from, String to, String unit, Double distance) {
		setFrom(from);
		setTo(to);
		setUnit(unit);
		setDistance(distance);
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setUnit(String unit) {
		this.unit = unit.toLowerCase();
	}

	public void setDistance(Double distance) {
		this.distance = BigDecimal
				.valueOf(distance)
				.setScale(2, RoundingMode.DOWN)
				.doubleValue();
	}

}
