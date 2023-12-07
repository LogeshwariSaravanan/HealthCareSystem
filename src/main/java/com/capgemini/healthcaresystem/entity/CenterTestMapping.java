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
	
//	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//	@JoinColumn(name="center_id")
//	private DiagnosticCenter diagnosticCenter;
//	
//	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//	@JoinColumn(name="test_id")
//	private Test test;
	
	private String centerId;
	
	private String testid;

	public CenterTestMapping() {
		super();
	}

	public CenterTestMapping(int tcId, String centerId, String testid) {
		super();
		this.tcId = tcId;
		this.centerId = centerId;
		this.testid = testid;
	}

	public int getTcId() {
		return tcId;
	}

	public void setTcId(int tcId) {
		this.tcId = tcId;
	}

	public String getCenterId() {
		return centerId;
	}

	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}

	public String getTestid() {
		return testid;
	}

	public void setTestid(String testid) {
		this.testid = testid;
	}

	@Override
	public String toString() {
		return "CenterTestMapping [tcId=" + tcId + ", centerId=" + centerId + ", testid=" + testid + "]";
	}
	
	

	

	
	

}
