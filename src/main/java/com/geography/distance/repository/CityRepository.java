package com.geography.distance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.geography.distance.model.City;

public interface CityRepository extends JpaRepository<City, Long> {
	
	@Query(value = "SELECT earth_distance(ll_to_earth(?1,?2), ll_to_earth(?3,?4)) as distance", nativeQuery = true)
	Double distanceByCubeInMeter(final Double firstLatitude, final Double firstLongitude, 
						  final Double secondLatitude, final Double secondLongitude);
	
	@Query("SELECT c FROM City c WHERE c.name = :name")
	List<City> findByName(@Param(value = "name") String name);
	
	@Query("SELECT c FROM City c INNER JOIN c.state s WHERE c.name = :name AND c.state.uf = :uf")
	Optional<City> findByNameAndUf(@Param(value = "name") String name, @Param(value = "uf") String uf);
	
}
