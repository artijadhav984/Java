package com.example.jdom.read;

import java.util.List;

import com.example.dataprovider.DataProvider;
import com.example.model.Customer;

public class ReadXMLWithJDOM {

	/**
	 * Class: Read the XML using JDOM
	 * @author Arti
	 *
	 */
	public static void main(String[] args) {
		
		JDOMCustomerReader reader = new JDOMCustomerReader();
		List<Customer> data = reader.readDataFromXml(DataProvider.DATADIR + "customers.xml", "//customer[age >= 65]");
		
		System.out.println("Number of customers: " + data.size());
		
		for (Customer customer : data) {
			System.out.println(customer);
		}

	}

}
