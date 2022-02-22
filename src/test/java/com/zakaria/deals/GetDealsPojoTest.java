package com.zakaria.deals;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.zakaria.deals.deals.entity.DealEntity;
import com.zakaria.deals.deals.pojo.getdeals.GetDealsPojo;

class GetDealsPojoTest {
	GetDealsPojo dealsPojo;
	@BeforeEach
	void setUp() throws Exception {
		dealsPojo = new GetDealsPojo();
	}

	@Test
	@DisplayName("check the object")
	@Order(1)
	void test1() {
		assertNotNull(dealsPojo);
	}
	

	@Test
	@DisplayName("check deals")
	@Order(2)
	void test2() {
		
		List<DealEntity> deals = null;
		dealsPojo.setDeals(deals);
		assertNull(deals);
	}
	
	@Test
	@DisplayName("get the deals")
	@Order(3)
	void test3() {
		
		List<DealEntity> deals = null;
		dealsPojo.setDeals(deals);
		assertEquals(null,dealsPojo.getDeals());
	}
	
	@AfterEach
	void leave() throws Exception {
		dealsPojo = null;
	}
}
