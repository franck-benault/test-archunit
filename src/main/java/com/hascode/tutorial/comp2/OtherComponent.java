package com.hascode.tutorial.comp2;


import com.hascode.tutorial.comp1.SomeComponent;

/**
 * 
 * example coming from hascode example
 * see http://www.hascode.com/2017/07/assuring-architectural-rules-with-archunit/
 *
 */
public class OtherComponent {
	private SomeComponent someComponent;

	public OtherComponent() {
		someComponent.foo();
	}

	public void bar() {
	}
}
