package com.capgemini.healthcaresystem.dto;



public class TestDto {
	private String testId;	
	private String testName;
	
	public TestDto() {
		super();
	}

	public TestDto(String testId, String testName) {
		super();
		this.testId = testId;
		this.testName = testName;
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	@Override
	public String toString() {
		return "TestDto [testId=" + testId + ", testName=" + testName + "]";
	}
	
	
}
