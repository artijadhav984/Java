package com.example.xmlpull.parse;

import java.io.IOException;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import com.example.dataprovider.DataProvider;
import com.example.model.Customer;

/**
 * Class: Read the XML using XmlPullParser
 * @author Arti
 *
 */
public class ReadXMLWithXmlPullParser {

	public static void main(String[] args) throws XmlPullParserException, IOException {
		
		XmlPullParserCustomerReader reader = new XmlPullParserCustomerReader();
		List<Customer> customers = reader.readDataFromXml(DataProvider.DATADIR + "customers.xml", "//customer[age < 35]");
		
		for (Customer customer : customers) {
			System.out.println(customer);
		}
	}

}
