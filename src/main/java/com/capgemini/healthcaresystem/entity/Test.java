package com.capgemini.healthcaresystem.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="ListOfTest")
public class Test {
	@Id
	@Column(name="test_id")
	private String testid;
	
	@Column(name="Test_Name")
	private String testName;
	
	
//	@JsonProperty(access = Access.WRITE_ONLY)
//	@Transient
//	@ManyToOne(cascade =CascadeType.ALL, fetch = FetchType.LAZY )
//	@JoinColumn(name="center_id", referencedColumnName = "center_id")
//	private DiagnosticCenter diagnosticCenter;


	public Test() {
		super();
	}


public Test(String testid, String testName) {
	super();
	this.testid = testid;
	this.testName = testName;
}


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


@Override
public String toString() {
	return "Test [testid=" + testid + ", testName=" + testName + "]";
}
	
	

}
