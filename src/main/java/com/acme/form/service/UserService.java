package com.acme.form.service;

import java.util.List;

import com.acme.form.model.UserMin;

public interface UserService {

	UserMin findById(String id);

	List<UserMin> findAll();

	void saveOrUpdate(UserMin user);

	void delete(String id);

}