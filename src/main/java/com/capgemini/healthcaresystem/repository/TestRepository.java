package com.capgemini.healthcaresystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.healthcaresystem.entity.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, String> {

}
