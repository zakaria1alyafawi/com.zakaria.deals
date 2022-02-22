package com.zakaria.deals;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.zakaria.deals.deals.pojo.base.BaseDealPojo;

class BaseDealPojoTest {
	
	BaseDealPojo baseDealPojo;

	@BeforeEach
	@Order(1)
	void setUp() throws Exception {
		baseDealPojo = new BaseDealPojo();
	}

	@Test
	@DisplayName("check error code")
	@Order(2)
	void getErrorCode() {
		baseDealPojo.setErrorCode("ora123");
		assertEquals("ora123", baseDealPojo.getErrorCode());
	}
	
	@Test
	@DisplayName("check error Message")
	@Order(3)
	void GetErrorMessage() {
		baseDealPojo.setErrorMsg("there is an error in the connection");
		assertFalse("page not found" == baseDealPojo.getErrorMsg());
	}
	
	@Test
	@DisplayName("check the object")
	@Order(4)
	void check_object() {
		
		assertNotNull( baseDealPojo);
	}
	
	@Test
	@DisplayName("check the object1")
	@Order(5)
	void check_object1() {
		baseDealPojo = null;
		assertNull(baseDealPojo);
	}
	
	@AfterEach
	void leave() throws Exception {
		baseDealPojo = null;
	}


}
