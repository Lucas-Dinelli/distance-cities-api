package com.geography.distance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geography.distance.model.State;

public interface StateRepository extends JpaRepository<State, Long> {
	
}
