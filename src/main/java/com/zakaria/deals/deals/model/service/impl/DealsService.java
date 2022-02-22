package com.zakaria.deals.deals.model.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zakaria.deals.deals.entity.DealEntity;
import com.zakaria.deals.deals.model.bo.IDealsBO;
import com.zakaria.deals.deals.model.service.IDealsService;
import com.zakaria.deals.deals.pojo.base.BaseDealPojo;
import com.zakaria.deals.deals.pojo.getdeals.GetDealsPojo;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DealsService implements IDealsService {
	@Autowired
	private IDealsBO dealsBO;

	@Override
	public GetDealsPojo getAll() {
		return this.dealsBO.getAll();
	}

	@Override
	public GetDealsPojo getBy(int id) {
		return this.dealsBO.getBy(id);
	}

	@Override
	public BaseDealPojo addNewDeal(DealEntity newDeal) {
		return this.dealsBO.addNewDeal(newDeal);
	}

	@Override
	public BaseDealPojo updateDeal(DealEntity updateDeal) {
		return this.dealsBO.updateDeal(updateDeal);
	}

	@Override
	public BaseDealPojo deleteBy(Integer id) {
		return this.dealsBO.deleteBy(id);
	}
}
