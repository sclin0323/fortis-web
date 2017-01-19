package com.hoyoung.fortis.services;

import org.springframework.http.HttpEntity;

import com.hoyoung.fortis.python.PythonResponse;

public interface RestTemplateService {
	
	public PythonResponse editConfigUserLocal(String account,String password);
	
	public PythonResponse deleteConfigUserLocal(String account);
	
	public PythonResponse editConfigUserGroup(String account,String userGroup);
	
	public PythonResponse reenableSystemInterface();
	
	public PythonResponse showUserDeviceGroupByUserDeviceGroup(String deviceGroup);
	
	public PythonResponse showUserDeviceByUserDevice(String deviceName);
	
	public PythonResponse editConfigUserDevice(String deviceName,String macAddress);
	
	public HttpEntity<PythonResponse> appendConfigUserDeviceGroups(String deviceName,String deviceGroup);

	public HttpEntity<PythonResponse> unselectConfigUserDeviceGroups(String deviceName, String deviceGroup);

	public HttpEntity<PythonResponse> deleteConfigUserDevice(String userDeviceId);

	public HttpEntity<PythonResponse> getSystemStatus();

	public boolean validErrorCode(PythonResponse response, int code);
}
