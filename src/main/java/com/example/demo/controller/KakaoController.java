package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.KakaoService;
import com.example.demo.vo.KakaoApi;
import com.example.demo.vo.KakaoLogin;
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
	
	@GetMapping("/login/demoshw/oauth")
	@ResponseBody
	public KakaoLogin callBack(@RequestParam String code) {
		System.out.println("인가코드 출력: " + code);
		
		// 토큰 받기
		KakaoToken accessToken = kakaoService.getAccessToken(code);
		
		// 사용자 정보 가져오기
		KakaoLogin userInfo = kakaoService.getUserInfo(accessToken.getAccess_token());
		
		return userInfo;
	}
}
