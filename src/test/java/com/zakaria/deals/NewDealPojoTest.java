package com.zakaria.deals;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.zakaria.deals.deals.pojo.newdeal.NewDealPojo;

class NewDealPojoTest {

	NewDealPojo dealPojo;

	@BeforeEach
	void setUp() throws Exception {
		dealPojo = new NewDealPojo();
	}

	@Test
	@DisplayName("get deal")
	@Order(1)
	void test() {
		dealPojo.setLastInsertId(2);
		assertEquals(2, dealPojo.getLastInsertId());
	}

	@AfterEach
	void end() {
		dealPojo = null;

	}

}
