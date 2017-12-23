package com.acme.form.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.acme.form.model.UserMin;
import com.acme.form.web.UserController;

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
		logger.debug("UserDaoMinImpl find by id.");
		Query searchUserQuery = new Query(Criteria.where("id").is(id));
		UserMin found = mongoOperation.findOne(searchUserQuery, UserMin.class);
		logger.debug("User found: " + found);
		return found;
	}

	@Override
	public List<UserMin> findAll() {
		logger.debug("UserDaoMinImpl find all.");
		for (UserMin userMin : mongoOperation.findAll(UserMin.class)) {
			logger.debug(userMin);
		}
		return mongoOperation.findAll(UserMin.class);
	}

	@Override
	public void save(UserMin user) {
		UserMin userMin = new UserMin(user.getUsername(), user.getSurname(), user.getPhone());
		logger.debug("UserDaoMinImpl save: " + userMin);
		mongoOperation.save(userMin);
	}

	@Override
	public void update(UserMin user) {
		//TODO çoklu alan update
		logger.debug("Update user. id:  " + user.getId());
		Query searchUserQuery = new Query(Criteria.where("id").is(user.getId()));
		mongoOperation.updateFirst(searchUserQuery, Update.update("phone", user.getPhone()), UserMin.class);
	}

	@Override
	public void delete(String id) {
		Query searchUserQuery = new Query(Criteria.where("id").is(id));
		mongoOperation.remove(searchUserQuery, UserMin.class);
	}

}