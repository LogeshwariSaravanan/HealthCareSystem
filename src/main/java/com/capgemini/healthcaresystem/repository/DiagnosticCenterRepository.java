package com.capgemini.healthcaresystem.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.healthcaresystem.entity.DiagnosticCenter;

import jakarta.transaction.Transactional;

@Repository
public interface DiagnosticCenterRepository extends JpaRepository<DiagnosticCenter, String> {

	@Transactional
	@Query("SELECT d.contactNo FROM DiagnosticCenter d")
	List<BigInteger> findAllCenterByContactNo();
	
	


}
