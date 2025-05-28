package com.example.demo.repository;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KakaoRepository {
	public int getUserInfo(String email, String nickName);
}
