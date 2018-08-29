# vending-machine

This is simple vending machine design, developed using java to gain design and OOP concept,

Vending machine,
1. Accepts coins of 1,5,10,25 cents 
2. Allow user to select products coke(25),pepsi(35),soda(45)
3. Allow user to take refund by cancelling the request
4. Return selected product and remaining change if any
5. Allow reset operation for vending machine supplier.

Note:  ***vender machine should not accept a request if it does not have sufficient change to return***

****Java Vending machine classes, interfaces****

1.VendingMachine - define API, usually all high level functionality

2.VendingMachineImpl - sample implementation of vendingMachine

3.VendingMachineFactory - a factory class to create different kinds of vending machine

4.Item - java Enum to represent item served by vending machine

5.Inventory - to represent an inventory, used for creating case and item inventory inside vending machine

6.Coin - java enum to represent coins supported by vending machine

7.Bucket - parameterized class to hold two objects. It's kind of pair class

8.NotFullPaidException - an exception thrown by vending machine when a user tries to collect an item, without paying the full amount

9.NotSufficientChangeException - Vending machine throws an exception if it does not have sufficient change to complete the request

10.SoldOutException = vending machine throws this exception if the user request for a product which is sold out.
