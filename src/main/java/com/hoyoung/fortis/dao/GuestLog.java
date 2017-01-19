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
 * GuestLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "GUEST_LOG", catalog = "fortisdb")

public class GuestLog implements java.io.Serializable {

	// Fields

	private String logId;
	private String method;
	private String account;
	private Date logDate;
	private Time logTime;
	private String logUid;
	private String logName;

	// Constructors

	/** default constructor */
	public GuestLog() {
	}

	/** full constructor */
	public GuestLog(String logId, String method, String account, Date logDate, Time logTime, String logUid,
			String logName) {
		this.logId = logId;
		this.method = method;
		this.account = account;
		this.logDate = logDate;
		this.logTime = logTime;
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

	@Column(name = "ACCOUNT", nullable = false, length = 20)

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
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