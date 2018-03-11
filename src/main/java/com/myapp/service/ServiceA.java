package com.myapp.service;

import com.myapp.Foo;
import com.myapp.controller.ControllerA;
import com.myapp.dao.Amount;

public class ServiceA {
	
	private static ControllerA controller = new ControllerA();
	
	private static Foo foo = new Foo();
	
	private Amount a;
	
	
	public ServiceA() {
		a = new Amount();
	}

}
