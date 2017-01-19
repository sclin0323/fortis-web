package com.hoyoung.fortis.command;

import java.sql.Time;
import java.util.Date;

public class GuestCommand {
	
	private String guestId;
	private String guestGroup;
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
	
	
	
	public Date getEndDate() {
		return endDate;
	}
	

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	

	public String getGuestId() {
		return guestId;
	}
	
	public void setGuestId(String guestId) {
		this.guestId = guestId;
	}
	

	public String getCrtUid() {
		return crtUid;
	}
	
	public void setCrtUid(String crtUid) {
		this.crtUid = crtUid;
	}
	
	public String getCrtName() {
		return crtName;
	}
	
	public void setCrtName(String crtName) {
		this.crtName = crtName;
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
	
	public String getUpdUid() {
		return updUid;
	}
	
	public void setUpdUid(String updUid) {
		this.updUid = updUid;
	}
	
	public String getUpdName() {
		return updName;
	}
	
	public void setUpdName(String updName) {
		this.updName = updName;
	}
	
	public Date getUpdDate() {
		return updDate;
	}
	
	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}
	
	public Time getUpdTime() {
		return updTime;
	}
	
	public void setUpdTime(Time updTime) {
		this.updTime = updTime;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getApplicantId() {
		return applicantId;
	}
	
	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}
	
	public String getApplicantName() {
		return applicantName;
	}
	
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}


	public String getGuestGroup() {
		return guestGroup;
	}
	


	public void setGuestGroup(String guestGroup) {
		this.guestGroup = guestGroup;
	}
	


	public String getGuestPwd() {
		return guestPwd;
	}
	


	public void setGuestPwd(String guestPwd) {
		this.guestPwd = guestPwd;
	}
	
	
	
	
}
