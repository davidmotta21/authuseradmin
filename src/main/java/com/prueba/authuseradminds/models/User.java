package com.prueba.authuseradminds.models;

import java.util.HashSet;
import java.util.Set;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "USERS",
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "USERNAME"),
			@UniqueConstraint(columnNames = "EMAIL")
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Size(max = 20)
	@Column(name = "username")
	private String username;
	@NotBlank
	@Size(max = 100)
	@Column(name = "first_name")
	private String firstName;
	@NotBlank
	@Size(max = 100)
	@Column(name = "last_name")
	private String lastName;
	@NotBlank
	@Column(name = "date_birth")
	private Date dateBirth;
	@NotBlank
	@Size(max = 150)
	@Column(name = "address")
	private String address;
	@NotBlank
	@Size(max = 200)
	@Column(name = "token")
	private String token;
	@NotBlank
	@Size(max = 120)
	@Column(name = "password")
	private String password;
	@NotBlank
	@Size(max = 20)
	@Column(name = "mobile_phone")
	private String mobilePhone;
	@NotBlank
	@Size(max = 50)
	@Email
	@Column(name = "email")
	private String email;
	@Column(name = "active")
	private Boolean active;

	public User() {
	}
	public User(Long id, String username, String firstName, String lastName, Date dateBirth, String address, String password, String mobilePhone, String email, Boolean active) {
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateBirth = dateBirth;
		this.address = address;
		this.password = password;
		this.mobilePhone = mobilePhone;
		this.email = email;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
