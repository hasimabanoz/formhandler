package com.acme;

import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.acme.form.web.UserController;

public class PhonePatternTest {
	
	private static final Logger logger = LogManager.getLogger(UserController.class);

	@Before
	public void beforeEachTest() {
		
	}

	@After
	public void afterEachTest() {
		
	}

	@Test
	public void testValid() {
		assertTrue(validatePhoneNumber("1234567890"));
		assertTrue(validatePhoneNumber("123-456-7890"));
		assertTrue(validatePhoneNumber("123-456-7890 x1234"));
		assertTrue(validatePhoneNumber("123-456-7890 ext1234"));
		assertTrue(validatePhoneNumber("(123)-456-7890"));
		assertTrue(validatePhoneNumber("123.456.7890"));
		assertTrue(validatePhoneNumber("123 456 7890"));
		
	}
	
	private static boolean validatePhoneNumber(String phoneNo) {
		// validate phone numbers of format "1234567890"
		if (phoneNo.matches("\\d{10}"))
			return true;
		// validating phone number with -, . or spaces
		else if (phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))
			return true;
		// validating phone number with extension length from 3 to 5
		else if (phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}"))
			return true;
		// validating phone number where area code is in braces ()
		else if (phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}"))
			return true;
		// return false if nothing matches the input
		else
			return false;
	}
	
	

}
