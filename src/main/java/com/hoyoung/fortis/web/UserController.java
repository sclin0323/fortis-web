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

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.hoyoung.fortis.authorize.SSLUtil;
import com.hoyoung.fortis.command.CheckUserIdPassword;
import com.hoyoung.fortis.command.SingleSideOnCommand;
import com.hoyoung.fortis.command.UserDeviceCommand;
import com.hoyoung.fortis.dao.SysSetting;
import com.hoyoung.fortis.python.PythonResponse;
import com.hoyoung.fortis.services.RestTemplateService;
import com.hoyoung.fortis.services.SysSettingService;
import com.hoyoung.fortis.services.UserDeviceLogService;
import com.hoyoung.fortis.services.UserDeviceService;

import javax.net.ssl.*;
import java.security.*;
import java.security.cert.X509Certificate;

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

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public @ResponseBody ModelAndView test(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		List dataList = userDeviceService.fetchByApplicantId("00101");

		return getSuccessModelAndView(model, dataList, dataList.size());
	}

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

	@RequestMapping(value = "/initialByPassword", method = RequestMethod.POST)
	public @ResponseBody ModelAndView initialByPassword(ModelMap model, HttpServletRequest request,
			HttpServletResponse response, @RequestBody SingleSideOnCommand cmd) {

		Map<String, Object> sysSetting = sysSettingService.fetchById("SETTING001");
		if (sysSetting == null) {
			return getFailureModelAndView(model, "設定載入失敗!! 請初始化設定");
		}

		System.out.print(cmd.getCn());

		// 檢查帳號密碼是否正確 2020-05-01
		try {
			SSLUtil.turnOffSslChecking();
			RestTemplate template = new RestTemplate();
			String url = "https://ncutuni.ncut.edu.tw/api/login";
			Map<String, String> m = new HashMap<>();
			m.put("userId", cmd.getCn());
			m.put("password", cmd.getUserPassword());
			m.put("systemKey", "9b63789b27a147bfa9f4ce3882bd13f1");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Map<String, String>> req = new HttpEntity<>(m, headers);
			CheckUserIdPassword result = template.postForObject(url, req, CheckUserIdPassword.class);

			if (result.getSuccess() == false) {
				return getFailureModelAndView(model, "帳號密碼驗證失敗!! ");
			}

			// 解碼 token
			DecodedJWT jwt = JWT.decode(result.getToken());
			String applicantName = jwt.getClaim("name").asString();
			String applicantTitle = jwt.getClaim("departmentName").asString();
			
			
		
			List datas = userDeviceService.fetchByApplicantId(cmd.getCn());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("deviceLimit", sysSetting.get("deviceLimit"));
			map.put("userDevices", datas);

			// map.put("ssologin", request.getSession().getAttribute("ssologin"));
			map.put("applicantId", cmd.getCn());
			map.put("applicantName", applicantName);
			map.put("applicantTitle", applicantTitle);

			return getSuccessModelAndView(model, map);

		} catch (Exception e) {
			e.printStackTrace();
			return getFailureModelAndView(model, "檢查帳號密碼失敗EXCEPTION!! ");
		}

		// return getFailureModelAndView(model, "連線設備執行指令失敗!! ");
	}

	@RequestMapping(value = "/initial", method = RequestMethod.GET)
	public @ResponseBody ModelAndView initial(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {

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
	public @ResponseBody ModelAndView add(ModelMap model, HttpServletRequest request,
			@RequestBody UserDeviceCommand cmd) {

		// 建立 Device Name
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
		String text = date.format(formatter);
		cmd.setDeviceName(cmd.getApplicantId() + "-" + text);
		// 建立 Device Group
		cmd.setDeviceGroup(getDeviceGroupByRandom());

		// 驗證新增 User Device
		try {
			userDeviceService.validateCreate(cmd);
		} catch (Exception e) {
			return getFailureModelAndView(model, e.getMessage());
		}

		// 新增 Fortinet : User Device and Group
		try {
			restTemplateService.editConfigUserDevice(cmd.getDeviceName(), cmd.getMacAddress());
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
		userDeviceLogService.saveUserDeviceLog("CREATE", cmd.getApplicantId(), cmd.getApplicantName(),
				cmd.getDeviceName());

		return getSuccessModelAndView(model, map);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody ModelAndView update(ModelMap model, HttpServletRequest request,
			@RequestBody UserDeviceCommand cmd) {

		// 驗證 - 網卡異動檢核網卡是否重複。
		try {
			userDeviceService.validateUpdate(cmd);
		} catch (Exception e) {
			return getFailureModelAndView(model, e.getMessage());
		}

		try {
			restTemplateService.editConfigUserDevice(cmd.getDeviceName(), cmd.getMacAddress());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("連線設備執行指令失敗!! ", e);
			return getFailureModelAndView(model, "連線設備執行指令失敗!! ");
		}

		// 更新人員訊息
		Map map = userDeviceService.update(cmd);

		// Log
		userDeviceLogService.saveUserDeviceLog("UPDATE", cmd.getUpdUid(), cmd.getUpdName(), cmd.getDeviceName());

		return getSuccessModelAndView(model, map);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public @ResponseBody ModelAndView delete(ModelMap model, HttpServletRequest request) {
		String logUid = request.getParameter("logUid");
		String logName = request.getParameter("logName");
		String deviceName = request.getParameter("deviceName");
		String deviceGroup = (String) userDeviceService.fetchById(deviceName).get("deviceGroup");

		System.out.println(logUid + " " + logName);

		try {
			restTemplateService.unselectConfigUserDeviceGroups(deviceName, deviceGroup);
			restTemplateService.deleteConfigUserDevice(deviceName);
			restTemplateService.reenableSystemInterface();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("連線設備執行指令失敗!! ", e);
			return getFailureModelAndView(model, "連線設備執行指令失敗!! ");
		}

		// Log
		userDeviceLogService.saveUserDeviceLog("DELETE", logUid, logName, deviceName);

		Map map = userDeviceService.delete(deviceName);

		return getSuccessModelAndView(model, map);
	}

}
