package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.KakaoService;
import com.example.demo.vo.Kakao;
import com.example.demo.vo.KakaoApi;
import com.example.demo.vo.KakaoInfo;
import com.example.demo.vo.KakaoToken;

@Controller
public class KakaoController {
	@Autowired
	private KakaoApi kakaoApi;
	
	@Autowired
	private KakaoService kakaoService;
	
	@RequestMapping("/usr/home/main")
	public String showTest(Model model) {
		String location = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=" + kakaoApi.getClient_id() + "&redirect_uri=" + kakaoApi.getRedirect_uri();
		
		model.addAttribute("location", location);
		
		return "usr/home/login";
	}
	
	// Redirect URI
	@GetMapping("/login/demoshw/oauth")
	@ResponseBody
	public Kakao callBack(@RequestParam String code) {
		System.out.println("인가코드 출력: " + code);
		
		// 토큰 받기
		KakaoToken accessToken = kakaoService.getAccessToken(code);
		
		// 사용자 정보 가져오기
		KakaoInfo userInfo = kakaoService.getUserInfo(accessToken.getAccess_token());
		
		System.out.println(accessToken);
		System.out.println(userInfo);
		
		// access_token만 가져오기
		String access_token = accessToken.getAccess_token();
		// refresh_token만 가져오기
		String refresh_token = accessToken.getRefresh_token();
		
		// 카카오 회원번호 가져오기
		long id = userInfo.getId();
		// 카카오 이메일 가져오기
		String kakao_email = userInfo.getKakao_account_email();
		// 카카오 닉네임 가져오기
		String kakao_nickname = userInfo.getProperties_nickname();
		// 카카오 연결 완료된 시각 가져오기
		String kakao_regDate = userInfo.getConnected_at();
		
		Kakao kakao = kakaoService.doJoin(id, access_token, refresh_token, kakao_email, kakao_nickname, kakao_regDate);
		
		return kakao;
	}
}
