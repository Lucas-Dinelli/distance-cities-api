package com.geography.distance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geography.distance.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {
	
}
