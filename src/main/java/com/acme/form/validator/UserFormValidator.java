package com.acme.form.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.acme.form.model.UserMin;
import com.acme.form.service.UserService;

//http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring
@Component
public class UserFormValidator implements Validator {

	@Autowired
	@Qualifier("phoneValidator")
	PhoneNumberValidator phoneValidator;

	@Autowired
	UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return UserMin.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		UserMin user = (UserMin) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty.userForm.username");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "NotEmpty.userForm.surname");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty.userForm.phone");
		
		if(!phoneValidator.valid(user.getPhone())){
			errors.rejectValue("phone", "Pattern.userForm.phone");
		}

	}

}