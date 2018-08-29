package com.sadhna.machine.main;

import com.sadhna.bucket.Bucket;
import com.sadhna.coin.Coin;
import com.sadhna.item.Item;
import com.sadhna.machine.api.VendingMachine;
import com.sadhna.machine.factory.VendingMachineFactory;
import com.sadhna.machineImpl.VendingMachineImpl;

public class VendingMachineMain {

	
	private static VendingMachine vm;
	
	public static void main(String[] args) 
	{
		vm = VendingMachineFactory.createVendingMachine();
		// normally initialize with all item with 5 count and all coin also 5 in each
		System.out.println("initial state of investory of cash and item");
		
		long cokePrice = vm.selectItemAndGetPrice(Item.COKE);
		
		System.out.println("price of coke is "+cokePrice);
		
		vm.insertCoin(Coin.QUARTER);
		
		Bucket bucket = vm.collectItemAndChange();
		
		System.out.println("return bucket items : "+ bucket.getFirst());
		System.out.println("return bucket items : "+ bucket.getSecond());
		
		vm.selectItemAndGetPrice(Item.COKE);
		vm.insertCoin(Coin.QUARTER);
		//
		Bucket bucket1 = vm.collectItemAndChange();
		System.out.println("return bucket items : "+ bucket1.getFirst());
		System.out.println("return bucket items : " + bucket1.getSecond());

		((VendingMachineImpl)vm).printStats();
		
	}

}
