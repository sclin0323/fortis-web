package com.hoyoung.fortis.services;

import java.util.LinkedHashMap;
import java.util.List;

import com.hoyoung.fortis.authorize.UserInfo;

public interface SysUserService extends BaseService{

	public List fetchBySearchWord(String searchWord,int page,int start,int limit);
	
	public long fetchCountBySearchWord(String searchWord, int page, int start, int limit);
	
	public UserInfo getUserInfoById(String userId);

	public String fetchPasswordById(String sysUserId);

}
