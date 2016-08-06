package com.hoyoung.fortis.command;

import java.sql.Time;
import java.util.Date;

public class FortinetUserDeviceCommand {
	private String userDevice;
	private String userDeviceGroup;
	private String macAddress;
	private Date crtDate;
	private Time crtTime;
	private Date updDate;
	private Time updTime;
	private Long timestampe;
	
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
	
	public Date getCrtDate() {
		return crtDate;
	}
	
	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}
	
	public Time getCrtTime() {
		return crtTime;
	}
	
	public void setCrtTime(Time crtTime) {
		this.crtTime = crtTime;
	}
	
	public Date getUpdDate() {
		return updDate;
	}
	
	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}
	
	public Time getUpdTime() {
		return updTime;
	}
	
	public void setUpdTime(Time updTime) {
		this.updTime = updTime;
	}
	
	public Long getTimestampe() {
		return timestampe;
	}
	
	public void setTimestampe(Long timestampe) {
		this.timestampe = timestampe;
	}
	
	
	
}
