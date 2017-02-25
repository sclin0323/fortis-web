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
 * UserDevice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "USER_DEVICE", catalog = "fortis")

public class UserDevice implements java.io.Serializable {

	// Fields

	private String deviceName;
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
	private String deviceGroup;

	// Constructors

	/** default constructor */
	public UserDevice() {
	}

	/** minimal constructor */
	public UserDevice(String deviceName, String applicantId, String macAddress, String crtUid, String crtName,
			Date crtDate, Time crtTime, String applicantName, Date applicantDate, Time applicantTime,
			String deviceGroup) {
		this.deviceName = deviceName;
		this.applicantId = applicantId;
		this.macAddress = macAddress;
		this.crtUid = crtUid;
		this.crtName = crtName;
		this.crtDate = crtDate;
		this.crtTime = crtTime;
		this.applicantName = applicantName;
		this.applicantDate = applicantDate;
		this.applicantTime = applicantTime;
		this.deviceGroup = deviceGroup;
	}

	/** full constructor */
	public UserDevice(String deviceName, String applicantId, String macAddress, String crtUid, String crtName,
			Date crtDate, Time crtTime, String updUid, String updName, Date updDate, Time updTime, String applicantName,
			Date applicantDate, Time applicantTime, String deviceGroup) {
		this.deviceName = deviceName;
		this.applicantId = applicantId;
		this.macAddress = macAddress;
		this.crtUid = crtUid;
		this.crtName = crtName;
		this.crtDate = crtDate;
		this.crtTime = crtTime;
		this.updUid = updUid;
		this.updName = updName;
		this.updDate = updDate;
		this.updTime = updTime;
		this.applicantName = applicantName;
		this.applicantDate = applicantDate;
		this.applicantTime = applicantTime;
		this.deviceGroup = deviceGroup;
	}

	// Property accessors
	@Id

	@Column(name = "DEVICE_NAME", unique = true, nullable = false, length = 40)

	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	@Column(name = "APPLICANT_ID", nullable = false, length = 20)

	public String getApplicantId() {
		return this.applicantId;
	}

	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}

	@Column(name = "MAC_ADDRESS", nullable = false, length = 20)

	public String getMacAddress() {
		return this.macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	@Column(name = "CRT_UID", nullable = false, length = 20)

	public String getCrtUid() {
		return this.crtUid;
	}

	public void setCrtUid(String crtUid) {
		this.crtUid = crtUid;
	}

	@Column(name = "CRT_NAME", nullable = false, length = 40)

	public String getCrtName() {
		return this.crtName;
	}

	public void setCrtName(String crtName) {
		this.crtName = crtName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CRT_DATE", nullable = false, length = 10)

	public Date getCrtDate() {
		return this.crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	@Column(name = "CRT_TIME", nullable = false, length = 8)

	public Time getCrtTime() {
		return this.crtTime;
	}

	public void setCrtTime(Time crtTime) {
		this.crtTime = crtTime;
	}

	@Column(name = "UPD_UID", length = 20)

	public String getUpdUid() {
		return this.updUid;
	}

	public void setUpdUid(String updUid) {
		this.updUid = updUid;
	}

	@Column(name = "UPD_NAME", length = 40)

	public String getUpdName() {
		return this.updName;
	}

	public void setUpdName(String updName) {
		this.updName = updName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPD_DATE", length = 10)

	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	@Column(name = "UPD_TIME", length = 8)

	public Time getUpdTime() {
		return this.updTime;
	}

	public void setUpdTime(Time updTime) {
		this.updTime = updTime;
	}

	@Column(name = "APPLICANT_NAME", nullable = false, length = 40)

	public String getApplicantName() {
		return this.applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "APPLICANT_DATE", nullable = false, length = 10)

	public Date getApplicantDate() {
		return this.applicantDate;
	}

	public void setApplicantDate(Date applicantDate) {
		this.applicantDate = applicantDate;
	}

	@Column(name = "APPLICANT_TIME", nullable = false, length = 8)

	public Time getApplicantTime() {
		return this.applicantTime;
	}

	public void setApplicantTime(Time applicantTime) {
		this.applicantTime = applicantTime;
	}

	@Column(name = "DEVICE_GROUP", nullable = false, length = 20)

	public String getDeviceGroup() {
		return this.deviceGroup;
	}

	public void setDeviceGroup(String deviceGroup) {
		this.deviceGroup = deviceGroup;
	}

}