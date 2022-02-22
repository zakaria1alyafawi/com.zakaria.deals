package com.zakaria.deals.deals.model.controller.rest;

import com.zakaria.deals.deals.entity.DealEntity;
import com.zakaria.deals.deals.pojo.base.BaseDealPojo;
import com.zakaria.deals.deals.pojo.getdeals.GetDealsPojo;

public interface IDealsRestController {
	public GetDealsPojo getAll();
	
	public GetDealsPojo getBy(Integer id);

	public BaseDealPojo addNewDeal(DealEntity newDeal);

	public BaseDealPojo updateDeal(DealEntity updateDeal);
	
	public BaseDealPojo deleteBy(Integer id);
	
	public String deals();
}
