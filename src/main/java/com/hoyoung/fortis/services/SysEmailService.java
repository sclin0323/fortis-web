package com.hoyoung.fortis.services;

import java.util.LinkedHashMap;
import java.util.List;

import com.hoyoung.fortis.authorize.UserInfo;

public interface SysEmailService {

	public boolean sendEmail(String email, String title, String message);
}
