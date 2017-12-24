package com.acme.form.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.form.dao.UserDaoMin;
import com.acme.form.model.UserMin;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	UserDaoMin userDaoMin;

	@Autowired
	public void setUserDaoMin(UserDaoMin userDaoMin) {
		this.userDaoMin = userDaoMin;
	}

	@Override
	public UserMin findById(String id) {
		return userDaoMin.findById(id);
	}

	@Override
	public List<UserMin> findAll() {
		return userDaoMin.findAll();
	}

	@Override
	public void saveOrUpdate(UserMin user) {

		if (findById(user.getId()) == null) {
			userDaoMin.save(user);
		} else {
			userDaoMin.update(user);
		}

	}

	@Override
	public void delete(String id) {
		userDaoMin.delete(id);
	}

}