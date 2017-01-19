package com.hoyoung.fortis.services;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hoyoung.fortis.command.SysEmailCommand;
import com.hoyoung.fortis.dao.SysEmail;

@Service
public class SysEmailServiceImpl extends BaseServiceImpl implements SysEmailService{

	final static Logger log = Logger.getLogger(SysEmailServiceImpl.class);
	
	public static final int STATUS_WAITING = 0;
	public static final int STATUS_SUCCESS = 1;
	public static final int STATUS_FAILURE = 9;
	
	@Override
	public Map<String, Object> create(Object obj) {
		SysEmailCommand cmd = (SysEmailCommand) obj;
		
		SysEmail o = new SysEmail();
		
		o.setSendTo(cmd.getSendTo());
		o.setStatus(STATUS_WAITING);
		o.setSubject(cmd.getSubject());
		o.setText(cmd.getText());
		o.setCrtDate(new Date());
		o.setCrtTime(new Time(new Date().getTime()));
		
		fortisDAO.save(o);

		return toMap(o, true);
	}

	@Override
	public void validateUpdate(Object obj) {
		
	}
	
	@Override
	public Map<String, Object> update(Object obj) {
		
		
		return null;
	}

	@Override
	public Map<String, Object> delete(Object obj) {		
		return null;
	}

	@Override
	protected Map<String, Object> toMap(Object obj, boolean isFull) {
		SysEmail o = (SysEmail) obj;
		
		Map<String, Object> m = new HashMap<String, Object>();
		
		if(isFull) {
			m.put("sysEmailId", o.getSysEmailId());
			m.put("sendTo", o.getSendTo());
			m.put("subject", o.getSubject());
			m.put("text", o.getText());
			m.put("status", o.getStatus());
			m.put("crtDate", toDateString(o.getCrtDate()));
			m.put("crtTime", toTimeString(o.getCrtTime()));
			
		}
		
		return m;
	}

	@Override
	protected Class getEntityClass() {
		return SysEmail.class;
	}

	@Override
	public void validateCreate(Object obj) {
		
	}

	

	





}
