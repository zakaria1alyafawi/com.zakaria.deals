package com.zakaria.deals.deals.model.controller.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zakaria.deals.deals.entity.DealEntity;
import com.zakaria.deals.deals.model.controller.rest.IDealsRestController;
import com.zakaria.deals.deals.model.service.IDealsService;
import com.zakaria.deals.deals.pojo.base.BaseDealPojo;
import com.zakaria.deals.deals.pojo.getdeals.GetDealsPojo;
import com.zakaria.deals.spring.contants.DealsSpringConstants;

@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
//@Controller
@RestController
@RequestMapping(DealsRestController.BASE_URL)
@CrossOrigin(origins = "*")
public class DealsRestController implements IDealsRestController {

	public static final String BASE_URL = DealsSpringConstants.APIs.Deals.REST_BASE_URL;

	@Autowired
	private IDealsService dealsService;

	@GetMapping(path = DealsSpringConstants.APIs.Deals.METHOD_GET_DEALS_ALL, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	@Override
//	@ResponseBody
	public GetDealsPojo getAll() {
		return this.dealsService.getAll();
	}

	@GetMapping(path = DealsSpringConstants.APIs.Deals.METHOD_GET_DEAL_BY
			+ "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	@Override
//	@ResponseBody
	public GetDealsPojo getBy(@PathVariable Integer id) {
		if (id == null) {
			return this.dealsService.getAll();
		} else {
			return this.dealsService.getBy(id);
		}
	}

	@GetMapping(path = DealsSpringConstants.APIs.Deals.METHOD_DELETE_DEAL
			+ "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	@Override
//	@ResponseBody
	public BaseDealPojo deleteBy(@PathVariable Integer id) {
		return this.dealsService.deleteBy(id);
	}

	@PostMapping(path = DealsSpringConstants.APIs.Deals.METHOD_ADD_DEAL, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	@Override
//	@ResponseBody
	public BaseDealPojo addNewDeal(@RequestBody DealEntity newDeal) {
		return this.dealsService.addNewDeal(newDeal);
	}

	@PostMapping(path = DealsSpringConstants.APIs.Deals.METHOD_EDIT_DEAL, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	@Override
//	@ResponseBody
	public BaseDealPojo updateDeal(@RequestBody DealEntity updateDeal) {
		return this.dealsService.updateDeal(updateDeal);
	}
	
	@Override
	@GetMapping("/deals")
	public String deals() {
		return "index";
	}
}
