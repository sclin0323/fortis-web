package com.hoyoung.fortis.services;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoyoung.fortis.command.UserDeviceCommand;
import com.hoyoung.fortis.dao.UserDevice;

@Service
public class UserDeviceServiceImpl extends BaseServiceImpl implements UserDeviceService {
	final static Logger log = Logger.getLogger(UserDeviceServiceImpl.class);
	
	private String searchWordSql;

	public void setSearchWordSql(String searchWordSql) {
		this.searchWordSql = searchWordSql;
	}

	@Override
	public List fetchBySearchWord(String searchWord, int page, int start, int limit) {

		String word = "%" + searchWord + "%";
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getEntityClass());
		detachedCriteria.add(Restrictions.or(
				Restrictions.like("deviceName", word), 
				Restrictions.like("applicantId", word),
				Restrictions.like("macAddress", word), 
				Restrictions.like("applicantName", word)));

		List dataList = fortisDAO.findByCriteria(detachedCriteria, start, limit);

		return toMapList(dataList, true);
	}

	@Transactional(readOnly = true)
	public long fetchCountBySearchWord(String searchWord, int page, int start, int limit) {

		String word = "%" + searchWord + "%";
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getEntityClass());
		detachedCriteria.add(Restrictions.or(
				Restrictions.like("deviceName", word), 
				Restrictions.like("applicantId", word),
				Restrictions.like("macAddress", word), 
				Restrictions.like("applicantName", word)));

		if (detachedCriteria == null) {
			return 0;
		}

		return fortisDAO.fetchCountByCriteria(detachedCriteria);
	}
	
	@Override
	public List fetchByApplicantId(String applicantId) {
	
		List dataList = fortisDAO.findByProperty(getEntityClass(), "applicantId", applicantId);
		
		return toMapList(dataList, true);
	}

	@Override
	public String validateCreate(Object obj) {
		UserDeviceCommand cmd = (UserDeviceCommand) obj;
		
		// DeviceName 是否已經存在
		UserDevice o = (UserDevice) fortisDAO.findById(getEntityClass(), cmd.getDeviceName());
		if (o != null) {
			return "該 Device Name 已經存在!!";
		}
		
		// 網卡是否已經存在
		List lists = fortisDAO.findByProperty(getEntityClass(), "macAddress", cmd.getMacAddress());
		if(lists.size() >0) {
			return "該 Mac Address 已經存在!!";
		}
		
		// 網卡格式是否有錯
		
		return null;
	}

	@Override
	public Map<String, Object> create(Object obj) {
		UserDeviceCommand cmd = (UserDeviceCommand) obj;

		UserDevice o = new UserDevice();
		o.setDeviceName(cmd.getDeviceName());
		o.setDeviceGroup(cmd.getDeviceGroup());
		o.setApplicantDate(new Date());
		o.setApplicantName(cmd.getApplicantName());
		o.setApplicantTime(new Time(new Date().getTime()));
		o.setApplicantId(cmd.getApplicantId());
		o.setMacAddress(cmd.getMacAddress());
		o.setCrtUid(cmd.getCrtUid());
		o.setCrtName(cmd.getCrtName());
		o.setCrtDate(new Date());
		o.setCrtTime(new Time(new Date().getTime()));

		fortisDAO.save(o);

		return toMap(o, true);
	}
	
	@Override
	public String validateUpdate(Object obj) {
		UserDeviceCommand cmd = (UserDeviceCommand) obj;
		
		// 檢核網卡是否已經存在
		List<UserDevice> list = fortisDAO.findByProperty(getEntityClass(), "macAddress", cmd.getMacAddress());
		if(list.size() >0) {
					return "該網卡卡號 (mac address) 已經存在!!";
		}
		
		return null;
	}
	
	@Override
	public boolean isUpdateMacAddress(UserDeviceCommand cmd) {
		
		UserDevice o = (UserDevice) fortisDAO.findById(getEntityClass(), cmd.getDeviceName());
		
		if(o.getMacAddress().equals(cmd.getMacAddress())) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean isUpdateDeviceGroup(UserDeviceCommand cmd) {
		UserDevice o = (UserDevice) fortisDAO.findById(getEntityClass(), cmd.getDeviceName());
		
		if(o.getDeviceGroup().equals(cmd.getDeviceGroup())) {
			return false;
		}
		
		return true;
	}

	@Override
	public Map<String, Object> update(Object obj) {
		

		UserDeviceCommand cmd = (UserDeviceCommand) obj;

		UserDevice o = (UserDevice) fortisDAO.findById(getEntityClass(), cmd.getDeviceName());

		o.setDeviceGroup(cmd.getDeviceGroup());
		o.setApplicantDate(new Date());
		o.setApplicantName(cmd.getApplicantName());
		o.setApplicantTime(new Time(new Date().getTime()));
		o.setApplicantId(cmd.getApplicantId());
		o.setMacAddress(cmd.getMacAddress());

		// update
		o.setUpdUid(cmd.getUpdUid());
		o.setUpdName(cmd.getUpdName());
		o.setUpdDate(new Date());
		o.setUpdTime(new Time(new Date().getTime()));

		fortisDAO.attachDirty(o);

		return toMap(o, true);
	}

	@Override
	public Map<String, Object> delete(Object obj) {
		String userDeviceId = (String) obj;
		
		UserDevice o = (UserDevice) fortisDAO.findById(getEntityClass(), userDeviceId);
		fortisDAO.delete(o);

		return toMap(o, true);
	}

	@Override
	protected Map<String, Object> toMap(Object obj, boolean isFull) {
		UserDevice o = (UserDevice) obj;

		Map<String, Object> m = new HashMap<String, Object>();
		
		if (isFull) {
			m.put("deviceName", o.getDeviceName());
			m.put("deviceGroup", o.getDeviceGroup());
			m.put("applicantId", o.getApplicantId());
			m.put("applicantName", o.getApplicantName());
			m.put("applicantDate", toDateString(o.getApplicantDate()));
			m.put("applicantTime", toTimeString(o.getApplicantTime()));
			m.put("macAddress", o.getMacAddress());

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
		return UserDevice.class;
	}



	





}
