package com.example.demo.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class KakaoApi {
	@Value("${kakao.client_id}")
	private String client_id;
	@Value("${kakao.redirect_uri}")
	private String redirect_uri;
}
