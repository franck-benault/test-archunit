package com.hascode.tutorial.comp1;

import java.util.logging.Logger;

import com.hascode.tutorial.comp2.OtherComponent;

/**
 * 
 * example coming from hascode example see
 * http://www.hascode.com/2017/07/assuring-architectural-rules-with-archunit/
 *
 */
@Deprecated
public class SomeComponent {

	OtherComponent comp = new OtherComponent();

	Logger log = Logger.getLogger(getClass().getName());

	public void foo() {
		log.info("logging with java.util logger...");
		comp.bar();
	}
}
