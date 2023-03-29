package com.prueba.authuseradminds.security.services;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.prueba.authuseradminds.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	@JsonIgnore
	private String password;

	private String realname;

	private String email;

	private Boolean active;


	public UserDetailsImpl(Long id, String username, String password, String realname, String email, Boolean active) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.realname = realname;
		this.email = email;
		this.active = active;
	}

	public static UserDetailsImpl build(User user) {
		return new UserDetailsImpl(
				user.getId(), 
				user.getUsername(),
				user.getPassword(),
				user.getFirstName(),
				user.getLastName(),
				user.getEmail(),
				user.getActive()
				);
	}

	public Long getId() {
		return id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public String getRealname() {return realname;}

	public String getEmail() { return email; }

	public Boolean getActive() {
		return active;
	}

	public Boolean getActive(Boolean active) {
		return active;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}
