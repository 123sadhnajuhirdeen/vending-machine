package com.sadhna.machine.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sadhna.bucket.Bucket;
import com.sadhna.coin.Coin;
import com.sadhna.item.Item;
import com.sadhna.machine.api.VendingMachine;
import com.sadhna.machine.factory.VendingMachineFactory;

public class VendingMachineTest {

	private static VendingMachine vm;
	
	
	@BeforeClass
	public static void setUp() 
	{
		vm = VendingMachineFactory.createVendingMachine();
	}

	@AfterClass
	public static void tearDown()
	{
		vm = null;
	}
	
	@Test
	public void testBuyItemWithExactPrice()
	{
		long price = vm.selectItemAndGetPrice(Item.COKE);
		
		assertEquals(Item.COKE.getPrice(),price);
		
		vm.insertCoin(Coin.QUARTER);
		
		Bucket<Item, List<Coin>> bucket = vm.collectItemAndChange();
		Item item = bucket.getFirst();
		
		List<Coin> change = bucket.getSecond();
		
		assertEquals(Item.COKE, item);
		assertTrue(change.isEmpty());
	}
	
	@Test
	public void testBuyItemWithMorePrice()
	{
		long price = vm.selectItemAndGetPrice(Item.SODA);
		assertEquals(Item.SODA.getPrice(), price);
		
		vm.insertCoin(Coin.QUARTER);
		vm.insertCoin(Coin.QUARTER);
		
		Bucket<Item, List<Coin>> bucket = vm.collectItemAndChange();
		
		Item item = bucket.getFirst();
		List<Coin> change = bucket.getSecond();
		
		assertEquals(Item.SODA, item);
		assertTrue(!change.isEmpty());
		
		assertEquals(50- Item.SODA.getPrice(), getTotal(change));
		
	}
	
	 private long getTotal(List<Coin> change)
	 {
	        long total = 0;
	        for(Coin c : change){
	            total = total + c.getDenomination();
	        }
	        return total;
	    }
}
