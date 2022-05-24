package com.external.api.cardio.modals;
import java.time.LocalDateTime;

import javax.persistence.Entity;

@Entity
public class Device extends AbstractEntity implements Cloneable {

    private String serialNumber;
    private String deviceName;
    private String associatedPatientId;
	private LocalDateTime time ;

    public Device(String serialNumber, String deviceName, String associatedPatientId, LocalDateTime time) {
		super();
		this.serialNumber = serialNumber;
		this.deviceName = deviceName;
		this.associatedPatientId = associatedPatientId;
		this.time = time;
	}

	public Device() {}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getAssociatedPatientId() {
		return associatedPatientId;
	}

	public void setAssociatedPatientId(String associatedPatientId) {
		this.associatedPatientId = associatedPatientId;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	@Override
    public String toString() {
      return String.format(
          "Device[serialNumber='%s', deviceName='%s']",
          serialNumber, deviceName);
    }

    @Override
    public Device clone() { 
        try {
            return (Device) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(
                    "The Device object could not be cloned.", e);
        }
    }
        
}
