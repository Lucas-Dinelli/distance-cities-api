package com.geography.distance.controller;

import static com.geography.distance.utils.CityUtils.createFakeCityModel;
import static com.geography.distance.utils.DistanceOutputDTOUtils.createFakeDistanceOutputDTO;
import static com.geography.distance.utils.CityOutputDTOUtils.createFakeCityOutputDTO;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.geography.distance.dto.CityOutputDTO;
import com.geography.distance.dto.DistanceOutputDTO;
import com.geography.distance.model.City;
import com.geography.distance.service.CityService;

@ExtendWith(SpringExtension.class)
public class CityControllerTest {
	
	@InjectMocks
	private CityController cityController;
	
	@Mock
	private CityService cityService;
	
	@BeforeEach
	public void setUp() {
		PageImpl<City> pageCities = new PageImpl<City>(Arrays.asList(createFakeCityModel()));
		
		when(cityService.findAll(any())).thenReturn(pageCities);
		when(cityService.findDistanceById(any(), any(), any())).thenReturn(createFakeDistanceOutputDTO());
		when(cityService.findDistanceByName(any(), any(), any(), any(), any())).thenReturn(createFakeDistanceOutputDTO());
		when(cityService.findByName(any())).thenReturn(Arrays.asList(createFakeCityOutputDTO()));
	}
	
	@Test
	@DisplayName("The method findAll returns list of cities when successful")
	public void findAll_ShouldReturnListOfCitiesInsidePage_WhenSuccessful() {
		City expectedCity = createFakeCityModel();
		
		Page<City> citiesPage = cityController.findAll(null).getBody();
		
		assertNotNull(citiesPage);
		assertFalse(citiesPage.toList().isEmpty());
		assertEquals(expectedCity, citiesPage.toList().get(0));
	}
	
	@Test
	@DisplayName("The method findByName returns list of CityOutputDTO when successful")
	public void findByName_ShouldReturnListOfCityOutputDTO_WhenSuccessful() {
		List<CityOutputDTO> expectedList = Arrays.asList(createFakeCityOutputDTO());
		
		List<CityOutputDTO> returnedList = cityController.findByName(null).getBody();
		
		assertEquals(expectedList.get(0), returnedList.get(0));
	}
	
	@Test
	@DisplayName("The method findDistanceById returns DistanceOutputDTO when successful")
	public void findDistanceById_ShouldReturnDistanceOutputDTO_WhenSuccessful() {
		DistanceOutputDTO distanceOutputDTO = createFakeDistanceOutputDTO();
		
		ResponseEntity<DistanceOutputDTO> returnedDistanceOutputDTO = cityController.findDistanceById(null, null, null);
		
		assertEquals(distanceOutputDTO.getFrom(), returnedDistanceOutputDTO.getBody().getFrom());
		assertEquals(distanceOutputDTO.getTo(), returnedDistanceOutputDTO.getBody().getTo());
		assertEquals(distanceOutputDTO.getUnit(), returnedDistanceOutputDTO.getBody().getUnit());
		assertEquals(distanceOutputDTO.getDistance(), returnedDistanceOutputDTO.getBody().getDistance());
	}
	
	@Test
	@DisplayName("The method findDistanceByName returns DistanceOutputDTO when successful")
	public void findDistanceByName_ShouldReturnDistanceOutputDTO_WhenSuccessful() {
		DistanceOutputDTO distanceOutputDTO = createFakeDistanceOutputDTO();
		
		ResponseEntity<DistanceOutputDTO> returnedDistanceOutputDTO = cityController.findDistanceByName(null, null, null, null, null);
		
		assertEquals(distanceOutputDTO.getFrom(), returnedDistanceOutputDTO.getBody().getFrom());
		assertEquals(distanceOutputDTO.getTo(), returnedDistanceOutputDTO.getBody().getTo());
		assertEquals(distanceOutputDTO.getUnit(), returnedDistanceOutputDTO.getBody().getUnit());
		assertEquals(distanceOutputDTO.getDistance(), returnedDistanceOutputDTO.getBody().getDistance());
	}
	
}
