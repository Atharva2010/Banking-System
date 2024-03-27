package com.banking.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking.entities.Customer;
import com.banking.entities.Transactions;
import com.banking.services.CustomerService;
import com.banking.services.TransactionService;

@RestController
@RequestMapping("/bankings")
public class CustomerController {

	private final CustomerService customerService;
	
	private final TransactionService transactionService;

	@Autowired
	public CustomerController(CustomerService customerService, TransactionService transactionService) {
		this.customerService = customerService;
		this.transactionService = transactionService;
	}
	
	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomers(){
		List<Customer> customers=customerService.getAllCustomer();
		return new ResponseEntity<>(customers,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
		Customer createCustomer = customerService.createCustomer(customer);
		return new ResponseEntity<>(createCustomer,HttpStatus.CREATED);
	}
	
	@PutMapping("/withdraw")
	public ResponseEntity<Customer> withdraw(@RequestParam String customerId, @RequestParam double amount){
		Customer customer = customerService.withdraw(customerId, amount);
		return new ResponseEntity<>(customer,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/deposit")
	public ResponseEntity<Customer> deposit(@RequestParam String customerId, @RequestParam double amount){
		Customer customer = customerService.deposit(customerId, amount);
		return new ResponseEntity<>(customer,HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/transfer")
	public ResponseEntity<String> transfer(@RequestParam String fromCustomerId, @RequestParam String toCustomerId, @RequestParam double amount){
		String transferAmt = customerService.transfer(fromCustomerId, toCustomerId, amount);
		if(transferAmt!=null) {
			return new ResponseEntity<>(transferAmt,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/transactions")
	public ResponseEntity<List<Transactions>> getAllTransactions(){
		List<Transactions> transactions = customerService.getAllTransactions();
		return new ResponseEntity<>(transactions,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<Transactions>> getTransactionsByCustomerId(@PathVariable Long id){
		List<Transactions> transactions = customerService.getTransactionByCustomerId(id);
		return new ResponseEntity<>(transactions,HttpStatus.OK);

	}
	
	
	
	
}
