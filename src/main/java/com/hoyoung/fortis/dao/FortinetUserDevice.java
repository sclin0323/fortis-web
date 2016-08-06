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
 * FortinetUserDevice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FORTINET_USER_DEVICE", catalog = "fortisdb")

public class FortinetUserDevice implements java.io.Serializable {

	// Fields

	private String userDevice;
	private String userDeviceGroup;
	private String macAddress;
	private Date crtDate;
	private Time crtTime;
	private Date updDate;
	private Time updTime;
	private Long timestampe;

	// Constructors

	/** default constructor */
	public FortinetUserDevice() {
	}

	/** minimal constructor */
	public FortinetUserDevice(String userDevice, String userDeviceGroup, String macAddress, Date crtDate, Time crtTime,
			Long timestampe) {
		this.userDevice = userDevice;
		this.userDeviceGroup = userDeviceGroup;
		this.macAddress = macAddress;
		this.crtDate = crtDate;
		this.crtTime = crtTime;
		this.timestampe = timestampe;
	}

	/** full constructor */
	public FortinetUserDevice(String userDevice, String userDeviceGroup, String macAddress, Date crtDate, Time crtTime,
			Date updDate, Time updTime, Long timestampe) {
		this.userDevice = userDevice;
		this.userDeviceGroup = userDeviceGroup;
		this.macAddress = macAddress;
		this.crtDate = crtDate;
		this.crtTime = crtTime;
		this.updDate = updDate;
		this.updTime = updTime;
		this.timestampe = timestampe;
	}

	// Property accessors
	@Id

	@Column(name = "USER_DEVICE", unique = true, nullable = false, length = 40)

	public String getUserDevice() {
		return this.userDevice;
	}

	public void setUserDevice(String userDevice) {
		this.userDevice = userDevice;
	}

	@Column(name = "USER_DEVICE_GROUP", nullable = false, length = 40)

	public String getUserDeviceGroup() {
		return this.userDeviceGroup;
	}

	public void setUserDeviceGroup(String userDeviceGroup) {
		this.userDeviceGroup = userDeviceGroup;
	}

	@Column(name = "MAC_ADDRESS", nullable = false, length = 20)

	public String getMacAddress() {
		return this.macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
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

	@Column(name = "TIMESTAMPE", nullable = false)

	public Long getTimestampe() {
		return this.timestampe;
	}

	public void setTimestampe(Long timestampe) {
		this.timestampe = timestampe;
	}

}