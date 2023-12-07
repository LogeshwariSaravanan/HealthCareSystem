package com.capgemini.healthcaresystem.dto;

import com.capgemini.healthcaresystem.entity.DiagnosticCenter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

public class TestDto {

private String testid;
	
	
	private String testName;
	
	
//	private DiagnosticCenterDto diagnosticCenterDto;


	public String getTestid() {
		return testid;
	}


	public void setTestid(String testid) {
		this.testid = testid;
	}


	public String getTestName() {
		return testName;
	}


	public void setTestName(String testName) {
		this.testName = testName;
	}


//	public DiagnosticCenterDto getDiagnosticCenterDto() {
//		return diagnosticCenterDto;
//	}
//
//
//	public void setDiagnosticCenterDto(DiagnosticCenterDto diagnosticCenterDto) {
//		this.diagnosticCenterDto = diagnosticCenterDto;
//	}
	
	
	
}
