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
import com.hoyoung.fortis.command.GuestCommand;
import com.hoyoung.fortis.command.SingleSideOnCommand;
import com.hoyoung.fortis.command.UserDeviceCommand;
import com.hoyoung.fortis.dao.SysSetting;
import com.hoyoung.fortis.python.PythonResponse;
import com.hoyoung.fortis.services.GuestService;
import com.hoyoung.fortis.services.RestTemplateService;
import com.hoyoung.fortis.services.SysEmailService;
import com.hoyoung.fortis.services.SysSettingService;
import com.hoyoung.fortis.services.UserDeviceLogService;
import com.hoyoung.fortis.services.UserDeviceService;

@Controller
@RequestMapping(value = "/guest")
public class GuestController extends BaseController {
	final static Logger log = Logger.getLogger(User.class);

	@Autowired
	private GuestService guestService;
	
	@Autowired
	private SysEmailService sysEmailService;

	@Autowired
	private RestTemplateService restTemplateService;

	@Autowired
	private SysSettingService sysSettingService;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public @ResponseBody ModelAndView test(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		
		List dataList = guestService.fetchByApplicantId("test1");
		
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
			response.sendRedirect("/guestApply.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/initial", method = RequestMethod.GET)
	public @ResponseBody ModelAndView initial(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {

		SingleSideOnCommand ssoCmd = (SingleSideOnCommand) request.getSession().getAttribute("ssologin");
		//SingleSideOnCommand ssoCmd = new SingleSideOnCommand();
		//ssoCmd.setCn("test1");
		//ssoCmd.setGivenName("test name");
		//ssoCmd.setTitle("Title");
		if (ssoCmd == null) {
			
			return getFailureModelAndView(model, "尚未完成登入驗證，請從SSO e-Portal 登入連線。");
		}
		 
		Map<String, Object> sysSetting = sysSettingService.fetchById("SETTING001");
		if (sysSetting == null) {
			return getFailureModelAndView(model, "設定載入失敗!! 請初始化設定");
		}
		
		List datas = guestService.fetchByApplicantId(ssoCmd.getCn());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("guestLimit", sysSetting.get("guestLimit"));
		map.put("guests", datas);

		map.put("ssologin", request.getSession().getAttribute("ssologin"));
		map.put("applicantId", ssoCmd.getCn());
		map.put("applicantName", ssoCmd.getGivenName());
		map.put("applicantTitle", ssoCmd.getTitle());

		return getSuccessModelAndView(model, map);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody ModelAndView add(ModelMap model, HttpServletRequest request, @RequestBody GuestCommand cmd) {

		// 建立 guestId and guestPwd
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddSSS");
		String guestId = date.format(formatter);
		cmd.setGuestId(cmd.getApplicantId()+"-"+guestId);
		cmd.setGuestPwd(guestId);
		cmd.setEndDate(new Date());
		
		// 建立 Device Group
		cmd.setGuestGroup(getGuestGroupByRandom());
		
		// 驗證新增資料
				try {
					guestService.validateCreate(cmd);
				} catch(Exception e) {
					return getFailureModelAndView(model, e.getMessage());
				}
		
				// 新增 Fortinet : User Device and Group
				try {
					//PythonResponse pr = restTemplateService.editConfigUserLocal(cmd.getGuestId(), cmd.getGuestPwd());
					// 檢查回傳的資料，使否出現網路卡號存在失敗
					//if (restTemplateService.validErrorCode(pr, -15) == false) {
					//	return getFailureModelAndView(model, "該網卡網路設備已經存在，新增失敗。 [Return code -15]");
					//}
					//restTemplateService.appendConfigUserDeviceGroups(cmd.getDeviceName(), cmd.getDeviceGroup());

					//restTemplateService.reenableSystemInterface();
				} catch (Exception e) {
					e.printStackTrace();
					log.error("連線設備執行指令失敗!! ", e);
					return getFailureModelAndView(model, "連線設備執行指令失敗!! ");
				}
				
				// 新增 Guest Appoint
				Map map = guestService.create(cmd);
				
				// 新增 發送 Email
				sysEmailService.create(guestService.getSysEmailCommand(cmd));

				return getSuccessModelAndView(model, map);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public @ResponseBody ModelAndView delete(ModelMap model, HttpServletRequest request) {
		String logUid = request.getParameter("logUid");
		String logName = request.getParameter("logName");
		String guestId = request.getParameter("guestId");

		System.out.println(logUid+" "+logName);
		
		try {
			//restTemplateService.unselectConfigUserDeviceGroups(deviceName, deviceGroup);
			//restTemplateService.deleteConfigUserDevice(deviceName);
			//restTemplateService.reenableSystemInterface();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("連線設備執行指令失敗!! ", e);
			return getFailureModelAndView(model, "連線設備執行指令失敗!! ");
		}

		//Log
		//userDeviceLogService.saveUserDeviceLog("DELETE", logUid, logName, deviceName);
		
		Map map = guestService.delete(guestId);
		
		return getSuccessModelAndView(model, map);
	}

}
