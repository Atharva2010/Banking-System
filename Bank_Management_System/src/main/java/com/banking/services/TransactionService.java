package com.banking.services;

import java.util.List;

import com.banking.entities.Transactions;

public interface TransactionService {

	void saveTransaction(Transactions transaction);
	
	List<Transactions> getAllTransactions();
	
	List<Transactions> getTransactionsByCustomerId(Long id);
	
	
}
