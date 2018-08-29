package com.sadhna.machine.factory;

import com.sadhna.machine.api.VendingMachine;
import com.sadhna.machineImpl.VendingMachineImpl;

public class VendingMachineFactory 
{

	public static VendingMachine createVendingMachine()
	{
		return new VendingMachineImpl();
	}
}
