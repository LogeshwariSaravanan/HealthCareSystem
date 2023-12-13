package com.capgemini.healthcaresystem.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="ListOfTest")
public class Test {
	@Id
	@Column(name="test_id")
	private String testid;
	
	@Column(name="Test_Name",unique = true)
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
	


@PrePersist
public void generateUserId() {
    if (testid == null || testid.isEmpty()) {
        // Default prefix (first two letters)
        String prefix = (String) testName.subSequence(0, 1);

        // Generate a unique part (UUID for uniqueness)
       // String uniquePart = UUID.randomUUID().toString().substring(1, 3);
        String uniquePart = UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 4);

        // Set the generated ID
        testid = prefix + uniquePart;
    }
}
	

}
