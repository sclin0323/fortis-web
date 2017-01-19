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
 * Guest entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "GUEST", catalog = "fortisdb")

public class Guest implements java.io.Serializable {

	// Fields

	private String guestId;
	private String guestPwd;
	private String crtUid;
	private String crtName;
	private Date crtDate;
	private Time crtTime;
	private String updUid;
	private String updName;
	private Date updDate;
	private Time updTime;
	private String email;
	private String applicantId;
	private String applicantName;
	private Date endDate;
	private String guestGroup;
	private Date applicantDate;
	private Time applicantTime;

	// Constructors

	/** default constructor */
	public Guest() {
	}

	/** minimal constructor */
	public Guest(String guestId, String guestPwd, String crtUid, String crtName, Date crtDate, Time crtTime,
			String email, String applicantId, String applicantName, Date endDate, String guestGroup, Date applicantDate,
			Time applicantTime) {
		this.guestId = guestId;
		this.guestPwd = guestPwd;
		this.crtUid = crtUid;
		this.crtName = crtName;
		this.crtDate = crtDate;
		this.crtTime = crtTime;
		this.email = email;
		this.applicantId = applicantId;
		this.applicantName = applicantName;
		this.endDate = endDate;
		this.guestGroup = guestGroup;
		this.applicantDate = applicantDate;
		this.applicantTime = applicantTime;
	}

	/** full constructor */
	public Guest(String guestId, String guestPwd, String crtUid, String crtName, Date crtDate, Time crtTime,
			String updUid, String updName, Date updDate, Time updTime, String email, String applicantId,
			String applicantName, Date endDate, String guestGroup, Date applicantDate, Time applicantTime) {
		this.guestId = guestId;
		this.guestPwd = guestPwd;
		this.crtUid = crtUid;
		this.crtName = crtName;
		this.crtDate = crtDate;
		this.crtTime = crtTime;
		this.updUid = updUid;
		this.updName = updName;
		this.updDate = updDate;
		this.updTime = updTime;
		this.email = email;
		this.applicantId = applicantId;
		this.applicantName = applicantName;
		this.endDate = endDate;
		this.guestGroup = guestGroup;
		this.applicantDate = applicantDate;
		this.applicantTime = applicantTime;
	}

	// Property accessors
	@Id

	@Column(name = "GUEST_ID", unique = true, nullable = false, length = 40)

	public String getGuestId() {
		return this.guestId;
	}

	public void setGuestId(String guestId) {
		this.guestId = guestId;
	}

	@Column(name = "GUEST_PWD", nullable = false, length = 20)

	public String getGuestPwd() {
		return this.guestPwd;
	}

	public void setGuestPwd(String guestPwd) {
		this.guestPwd = guestPwd;
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

	@Column(name = "EMAIL", nullable = false, length = 80)

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "APPLICANT_ID", nullable = false, length = 20)

	public String getApplicantId() {
		return this.applicantId;
	}

	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}

	@Column(name = "APPLICANT_NAME", nullable = false, length = 20)

	public String getApplicantName() {
		return this.applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE", nullable = false, length = 10)

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "GUEST_GROUP", nullable = false, length = 20)

	public String getGuestGroup() {
		return this.guestGroup;
	}

	public void setGuestGroup(String guestGroup) {
		this.guestGroup = guestGroup;
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

}