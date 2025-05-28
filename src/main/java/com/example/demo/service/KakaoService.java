package com.example.demo.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.KakaoRepository;
import com.example.demo.vo.KakaoApi;
import com.example.demo.vo.KakaoToken;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class KakaoService {
	@Autowired
	private KakaoApi kakaoApi;
	
	@Autowired
	private KakaoToken kakaoToken;
	
	@Autowired
	private KakaoRepository kakaoRepository;
	
	public KakaoService(KakaoRepository kakaoRepository) {
		this.kakaoRepository = kakaoRepository;
	}
	
	public KakaoToken getAccessToken(String code) {
	    String reqUrl = "https://kauth.kakao.com/oauth/token";

	    try {
	        URL url = new URL(reqUrl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	        // 필수 헤더 설정
	        conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
	        conn.setDoOutput(true);

	        // 파라미터 설정
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
	        StringBuilder sb = new StringBuilder();

	        sb.append("grant_type=authorization_code");
	        sb.append("&client_id=").append(kakaoApi.getClient_id());
	        sb.append("&redirect_uri=").append(kakaoApi.getRedirect_uri());
	        sb.append("&code=").append(code);

	        bw.write(sb.toString());
	        bw.flush();

	        int responseCode = conn.getResponseCode();
	        log.info("[KakaoService.getAccessToken] responseCode = {}", responseCode);

	        BufferedReader br = new BufferedReader(new InputStreamReader(
	            responseCode >= 200 && responseCode < 300 ? conn.getInputStream() : conn.getErrorStream()
	        ));

	        StringBuilder responseSb = new StringBuilder();
	        String line;
	        while ((line = br.readLine()) != null) {
	            responseSb.append(line);
	        }

	        String result = responseSb.toString();
	        log.info("responseBody = {}", result);

	        JsonElement element = JsonParser.parseString(result);
	        JsonObject json = element.getAsJsonObject();
	        
	        kakaoToken.setToken_type(json.get("token_type").getAsString());
	        kakaoToken.setAccess_token(json.get("access_token").getAsString());
	        
	        if (json.has("id_token")) {
	            kakaoToken.setId_token(json.get("id_token").getAsString());
	        }
	        
	        kakaoToken.setExpires_in(json.get("expires_in").getAsInt());
	        kakaoToken.setRefresh_token(json.get("refresh_token").getAsString());
	        kakaoToken.setRefresh_token_expires_in(json.get("refresh_token_expires_in").getAsInt());
	        
	        if (json.has("scope")) {
	            kakaoToken.setScope(json.get("scope").getAsString());
	        }
	        
	        br.close();
	        bw.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return kakaoToken;
	}

	public Map<String, Object> getUserInfo(String accessToken) {
		String reqUrl = "https://kapi.kakao.com/v2/user/me";
		HashMap<String, Object> userInfo = new HashMap<>();

		try{
	        URL url = new URL(reqUrl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
	        conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

	        int responseCode = conn.getResponseCode();
	        log.info("[KakaoService.getUserInfo] responseCode : {}",  responseCode);

	        BufferedReader br;
	        if (responseCode >= 200 && responseCode <= 300) {
	            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }

	        String line = "";
	        StringBuilder responseSb = new StringBuilder();
	        while((line = br.readLine()) != null){
	            responseSb.append(line);
	        }
	        String result = responseSb.toString();
	        log.info("responseBody = {}", result);

	        JsonElement element = JsonParser.parseString(result);

	        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
	        JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

	        String nickname = properties.getAsJsonObject().get("nickname").getAsString();
	        String email = kakaoAccount.getAsJsonObject().get("email").getAsString();

	        userInfo.put("nickname", nickname);
	        userInfo.put("email", email);
	        
	        kakaoRepository.getUserInfo(email, nickname);

	        br.close();
	    }catch (Exception e){
	        e.printStackTrace();
	    }
	    return userInfo;
	}
}
