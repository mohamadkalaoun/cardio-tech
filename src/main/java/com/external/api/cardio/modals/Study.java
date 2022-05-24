package com.external.api.cardio.modals;

import java.time.LocalDateTime;

import javax.persistence.Entity;

@Entity
public class Study extends AbstractEntity implements Cloneable {
	
	private String deviceSerialNumber;
	private String deviceName;
	private String patientId;
	private String patientName;
	private String studyStatus;
	private LocalDateTime startTime;
	private String duration;
	private String heartBeatsValues;
	
	public Study() {
	}
	
	public Study(String deviceSerialNumber, String deviceName, String patientId, String patientName, String studyStatus, LocalDateTime startTime, String duration) {
		super();
		this.deviceSerialNumber = deviceSerialNumber;
		this.deviceName = deviceName;
		this.patientId = patientId;
		this.patientName = patientName;
		this.studyStatus = studyStatus;
		this.startTime = startTime;
		this.duration = duration;
	}
	
	public String getDeviceSerialNumber() {
		return deviceSerialNumber;
	}
	
	public void setDeviceSerialNumber(String deviceSerialNumber) {
		this.deviceSerialNumber = deviceSerialNumber;
	}
	
	public String getDeviceName() {
		return deviceName;
	}
	
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	public String getPatientId() {
		return patientId;
	}
	
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	public String getPatientName() {
		return patientName;
	}
	
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	public String getStudyStatus() {
		return studyStatus;
	}
	
	public void setStudyStatus(String studyStatus) {
		this.studyStatus = studyStatus;
	}
	
	public LocalDateTime getStartTime() {
		return startTime;
	}
	
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	
	public String getDuration() {
		return duration;
	}
	
	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getHeartBeatsValues() {
		return heartBeatsValues;
	}

	public void setHeartBeatsValues(String heartBeatsValues) {
		this.heartBeatsValues = heartBeatsValues;
	}
	
}