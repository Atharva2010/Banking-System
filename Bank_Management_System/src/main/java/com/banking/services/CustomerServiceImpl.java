package com.banking.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.entities.Customer;
import com.banking.entities.TransactionType;
import com.banking.entities.Transactions;
import com.banking.repositories.CustomerRepository;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{

	private final CustomerRepository customerRepository;
	
	private final TransactionService transactionService;
	
	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository, TransactionService transactionService) {
		super();
		this.customerRepository = customerRepository;
		this.transactionService = transactionService;
	}

	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Override
	public Customer createCustomer(Customer cust) {
		
		
		return customerRepository.save(cust);
	}

	@Override
	public Customer withdraw(String customerId, double amount) {
		// TODO Auto-generated method stub
		Customer cust = customerRepository.findByCustomerId(customerId);
		if(cust==null) {
			throw new RuntimeException("invalid customer id");
		}
		if(cust.getBalance()<amount) {
			throw new RuntimeException("insufficient balance");
		}
		
		cust.setBalance(cust.getBalance()-amount);
		customerRepository.save(cust);
		
		Transactions transaction = new Transactions();
		transaction.setTimeStamp(LocalDateTime.now());
		transaction.setAmount(amount);
		transaction.setType(TransactionType.WITHDRAW);
		transaction.setCustomer(cust);
		transactionService.saveTransaction(transaction);
		
		return cust;
		
	}

	@Override
	public Customer deposit(String customerId, double amount) {
		// TODO Auto-generated method stub
		Customer cust = customerRepository.findByCustomerId(customerId);
		if(cust==null) {
			throw new RuntimeException("Invalid Customer id");
		}
		
		cust.setBalance(cust.getBalance()+amount);
		customerRepository.save(cust);
		
		Transactions transaction = new Transactions();
		
		transaction.setTimeStamp(LocalDateTime.now());
		transaction.setAmount(amount);
		transaction.setCustomer(cust);
		transaction.setType(TransactionType.DEPOSIT);
		transactionService.saveTransaction(transaction);
		
		return cust;
	}

	@Override
	public String transfer(String fromCustId, String toCustId, double amount) {
		// TODO Auto-generated method stub
		Customer fromCustomer = customerRepository.findByCustomerId(fromCustId);
		Customer toCustomer = customerRepository.findByCustomerId(toCustId);
		
		if(fromCustomer==null || toCustomer == null) {
			throw new RuntimeException("Invalid customer id");
		}
		
		if(fromCustomer.getBalance()<amount) {
			throw new RuntimeException("insufficient balance");
		}
		
		fromCustomer.setBalance(fromCustomer.getBalance()-amount);
		toCustomer.setBalance(toCustomer.getBalance()+amount);
		customerRepository.save(fromCustomer);
		customerRepository.save(toCustomer);
		
		Transactions fromTransaction = new Transactions();
		
		fromTransaction.setTimeStamp(LocalDateTime.now());
		fromTransaction.setAmount(amount);
		fromTransaction.setType(TransactionType.TRANSFERRED_FROM);
		fromTransaction.setCustomer(fromCustomer);
		transactionService.saveTransaction(fromTransaction);
		
		Transactions toTransaction = new Transactions();
		
		toTransaction.setTimeStamp(LocalDateTime.now());
		toTransaction.setAmount(amount);
		toTransaction.setCustomer(toCustomer);
		toTransaction.setType(TransactionType.TRANSFERRED_TO);
		transactionService.saveTransaction(toTransaction);
		
		return "Transferred Successfully";
	}

	@Override
	public List<Transactions> getAllTransactions() {
		// TODO Auto-generated method stub
		List<Transactions> transactions = transactionService.getAllTransactions();
		return transactions;
	}

	@Override
	public List<Transactions> getTransactionByCustomerId(Long id) {
		// TODO Auto-generated method stub
		List<Transactions> transactions = transactionService.getTransactionsByCustomerId(id);
		return transactions;
	}
	
}