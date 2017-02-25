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
 * SysSetting entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_SETTING", catalog = "fortisdb")

public class SysSetting implements java.io.Serializable {

	// Fields

	private String sysSettingId;
	private String name;
	private String hostname;
	private Integer port;
	private String loginName;
	private String password;
	private Integer deviceLimit;
	private String crtUid;
	private String crtName;
	private Date crtDate;
	private Time crtTime;
	private String updUid;
	private String updName;
	private Date updDate;
	private Time updTime;
	private Integer guestLimit;
	private Time guestStart;
	private Time guestEnd;

	// Constructors

	/** default constructor */
	public SysSetting() {
	}

	/** minimal constructor */
	public SysSetting(String sysSettingId, String name, String hostname, Integer port, String loginName,
			String password, Integer deviceLimit, String crtUid, String crtName, Date crtDate, Time crtTime,
			Integer guestLimit, Time guestStart, Time guestEnd) {
		this.sysSettingId = sysSettingId;
		this.name = name;
		this.hostname = hostname;
		this.port = port;
		this.loginName = loginName;
		this.password = password;
		this.deviceLimit = deviceLimit;
		this.crtUid = crtUid;
		this.crtName = crtName;
		this.crtDate = crtDate;
		this.crtTime = crtTime;
		this.guestLimit = guestLimit;
		this.guestStart = guestStart;
		this.guestEnd = guestEnd;
	}

	/** full constructor */
	public SysSetting(String sysSettingId, String name, String hostname, Integer port, String loginName,
			String password, Integer deviceLimit, String crtUid, String crtName, Date crtDate, Time crtTime,
			String updUid, String updName, Date updDate, Time updTime, Integer guestLimit, Time guestStart,
			Time guestEnd) {
		this.sysSettingId = sysSettingId;
		this.name = name;
		this.hostname = hostname;
		this.port = port;
		this.loginName = loginName;
		this.password = password;
		this.deviceLimit = deviceLimit;
		this.crtUid = crtUid;
		this.crtName = crtName;
		this.crtDate = crtDate;
		this.crtTime = crtTime;
		this.updUid = updUid;
		this.updName = updName;
		this.updDate = updDate;
		this.updTime = updTime;
		this.guestLimit = guestLimit;
		this.guestStart = guestStart;
		this.guestEnd = guestEnd;
	}

	// Property accessors
	@Id

	@Column(name = "SYS_SETTING_ID", unique = true, nullable = false, length = 20)

	public String getSysSettingId() {
		return this.sysSettingId;
	}

	public void setSysSettingId(String sysSettingId) {
		this.sysSettingId = sysSettingId;
	}

	@Column(name = "NAME", nullable = false, length = 40)

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "HOSTNAME", nullable = false, length = 20)

	public String getHostname() {
		return this.hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	@Column(name = "PORT", nullable = false)

	public Integer getPort() {
		return this.port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	@Column(name = "LOGIN_NAME", nullable = false, length = 20)

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "PASSWORD", nullable = false, length = 40)

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "DEVICE_LIMIT", nullable = false)

	public Integer getDeviceLimit() {
		return this.deviceLimit;
	}

	public void setDeviceLimit(Integer deviceLimit) {
		this.deviceLimit = deviceLimit;
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

	@Column(name = "GUEST_LIMIT", nullable = false)

	public Integer getGuestLimit() {
		return this.guestLimit;
	}

	public void setGuestLimit(Integer guestLimit) {
		this.guestLimit = guestLimit;
	}

	@Column(name = "GUEST_START", nullable = false, length = 8)

	public Time getGuestStart() {
		return this.guestStart;
	}

	public void setGuestStart(Time guestStart) {
		this.guestStart = guestStart;
	}

	@Column(name = "GUEST_END", nullable = false, length = 8)

	public Time getGuestEnd() {
		return this.guestEnd;
	}

	public void setGuestEnd(Time guestEnd) {
		this.guestEnd = guestEnd;
	}

}