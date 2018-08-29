package com.sadhna.machineImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sadhna.bucket.Bucket;
import com.sadhna.coin.Coin;
import com.sadhna.inventory.Inventory;
import com.sadhna.item.Item;
import com.sadhna.machine.api.VendingMachine;
import com.sadhna.machine.exception.NotFullPaidException;
import com.sadhna.machine.exception.NotSufficientChangeException;
import com.sadhna.machine.exception.SoldOutException;

public class VendingMachineImpl implements VendingMachine{

	private Inventory<Coin> cashInventory = new Inventory<Coin>();
	private Inventory<Item> itemInventory = new Inventory<Item>();
	private long totalSales;
	private Item currentItem;
	private long currentBalance;
	
	
	public VendingMachineImpl() 
	{
		initialize();
	}
	
	private void initialize()
	{
		//initialize machine with 5 coins of each denomination
		// and 5 cans of each item
		for(Coin c: Coin.values())
		{
			cashInventory.put(c, 5);
		}
		
		for(Item i: Item.values())
		{
			itemInventory.put(i, 5);
		}
	}
	
	
	@Override
	public long selectItemAndGetPrice(Item item) 
	{
		 if(itemInventory.hasItem(item)) 
		 {
			 currentItem = item;
			 
			 return currentItem.getPrice();
		 }
		 
		 throw new SoldOutException("Sold out, please buy another item");
		
	}

	@Override
	public void insertCoin(Coin coin) {
		currentBalance = currentBalance + coin.getDenomination();
		cashInventory.add(coin);
		
	}

	@Override
	public List<Coin> refund() {
		List<Coin> refund = getChange(currentBalance);
		updateCashInventory(refund);
		currentBalance = 0;
		currentItem = null;
		
		return refund;
	}

	@Override
	public Bucket<Item, List<Coin>> collectItemAndChange() {
		
		Item item = collectItem();
		totalSales = totalSales + currentItem.getPrice();
		
		List<Coin> change = collectChange();
		
		return new Bucket<Item, List<Coin>>(item,change);
	}
	
	private Item collectItem() throws NotSufficientChangeException, NotFullPaidException
	{
		if(isFullPaid())
		{
			if(hasSufficientChange())
			{
				itemInventory.deduct(currentItem);
				return currentItem;
			}
			
			throw new NotSufficientChangeException("Not sufficient change in inventory");
		}
		long remainingBalance = currentItem.getPrice() - currentBalance;
		
		throw new NotFullPaidException("Price not full paid, remaining : ", remainingBalance);	
	}
	
	private List<Coin> collectChange()
	{
		long changeAmount = currentBalance - currentItem.getPrice();
		List<Coin> change = getChange(changeAmount);
		updateCashInventory(change);
		currentBalance = 0;
		currentItem = null;
		
		return change;
	}
	

	@Override
	public void reset() 
	{
		cashInventory.clear();
		itemInventory.clear();
		
		totalSales = 0;
		currentItem = null;
		currentBalance = 0;	
	}
	
	
	public void printStats()
	{
		System.out.println("Total Sales : "+ totalSales);
		System.out.println("Current Item Inventiry : "+ itemInventory);
		
		System.out.println("Coke item : "+ itemInventory.getQuantity(Item.COKE));
		System.out.println("Pepsi item : "+ itemInventory.getQuantity(Item.PEPSI));
		System.out.println("Soda item : "+ itemInventory.getQuantity(Item.SODA));	
		
		System.out.println("Current Cash Inventiry : "+ cashInventory);
		System.out.println("Dime : "+ cashInventory.getQuantity(Coin.DIME));
		System.out.println("nickle : "+ cashInventory.getQuantity(Coin.NICKLE));
		System.out.println("penny : "+ cashInventory.getQuantity(Coin.PENNY));
		System.out.println("quarter : "+ cashInventory.getQuantity(Coin.QUARTER));
		
	}
	
	private boolean hasSufficientChange()
	{
		return hasSufficientChangeForAmount(currentBalance - currentItem.getPrice());
	}
	
