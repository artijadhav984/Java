package com.example.simple.read;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.transform.RegistryMatcher;

import com.example.simple.model.Customer;
import com.example.simple.model.Customers;

/**
 * Class: Read the XML using Simple XML Serialization Framework
 *
 */
public class ReadXMLWithSimple {

	public static void main(String[] args) throws Exception {
		
		// This is to read date in specific format
		DateFormat df = new SimpleDateFormat(Customer.XMLDATEFORMAT);
		DateFormatTransformer dTransformer = new DateFormatTransformer(df);
		RegistryMatcher regMatcher = new RegistryMatcher();
		regMatcher.bind(Date.class, dTransformer);
		
		// main code to read file
		File source = new File("./data/customers.xml");
		Serializer serializer = new Persister(regMatcher);
		Customers customers = serializer.read(Customers.class, source);
		List<Customer> data = customers.getCustomers();
		
		for (Customer customer : data) {
			System.out.println(customer);
		}
	}

}
