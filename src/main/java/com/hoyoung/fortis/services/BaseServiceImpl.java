package com.hoyoung.fortis.services;

import java.io.Serializable;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.hoyoung.fortis.dao.FortisDAO;


public abstract class BaseServiceImpl {

	private static final Log log = LogFactory.getLog(BaseServiceImpl.class);
	
	@Autowired(required=true)
	protected FortisDAO fortisDAO;

	private ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {

		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}

		@Override
		public DateFormat get() {
			return super.get();
		}

		@Override
		public void set(DateFormat value) {
			super.set(value);
		}

		@Override
		public void remove() {
			super.remove();
		}

	};

	private ThreadLocal<DateFormat> tf = new ThreadLocal<DateFormat>() {

		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("HH:mm:ss");
		}

		@Override
		public DateFormat get() {
			return super.get();
		}

		@Override
		public void set(DateFormat value) {
			super.set(value);
		}

		@Override
		public void remove() {
			super.remove();
		}

	};

	private ThreadLocal<DateFormat> dtf = new ThreadLocal<DateFormat>() {

		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}

		@Override
		public DateFormat get() {
			return super.get();
		}

		@Override
		public void set(DateFormat value) {
			super.set(value);
		}

		@Override
		public void remove() {
			super.remove();
		}

	};
	
	protected String toDateString(Date d) {
		if (d != null) {
			return df.get().format(d);
		}
		return null;
	}

	protected String toDateTimeString(Date d) {
		if (d != null) {
			return dtf.get().format(d);
		}
		return null;
	}

	protected String toTimeString(Time d) {
		if (d != null) {
			return tf.get().format(d);
		}
		return null;
	}

	protected String toTimeString(Date d) {
		if (d != null) {
			return tf.get().format(d);
		}
		return null;
	}
	
	public String test(){
		return "";
	}
	
	@Transactional(readOnly=true)
	public List<Map<String, Object>> fetchSelect() {
		
		List dataList = fortisDAO.findAll(getEntityClass());
		
		return toMapList(dataList, false);
	}
	
	@Transactional(readOnly=true)
	public Map<String, Object> fetchById(Serializable id) {
		Class clazz =getEntityClass();
		
		Object o = fortisDAO.findById(clazz, id);
		
		if(o == null) {
			return null;
		}
		
		return toMap(o, true);
	}
	
	// 回傳 List Data 
	protected List<Map<String, Object>> toMapList(List data, boolean isFull ) {
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		for (Object obj : data) {
			Map<String, Object> m = toMap(obj, isFull);
			if (m != null) {
				maps.add(m);
			}
		}
		return maps;
	}
	
	protected abstract Map<String, Object> toMap(Object obj, boolean isFull);
	
	protected abstract Class getEntityClass();
	
}
