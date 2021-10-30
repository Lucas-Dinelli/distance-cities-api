package com.geography.distance.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import com.geography.distance.dto.DistanceOutputDTO;
import com.geography.distance.model.City;
import com.geography.distance.repository.CityRepository;
import com.geography.distance.service.exceptions.IllegalArgumentCustomException;
import com.geography.distance.service.exceptions.ObjectNotFoundException;
import com.geography.distance.utils.Conversion;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CityService {
	
	private CityRepository cityRepository;
	
	public Page<City> findAll(Pageable pageable) {
		return cityRepository.findAll(pageable);
	}
	
	public DistanceOutputDTO findDistanceById(Long idFirstCity, Long idSecondCity, String unitMeasurement) {
		final City firstCity = this.findById(idFirstCity);
		final City secondCity = this.findById(idSecondCity);
		return this.findDistanceBetweenCities(firstCity, secondCity, unitMeasurement);
	}
	
	public DistanceOutputDTO findDistanceByName(String nameFirstCity, String ufFirstCity, String nameSecondCity, String ufSecondCity, String unitMeasurement) {
		final City firstCity = this.findByName(nameFirstCity, ufFirstCity.toUpperCase());
		final City secondCity = this.findByName(nameSecondCity, ufSecondCity.toUpperCase());
		return this.findDistanceBetweenCities(firstCity, secondCity, unitMeasurement);
	}
	
	private City findById(Long id) {
		Optional<City> objCity = cityRepository.findById(id);
		return objCity.orElseThrow(() -> new ObjectNotFoundException("The id '" + id + "' is not from any city!"));
	}
	
	private City findByName(String name, String uf) {
		Optional<City> objCity = cityRepository.findByName(name, uf);
		return objCity.orElseThrow(() -> new ObjectNotFoundException("'" + name + ", " + uf + "' does not correspond to any city!"));
	}
	
	private Double convertDistance(Double distance, String unitMeasurement) {
		try {
			distance = distance * Conversion.valueOf(unitMeasurement.toUpperCase()).getNumberToConversion();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentCustomException("Unit Error. Just " + Conversion.getAllUnits());
		}
		return distance;
	}
	
	private DistanceOutputDTO findDistanceBetweenCities(City firstCity, City secondCity, String unitMeasurement) {
		Point firstPoint = firstCity.getLocation();
		Point secondPoint = secondCity.getLocation();
		
		Double distance = cityRepository.distanceByCubeInMeter(firstPoint.getX(), firstPoint.getY(), secondPoint.getX(), secondPoint.getY());
		
		Double convertedDistance = this.convertDistance(distance, unitMeasurement);
		
		return new DistanceOutputDTO(firstCity.getName() + ", " + firstCity.getState().getUf(), 
									 secondCity.getName() + ", " + secondCity.getState().getUf(), 
									 unitMeasurement, 
									 convertedDistance);
	}
	
}
