package com.capgemini.healthcaresystem.entity;

import java.math.BigInteger;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="ListOfUser")
public class User {
	@Id
	private String userId;
	private String userName;
	private String userPassword;
	@Column(unique = true)
	private BigInteger contactNo;
	private String userRole="Customer";
	private String userEmail;
	private int age;
	private String gender;
	public User() {
		super();
	}
	public User(String userId, String userName, String userPassword, BigInteger contactNo, String userRole,
			String userEmail, int age, String gender) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.contactNo = contactNo;
		this.userRole = userRole;
		this.userEmail = userEmail;
		this.age = age;
		this.gender = gender;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public BigInteger getContactNo() {
		return contactNo;
	}
	public void setContactNo(BigInteger contactNo) {
		this.contactNo = contactNo;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", contactNo="
				+ contactNo + ", userRole=" + userRole + ", userEmail=" + userEmail + ", age=" + age + ", gender="
				+ gender + "]";
	}
	
	@PrePersist
    public void generateUserId() {
        if (userId == null || userId.isEmpty()) {
            String prefix = (String) userName.subSequence(0, 3);
            String uniquePart = UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 4);
            userId = prefix + uniquePart;
            }
        }
	}
