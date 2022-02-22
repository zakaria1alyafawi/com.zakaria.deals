package com.zakaria.deals.deals.pojo.getdeals;

import java.util.List;

import com.zakaria.deals.deals.entity.DealEntity;
import com.zakaria.deals.deals.pojo.base.BaseDealPojo;

public class GetDealsPojo extends BaseDealPojo {

	private static final long serialVersionUID = -884186437342238633L;
	
	private List<DealEntity> deals;

	public List<DealEntity> getDeals() {
		return deals;
	}

	public void setDeals(List<DealEntity> deals) {
		this.deals = deals;
	}
}
