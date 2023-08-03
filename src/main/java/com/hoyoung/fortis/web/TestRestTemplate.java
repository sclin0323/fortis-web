package com.hoyoung.fortis.web;

import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
import java.security.NoSuchAlgorithmException;
import java.security.KeyManagementException;

public class TestRestTemplate {

	public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException{
		
		
		
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
        
        // 設定請求參數
        MultiValueMap<String, String> m = new LinkedMultiValueMap<>();
        m.add("userId", "01101");
        m.add("password", "ncut01101");
        m.add("remember", "true");
        m.add("systemKey", "3294dde9518e4fa8b4050ba489f673a1");
        
        
        // 創建一個 HttpEntity，將請求參數和頭部結合
        HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(m, headers);

        // 發送 POST 請求，並取得回應
        String url = "https://ncutuni.ncut.edu.tw/api/login";
        ResponseEntity<String> response = restTemplate.postForEntity(url, req, String.class);
        
        // 處理回應
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            System.out.println("Response: " + responseBody);
        } else {
            //System.err.println("POST request failed with status code: " + response);
        }

	}

}
