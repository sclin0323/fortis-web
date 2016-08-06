package com.hoyoung.fortis.web;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.hoyoung.fortis.command.SingleSideOnCommand;
import com.hoyoung.fortis.command.UserDeviceCommand;
import com.hoyoung.fortis.dao.SysSetting;
import com.hoyoung.fortis.python.PythonResponse;
import com.hoyoung.fortis.services.RestTemplateService;
import com.hoyoung.fortis.services.SysSettingService;
import com.hoyoung.fortis.services.UserDeviceLogService;
import com.hoyoung.fortis.services.UserDeviceService;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {
	final static Logger log = Logger.getLogger(User.class);

	@Autowired
	private UserDeviceService userDeviceService;

	@Autowired
	private RestTemplateService restTemplateService;

	@Autowired
	private SysSettingService sysSettingService;
	
	@Autowired
	private UserDeviceLogService userDeviceLogService;

	@RequestMapping(value = "/ssologin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void ssologin(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			SingleSideOnCommand cmd) {
		log.info("ssologin");
		log.info(cmd.getSessionId());

		HttpSession session = request.getSession();
		session.setAttribute("ssologin", cmd);

		try {
			response.sendRedirect("/apply.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/initial", method = RequestMethod.GET)
	public @ResponseBody ModelAndView initial(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		// Testing
		//SingleSideOnCommand ssoCmd = new SingleSideOnCommand();
		//ssoCmd.setCn("guest-0");
		//ssoCmd.setGivenName("NAME");
		//ssoCmd.setTitle("TITLE");
		
		SingleSideOnCommand ssoCmd = (SingleSideOnCommand) request.getSession().getAttribute("ssologin");
		if (ssoCmd == null) {
			
			return getFailureModelAndView(model, "尚未完成登入驗證，請從SSO e-Portal 登入連線。");
		}
		
		 
		Map<String, Object> sysSetting = sysSettingService.fetchById("SETTING001");
		if (sysSetting == null) {
			return getFailureModelAndView(model, "設定載入失敗!! 請初始化設定");
		}
		

		List datas = userDeviceService.fetchByApplicantId(ssoCmd.getCn());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deviceLimit", sysSetting.get("deviceLimit"));
		map.put("userDevices", datas);

		map.put("ssologin", request.getSession().getAttribute("ssologin"));
		map.put("applicantId", ssoCmd.getCn());
		map.put("applicantName", ssoCmd.getGivenName());
		map.put("applicantTitle", ssoCmd.getTitle());

		return getSuccessModelAndView(model, map);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody ModelAndView add(ModelMap model, HttpServletRequest request, @RequestBody UserDeviceCommand cmd) {

		// 建立 Device Name
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
		String text = date.format(formatter);
		cmd.setDeviceName(cmd.getApplicantId() + "-" + text);
		// 建立 Device Group
		cmd.setDeviceGroup(getDeviceGroupByRandom());

		// 驗證新增 User Device
		String validateMsg = userDeviceService.validateCreate(cmd);
		if (validateMsg != null) {
			return getFailureModelAndView(model, validateMsg);
		}

		// 新增 Fortinet : User Device and Group
		try {
			PythonResponse r1 = restTemplateService.editConfigUserDevice(cmd.getDeviceName(), cmd.getMacAddress());
			// 檢查回傳的資料，使否出現網路卡號存在失敗
			if (restTemplateService.validErrorCode(r1, -15) == false) {
				return getFailureModelAndView(model, "該網卡網路設備已經存在，新增失敗。 [Return code -15]");
			}
			restTemplateService.appendConfigUserDeviceGroups(cmd.getDeviceName(), cmd.getDeviceGroup());

			restTemplateService.reenableSystemInterface();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("連線設備執行指令失敗!! ", e);
			return getFailureModelAndView(model, "連線設備執行指令失敗!! ");
		}

		// 新增人員訊息
		Map map = userDeviceService.create(cmd);
		
		// Log
		SingleSideOnCommand ssoCmd = (SingleSideOnCommand) request.getSession().getAttribute("ssologin");
		userDeviceLogService.saveUserDeviceLog("CREATE", ssoCmd.getCn(), ssoCmd.getGivenName(), cmd.getDeviceName());


		return getSuccessModelAndView(model, map);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody ModelAndView update(ModelMap model, HttpServletRequest request, @RequestBody UserDeviceCommand cmd) {

		// 網卡異動檢核網卡是否重複。
		String validateMsg = userDeviceService.validateUpdate(cmd);
		if (validateMsg != null) {
			return getFailureModelAndView(model, validateMsg);
		}

		try {
			PythonResponse r1 = restTemplateService.editConfigUserDevice(cmd.getDeviceName(), cmd.getMacAddress());
			// 檢查回傳的資料，使否出現網路卡號存在失敗
			if (restTemplateService.validErrorCode(r1, -15) == false) {
				return getFailureModelAndView(model, "該網卡網路設備已經存在，新增失敗。 [Return code -15]");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("連線設備執行指令失敗!! ", e);
			return getFailureModelAndView(model, "連線設備執行指令失敗!! ");
		}

		// 更新人員訊息
		Map map = userDeviceService.update(cmd);
		
		//Log
		SingleSideOnCommand ssoCmd = (SingleSideOnCommand) request.getSession().getAttribute("ssologin");
		userDeviceLogService.saveUserDeviceLog("UPDATE", ssoCmd.getCn(), ssoCmd.getGivenName(), cmd.getDeviceName());

		return getSuccessModelAndView(model, map);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public @ResponseBody ModelAndView delete(ModelMap model, HttpServletRequest request) {
		String deviceName = request.getParameter("deviceName");
		String deviceGroup = (String) userDeviceService.fetchById(deviceName).get("deviceGroup");

		try {
			restTemplateService.unselectConfigUserDeviceGroups(deviceName, deviceGroup);
			restTemplateService.deleteConfigUserDevice(deviceName);
			restTemplateService.reenableSystemInterface();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("連線設備執行指令失敗!! ", e);
			return getFailureModelAndView(model, "連線設備執行指令失敗!! ");
		}

		//Log
		SingleSideOnCommand ssoCmd = (SingleSideOnCommand) request.getSession().getAttribute("ssologin");
		userDeviceLogService.saveUserDeviceLog("DELETE", ssoCmd.getCn(), ssoCmd.getGivenName(), deviceName);
		
		Map map = userDeviceService.delete(deviceName);
		
		return getSuccessModelAndView(model, map);
	}

}
