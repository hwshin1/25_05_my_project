package com.example.demo.vo;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class KakaoToken {
	// 토큰 타입
	private String token_type;
	// 사용자 엑세스 토큰 값
	private String access_token;
	// ID 토큰 값
	private String id_token;
	// 액세스 토큰과 ID 토큰의 만료 시간(초)
	private Integer expires_in;
	// 사용자 리프레시 토큰 값
	private String refresh_token;
	// 리프레시 토큰 만료 시간(초)
	private Integer refresh_token_expires_in;
	// 인증된 사용자의 정보 조회 권한 범위
	private String scope;
}
