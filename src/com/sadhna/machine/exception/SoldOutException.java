package com.sadhna.machine.exception;

public class SoldOutException extends RuntimeException{

	private String message;
	
	public SoldOutException(String message) 
	{
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}
