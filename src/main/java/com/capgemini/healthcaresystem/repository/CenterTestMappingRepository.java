package com.capgemini.healthcaresystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.healthcaresystem.entity.CenterTestMapping;
import com.capgemini.healthcaresystem.entity.DiagnosticCenter;
import com.capgemini.healthcaresystem.entity.Tests;

import jakarta.transaction.Transactional;

public interface CenterTestMappingRepository extends JpaRepository<CenterTestMapping, Integer>{

	List<CenterTestMapping> findBydiagnosticCenter(DiagnosticCenter diagnosticCenter);
		
	@Transactional
	@Query("SELECT c.test FROM CenterTestMapping c WHERE c.diagnosticCenter=?1")
	List<Tests> findTestByCenter(DiagnosticCenter diagnosticCenter);
	
	
	@Transactional
	@Modifying
	@Query("DELETE FROM CenterTestMapping c WHERE c.diagnosticCenter=?1")
	void deleteCenterTestMappingByCenter(DiagnosticCenter diagnosticCenter);

	@Transactional
	@Modifying
	@Query("DELETE FROM CenterTestMapping c WHERE c.diagnosticCenter=?1 AND c.test=?2")
	void deleteTestFromCenter(DiagnosticCenter diagnosticCenter,Tests test);

	
	@Transactional
	@Modifying
	@Query("DELETE FROM CenterTestMapping c WHERE c.test=?1")
	void deleteCenterByTest(Tests test);
	
	
}
