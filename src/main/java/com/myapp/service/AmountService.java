package com.myapp.service;

import java.util.ArrayList;

import java.util.List;

import com.myapp.controller.AmountController;
import com.myapp.dao.AmountDao;

public class AmountService {
	
	private AmountController controller = new AmountController();
	
	public List<AmountDao> getAmounts() {
		return new ArrayList<AmountDao>(controller.getAmounts());
	}
	
	public AmountDao getFirstAmount() {
		AmountDao a= controller.getAmounts().get(0);
		return a;
	}
	
	public AmountService() {
		//AmountQuery q = new AmountQuery();
		//AmountDao a  = new AmountDao("975");
	}

}
