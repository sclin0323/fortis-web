package com.hoyoung.fortis.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.hoyoung.fortis.dao.FortisDAO;
import com.hoyoung.fortis.dao.SysSetting;
import com.hoyoung.fortis.python.PythonResponse;

@Service
@PropertySource("classpath:config.properties")
public class RestTemplateServiceImpl implements RestTemplateService {
	final static Logger log = Logger.getLogger(RestTemplateServiceImpl.class);

	@Value("${fortis_wspy.urlPrefix}")
	public String urlPrefix;

	@Autowired(required = true)
	protected FortisDAO fortisDAO;

	RestTemplate restTemplate = new RestTemplate();

	public RestTemplateServiceImpl() {
		super();
	}

	// For other
	@Override
	public PythonResponse getSystemStatus() {
		String command = "get system status";
		return sendRequestToFortinet(command);
	}

	@Override
	public PythonResponse reenableSystemInterface() {
		String command = "conf vdom \n edit wireless-0 \n config system interface \n edit port34 \n set device-identification disable \n set device-identification enable \n end \n exit \n";
		return sendRequestToFortinet(command);
	}

	// For Config User Device
	@Override
	public PythonResponse editConfigUserDevice(String deviceName, String macAddress) {
		String command = "conf vdom \n edit wireless-0 \n config user device \n edit " + deviceName + " \n set mac "
				+ macAddress + " \n next \n end \n";

		return sendRequestToFortinet(command);
	}

	@Override
	public PythonResponse appendConfigUserDeviceGroups(String deviceName, String deviceGroup) {

		String command = "conf vdom \n edit wireless-0 \n config user device-group \n edit " + deviceGroup
				+ " \n append member " + deviceName + " \n next \n end \n end \n exit \n";

		return sendRequestToFortinet(command);
	}

	@Override
	public PythonResponse unselectConfigUserDeviceGroups(String deviceName, String deviceGroup) {
		String command = "conf vdom \n edit wireless-0 \n config user device-group \n edit " + deviceGroup
				+ " \n unselect member " + deviceName + " \n next \n end \n end \n exit \n";

		return sendRequestToFortinet(command);
	}

	@Override
	public PythonResponse deleteConfigUserDevice(String deviceName) {
		String command = "conf vdom \n edit wireless-0 \n config user device \n delete " + deviceName
				+ " \n end \n end \n exit";

		return sendRequestToFortinet(command);
	}
	
	// For Config User (Guest)
	@Override
	public PythonResponse editConfigUserLocal(String guestName, String password) {
		String command = "config vdom \n edit wireless-0 \n config user local \n edit "+guestName+" \n set type password \n set passwd "+password+" \n next \n end \n end \n exit \n";
		return sendRequestToFortinet(command);
	}
	
	@Override
	public PythonResponse appendConfigUserGroups(String guestName, String guestGroup) {
		String command = "config vdom \n edit wireless-0 \n config user group \n edit "+guestGroup+" \n append member "+guestName+" \n next \n end \n exit";
		return sendRequestToFortinet(command);
	}
	
	@Override
	public PythonResponse unselectConfigUserGroups(String guestName, String guestGroup) {
		String command = "config vdom \n edit wireless-0 \n config user group \n edit "+guestGroup+" \n unselect member "+guestName+" \n next \n end \n exit";

		return sendRequestToFortinet(command);
	}

	@Override
	public PythonResponse deleteConfigUserLocal(String guestName) {
		String command = "conf vdom \n edit wireless-0 \n config user local \n delete "+guestName+" \n end \n end \n exit";
		
		return sendRequestToFortinet(command);
	}

	//  Send Request to FortisPython
	private PythonResponse sendRequestToFortinet(String command) {
		SysSetting setting = (SysSetting) fortisDAO.findById(SysSetting.class, "SETTING001");

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlPrefix + "sendFortinetCommand/")
				.queryParam("hostname", setting.getHostname()).queryParam("port", setting.getPort())
				.queryParam("username", setting.getLoginName()).queryParam("password", setting.getPassword())
				.queryParam("command", command);

		HttpEntity<?> entity = new HttpEntity<>(headers);

		HttpEntity<PythonResponse> response = null;
		try {
			response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, PythonResponse.class);
		} catch (RestClientException e) {
			log.error("連線存取Fortinet設備狀態失敗!!", e);
			throw new RestClientException("連線存取Fortinet設備狀態失敗!!");
		}

		PythonResponse body = response.getBody();
		// 檢查回傳物件狀態
		if (body.isStatus() == false) {
			throw new RestClientException("執行失敗 \n\n" + body.getMessage());
		}

		// 檢查 Error Code
		if (checkCommandFail(body) == false) {
			Gson gson = new Gson();
			throw new RestClientException("執行失敗 \n\n" + gson.toJson(body.getData()));
		}

		return body;
	}

	public boolean checkCommandFail(PythonResponse response) {
		String command = "Command fail";

		for (String line : response.getData()) {
			if (line.contains(command) == true) {
				return false;
			}
		}

		return true;
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}


	

}
