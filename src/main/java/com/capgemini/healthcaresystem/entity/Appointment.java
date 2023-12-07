package com.capgemini.healthcaresystem.entity;

import java.math.BigInteger;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="appointment_details")
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger appointmentId;
	private LocalDateTime dateAndTime;
	private boolean approved;
	
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="TestId")
	private Test test;

	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
	

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="center_id")
	private DiagnosticCenter diagnosticCenter;
	
	public Appointment() {
		super();
	}

	public Appointment(BigInteger appointmentId, LocalDateTime dateAndTime, boolean approved, Test test, User user,
			DiagnosticCenter diagnosticCenter) {
		super();
		this.appointmentId = appointmentId;
		this.dateAndTime = dateAndTime;
		this.approved = approved;
		this.test = test;
		this.user = user;
		this.diagnosticCenter = diagnosticCenter;
	}

	public BigInteger getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(BigInteger appointmentId) {
		this.appointmentId = appointmentId;
	}

	public LocalDateTime getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(LocalDateTime dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DiagnosticCenter getDiagnosticCenter() {
		return diagnosticCenter;
	}

	public void setDiagnosticCenter(DiagnosticCenter diagnosticCenter) {
		this.diagnosticCenter = diagnosticCenter;
	}

	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", dateAndTime=" + dateAndTime + ", approved=" + approved
				+ ", test=" + test + ", user=" + user + ", diagnosticCenter=" + diagnosticCenter + "]";
	}

	
	
}
