package com.acme.form.dao;

import java.util.List;

import com.acme.form.model.User;

public interface UserDaoMin {

	User findById(Integer id);

	List<User> findAll();

	void save(User user);

	void update(User user);

	void delete(Integer id);

}