package com.banking.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Customer extends BaseEntity{
	
	@NotBlank(message="name cannot be blank")
	@Column(name = "CustomerName")
	private String name;
	
	@Column(unique = true)
	private String customerId;
	
	@Column(name = "Balance")
	private double balance;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	private List<Transactions> transactions = new ArrayList<>();

	public Customer(@NotBlank(message = "name cannot be blank") String name, String customerId, double balance) {
		this.name = name;
		this.customerId = customerId;
		this.balance = balance;
	}
	
	

}
