package com.hoyoung.test;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.hoyoung.fortis.command.LoginApiCommand;

public class TestSSO {

	public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException {
		
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

        // 使用字串格式化將變數插入 JSON 數據
        String jsonBody = String.format("{\"UserId\":\"%s\",\"password\":\"%s\",\"systemKey\":\"3294dde9518e4fa8b4050ba489f673a1\"}", "01101", "Ncut01101");

        System.out.print(jsonBody);
        
        // 創建 HttpEntity，將 JSON 數據和頭部結合
        HttpEntity<String> req = new HttpEntity<>(jsonBody, headers);
        
        // 發送 POST 請求，並取得回應
        String url = "https://ncutuni.ncut.edu.tw/api/login";
        ResponseEntity<LoginApiCommand> res = restTemplate.postForEntity(url, req, LoginApiCommand.class);
        

	}

}
