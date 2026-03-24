package com.madan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madan.model.SensorReading;

public interface SensorRepository extends JpaRepository<SensorReading,Long>{

	List<SensorReading> findTop5ByOrderByTimestampDesc();
}
