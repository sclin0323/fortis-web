package com.hoyoung.fortis.command;

import java.sql.Time;
import java.util.Date;

public class SysSettingCommand {

	private String sysSettingId;
	private String name;
	private String hostname;
	private Integer port;
	private String loginName;
	private String password;
	private Integer deviceLimit;
	
	private Integer guestLimit;
	private Time guestStart;
	private Time guestEnd;
	
	private String crtUid;
	private String crtName;
	private Date crtDate;
	private Time crtTime;
	private String updUid;
	private String updName;
	private Date updDate;
	private Time updTime;
	
	
	public Integer getGuestLimit() {
		return guestLimit;
	}
	

	public void setGuestLimit(Integer guestLimit) {
		this.guestLimit = guestLimit;
	}
	

	public Time getGuestStart() {
		return guestStart;
	}
	

	public void setGuestStart(Time guestStart) {
		this.guestStart = guestStart;
	}
	

	public Time getGuestEnd() {
		return guestEnd;
	}
	

	public void setGuestEnd(Time guestEnd) {
		this.guestEnd = guestEnd;
	}
	

	public String getSysSettingId() {
		return sysSettingId;
	}
	
	public void setSysSettingId(String sysSettingId) {
		this.sysSettingId = sysSettingId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getHostname() {
		return hostname;
	}
	
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	
	public Integer getPort() {
		return port;
	}
	
	public void setPort(Integer port) {
		this.port = port;
	}
	
	public String getLoginName() {
		return loginName;
	}
	
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Integer getDeviceLimit() {
		return deviceLimit;
	}
	
	public void setDeviceLimit(Integer deviceLimit) {
		this.deviceLimit = deviceLimit;
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
