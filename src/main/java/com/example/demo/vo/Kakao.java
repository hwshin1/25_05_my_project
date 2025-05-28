package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Kakao {
	private int id;
	private String email;
	private String nickName;
	
	public Kakao(String email, String nickName) {
		this.email = email;
		this.nickName = nickName;
	}
}
