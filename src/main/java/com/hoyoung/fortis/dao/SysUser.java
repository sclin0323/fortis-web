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
 * SysUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_USER", catalog = "fortisdb")

public class SysUser implements java.io.Serializable {

	// Fields

	private String sysUserId;
	private String name;
	private String password;
	private String role;
	private String crtUid;
	private String crtName;
	private Date crtDate;
	private Time crtTime;
	private String updUid;
	private String updName;
	private Date updDate;
	private Time updTime;

	// Constructors

	/** default constructor */
	public SysUser() {
	}

	/** minimal constructor */
	public SysUser(String sysUserId, String name, String password, String role, String crtUid, String crtName,
			Date crtDate, Time crtTime) {
		this.sysUserId = sysUserId;
		this.name = name;
		this.password = password;
		this.role = role;
		this.crtUid = crtUid;
		this.crtName = crtName;
		this.crtDate = crtDate;
		this.crtTime = crtTime;
	}

	/** full constructor */
	public SysUser(String sysUserId, String name, String password, String role, String crtUid, String crtName,
			Date crtDate, Time crtTime, String updUid, String updName, Date updDate, Time updTime) {
		this.sysUserId = sysUserId;
		this.name = name;
		this.password = password;
		this.role = role;
		this.crtUid = crtUid;
		this.crtName = crtName;
		this.crtDate = crtDate;
		this.crtTime = crtTime;
		this.updUid = updUid;
		this.updName = updName;
		this.updDate = updDate;
		this.updTime = updTime;
	}

	// Property accessors
	@Id

	@Column(name = "SYS_USER_ID", unique = true, nullable = false, length = 20)

	public String getSysUserId() {
		return this.sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}

	@Column(name = "NAME", nullable = false, length = 40)

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PASSWORD", nullable = false, length = 80)

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "ROLE", nullable = false, length = 20)

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
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

}