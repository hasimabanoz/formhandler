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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.acme.form.model.User;
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
	public User findById(Integer id) {
		
		return null;
	}

	@Override
	public List<User> findAll() {
		logger.debug("UserDaoMinImpl find all.");
		for (UserMin userMin : mongoOperation.findAll(UserMin.class)) {
			logger.debug(userMin);
		}
		return null;
	}

	@Override
	public void save(User user) {
		UserMin userMin = new UserMin(user.getName(), user.getPassword(), Integer.toString(user.getNumber()));
		logger.debug("UserDaoMinImpl save: " + userMin);
		mongoOperation.save(userMin);
	}

	@Override
	public void update(User user) {

	}

	@Override
	public void delete(Integer id) {

	}

	private SqlParameterSource getSqlParameterByModel(User user) {

		// Unable to handle List<String> or Array
		// BeanPropertySqlParameterSource

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", user.getId());
		paramSource.addValue("name", user.getName());
		paramSource.addValue("email", user.getEmail());
		paramSource.addValue("address", user.getAddress());
		paramSource.addValue("password", user.getPassword());
		paramSource.addValue("newsletter", user.isNewsletter());

		// join String
		paramSource.addValue("framework", convertListToDelimitedString(user.getFramework()));
		paramSource.addValue("sex", user.getSex());
		paramSource.addValue("number", user.getNumber());
		paramSource.addValue("country", user.getCountry());
		paramSource.addValue("skill", convertListToDelimitedString(user.getSkill()));

		return paramSource;
	}

	private static final class UserMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setFramework(convertDelimitedStringToList(rs.getString("framework")));
			user.setAddress(rs.getString("address"));
			user.setCountry(rs.getString("country"));
			user.setNewsletter(rs.getBoolean("newsletter"));
			user.setNumber(rs.getInt("number"));
			user.setPassword(rs.getString("password"));
			user.setSex(rs.getString("sex"));
			user.setSkill(convertDelimitedStringToList(rs.getString("skill")));
			return user;
		}
	}

	private static List<String> convertDelimitedStringToList(String delimitedString) {

		List<String> result = new ArrayList<String>();

		if (!StringUtils.isEmpty(delimitedString)) {
			result = Arrays.asList(StringUtils.delimitedListToStringArray(delimitedString, ","));
		}
		return result;

	}

	private String convertListToDelimitedString(List<String> list) {

		String result = "";
		if (list != null) {
			result = StringUtils.arrayToCommaDelimitedString(list.toArray());
		}
		return result;

	}

}