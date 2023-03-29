package com.prueba.authuseradminds.security.services;

import java.util.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.prueba.authuseradminds.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	@JsonIgnore
	private String password;

	private String firstName;

	private String lastName;

	private Date dateBirth;


	private String address;


	private String token;

	private String mobilePhone;

	private String email;

	private Boolean active;


	public UserDetailsImpl(Long id, String username, String password, String firstName, String lastName,
						   Date dateBirth, String address, String token, String mobilePhone, String email, Boolean active) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateBirth = dateBirth;
		this.address = address;
		this.token = token;
		this.mobilePhone = mobilePhone;
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
				user.getDateBirth(),
				user.getAddress(),
				user.getToken(),
				user.getMobilePhone(),
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

	public String getEmail() { return email; }

	public Boolean getActive() {
		return active;
	}

	public Boolean getActive(Boolean active) {
		return active;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
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
