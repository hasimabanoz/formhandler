package com.acme.form.dao;

import java.util.List;

import com.acme.form.model.UserMin;

public interface UserDaoMin {

	UserMin findById(String id);

	List<UserMin> findAll();

	void save(UserMin user);

	void update(UserMin user);

	void delete(String id);

}