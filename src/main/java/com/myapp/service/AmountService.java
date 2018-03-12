package com.myapp.service;

import java.util.List;

import com.myapp.controller.AmountController;
import com.myapp.dao.AmountDao;

public class AmountService {
	
	AmountController controller = new AmountController();
	
	public List<AmountDao> getAmounts() {
		return controller.getAmounts();
	}
	
	
	public AmountService() {
		
	}

}
