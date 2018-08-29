package com.sadhna.coin;

/**
 * Coins supported by vending machine
 * @author SSadhna
 *
 */
public enum Coin {
	
	PENNY(1),
	NICKLE(5),
	DIME(10),
	QUARTER(25);
	
	private Coin(int denomination)
	{
		this.denomination = denomination;
	}
	
	private int denomination;

	public int getDenomination() {
		return denomination;
	}

	public void setDenomination(int denomination) {
		this.denomination = denomination;
	}
	
	
	
}
