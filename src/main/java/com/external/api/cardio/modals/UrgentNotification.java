package com.external.api.cardio.modals;

import java.time.LocalDateTime;

import javax.persistence.Entity;

@Entity
public class UrgentNotification extends AbstractEntity implements Cloneable {
	
	private String serialNumber;
	private String associatedPatientId;
	private String patientName;
	private LocalDateTime time;
	private String msg;
	
	public UrgentNotification() {
	}
	
	public String getSerialNumber() {
		return serialNumber;
	}
	
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public String getAssociatedPatientId() {
		return associatedPatientId;
	}
	
	public void setAssociatedPatientId(String associatedPatientId) {
		this.associatedPatientId = associatedPatientId;
	}
	
	public String getPatientName() {
		return patientName;
	}
	
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	public LocalDateTime getTime() {
		return time;
	}
	
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}