package com.myapp;

import org.apache.log4j.Logger;

public class Foo {
	
	static Logger logger = Logger.getLogger(Foo.class);
	
	public Foo() {
		System.out.println("Foo created");
	}

}
