package com.hoyoung.fortis.services;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hoyoung.fortis.dao.UserDevice;
import com.hoyoung.fortis.dao.UserDeviceLog;

@Service
public class UserDeviceLogServiceImpl extends BaseServiceImpl implements UserDeviceLogService {
	final static Logger log = Logger.getLogger(UserDeviceLogServiceImpl.class);

	@Override
	public Map<String, Object> saveUserDeviceLog(String method,String logUid, String logName, String deviceName) {
		
		// 建立 Log Id
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddmmss");
		String logId = date.format(formatter);
		
		UserDeviceLog o = new UserDeviceLog();
		o.setLogId(logId);
		o.setLogUid(logUid);
		o.setLogName(logName);
		o.setLogDate(new Date());
		o.setLogTime(new Time(new Date().getTime()));
		o.setMethod(method);
		
		// save log
		UserDevice  userDevice = (UserDevice) fortisDAO.findById(UserDevice.class, deviceName);
		o.setDeviceName(userDevice.getDeviceName());
		o.setDeviceGroup(userDevice.getDeviceGroup());
		o.setApplicantDate(userDevice.getApplicantDate());
		o.setApplicantName(userDevice.getApplicantName());
		o.setApplicantTime(userDevice.getApplicantTime());
		o.setApplicantId(userDevice.getApplicantId());
		o.setMacAddress(userDevice.getMacAddress());

		fortisDAO.save(o);

		return toMap(o, true);
	}

	@Override
	protected Map<String, Object> toMap(Object obj, boolean isFull) {
		UserDeviceLog o = (UserDeviceLog) obj;

		Map<String, Object> m = new HashMap<String, Object>();
		
		if (isFull) {
			
			m.put("logId", o.getLogId());
			m.put("method", o.getMethod());
			m.put("logUid", o.getLogUid());
			m.put("logName", o.getLogName());
			m.put("logDate", toDateString(o.getLogDate()));
			m.put("logTime", toDateString(o.getLogTime()));
			
			m.put("deviceName", o.getDeviceName());
			m.put("deviceGroup", o.getDeviceGroup());
			m.put("applicantId", o.getApplicantId());
			m.put("applicantName", o.getApplicantName());
			m.put("applicantDate", toDateString(o.getApplicantDate()));
			m.put("applicantTime", toTimeString(o.getApplicantTime()));
			m.put("macAddress", o.getMacAddress());
			
		}

		return m;
	}

	@Override
	protected Class getEntityClass() {
		return UserDeviceLog.class;
	}





}
