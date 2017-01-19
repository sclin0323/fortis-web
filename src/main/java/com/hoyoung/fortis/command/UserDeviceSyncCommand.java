package com.hoyoung.fortis.command;

import java.sql.Time;
import java.util.Date;

public class UserDeviceSyncCommand {
	private String userDevice;
	private String userDeviceGroup;
	private String macAddress;
	private Long timestampe;
	
	
	
	public UserDeviceSyncCommand() {
		super();
	}

	public UserDeviceSyncCommand(String userDevice, String userDeviceGroup, String macAddress, Long timestampe) {
		super();
		this.userDevice = userDevice;
		this.userDeviceGroup = userDeviceGroup;
		this.macAddress = macAddress;
		this.timestampe = timestampe;
	}

	public String getUserDevice() {
		return userDevice;
	}
	
	public void setUserDevice(String userDevice) {
		this.userDevice = userDevice;
	}
	
	public String getUserDeviceGroup() {
		return userDeviceGroup;
	}
	
	public void setUserDeviceGroup(String userDeviceGroup) {
		this.userDeviceGroup = userDeviceGroup;
	}
	
	public String getMacAddress() {
		return macAddress;
	}
	
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	
	public Long getTimestampe() {
		return timestampe;
	}
	
	public void setTimestampe(Long timestampe) {
		this.timestampe = timestampe;
	}
	
	
	
}
