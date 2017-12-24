package com.acme.form.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.acme.form.model.UserMin;

@Repository
public class UserDaoMinImpl implements UserDaoMin {

	private static final Logger logger = LogManager.getLogger(UserDaoMinImpl.class);

	MongoTemplate mongoTemplate;
	MongoOperations mongoOperation;

	@Autowired
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@PostConstruct
	public void initMongoOperations() {
		mongoOperation = (MongoOperations) mongoTemplate;
	}

	@Override
	public UserMin findById(String id) {
		logger.trace("FindById. Id: " + id);
		Query searchUserQuery = new Query(Criteria.where("id").is(id));
		UserMin found = mongoOperation.findOne(searchUserQuery, UserMin.class);
		logger.trace("User found: " + found);
		return found;
	}

	@Override
	public List<UserMin> findAll() {
		logger.trace("FindAll.");
		List<UserMin> users = mongoOperation.findAll(UserMin.class);
		logger.trace("User list size: " + users.size());
		return users;
	}

	@Override
	public void save(UserMin user) {
		logger.trace("Save. : " + user);
		UserMin userMin = new UserMin(user.getUsername(), user.getSurname(), user.getPhone());
		mongoOperation.save(userMin);
	}

	@Override
	public void update(UserMin user) {
		logger.trace("Update user. id:  " + user.getId());
		UserMin found = findById(user.getId());
		found.setUsername(user.getUsername());
		found.setSurname(user.getSurname());
		found.setPhone(user.getPhone());
		mongoOperation.save(found);
	}

	@Override
	public void delete(String id) {
		logger.trace("Delete user. id: " + id);
		Query searchUserQuery = new Query(Criteria.where("id").is(id));
		mongoOperation.remove(searchUserQuery, UserMin.class);
	}

}