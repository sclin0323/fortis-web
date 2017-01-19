package com.hoyoung.fortis.services;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoyoung.fortis.dao.Guest;
import com.hoyoung.fortis.dao.GuestLog;
import com.hoyoung.fortis.dao.UserDevice;
import com.hoyoung.fortis.dao.UserDeviceLog;

@Service
public class GuestLogServiceImpl extends BaseServiceImpl implements GuestLogService {
	final static Logger log = Logger.getLogger(GuestLogServiceImpl.class);

	@Override
	public List fetchBySearchWord(String searchWord, int page, int start, int limit) {
		String word = "%" + searchWord + "%";
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getEntityClass());
		detachedCriteria.add(Restrictions.or(
				Restrictions.like("method", word), 
				Restrictions.like("email", word), 
				Restrictions.like("applicantId", word), 
				Restrictions.like("applicantName", word), 
				Restrictions.like("guestGroup", word),
				Restrictions.like("logUid", word),
				Restrictions.like("logName", word)));
		detachedCriteria.addOrder(Order.desc("logId"));

		List dataList = fortisDAO.findByCriteria(detachedCriteria, start, limit);

		return toMapList(dataList, true);
	}

	@Transactional(readOnly = true)
	public long fetchCountBySearchWord(String searchWord, int page, int start, int limit) {

		String word = "%" + searchWord + "%";
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getEntityClass());
		detachedCriteria.add(Restrictions.or(
				Restrictions.like("method", word), 
				Restrictions.like("email", word), 
				Restrictions.like("applicantId", word), 
				Restrictions.like("applicantName", word), 
				Restrictions.like("guestGroup", word),
				Restrictions.like("logUid", word),
				Restrictions.like("logName", word)));

		return fortisDAO.fetchCountByCriteria(detachedCriteria);
	}
	
	@Override
	public Map<String, Object> saveGuestLog(String method,String logUid, String logName, String guestId) {
		
		// 建立 Log Id
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddhhmmssSSS");
		String logId = date.format(formatter);
		
		GuestLog o = new GuestLog();
		o.setLogId(logId);
		o.setLogUid(logUid);
		o.setLogName(logName);
		o.setLogDate(new Date());
		o.setLogTime(new Time(new Date().getTime()));
		o.setMethod(method);
		
		// save log
		Guest  guest = (Guest) fortisDAO.findById(Guest.class, guestId);
		
		o.setEmail(guest.getEmail());
		o.setEndDate(guest.getEndDate());
		o.setGuestGroup(guest.getGuestGroup());
		o.setApplicantId(guest.getApplicantId());
		o.setApplicantName(guest.getApplicantName());
		o.setApplicantDate(guest.getApplicantDate());
		o.setApplicantTime(guest.getApplicantTime());

		fortisDAO.save(o);

		return toMap(o, true);
	}

	@Override
	protected Map<String, Object> toMap(Object obj, boolean isFull) {
		GuestLog o = (GuestLog) obj;

		Map<String, Object> m = new HashMap<String, Object>();
		
		if (isFull) {
			
			m.put("logId", o.getLogId());
			m.put("method", o.getMethod());
			m.put("logUid", o.getLogUid());
			m.put("logName", o.getLogName());
			m.put("logDate", toDateString(o.getLogDate()));
			m.put("logTime", toTimeString(o.getLogTime()));
			
			m.put("email", o.getEmail());
			m.put("endDate", o.getEndDate());
			m.put("guestGroup", o.getGuestGroup());
			
			m.put("applicantId", o.getApplicantId());
			m.put("applicantName", o.getApplicantName());
			m.put("applicantDate", toDateString(o.getApplicantDate()));
			m.put("applicantTime", toTimeString(o.getApplicantTime()));
		}

		return m;
	}

	@Override
	protected Class getEntityClass() {
		return GuestLog.class;
	}

}
