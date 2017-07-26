package com.example.simple.model;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * Class: Customers class with annotating POJO's properties
 * Defined for root element customers
 *
 */
@Root(name="customers")
public class Customers {
	
	// this is to avoid repeating customers element
	@ElementList(inline=true)
	private List<Customer> customers;

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
		
}
