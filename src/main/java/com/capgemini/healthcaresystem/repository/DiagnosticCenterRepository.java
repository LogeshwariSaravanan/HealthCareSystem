package com.capgemini.healthcaresystem.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.healthcaresystem.entity.DiagnosticCenter;


@Repository
public interface DiagnosticCenterRepository extends JpaRepository<DiagnosticCenter, String> {



}
