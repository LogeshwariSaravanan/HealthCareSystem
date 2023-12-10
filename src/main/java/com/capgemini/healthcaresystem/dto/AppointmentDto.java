package com.capgemini.healthcaresystem.dto;

import java.math.BigInteger;
import java.time.LocalDateTime;



public class AppointmentDto {

	

	private BigInteger appointmentId;
	private LocalDateTime dateAndTime;
	private boolean approved;
	private TestDto testDto;
	private UserDto userDto;
	private DiagnosticCenterDto diagnosticCenterDto;
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
	public TestDto getTestDto() {
		return testDto;
	}
	public void setTestDto(TestDto testDto) {
		this.testDto = testDto;
	}
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	public DiagnosticCenterDto getDiagnosticCenterDto() {
		return diagnosticCenterDto;
	}
	public void setDiagnosticCenterDto(DiagnosticCenterDto diagnosticCenterDto) {
		this.diagnosticCenterDto = diagnosticCenterDto;
	}
	
	

}
