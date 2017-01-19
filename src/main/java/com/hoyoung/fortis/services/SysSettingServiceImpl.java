package com.hoyoung.fortis.services;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hoyoung.fortis.command.SysSettingCommand;
import com.hoyoung.fortis.dao.SysSetting;
import com.hoyoung.fortis.dao.UserDevice;

@Service
public class SysSettingServiceImpl extends BaseServiceImpl implements SysSettingService{

	@Override
	public Map<String, Object> create(Object obj) {
		SysSettingCommand cmd = (SysSettingCommand) obj;
		
		SysSetting o = new SysSetting();
		o.setSysSettingId(cmd.getSysSettingId());
		o.setHostname(cmd.getHostname());
		o.setName(cmd.getName());
		o.setPort(cmd.getPort());
		o.setLoginName(cmd.getLoginName());
		o.setPassword(cmd.getPassword());
		o.setDeviceLimit(cmd.getDeviceLimit());
		
		o.setCrtUid(cmd.getCrtUid());
		o.setCrtName(cmd.getCrtName());
		o.setCrtDate(new Date());
		o.setCrtTime(new Time(new Date().getTime()));
		
		fortisDAO.save(o);

		return toMap(o, true);
	}

	@Override
	public Map<String, Object> update(Object obj) {
		SysSettingCommand cmd = (SysSettingCommand) obj;
	
		
		SysSetting o = (SysSetting) fortisDAO.findById(getEntityClass(), cmd.getSysSettingId());
		
		o.setHostname(cmd.getHostname());
		o.setPort(cmd.getPort());
		o.setLoginName(cmd.getLoginName());
		o.setPassword(cmd.getPassword());
		o.setDeviceLimit(cmd.getDeviceLimit());
		
		o.setGuestLimit(cmd.getGuestLimit());
		o.setGuestStart(cmd.getGuestStart());
		o.setGuestEnd(cmd.getGuestEnd());
		
		o.setUpdUid(cmd.getUpdUid());
		o.setUpdName(cmd.getUpdName());
		o.setUpdDate(new Date());
		o.setUpdTime(new Time(new Date().getTime()));
		
		fortisDAO.attachDirty(o);
		
		return toMap(o, true);
	}

	@Override
	public Map<String, Object> delete(Object obj) {		
		String objectId = (String) obj;

		SysSetting o = (SysSetting) fortisDAO.findById(getEntityClass(), objectId);
		if(o == null) {
			return null;
		}
		fortisDAO.delete(o);
		return toMap(o, true);
	}

	@Override
	protected Map<String, Object> toMap(Object obj, boolean isFull) {
		SysSetting o = (SysSetting) obj;
		
		Map<String, Object> m = new HashMap<String, Object>();
		
		if(isFull) {
			m.put("sysSettingId", o.getSysSettingId());
			m.put("name", o.getName());
			m.put("hostname", o.getHostname());
			m.put("port", o.getPort());
			m.put("loginName", o.getLoginName());
			m.put("password", o.getPassword());
			m.put("deviceLimit", o.getDeviceLimit());
			
			m.put("guestLimit", o.getGuestLimit());
			m.put("guestStart", toTimeString(o.getGuestStart()));
			m.put("guestEnd", toTimeString(o.getGuestEnd()));
			
			m.put("crtUid", o.getCrtUid());
			m.put("crtName", o.getCrtName());
			m.put("crtDate", o.getCrtDate());
			m.put("crtTime", toTimeString(o.getCrtTime()));
			m.put("updUid", o.getUpdUid());
			m.put("updName", o.getUpdName());
			m.put("updDate", o.getUpdDate());
			m.put("updTime", toTimeString(o.getUpdTime()));
		}
		
		return m;
	}

	@Override
	protected Class getEntityClass() {
		return SysSetting.class;
	}

	@Override
	public void validateCreate(Object obj) {
		
	}

	@Override
	public void validateUpdate(Object obj) {
		
	}

}
