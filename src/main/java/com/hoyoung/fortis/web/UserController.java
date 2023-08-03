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
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import com.google.gson.Gson;
import com.hoyoung.fortis.command.SingleSideOnCommand;
import com.hoyoung.fortis.command.UserDeviceCommand;
import com.hoyoung.fortis.dao.SysSetting;
import com.hoyoung.fortis.python.PythonResponse;
import com.hoyoung.fortis.services.RestTemplateService;
import com.hoyoung.fortis.services.SysSettingService;
import com.hoyoung.fortis.services.UserDeviceLogService;
import com.hoyoung.fortis.services.UserDeviceService;
import org.springframework.http.HttpHeaders;

///////////////////
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
import java.security.NoSuchAlgorithmException;
import java.security.KeyManagementException;


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
			response.sendRedirect("/guest.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody ModelAndView login(ModelMap model, HttpServletRequest request, HttpServletResponse response, @RequestBody UserDeviceCommand cmd) throws NoSuchAlgorithmException, KeyManagementException, Exception{
		

		
		// 忽略 SSL 驗證
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[]{new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {}

            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {}

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        }}, new java.security.SecureRandom());

        // 創建自定義的 HttpRequestFactory，設置忽略 SSL 驗證
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(
                HttpClients.custom()
                        .setSslcontext(sslContext)
                        .build()
        );

        // 使用自定義的 HttpRequestFactory 創建 RestTemplate
        RestTemplate restTemplate = new RestTemplate(requestFactory);
		
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        
        // 設定 JSON 數據
        String jsonBody = "{\"userId\":\"01101\",\"password\":\"ncut01101\",\"remember\":\"true\",\"systemKey\":\"3294dde9518e4fa8b4050ba489f673a1\"}";

        // 創建 HttpEntity，將 JSON 數據和頭部結合
        HttpEntity<String> req = new HttpEntity<>(jsonBody, headers);
        
        // 發送 POST 請求，並取得回應
        String url = "https://ncutuni.ncut.edu.tw/api/login";
        ResponseEntity<String> res = restTemplate.postForEntity(url, req, String.class);
        
        // 處理回應
        if (res.getStatusCode().is2xxSuccessful()) {
            String responseBody = res.getBody();
            System.out.println("Response: " + responseBody);
            
            HttpSession session = request.getSession();
            
            SingleSideOnCommand cd = new SingleSideOnCommand();
            cd.setCn("01101");
    		session.setAttribute("ssologin", cd);
    		
    		response.sendRedirect("/applypage.html");
            
            
        } else {
            System.err.println("POST request failed with status code: " + response);
        }


		Map<String, Object> map = new HashMap<String, Object>();



		return getSuccessModelAndView(model, map);


	}



	@RequestMapping(value = "/initial", method = RequestMethod.GET)
	public @ResponseBody ModelAndView initial(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {

		SingleSideOnCommand ssoCmd = (SingleSideOnCommand) request.getSession().getAttribute("ssologin");
		if (ssoCmd == null) {
			
			return getFailureModelAndView(model, "尚未完成登入驗證，請從 Login 登入。");
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
		try {
			userDeviceService.validateCreate(cmd);
		} catch(Exception e) {
			return getFailureModelAndView(model, e.getMessage());
		}

		// 新增 Fortinet : User Device and Group
		try {
			PythonResponse r1 = restTemplateService.editConfigUserDevice(cmd.getDeviceName(), cmd.getMacAddress());
			// 檢查回傳的資料，使否出現網路卡號存在失敗
			//if (restTemplateService.validErrorCode(r1, -15) == false) {
			//	return getFailureModelAndView(model, "該網卡網路設備已經存在，新增失敗。 [Return code -15]");
			//}
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
		userDeviceLogService.saveUserDeviceLog("CREATE", cmd.getApplicantId(), cmd.getApplicantName(), cmd.getDeviceName());


		return getSuccessModelAndView(model, map);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody ModelAndView update(ModelMap model, HttpServletRequest request, @RequestBody UserDeviceCommand cmd) {

		// 驗證 - 網卡異動檢核網卡是否重複。
		try {
			userDeviceService.validateUpdate(cmd);
		} catch(Exception e) {
			return getFailureModelAndView(model, e.getMessage());
		}

		try {
			PythonResponse r1 = restTemplateService.editConfigUserDevice(cmd.getDeviceName(), cmd.getMacAddress());
			// 檢查回傳的資料，使否出現網路卡號存在失敗
			//if (restTemplateService.validErrorCode(r1, -15) == false) {
			//	return getFailureModelAndView(model, "該網卡網路設備已經存在，新增失敗。 [Return code -15]");
			//}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("連線設備執行指令失敗!! ", e);
			return getFailureModelAndView(model, "連線設備執行指令失敗!! ");
		}

		// 更新人員訊息
		Map map = userDeviceService.update(cmd);
		
		//Log
		userDeviceLogService.saveUserDeviceLog("UPDATE", cmd.getUpdUid(), cmd.getUpdName(), cmd.getDeviceName());

		return getSuccessModelAndView(model, map);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public @ResponseBody ModelAndView delete(ModelMap model, HttpServletRequest request) {
		String logUid = request.getParameter("logUid");
		String logName = request.getParameter("logName");
		String deviceName = request.getParameter("deviceName");
		String deviceGroup = (String) userDeviceService.fetchById(deviceName).get("deviceGroup");

		System.out.println(logUid+" "+logName);
		
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
		userDeviceLogService.saveUserDeviceLog("DELETE", logUid, logName, deviceName);
		
		Map map = userDeviceService.delete(deviceName);
		
		return getSuccessModelAndView(model, map);
	}

}
