package com.hoyoung.fortis.services;

import org.springframework.http.HttpEntity;

import com.hoyoung.fortis.python.PythonResponse;

public interface RestTemplateService {
	
	// For Config User Device
	public PythonResponse editConfigUserDevice(String deviceName,String macAddress);
	
	public PythonResponse appendConfigUserDeviceGroups(String deviceName,String deviceGroup);
	
	public PythonResponse unselectConfigUserDeviceGroups(String deviceName, String deviceGroup);
	
	public PythonResponse deleteConfigUserDevice(String deviceName);
	
	//public PythonResponse showUserDeviceGroupByUserDeviceGroup(String deviceGroup);
	
	//public PythonResponse showUserDeviceByUserDevice(String deviceName);
	
	// For Config User
	
	public PythonResponse editConfigUserLocal(String guestName,String password);
	
	public PythonResponse appendConfigUserGroups(String guestName, String guestGroup);
	
	public PythonResponse unselectConfigUserGroups(String guestName, String guestGroup);
	
	public PythonResponse deleteConfigUserLocal(String account);
	
	//public PythonResponse editConfigUserGroup(String account,String userGroup);
	
	
	
	
	// For Other
	public PythonResponse reenableSystemInterface();
	
	public PythonResponse getSystemStatus();
}
