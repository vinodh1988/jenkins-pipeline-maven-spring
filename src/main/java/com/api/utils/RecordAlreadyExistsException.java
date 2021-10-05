package com.api.utils;

public class RecordAlreadyExistsException extends Exception{
	private Integer sno;
	
	public RecordAlreadyExistsException(Integer sno) {
		// TODO Auto-generated constructor stub
	   this.sno=sno;	
	}
	@Override
	public String getMessage() {
		return "The record with this key "+sno+" already exists";
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getMessage();
	}
}
