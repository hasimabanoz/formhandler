package com.acme.form.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class UserMin {

	@Id
	private String id;
	String username;
	String surname;
	String phone;

	public UserMin() {
		super();
	}

	public UserMin(String username, String surname, String phone) {
		super();
		this.username = username;
		this.surname = surname;
		this.phone = phone;
	}

	public boolean isNew() {
		return (this.id == null);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", surname=" + surname + "]";
	}

}