package com.myapp.controller;

import java.util.List;

import com.myapp.dao.AmountDao;
import com.myapp.query.AmountQuery;


public class AmountController {

	private AmountQuery query = new AmountQuery();
	
	public List<AmountDao> getAmounts() {
		return query.getAmounts();
	}
}
