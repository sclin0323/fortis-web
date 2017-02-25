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
@Table(name = "GUEST_LOG", catalog = "fortis")

public class GuestLog implements java.io.Serializable {

	// Fields

	private String logId;
	private String method;
	private String email;
	private Date logDate;
	private Time logTime;
	private String logUid;
	private String logName;
	private String applicantId;
	private String applicantName;
	private Date applicantDate;
	private Time applicantTime;
	private Date endDate;
	private String guestGroup;

	// Constructors

	/** default constructor */
	public GuestLog() {
	}

	/** full constructor */
	public GuestLog(String logId, String method, String email, Date logDate, Time logTime, String logUid,
			String logName, String applicantId, String applicantName, Date applicantDate, Time applicantTime,
			Date endDate, String guestGroup) {
		this.logId = logId;
		this.method = method;
		this.email = email;
		this.logDate = logDate;
		this.logTime = logTime;
		this.logUid = logUid;
		this.logName = logName;
		this.applicantId = applicantId;
		this.applicantName = applicantName;
		this.applicantDate = applicantDate;
		this.applicantTime = applicantTime;
		this.endDate = endDate;
		this.guestGroup = guestGroup;
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

	@Column(name = "EMAIL", nullable = false, length = 80)

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@Column(name = "APPLICANT_ID", nullable = false, length = 20)

	public String getApplicantId() {
		return this.applicantId;
	}

	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE", nullable = false, length = 10)

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "GUEST_GROUP", nullable = false, length = 40)

	public String getGuestGroup() {
		return this.guestGroup;
	}

	public void setGuestGroup(String guestGroup) {
		this.guestGroup = guestGroup;
	}

}