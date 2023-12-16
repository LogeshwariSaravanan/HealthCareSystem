package com.capgemini.healthcaresystem.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="center_test_mapping")
public class CenterTestMapping {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int tcId;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="center_id")
	private DiagnosticCenter diagnosticCenter;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="test_id")
	private Tests test;
	
	
	public CenterTestMapping() {
		super();
	}


	public CenterTestMapping(int tcId, DiagnosticCenter diagnosticCenter, Tests test) {
		super();
		this.tcId = tcId;
		this.diagnosticCenter = diagnosticCenter;
		this.test = test;
	}


	public int getTcId() {
		return tcId;
	}


	public void setTcId(int tcId) {
		this.tcId = tcId;
	}


	public DiagnosticCenter getDiagnosticCenter() {
		return diagnosticCenter;
	}


	public void setDiagnosticCenter(DiagnosticCenter diagnosticCenter) {
		this.diagnosticCenter = diagnosticCenter;
	}


	public Tests getTest() {
		return test;
	}


	public void setTest(Tests test) {
		this.test = test;
	}


	@Override
	public String toString() {
		return "CenterTestMapping [tcId=" + tcId + ", diagnosticCenter=" + diagnosticCenter + ", test=" + test + "]";
	}

	
	
}
