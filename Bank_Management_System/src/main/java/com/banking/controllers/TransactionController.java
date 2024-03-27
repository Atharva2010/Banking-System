package com.banking.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.entities.Transactions;
import com.banking.services.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	private TransactionService transactionService;

	@Autowired
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
	@GetMapping
	public ResponseEntity<List<Transactions>> getAllTransactions(){
		List<Transactions> transactions = transactionService.getAllTransactions();
		return new ResponseEntity<>(transactions,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<Transactions>> getTransactionByCustomerId(@PathVariable Long id ){
		List<Transactions> transactions = transactionService.getTransactionsByCustomerId(id);
		return new ResponseEntity<>(transactions,HttpStatus.OK);
	}
	
	
}
