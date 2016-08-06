package com.hoyoung.fortis.services;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.hoyoung.fortis.command.UserDeviceCommand;
import com.hoyoung.fortis.dao.UserDevice;

public interface UserDeviceLogService{

	public Map<String, Object> saveUserDeviceLog(String method,String logUid, String logName, String deviceName);
		
}
