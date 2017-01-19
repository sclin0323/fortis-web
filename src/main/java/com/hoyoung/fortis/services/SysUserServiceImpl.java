package com.hoyoung.fortis.services;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.hoyoung.fortis.authorize.UserInfo;
import com.hoyoung.fortis.command.SysUserCommand;
import com.hoyoung.fortis.dao.SysUser;

@Service
public class SysUserServiceImpl extends BaseServiceImpl implements SysUserService{

	final static Logger log = Logger.getLogger(SysUserServiceImpl.class);
	
	@Override
	public UserInfo getUserInfoById(String userId) {
		
		SysUser sysUser = (SysUser) fortisDAO.findById(getEntityClass(), userId);
		
		UserInfo userInfo = new UserInfo();
		
		userInfo.setSysUserId(sysUser.getSysUserId());
		userInfo.setName(sysUser.getName());
		
		return userInfo;
	}
	
	@Override
	public String fetchPasswordById(String sysUserId) {
		SysUser sysUser = (SysUser) fortisDAO.findById(getEntityClass(), sysUserId);
		return sysUser.getPassword();
	}
	
	@Override
	public List fetchBySearchWord(String searchWord, int page, int start, int limit) {
		String word = "%" + searchWord + "%";
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getEntityClass());
		detachedCriteria.add(Restrictions.or(
				Restrictions.like("sysUserId", word), 
				Restrictions.like("name", word),
				Restrictions.like("role", word)));

		List dataList = fortisDAO.findByCriteria(detachedCriteria, start, limit);

		return toMapList(dataList, true);
	}

	@Override
	public long fetchCountBySearchWord(String searchWord, int page, int start, int limit) {
		String word = "%" + searchWord + "%";
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getEntityClass());
		detachedCriteria.add(Restrictions.or(
				Restrictions.like("sysUserId", word), 
				Restrictions.like("name", word),
				Restrictions.like("role", word)));

		if (detachedCriteria == null) {
			return 0;
		}

		return fortisDAO.fetchCountByCriteria(detachedCriteria);
	}
	
	@Override
	public Map<String, Object> create(Object obj) {
		SysUserCommand cmd = (SysUserCommand) obj;
		
		SysUser o = new SysUser();
		
		log.info(cmd.getSysUserId());
		log.info(cmd.getName());
		log.info(cmd.getPassword());
		log.info(cmd.getRole());
		
		o.setSysUserId(cmd.getSysUserId());
		o.setName(cmd.getName());
		o.setPassword(cmd.getPassword());
		o.setRole(cmd.getRole());
		
		o.setCrtUid(cmd.getCrtUid());
		o.setCrtName(cmd.getCrtName());
		o.setCrtDate(new Date());
		o.setCrtTime(new Time(new Date().getTime()));
		
		fortisDAO.save(o);

		return toMap(o, true);
	}

	@Override
	public void validateUpdate(Object obj) {
		SysUserCommand cmd = (SysUserCommand) obj;
		
		// 判斷是否有需要修改密碼
		if(cmd.isChangePassword()) {
			// 需要判斷原密碼輸入是否正確
			SysUser o = (SysUser) fortisDAO.findById(getEntityClass(), cmd.getSysUserId());
			if(o.getPassword().equals(cmd.getPassword()) == false) {
				throw new IllegalArgumentException("原密碼密碼錯誤，請重新確認。");
			}
		}
		
	}
	
	@Override
	public Map<String, Object> update(Object obj) {
		SysUserCommand cmd = (SysUserCommand) obj;
			
		SysUser o = (SysUser) fortisDAO.findById(getEntityClass(), cmd.getSysUserId());

		o.setName(cmd.getName());
		
		log.info("==================================================================");
		log.info(cmd.getPassword());
		
		o.setPassword(cmd.getPassword());
		o.setRole(cmd.getRole());
		
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

		SysUser o = (SysUser) fortisDAO.findById(getEntityClass(), objectId);
		if(o == null) {
			return null;
		}
		fortisDAO.delete(o);
		return toMap(o, true);
	}

	@Override
	protected Map<String, Object> toMap(Object obj, boolean isFull) {
		SysUser o = (SysUser) obj;
		
		Map<String, Object> m = new HashMap<String, Object>();
		
		if(isFull) {
			m.put("sysUserId", o.getSysUserId());
			m.put("name", o.getName());
			m.put("role", o.getRole());
			
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
		return SysUser.class;
	}

	@Override
	public void validateCreate(Object obj) {
		
	}

	

	





}
