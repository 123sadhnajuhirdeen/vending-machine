package com.sadhna.machine.api;

import java.util.List;

import com.sadhna.bucket.Bucket;
import com.sadhna.coin.Coin;
import com.sadhna.item.Item;

/**
 * Declare all high level functionality
 * @author SSadhna
 *
 */
public interface VendingMachine {
	
	public long selectItemAndGetPrice(Item item);
	
	public void insertCoin(Coin coin);
	
	public List<Coin> refund();
	
	public Bucket<Item, List<Coin>> collectItemAndChange();
	
	public void reset();
	
	

}
