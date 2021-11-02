package com.geography.distance.service;

import static com.geography.distance.utils.CityUtils.createFakeCityModel;
import static com.geography.distance.utils.CityUtils.createSecondFakeCityModel;
import static com.geography.distance.utils.CityOutputDTOUtils.createFakeCityOutputDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.geography.distance.dto.CityOutputDTO;
import com.geography.distance.dto.DistanceOutputDTO;
import com.geography.distance.model.City;
import com.geography.distance.repository.CityRepository;
import com.geography.distance.service.exceptions.IllegalArgumentCustomException;
import com.geography.distance.service.exceptions.ObjectNotFoundException;

@ExtendWith(MockitoExtension.class)
public class CityServiceTest {
	
	@InjectMocks
	private CityService cityService;
	
	@Mock
	private CityRepository cityRepository;
	
	private City expectedFirstCity = createFakeCityModel();
	private City expectedSecondCity = createSecondFakeCityModel();
	
	@Test
	@DisplayName("The method findAll returns page of cities when successful")
	public void findAll_ShouldReturnPage_WhenSuccessful() {
		PageImpl<City> expectedPageCities = new PageImpl<>(Arrays.asList(expectedFirstCity, expectedSecondCity));
		
		when(cityRepository.findAll(any(Pageable.class))).thenReturn(expectedPageCities);
		
		Page<City> pageCities = cityService.findAll(expectedPageCities.getPageable());
		
		assertEquals(expectedPageCities, pageCities);
	}
	
	@Test
	@DisplayName("The method findByName returns list of CityOutpuDTO when successful")
	public void findByName_ShouldReturnListOfCityOutputDTO_WhenSuccessful() {
		List<CityOutputDTO> expectedList = Arrays.asList(createFakeCityOutputDTO());
		
		when(cityRepository.findByName(any(String.class))).thenReturn(Arrays.asList(createFakeCityModel()));
		
		List<CityOutputDTO> returnedList = cityService.findByName("Rio de Janeiro");
		
		assertEquals(expectedList.get(0), returnedList.get(0));
	}
	
	@Test
	@DisplayName("The method findDistanceById returns DistanceOutputDTO when successful")
	public void findDistanceById_ShouldReturnDistanceOutputDTO_WhenSuccessful() {
		when(cityRepository.findById(1L)).thenReturn(Optional.of(createFakeCityModel()));
		when(cityRepository.findById(2L)).thenReturn(Optional.of(createSecondFakeCityModel()));
		when(cityRepository.distanceByCubeInMeter(any(Double.class), any(Double.class), any(Double.class), any(Double.class))).thenReturn(358527.51);
		
		DistanceOutputDTO distanceOutputReturned = cityService.findDistanceById(1L, 2L, "kilometer");
		
		assertEquals(expectedFirstCity.getName() + ", " + expectedFirstCity.getState().getUf(), distanceOutputReturned.getFrom());
		assertEquals(expectedSecondCity.getName() + ", " + expectedSecondCity.getState().getUf(), distanceOutputReturned.getTo());
		assertEquals("kilometer", distanceOutputReturned.getUnit());
		assertEquals(358.52, distanceOutputReturned.getDistance());
	}
	
	@Test
	@DisplayName("The method findDistanceByName returns DistanceOutputDTO when successful")
	public void findDistanceByName_ShouldReturnDistanceOutputDTO_WhenSuccessful() {
		when(cityRepository.findByNameAndUf("Rio de Janeiro", "RJ")).thenReturn(Optional.of(expectedFirstCity));
		when(cityRepository.findByNameAndUf("São Paulo", "SP")).thenReturn(Optional.of(expectedSecondCity));
		when(cityRepository.distanceByCubeInMeter(any(Double.class), any(Double.class), any(Double.class), any(Double.class))).thenReturn(358527.51);
		
		DistanceOutputDTO distanceOutputReturned = cityService.findDistanceByName("Rio de Janeiro", "RJ", "São Paulo", "SP", "kilometer");
		
		assertEquals(expectedFirstCity.getName() + ", " + expectedFirstCity.getState().getUf(), distanceOutputReturned.getFrom());
		assertEquals(expectedSecondCity.getName() + ", " + expectedSecondCity.getState().getUf(), distanceOutputReturned.getTo());
		assertEquals("kilometer", distanceOutputReturned.getUnit());
		assertEquals(358.52, distanceOutputReturned.getDistance());
	}
	
	@Test
	@DisplayName("The method findById throws ObjectNotFoundException when id does not exist")
	public void findById_ShouldThrowObjectNotFoundException_WhenIdNotExist() {
		assertThrows(ObjectNotFoundException.class, () -> cityService.findDistanceById(1L, 2L, "kilometer"));
	}
	
	@Test
	@DisplayName("The method findByNameAndUf throws ObjectNotFoundException when city does not exist")
	public void findByNameAndUf_ShouldThrowObjectNotFoundException_WhenCityNotExist() {
		assertThrows(ObjectNotFoundException.class, () -> cityService.findDistanceByName("X", "RJ", "São Paulo", "SP", "kilometer"));
	}
	
	@Test
	@DisplayName("The method convertDistance throws IllegalArgumentCustomException when unit does not exist")
	public void convertDistance_ShouldThrowIllegalArgumentCustomException_WhenUnitNotExist() {
		when(cityRepository.findByNameAndUf("Rio de Janeiro", "RJ")).thenReturn(Optional.of(expectedFirstCity));
		when(cityRepository.findByNameAndUf("São Paulo", "SP")).thenReturn(Optional.of(expectedSecondCity));
		when(cityRepository.distanceByCubeInMeter(any(Double.class), any(Double.class), any(Double.class), any(Double.class))).thenReturn(358527.51);
		
		assertThrows(IllegalArgumentCustomException.class, () -> cityService.findDistanceByName("Rio de Janeiro", "RJ", "São Paulo", "SP", "foot"));
	}
	
}
