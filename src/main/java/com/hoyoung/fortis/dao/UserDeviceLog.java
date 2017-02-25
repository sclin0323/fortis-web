package com.hoyoung.fortis.dao;

import java.sql.Time;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * UserDeviceLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "USER_DEVICE_LOG", catalog = "fortis")

public class UserDeviceLog implements java.io.Serializable {

	// Fields

	private String logId;
	private String method;
	private String deviceName;
	private String applicantId;
	private String applicantName;
	private Date applicantDate;
	private Time applicantTime;
	private String macAddress;
	private Date logDate;
	private Time logTime;
	private String deviceGroup;
	private String logUid;
	private String logName;

	// Constructors

	/** default constructor */
	public UserDeviceLog() {
	}

	/** minimal constructor */
	public UserDeviceLog(String logId, String method, Date logDate, Time logTime, String logUid, String logName) {
		this.logId = logId;
		this.method = method;
		this.logDate = logDate;
		this.logTime = logTime;
		this.logUid = logUid;
		this.logName = logName;
	}

	/** full constructor */
	public UserDeviceLog(String logId, String method, String deviceName, String applicantId, String applicantName,
			Date applicantDate, Time applicantTime, String macAddress, Date logDate, Time logTime, String deviceGroup,
			String logUid, String logName) {
		this.logId = logId;
		this.method = method;
		this.deviceName = deviceName;
		this.applicantId = applicantId;
		this.applicantName = applicantName;
		this.applicantDate = applicantDate;
		this.applicantTime = applicantTime;
		this.macAddress = macAddress;
		this.logDate = logDate;
		this.logTime = logTime;
		this.deviceGroup = deviceGroup;
		this.logUid = logUid;
		this.logName = logName;
	}

	// Property accessors
	@Id

	@Column(name = "LOG_ID", unique = true, nullable = false, length = 20)

	public String getLogId() {
		return this.logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	@Column(name = "METHOD", nullable = false, length = 20)

	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	@Column(name = "DEVICE_NAME", length = 40)

	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	@Column(name = "APPLICANT_ID", length = 20)

	public String getApplicantId() {
		return this.applicantId;
	}

	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}

	@Column(name = "APPLICANT_NAME", length = 40)

	public String getApplicantName() {
		return this.applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "APPLICANT_DATE", length = 10)

	public Date getApplicantDate() {
		return this.applicantDate;
	}

	public void setApplicantDate(Date applicantDate) {
		this.applicantDate = applicantDate;
	}

	@Column(name = "APPLICANT_TIME", length = 8)

	public Time getApplicantTime() {
		return this.applicantTime;
	}

	public void setApplicantTime(Time applicantTime) {
		this.applicantTime = applicantTime;
	}

	@Column(name = "MAC_ADDRESS", length = 20)

	public String getMacAddress() {
		return this.macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LOG_DATE", nullable = false, length = 10)

	public Date getLogDate() {
		return this.logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	@Column(name = "LOG_TIME", nullable = false, length = 8)

	public Time getLogTime() {
		return this.logTime;
	}

	public void setLogTime(Time logTime) {
		this.logTime = logTime;
	}

	@Column(name = "DEVICE_GROUP", length = 20)

	public String getDeviceGroup() {
		return this.deviceGroup;
	}

	public void setDeviceGroup(String deviceGroup) {
		this.deviceGroup = deviceGroup;
	}

	@Column(name = "LOG_UID", nullable = false, length = 18)

	public String getLogUid() {
		return this.logUid;
	}

	public void setLogUid(String logUid) {
		this.logUid = logUid;
	}

	@Column(name = "LOG_NAME", nullable = false, length = 18)

	public String getLogName() {
		return this.logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

}