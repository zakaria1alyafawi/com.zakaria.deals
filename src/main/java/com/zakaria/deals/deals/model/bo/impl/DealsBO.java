package com.zakaria.deals.deals.model.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zakaria.deals.deals.entity.DealEntity;
import com.zakaria.deals.deals.model.bo.IDealsBO;
import com.zakaria.deals.deals.model.dao.IDealsDAO;
import com.zakaria.deals.deals.pojo.base.BaseDealPojo;
import com.zakaria.deals.deals.pojo.getdeals.GetDealsPojo;


@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DealsBO implements IDealsBO{
	@Autowired
	private IDealsDAO dealsDAO;

	@Override
	public GetDealsPojo getAll() {
		return this.dealsDAO.getAll();
	}

	@Override
	public GetDealsPojo getBy(int id) {
		return this.dealsDAO.getBy(id);
	}

//	@Override
//	public BaseDealPojo addNewDeal(String fromCurrencyCode, String toCurrencyCode, Timestamp dealTime, double dealAmount) {
//		// business validation like valid currencyCode
//		
//		DealEntity newDeal = new DealEntity();
//		newDeal.setFromCurrencyCode(fromCurrencyCode);
//		newDeal.setToCurrencyCode(toCurrencyCode);
//		newDeal.setDealTime(dealTime);
//		newDeal.setDealAmount(dealAmount);
//		
//		return this.dealsDAO.addNewDeal(newDeal);
//	}
	
	@Override
	public BaseDealPojo addNewDeal(DealEntity newDeal) {
		// business validation like valid currencyCode
		
//		DealEntity newDeal = new DealEntity();
//		newDeal.setFromCurrencyCode(fromCurrencyCode);
//		newDeal.setToCurrencyCode(toCurrencyCode);
//		newDeal.setDealTime(dealTime);
//		newDeal.setDealAmount(dealAmount);
		
		return this.dealsDAO.addNewDeal(newDeal);
	}

	@Override
	public BaseDealPojo updateDeal(DealEntity updateDeal) {
		return this.dealsDAO.updateDeal(updateDeal);
	}

	@Override
	public BaseDealPojo deleteBy(Integer id) {
		return this.dealsDAO.deleteBy(id);
	}
}
