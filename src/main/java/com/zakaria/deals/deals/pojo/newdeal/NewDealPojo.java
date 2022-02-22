package com.zakaria.deals.deals.pojo.newdeal;

import com.zakaria.deals.deals.pojo.base.BaseDealPojo;

public class NewDealPojo extends BaseDealPojo {

	private static final long serialVersionUID = 834414714259720490L;
	
	private int lastInsertId;

	public int getLastInsertId() {
		return lastInsertId;
	}

	public void setLastInsertId(int lastInsertId) {
		this.lastInsertId = lastInsertId;
	}
}
