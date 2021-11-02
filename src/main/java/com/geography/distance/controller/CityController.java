package com.geography.distance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geography.distance.dto.CityOutputDTO;
import com.geography.distance.dto.DistanceOutputDTO;
import com.geography.distance.model.City;
import com.geography.distance.service.CityService;

@RestController
@RequestMapping("cities")
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@GetMapping
	public ResponseEntity<Page<City>> findAll(Pageable pageable) {
		
		return ResponseEntity.ok().body(cityService.findAll(pageable));
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<List<CityOutputDTO>> findByName(@PathVariable String name) {
		List<CityOutputDTO> citiesOutputDTO = cityService.findByName(name);
		return ResponseEntity.ok().body(citiesOutputDTO);
	}
	
	@GetMapping("/distance/id")
	public ResponseEntity<DistanceOutputDTO> findDistanceById(
										@RequestParam(name = "from") final Long idFirstCity, 
										@RequestParam(name = "to") final Long idSecondCity, 
										@RequestParam(name = "unit", defaultValue = "meter") String unitMeasurement) {
		
		return ResponseEntity.ok().body(cityService.findDistanceById(idFirstCity, idSecondCity, unitMeasurement));
	}
	
	@GetMapping("/distance/name")
	public ResponseEntity<DistanceOutputDTO> findDistanceByName(
										@RequestParam(name = "from") final String nameFirstCity,
										@RequestParam(name = "ufFrom") final String ufFirstCity,
										@RequestParam(name = "to") final String nameSecondCity,
										@RequestParam(name = "ufTo") final String ufSecondCity,
										@RequestParam(name = "unit", defaultValue = "meter") String unitMeasurement) {
		
		return ResponseEntity.ok().body(cityService.findDistanceByName(nameFirstCity, ufFirstCity, 
																	   nameSecondCity, ufSecondCity, 
																	   unitMeasurement));
	}

}
