package com.hoyoung.fortis.services;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.hoyoung.fortis.command.UserDeviceCommand;
import com.hoyoung.fortis.dao.UserDevice;

public interface GuestLogService {

	public Map<String, Object> saveGuestLog(String method, String logUid, String logName, String guestId);

	// 查詢
	public List fetchBySearchWord(String searchWord, int page, int start, int limit);

	public long fetchCountBySearchWord(String searchWord, int page, int start, int limit);

}
