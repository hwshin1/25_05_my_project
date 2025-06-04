package com.example.demo.repository;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KakaoRepository {
	public int doJoin(long id, String access_token, String refresh_token, String kakao_email, String kakao_nickname,
			String kakao_regDate);
}
