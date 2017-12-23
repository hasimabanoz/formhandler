package com.acme.form.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.acme.form.dao.UserDao;
import com.acme.form.model.User;
import com.acme.form.model.UserMin;

@Service("userService")
public class UserServiceImpl implements UserService {

	UserDao userDao;

	MongoTemplate mongoTemplate;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	// @Autowired
	// public void setMongoTemplate(MongoTemplate mongoTemplate) {
	// this.mongoTemplate = mongoTemplate;
	// }

	@Override
	public User findById(Integer id) {
		return userDao.findById(id);
	}

	@Override
	public List<User> findAll() {
		System.out.println("FIND ALL");
		// MongoOperations mongoOperation = (MongoOperations) mongoTemplate;
		// UserMin userMin = new UserMin("hasim", "pass", "1234");
		// save
		// mongoOperation.save(userMin);
		return userDao.findAll();
	}

	@Override
	public void saveOrUpdate(User user) {

		if (findById(user.getId()) == null) {
			userDao.save(user);
		} else {
			userDao.update(user);
		}

	}

	@Override
	public void delete(int id) {
		userDao.delete(id);
	}

}