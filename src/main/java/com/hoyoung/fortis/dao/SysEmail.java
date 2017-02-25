package com.hoyoung.fortis.dao;

import java.sql.Time;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SysEmail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_EMAIL", catalog = "fortisdb")

public class SysEmail implements java.io.Serializable {

	// Fields

	private Long sysEmailId;
	private String sendTo;
	private String subject;
	private String text;
	private Date crtDate;
	private Time crtTime;
	private Integer status;

	// Constructors

	/** default constructor */
	public SysEmail() {
	}

	/** full constructor */
	public SysEmail(String sendTo, String subject, String text, Date crtDate, Time crtTime, Integer status) {
		this.sendTo = sendTo;
		this.subject = subject;
		this.text = text;
		this.crtDate = crtDate;
		this.crtTime = crtTime;
		this.status = status;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "SYS_EMAIL_ID", unique = true, nullable = false)

	public Long getSysEmailId() {
		return this.sysEmailId;
	}

	public void setSysEmailId(Long sysEmailId) {
		this.sysEmailId = sysEmailId;
	}

	@Column(name = "SEND_TO", nullable = false, length = 80)

	public String getSendTo() {
		return this.sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	@Column(name = "SUBJECT", nullable = false, length = 80)

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(name = "TEXT", nullable = false, length = 200)

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
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

	@Column(name = "STATUS", nullable = false)

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}