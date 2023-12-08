package com.capgemini.healthcaresystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.healthcaresystem.entity.CenterTestMapping;

public interface CenterTestMappingRepository extends JpaRepository<CenterTestMapping, Integer>{

	List<CenterTestMapping> findBycenterId(String centerId);
	
//	@Query("select c from CenterTestMapping c where centerId=?1")
//	List<String> findBycenterId(String centerId);
	
}
