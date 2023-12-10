package com.capgemini.healthcaresystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.healthcaresystem.entity.Appointment;
import com.capgemini.healthcaresystem.entity.DiagnosticCenter;


@Repository
public interface DiagnosticCenterRepository extends JpaRepository<DiagnosticCenter, String> {

	List<Appointment> findByCenterId(String centerId);


}
