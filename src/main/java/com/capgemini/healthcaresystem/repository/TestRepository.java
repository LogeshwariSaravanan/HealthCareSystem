package com.capgemini.healthcaresystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.healthcaresystem.entity.Tests;

import jakarta.transaction.Transactional;

@Repository
public interface TestRepository extends JpaRepository<Tests, String> {
	
	@Transactional
	@Query("SELECT t.testName FROM Tests t")
	List<String> findTest();

	@Transactional
	@Query("SELECT t.testid FROM Tests t")
	List<String> findAllTestId();


}
