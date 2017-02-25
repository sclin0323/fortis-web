package com.hoyoung.fortis.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * UserDeviceSync entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "USER_DEVICE_SYNC", catalog = "fortisdb")

public class UserDeviceSync implements java.io.Serializable {

	// Fields

	private String userDevice;
	private Long version;
	private String userDeviceGroup;
	private String macAddress;
	private Long test;

	// Constructors

	/** default constructor */
	public UserDeviceSync() {
	}

	/** full constructor */
	public UserDeviceSync(String userDevice, String userDeviceGroup, String macAddress, Long test) {
		this.userDevice = userDevice;
		this.userDeviceGroup = userDeviceGroup;
		this.macAddress = macAddress;
		this.test = test;
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

	@Version
	@Column(name = "VERSION", nullable = false)

	public Long getVersion() {
		return this.version;
	}

	public void setVersion(Long version) {
		this.version = version;
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

	@Column(name = "TEST", nullable = false)

	public Long getTest() {
		return this.test;
	}

	public void setTest(Long test) {
		this.test = test;
	}

}