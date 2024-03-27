package com.banking.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Transactions extends BaseEntity {

	private LocalDateTime timeStamp;
	
	
	private double amount;
	
	@Enumerated(EnumType.STRING)
	private TransactionType type;
	
	
	@ManyToOne
	@JoinColumn(name = "customer_Id")
	private Customer customer;
}
