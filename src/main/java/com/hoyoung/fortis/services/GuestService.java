package com.hoyoung.fortis.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hoyoung.fortis.command.GuestCommand;
import com.hoyoung.fortis.command.SysEmailCommand;
import com.hoyoung.fortis.command.UserDeviceCommand;
import com.hoyoung.fortis.dao.Guest;
import com.hoyoung.fortis.dao.SysEmail;

public interface GuestService extends BaseService{

		// 查詢
		public List fetchByApplicantId(String applicantId);
	
		public List fetchBySearchWord(String searchWord,int page,int start,int limit);
		
		public long fetchCountBySearchWord(String searchWord, int page, int start, int limit);
		
		public SysEmailCommand getSysEmailCommand(GuestCommand cmd);
		
		//public List fetchByStatusAndStartDate(int status, Date date);

		// 檢查是否有異動網卡
		//public boolean isUpdateMacAddress(UserDeviceCommand cmd);
		
		// 檢查是否有異動使用者群組
		//public boolean isUpdateDeviceGroup(UserDeviceCommand cmd);
}
