package com.zakaria.deals;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.zakaria.deals.deals.entity.DealEntity;

class DealEntityTest {

	DealEntity dealEntity;

	@BeforeEach
	void setUp() throws Exception {
		dealEntity = new DealEntity();
	}

	@Test
	@DisplayName("check get id")
	@Order(1)
	void get_id() {
		dealEntity.setId(1);
		assertEquals(1, dealEntity.getId());

	}

	@Test
	@DisplayName("check from currency code")
	@Order(2)
	void getFromCurrencyCode() {
		dealEntity.setFromCurrencyCode("JOD");
		assertTrue("JOD" == dealEntity.getFromCurrencyCode());

	}

	@Test
	@DisplayName("check to currency code")
	@Order(3)
	void getToCurrencyCode() {
		dealEntity.setToCurrencyCode("USD");
		assertNotEquals("JOD", dealEntity.getToCurrencyCode());

	}
	
	@Test
	@DisplayName("check object not null")
	@Order(4)
	void check_object_not_null() {
		
		assertNotNull(dealEntity);

	}
	
	@AfterEach
	void leave() throws Exception {
		dealEntity = null;
	}

}
