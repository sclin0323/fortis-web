package com.hoyoung.fortis.web;

import java.beans.PropertyEditorSupport;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hoyoung.fortis.authorize.UserInfo;
import com.hoyoung.fortis.python.PythonResponse;
import com.hoyoung.fortis.services.SysUserService;

public abstract class BaseController {
	
	private static final Log log = LogFactory.getLog(BaseController.class);
	
	public static final int STATUS_FAILURE = -1;
	public static final int STATUS_LOGIN_INCORRECT = -5;
	public static final int STATUS_LOGIN_REQUIRED = -7;
	public static final int STATUS_LOGIN_SUCCESS = -8;
	public static final int STATUS_MAX_LOGIN_ATTEMPTS_EXCEEDED = -6;
	public static final int STATUS_SERVER_TIMEOUT = -100;
	public static final int STATUS_SUCCESS = 0;
	public static final int STATUS_TRANSPORT_ERROR = -90;
	public static final int STATUS_VALIDATION_ERROR = -4;
	
	@Autowired
	protected SysUserService sysUserService;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
	
	}
	
	// 回傳成功
	protected ModelAndView getSuccessModelAndView(ModelMap model, Map map) {
		
		model.put("data", map);
		model.put("status", STATUS_SUCCESS);
		ModelAndView mav = new ModelAndView("jsonView", model);
		return mav;
	}
	
	protected ModelAndView getSuccessModelAndView(ModelMap model, List list) {
		model.put("data", list);
		model.put("status", STATUS_SUCCESS);
		ModelAndView mav = new ModelAndView("jsonView", model);
		return mav;
	}
	
	protected ModelAndView getSuccessModelAndView(ModelMap model, List list, long total) {
		model.put("data", list);
		model.put("total", total);
		model.put("status", STATUS_SUCCESS);
		ModelAndView mav = new ModelAndView("jsonView", model);
		return mav;
	}
	
	// 回傳失敗
	protected ModelAndView getFailureModelAndView(ModelMap model, String message) {
		
		model.put("success", false);
		model.put("status", STATUS_FAILURE);
		model.put("message", message);
		ModelAndView mav = new ModelAndView("jsonView", model);
		return mav;
	}
	
	// 回傳尚未 LOGIN
	protected ModelAndView getLoginRequiredModelAndView(ModelMap model) {
		model.put("status", STATUS_LOGIN_REQUIRED);
		ModelAndView mav = new ModelAndView("jsonView", model);
		return mav;
	}
	
	// 登入帳號密碼錯誤
	protected ModelAndView getLoginFailureModelAndView(ModelMap model) {
		model.put("status", STATUS_LOGIN_INCORRECT);
		ModelAndView mav = new ModelAndView("jsonView", model);
		return mav;
	}
	
	// 登入帳號密碼正確
	protected ModelAndView getLoginSuccessModelAndView(ModelMap model) {
		model.put("status", STATUS_LOGIN_SUCCESS);
		ModelAndView mav = new ModelAndView("jsonView", model);
		return mav;
	}
	
	protected UserInfo getUserInfo(HttpServletRequest request){
		
		String userId = getLogonId(request);
		if(userId != null) {
			UserInfo userInfo = sysUserService.getUserInfoById(userId);
			return userInfo;
		}
		
		return null;
		
	}
	
	// Prettify PythonResponse 
	protected String getPrettifyPythonResponse(PythonResponse body) {
		String newLine = new String();
		for(String line : body.getData()){
			newLine = newLine + line;
		}
		return newLine;
	}

	protected String getLogonId(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			return principal.getName();
		}
		return null;
	}
	
	// 隨機 選取一個 Device Group
	protected String getDeviceGroupByRandom() {
		Random generator = new Random(); 
		int i = generator.nextInt(10);
		
		return "none-auth-group-"+i;
	}
	
	// 隨機 選取一個 Guest Group
		protected String getGuestGroupByRandom() {
			Random generator = new Random(); 
			int i = generator.nextInt(10);
			
			return "none-auth-group-"+i;
		}
	
}
