package com.hoyoung.fortis.command;

import java.sql.Time;
import java.util.Date;

public class SysEmailCommand {

	private Long sysEmailId;
	private String sendTo;
	private String subject;
	private String text;
	private Date crtDate;
	private Time crtTime;
	private Integer status;
	
	public Long getSysEmailId() {
		return sysEmailId;
	}
	
	public void setSysEmailId(Long sysEmailId) {
		this.sysEmailId = sysEmailId;
	}
	
	public String getSendTo() {
		return sendTo;
	}
	
	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Date getCrtDate() {
		return crtDate;
	}
	
	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}
	
	public Time getCrtTime() {
		return crtTime;
	}
	
	public void setCrtTime(Time crtTime) {
		this.crtTime = crtTime;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
}
