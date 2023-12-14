package com.capgemini.healthcaresystem.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="ListOfTest")
public class Tests {
	@Id
	@Column(name="test_id")
	private String testid;
	
	@Column(name="Test_Name",unique = true)
	private String testName;
	
	public Tests() {
		super();
	}
	
	public Tests(String testid, String testName) {
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
	@PrePersist
	public void generateUserId() {
		if (testid == null || testid.isEmpty()) {
			String prefix = (String) testName.subSequence(0, 1);
			String uniquePart = UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 4);
			testid = prefix + uniquePart;
			}
		}
	}
