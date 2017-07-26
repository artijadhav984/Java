package com.example.simple.create;

//import java.io.File;
//import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.example.simple.dataprovider.DataProvider;
import com.example.simple.model.Customer;
import com.example.simple.model.Customers;

/**
 * Class: Create the XML file from data using Simple XML Serialization Framework
 *
 */
public class CreateXMLWithSimple {
	public static void main(String[] args) throws IOException {

		List<Customer> data = DataProvider.getData(DataProvider.SMALL);

		Customers customers = new Customers();
		customers.setCustomers(data);
		
		Serializer serializer = new Persister();
		StringWriter sw = new StringWriter();
//		File file = new File("./output/customers.xml");
//		FileWriter fw = new FileWriter(file);
		
		try {
			serializer.write(customers, sw);
			System.out.println(sw.toString());
//			serializer.write(customers, fw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
