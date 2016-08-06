package com.hoyoung.fortis.command;

import java.sql.Time;
import java.util.Date;

public class SysUserCommand {
	private String sysUserId;
	private String name;
	private String password;
	private String newPassword;
	private String role;
	private String crtUid;
	private String crtName;
	private Date crtDate;
	private Time crtTime;
	private String updUid;
	private String updName;
	private Date updDate;
	private Time updTime;
	private boolean changePassword;
	
	
	
	public String getNewPassword() {
		return newPassword;
	}
	


	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	


	public boolean isChangePassword() {
		return changePassword;
	}
	

	public void setChangePassword(boolean changePassword) {
		this.changePassword = changePassword;
	}
	

	public String getSysUserId() {
		return sysUserId;
	}
	
	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
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
	
	
	
}
