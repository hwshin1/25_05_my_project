package com.example.demo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoToken {
	@JsonProperty("token_type")
	private String tokenType;
	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("id_token")
	private String idToken;
	@JsonProperty("expires_in")
	private int expiresIn;
	@JsonProperty("refresh_token")
	private String refreshToken;
	@JsonProperty("refresh_token_expires_in")
	private int refreshTokenExpiresIn;
	@JsonProperty("scope")
	private String scope;
}
