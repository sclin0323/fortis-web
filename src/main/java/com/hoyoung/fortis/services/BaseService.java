package com.hoyoung.fortis.services;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

public interface BaseService {
	
	// 檢核
	public String validateCreate(Object obj);
	
	public String validateUpdate(Object obj);

	// 查詢
	public Map<String, Object> fetchById(Serializable id);
	
	public List<Map<String, Object>> fetchSelect();
	
	// 新增
	public Map<String, Object> create(Object o);
	
	// 修改
	public Map<String, Object> update(Object o);
	
	// 刪除
	public Map<String, Object> delete(Object o);
	
}
