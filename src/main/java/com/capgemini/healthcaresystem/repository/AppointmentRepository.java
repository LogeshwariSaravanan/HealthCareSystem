package com.capgemini.healthcaresystem.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.healthcaresystem.entity.Appointment;
import com.capgemini.healthcaresystem.entity.DiagnosticCenter;
import com.capgemini.healthcaresystem.entity.User;

import jakarta.transaction.Transactional;

@Repository
public interface AppointmentRepository  extends JpaRepository<Appointment, Integer>{

	@Transactional
	@Modifying
	@Query("UPDATE Appointment a SET a.approved=true WHERE a.appointmentId=?1")
	void approveappointment(BigInteger appointment_id);
	
	List<Appointment> findByUser(User user);
	
	List<Appointment> findByDiagnosticCenter(DiagnosticCenter diagnosticCenter);
	
}
