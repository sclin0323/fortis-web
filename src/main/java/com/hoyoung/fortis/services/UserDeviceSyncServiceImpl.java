package com.hoyoung.fortis.services;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hoyoung.fortis.dao.UserDeviceSync;

@Service
public class UserDeviceSyncServiceImpl extends BaseServiceImpl implements UserDeviceSyncService {
	final static Logger log = Logger.getLogger(UserDeviceSyncServiceImpl.class);

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	public List<Map<String, Object>> fetchAll() {

		List dataList = fortisDAO.findAll(getEntityClass());

		return toMapList(dataList, false);
	}

	@Override
	//@Transactional(isolation = Isolation.DEFAULT)
	public void test1() {
		log.info("tarnsation start");
        UserDeviceSync o = (UserDeviceSync) fortisDAO.findById(getEntityClass(), "1");
        o.setTest(o.getTest() + 5000L);
        log.info(o.getVersion());
        
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        fortisDAO.attachDirty(o);
        log.info(o.getVersion());
        log.info("tarnsation end");
	}

	@Override
	//@Transactional(isolation = Isolation.DEFAULT)
	public void test2() {
		log.info("tarnsation start");
        UserDeviceSync o = (UserDeviceSync) fortisDAO.findById(getEntityClass(), "1");
        o.setTest(o.getTest() + 500L);
        log.info(o.getVersion());
        fortisDAO.attachDirty(o);
        log.info(o.getVersion());
        log.info("tarnsation end");
	}

	@Override
	public void test3() {
		UserDeviceSync o = new UserDeviceSync();
		o.setMacAddress("mac address");
		o.setTest(10L);
		o.setUserDevice("1");
		o.setUserDeviceGroup("1 Group");
		
		fortisDAO.attachDirty(o);

	}


	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void test11() {
		UserDeviceSync o = (UserDeviceSync) fortisDAO.findById(getEntityClass(), "1");

		try {
			System.out.println("start waiting...");
			Thread.sleep(5000);
			System.out.println("end waiting...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		o.setTest(o.getTest() + 100);
	}

	@Override
	@Transactional
	public void test7() {
		UserDeviceSync o = (UserDeviceSync) fortisDAO.findById(getEntityClass(), "1");

		for (int i = 1; i < 100000; i++) {
			System.out.println("update");
			o.setMacAddress(i + "_update");
			fortisDAO.attachDirty(o);
		}
	}

	@Override
	public void test6() {
		UserDeviceSync o = new UserDeviceSync("1", "1", "1", System.currentTimeMillis());
		fortisDAO.attachDirty(o);
		o.setMacAddress("2");
		fortisDAO.attachDirty(o);
		o.setMacAddress("3");
		fortisDAO.attachDirty(o);
		o.setMacAddress("4");
		fortisDAO.attachDirty(o);
		System.out.println("== LINE ==");
	}

	@Override
	@Transactional
	public void test5() {
		for (int i = 3; i <= 10; i++) {
			String value = String.valueOf(i);
			fortisDAO.attachDirty(
					new UserDeviceSync(value, value + "_update", value + "update", System.currentTimeMillis()));
		}
	}
	
	@Override
	public void test4() {
		for (int i = 1; i <= 5; i++) {
			String value = String.valueOf(i);
			fortisDAO.attachDirty(new UserDeviceSync(value, value, value, System.currentTimeMillis()));
			if (i == 3)
				throw new RuntimeException();
		}
	}

	@Override
	protected Map<String, Object> toMap(Object obj, boolean isFull) {
		UserDeviceSync o = (UserDeviceSync) obj;

		Map<String, Object> m = new HashMap<String, Object>();

		if (isFull) {
			m.put("userDevice", o.getUserDevice());
			m.put("userDeviceGroup", o.getUserDeviceGroup());
			m.put("macAddress", o.getMacAddress());
			m.put("test", o.getTest());
		} else {
			// select
			m.put("userDevice", o.getUserDevice());
			m.put("userDeviceGroup", o.getUserDeviceGroup());
			m.put("macAddress", o.getMacAddress());
			m.put("test", o.getTest());
		}

		return m;
	}

	@Override
	protected Class getEntityClass() {
		return UserDeviceSync.class;
	}

	@Override
	public void validateCreate(Object obj) {
		
	}

	@Override
	public void validateUpdate(Object obj) {
		
	}

	@Override
	public Map<String, Object> create(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> update(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> delete(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void test10() {
		// TODO Auto-generated method stub
		
	}

}
