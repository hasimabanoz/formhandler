package com.acme.form.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.acme.form.dao.UserDao;
import com.acme.form.dao.UserDaoMin;
import com.acme.form.model.User;
import com.acme.form.model.UserMin;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	UserDao userDao;
	UserDaoMin userDaoMin;

	// MongoTemplate mongoTemplate;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setUserDaoMin(UserDaoMin userDaoMin) {
		this.userDaoMin = userDaoMin;
	}

	// @Autowired
	// public void setMongoTemplate(MongoTemplate mongoTemplate) {
	// this.mongoTemplate = mongoTemplate;
	// }

	@Override
	public User findById(Integer id) {
		userDaoMin.findById(id);
		return userDao.findById(id);
	}

	@Override
	public List<User> findAll() {
		logger.debug("FIND ALL");
		userDaoMin.findAll();
		return userDao.findAll();
	}

	@Override
	public void saveOrUpdate(User user) {

		if (findById(user.getId()) == null) {
			userDao.save(user);
			userDaoMin.save(user);
		} else {
			userDao.update(user);
			userDaoMin.update(user);
		}

	}

	@Override
	public void delete(int id) {
		userDao.delete(id);
		userDaoMin.delete(id);
	}

}