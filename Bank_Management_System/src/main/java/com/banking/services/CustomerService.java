package com.banking.services;

import java.util.List;

import com.banking.entities.Customer;
import com.banking.entities.Transactions;

public interface CustomerService {

	List<Customer> getAllCustomer();
	Customer createCustomer(Customer cust);
	Customer withdraw(String customerId, double amount);
	Customer deposit(String customerId, double amount);
	String transfer(String fromCustId, String toCustId,double amount);
	List<Transactions> getAllTransactions();
	List<Transactions> getTransactionByCustomerId(Long id);
}
