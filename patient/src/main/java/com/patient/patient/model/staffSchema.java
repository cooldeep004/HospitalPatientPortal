package com.patient.patient.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "staffdata")
@NoArgsConstructor
@Getter
@Setter
public class staffSchema implements UserDetails {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String name;
	@NotNull
	@Size(min=3, max=10)
	private String user;
	@NotNull
	@Email
	private String email;
	@NotNull
	private String password;
	@NotNull
	private String staffType;
	@NotNull
	@Size(min=10, max=10,message="please enter valid contact number")
	private String contact;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStaffType() {
		return staffType;
	}

	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "staffSchema [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", staffType=" + staffType + ", contact=" + contact + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<String> li = new ArrayList<>();
		li.add("user");
		List<SimpleGrantedAuthority> auth = li.stream().map((role) -> new SimpleGrantedAuthority(role))
				.collect(Collectors.toList());
		return auth;
	}

	@Override
	public String getUsername() {
		return this.getUser();
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

}
