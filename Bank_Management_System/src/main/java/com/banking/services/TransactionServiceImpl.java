package com.banking.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.entities.Transactions;
import com.banking.repositories.TransactionRepository;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

	private TransactionRepository transactionRepository;
	
	
	@Autowired
	public TransactionServiceImpl(TransactionRepository transactionRepository) {
		super();
		this.transactionRepository = transactionRepository;
	}

	@Override
	public void saveTransaction(Transactions transaction) {
		transactionRepository.save(transaction);
	}

	@Override
	public List<Transactions> getAllTransactions() {
		// TODO Auto-generated method stub
		return transactionRepository.findAll();
	}

	@Override
	public List<Transactions> getTransactionsByCustomerId(Long id) {
		// TODO Auto-generated method stub
		return transactionRepository.findByCustomerId(id);
	}

}
