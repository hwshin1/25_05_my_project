package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Kakao {
	private long kakao_id; 
	private String access_token;
	private String refresh_token;
	private String kakao_email;
	private String kakao_nickname;
	private String kakao_regDate;
}
