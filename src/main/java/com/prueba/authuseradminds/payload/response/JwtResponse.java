package com.prueba.authuseradminds.payload.response;



import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String realname;
	private String email;
	private Boolean active;


	public JwtResponse(String accessToken, Long id, String username, String realname, String email, Boolean active) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.realname = realname;
		this.email = email;
		this.active = active;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}



}
