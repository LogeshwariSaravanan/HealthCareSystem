package com.capgemini.healthcaresystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.healthcaresystem.entity.Test;

import jakarta.transaction.Transactional;

@Repository
public interface TestRepository extends JpaRepository<Test, String> {
	
	@Transactional
	@Modifying
	@Query("SELECT t.testid FROM Test t")
	List<String> listOfTestId();

}
