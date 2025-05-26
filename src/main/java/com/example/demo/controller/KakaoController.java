package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.service.KakaoService;
import com.example.demo.vo.KakaoApi;

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
	public ResponseEntity<?> callBack(@RequestParam("code") String code) {
		System.out.println("인가코드 파라미터 출력: " + code);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
