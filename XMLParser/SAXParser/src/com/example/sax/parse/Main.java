package com.example.sax.parse;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import com.example.dataprovider.DataProvider;
import com.example.model.Customer;

/**
 * Class: Read the XML using SAX (Simple API for XML)
 * @author Arti
 *
 */
public class Main {
	
	public static void main(String[] args) throws IOException, ParserConfigurationException {
		String filename = DataProvider.DATADIR + "customers.xml";
		SAXCustomerHandler handler = new SAXCustomerHandler();
		List<Customer> data = handler.readDataFromXml(filename);
		
		if (data != null) {
			System.out.println("Number of customers: " + data.size());

			for (Customer customer : data) {
				System.out.println(customer);
			}
		}
	}

}
