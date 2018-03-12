package com.myapp.dao;



public class AmountDao {
	
	private String code2;

	public String getCode2() {
		return code2;
	}

	public void setCode2(String code2) {
		this.code2 = code2;
	}

	public AmountDao(String code2) {
		super();
		this.code2 = code2;
	}

	@Override
	public String toString() {
		return "AmountDao [code2=" + code2 + "]";
	}
	
	

}
