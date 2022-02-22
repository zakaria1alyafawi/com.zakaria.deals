package com.zakaria.deals.deals.model.service;

import com.zakaria.deals.deals.entity.DealEntity;
import com.zakaria.deals.deals.pojo.base.BaseDealPojo;
import com.zakaria.deals.deals.pojo.getdeals.GetDealsPojo;

public interface IDealsService {
	public GetDealsPojo getAll();

	public GetDealsPojo getBy(int id);

	public BaseDealPojo addNewDeal(DealEntity newDeal);
	
	public BaseDealPojo deleteBy(Integer id);

	public BaseDealPojo updateDeal(DealEntity updateDeal);
}
