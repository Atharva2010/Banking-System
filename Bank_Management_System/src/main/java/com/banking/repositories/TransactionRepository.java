package com.banking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.entities.Transactions;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {

	List<Transactions> findByCustomerId(Long id);
}
