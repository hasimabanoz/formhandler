package com.acme.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component("phoneValidator")
public class PhoneNumberValidator {

	public PhoneNumberValidator() {

	}

	public boolean valid(final String phone) {
		// validate phone numbers of format "1234567890"
		if (phone.matches("\\d{10}"))
			return true;
		// validating phone number with -, . or spaces
		else if (phone.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))
			return true;
		// validating phone number with extension length from 3 to 5
		else if (phone.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}"))
			return true;
		// validating phone number where area code is in braces ()
		else if (phone.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}"))
			return true;
		// empty phone number
		else if (StringUtils.isEmpty(phone))
			return true;
		// return false if nothing matches the input
		else
			return false;
	}
}