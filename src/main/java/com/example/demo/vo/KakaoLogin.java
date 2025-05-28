package com.example.demo.vo;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class KakaoLogin {
	// 회원번호
	private long id;
	// 서비스에 연결 완료된 시각
	private String connected_at;
	// 사용자 프로퍼티에서 닉네임
	private String properties_nickname;
	// 카카오계정 정보에서 이메일
	private String kakao_account_email;
}