	private boolean hasSufficientChangeForAmount(long amount)
	{
		boolean hasChange = true;
		
		try 
		{
			getChange(amount);
		} 
		
		catch (NotSufficientChangeException  e) 
		{
			return hasChange = false;
		}
		
		return hasChange;
	}
	
	private void updateCashInventory(List<Coin> change)
	{
		for(Coin c: change)
		{
			cashInventory.deduct(c);
		}
	}
	
	public long getTotalSales()
	{
		return totalSales;
	}
	
	private List<Coin> getChange(long changeAmount) throws NotSufficientChangeException
	{
//        List<Coin> changes = Collections.EMPTY_LIST;
//        if(amount > 0)
//        {
//            changes = new ArrayList<Coin>();
//            long balance = amount;
//            while(balance > 0){
//                if(balance >= Coin.QUARTER.getDenomination() && cashInventory.hasItem(Coin.QUARTER))
//                {
//                    changes.add(Coin.QUARTER);
//                    balance = balance - Coin.QUARTER.getDenomination();
//                    continue;
//                   
//                }
//                else if(balance >= Coin.DIME.getDenomination()  && cashInventory.hasItem(Coin.DIME))
//                {
//                    changes.add(Coin.DIME);
//                    balance = balance - Coin.DIME.getDenomination();
//                    continue;
//                   
//                }
//                else if(balance >= Coin.NICKLE.getDenomination() && cashInventory.hasItem(Coin.NICKLE)) 
//                {
//                    changes.add(Coin.NICKLE);
//                    balance = balance - Coin.NICKLE.getDenomination();
//                    continue;
//                   
//                }
//                else if(balance >= Coin.PENNY.getDenomination() && cashInventory.hasItem(Coin.PENNY)) 
//                {
//                    changes.add(Coin.PENNY);
//                    balance = balance - Coin.PENNY.getDenomination();
//                    continue;
//                   
//                }
//                else
//                {
//                    throw new NotSufficientChangeException("NotSufficientChange, Please try another product");
//                }
//            }
//        }
//       
//        return changes;
		
		List changes = Collections.emptyList();
		int CHANGE_PENNY = 0;
		int CHANGE_NICKEL = 0;
		int CHANGE_DIME = 0;
		int CHANGE_QUARTER = 0;
		int CHANGE_DOLLAR = 0;
		if(changeAmount > 0)
		{
		changes = new ArrayList();
		long remaining = changeAmount;
				while (remaining > 0) {
				
				if (remaining >= Coin.QUARTER.getDenomination() && cashInventory.hasItem(Coin.QUARTER) && CHANGE_QUARTER < cashInventory.getQuantity(Coin.QUARTER) ) 
				{
						changes.add(Coin.QUARTER);
						remaining = remaining - Coin.QUARTER.getDenomination();
						CHANGE_QUARTER++;
						continue;
				} 
				else if (remaining >= Coin.DIME.getDenomination() && cashInventory.hasItem(Coin.DIME) && CHANGE_DIME < cashInventory.getQuantity(Coin.DIME)) 
				{
						changes.add(Coin.DIME);
						remaining = remaining - Coin.DIME.getDenomination();
						CHANGE_DIME++;
						continue;
				} 
				else if (remaining >= Coin.NICKLE.getDenomination() && cashInventory.hasItem(Coin.NICKLE) && CHANGE_NICKEL < cashInventory.getQuantity(Coin.NICKLE)) {
						changes.add(Coin.NICKLE);
						remaining = remaining - Coin.NICKLE.getDenomination();
						CHANGE_NICKEL++;
						continue;
				} 
				else if (remaining >= Coin.PENNY.getDenomination() && cashInventory.hasItem(Coin.PENNY) && CHANGE_PENNY < cashInventory.getQuantity(Coin.PENNY)) 
				{
						changes.add(Coin.PENNY);
						remaining = remaining - Coin.PENNY.getDenomination();
						CHANGE_PENNY++;
						continue;
				} 
				else
				{
					throw new NotSufficientChangeException(
					"Not Sufficient Change, Please try another product");
				}
				
			}

		}
		return changes;

    }
	
	private boolean isFullPaid() 
	{
        if(currentBalance >= currentItem.getPrice())
        {
            return true;
        }
        return false;
    }


}
