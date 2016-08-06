package com.hoyoung.fortis.command;

import java.sql.Time;
import java.util.Date;

public class UserDeviceCommand {
	private String deviceName;
	private String deviceGroup;
	private String applicantId;
	private String macAddress;
	private String crtUid;
	private String crtName;
	private Date crtDate;
	private Time crtTime;
	private String updUid;
	private String updName;
	private Date updDate;
	private Time updTime;
	private String applicantName;
	private Date applicantDate;
	private Time applicantTime;
	
	
	public String getDeviceGroup() {
		return deviceGroup;
	}
	

	public void setDeviceGroup(String deviceGroup) {
		this.deviceGroup = deviceGroup;
	}
	

	public String getDeviceName() {
		return deviceName;
	}
	
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	public String getApplicantId() {
		return applicantId;
	}
	
	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}
	
	public String getMacAddress() {
		return macAddress;
	}
	
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	
	public String getCrtUid() {
		return crtUid;
	}
	
	public void setCrtUid(String crtUid) {
		this.crtUid = crtUid;
	}
	
	public String getCrtName() {
		return crtName;
	}
	
	public void setCrtName(String crtName) {
		this.crtName = crtName;
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
	
	public String getUpdUid() {
		return updUid;
	}
	
	public void setUpdUid(String updUid) {
		this.updUid = updUid;
	}
	
	public String getUpdName() {
		return updName;
	}
	
	public void setUpdName(String updName) {
		this.updName = updName;
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
	
	public String getApplicantName() {
		return applicantName;
	}
	
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	
	public Date getApplicantDate() {
		return applicantDate;
	}
	
	public void setApplicantDate(Date applicantDate) {
		this.applicantDate = applicantDate;
	}
	
	public Time getApplicantTime() {
		return applicantTime;
	}
	
	public void setApplicantTime(Time applicantTime) {
		this.applicantTime = applicantTime;
	}


	@Override
	public String toString() {
		return "UserDeviceCommand [deviceName=" + deviceName + ", deviceGroup=" + deviceGroup + ", applicantId="
				+ applicantId + ", macAddress=" + macAddress + ", crtUid=" + crtUid + ", crtName=" + crtName
				+ ", crtDate=" + crtDate + ", crtTime=" + crtTime + ", updUid=" + updUid + ", updName=" + updName
				+ ", updDate=" + updDate + ", updTime=" + updTime + ", applicantName=" + applicantName
				+ ", applicantDate=" + applicantDate + ", applicantTime=" + applicantTime + "]";
	}
	
	
	
	
	
}
