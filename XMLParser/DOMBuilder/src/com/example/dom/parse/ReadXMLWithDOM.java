package com.example.dom.parse;

import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import com.example.dataprovider.DataProvider;
import com.example.model.Customer;

/**
 * Class: Read the XML using Document Object Model
 * @author Arti
 *
 */
public class ReadXMLWithDOM {

	public static void main(String[] args) throws XPathExpressionException {
		
		String filename = DataProvider.DATADIR + "customers.xml";
		// filter can be empty string to get all records from file
		String filter = "//customer[age >= 50]";
		
		DOMCustomerReader reader = new DOMCustomerReader();
		List<Customer> data = reader.readDataFromXml(filename, filter);
		System.out.println("Total customers: " + data.size());
		
		for (Customer customer : data) {
			System.out.println(customer);
		}
	}

}
