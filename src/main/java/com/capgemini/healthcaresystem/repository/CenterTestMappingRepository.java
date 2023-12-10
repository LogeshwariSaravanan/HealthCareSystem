package com.capgemini.healthcaresystem.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.healthcaresystem.entity.CenterTestMapping;

import jakarta.transaction.Transactional;

public interface CenterTestMappingRepository extends JpaRepository<CenterTestMapping, Integer>{

	List<CenterTestMapping> findBycenterId(String centerId);
		
	@Transactional
	@Modifying
	@Query("SELECT c.testid FROM CenterTestMapping c WHERE c.centerId=?1")
	List<String> findTestIdByCenterId(String centerId);
	
	
	@Transactional
	@Modifying
	@Query("DELETE FROM CenterTestMapping c WHERE c.centerId=?1")
	void deleteCenterTestMappingById(String centerId);

	@Transactional
	@Modifying
	@Query("DELETE FROM CenterTestMapping c WHERE c.centerId=?1 AND c.testid=?2")
	void deletebyCenterIdAndTestId(String diagnosticCenterId, String testId);
	
//	@Transactional
//	@Modifying
//	@Query("SELECT c.tcId FROM CenterTestMapping c WHERE c.centerId=?1 and c.testid=?2")
//	int findIdByCenterIdAndTestId(String centerId,String testId);
	
}
