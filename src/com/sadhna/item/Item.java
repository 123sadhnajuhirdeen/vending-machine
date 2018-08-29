package com.sadhna.item;

public enum Item {
	
	COKE("Coke", 21),
	PEPSI("Pepsi", 35),
	SODA("Soda", 45);
	
	private String name;
	private long price;
	
	private Item(String name,long price)
	{
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}
	
	
	

}
