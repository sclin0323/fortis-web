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
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hoyoung.fortis.command.GuestCommand;
import com.hoyoung.fortis.command.SysEmailCommand;
import com.hoyoung.fortis.command.UserDeviceCommand;
import com.hoyoung.fortis.dao.Guest;
import com.hoyoung.fortis.dao.SysEmail;
import com.hoyoung.fortis.dao.UserDevice;

@Service
public class GuestServiceImpl extends BaseServiceImpl implements GuestService {
	final static Logger log = Logger.getLogger(GuestServiceImpl.class);
	
	private String searchWordSql;

	public void setSearchWordSql(String searchWordSql) {
		this.searchWordSql = searchWordSql;
	}
	
	@Override
	public List fetchBySearchWord(String searchWord, int page, int start, int limit) {

		String word = "%" + searchWord + "%";
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getEntityClass());
		detachedCriteria.add(Restrictions.or(
				Restrictions.like("email", word),
				Restrictions.like("applicantId", word), 
				Restrictions.like("applicantName", word)));
		
		detachedCriteria.addOrder(Order.desc("guestId"));

		List dataList = fortisDAO.findByCriteria(detachedCriteria, start, limit);

		return toMapList(dataList, true);
	}

	@Transactional(readOnly = true)
	public long fetchCountBySearchWord(String searchWord, int page, int start, int limit) {

		String word = "%" + searchWord + "%";
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getEntityClass());
		detachedCriteria.add(Restrictions.or(
				Restrictions.like("email", word), 
				Restrictions.like("applicantId", word),
				Restrictions.like("applicantName", word)));

		return fortisDAO.fetchCountByCriteria(detachedCriteria);
	}

	@Override
	public List fetchByApplicantId(String applicantId) {
		List dataList = fortisDAO.findByProperty(getEntityClass(), "applicantId", applicantId);																								    
		
		return toMapList(dataList, true);
	}


	@Override
	public void validateCreate(Object obj) {
		GuestCommand cmd = (GuestCommand) obj;
		
		// GuestId 是否已經存在
		Guest o = (Guest) fortisDAO.findById(getEntityClass(), cmd.getGuestId());
		if (o != null) {
			throw new IllegalArgumentException("該 Guest ID 已經存在!!");
		}
	}

	@Override
	public Map<String, Object> create(Object obj) {
		GuestCommand cmd = (GuestCommand) obj;
		
		Guest o = new Guest();
		
		
		o.setGuestId(cmd.getGuestId());
		o.setGuestGroup(cmd.getGuestGroup());
		o.setGuestPwd(cmd.getGuestPwd());

		o.setApplicantId(cmd.getApplicantId());
		o.setApplicantName(cmd.getApplicantName());
		
		o.setEmail(cmd.getEmail());
		o.setEndDate(cmd.getEndDate());
	
		o.setCrtUid(cmd.getCrtUid());
		o.setCrtName(cmd.getCrtName());
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

		return toMap(null, true);
	}

	@Override
	public Map<String, Object> delete(Object obj) {
		String guestId = (String) obj;
		
		Guest o = (Guest) fortisDAO.findById(getEntityClass(), guestId);
		
		fortisDAO.delete(o);

		return toMap(o, true);
	}

	@Override
	protected Map<String, Object> toMap(Object obj, boolean isFull) {
		Guest o = (Guest) obj;

		Map<String, Object> m = new HashMap<String, Object>();
		
		if (isFull) {
			m.put("guestId", o.getGuestId());
			m.put("guestPwd", o.getGuestPwd());
			m.put("guestGroup", o.getGuestGroup());
			
			m.put("applicantId", o.getApplicantId());
			m.put("applicantName", o.getApplicantName());
			
			m.put("email", o.getEmail());
			m.put("endDate", toDateString(o.getEndDate()));
			
			m.put("crtUid", o.getCrtUid());
			m.put("crtName", o.getCrtName());
			m.put("crtDate", toDateString(o.getCrtDate()));
			m.put("crtTime", toTimeString(o.getCrtTime()));
			m.put("updUid", o.getUpdUid());
			m.put("updName", o.getUpdName());
			m.put("updDate", toDateString(o.getUpdDate()));
			m.put("updTime", toTimeString(o.getUpdTime()));
		}

		return m;
	}

	@Override
	protected Class getEntityClass() {
		return Guest.class;
	}

	@Override
	public SysEmailCommand getSysEmailCommand(GuestCommand cmd) {
		
		SysEmailCommand sysEmailCommand = new SysEmailCommand();
		
		sysEmailCommand.setSendTo(cmd.getEmail());
		sysEmailCommand.setSubject("無線網路臨時帳號密碼通知信");
		
		String text = "帳號: "+cmd.getGuestId()+" 密碼: "+cmd.getGuestPwd();
		sysEmailCommand.setText(text);
		
		return sysEmailCommand;
	}


}
