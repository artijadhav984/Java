package com.example.sax.parse;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import com.example.model.Customer;

/**
 * Class: SAX (Simple API for XML) Customer XML file Reader
 * @author Arti
 *
 */
public class SAXCustomerHandler extends DefaultHandler {
	
	private List<Customer> data;
	private Customer customer;
	private String currentElement = "";
	private StringBuilder currentText;
	
	/**
	 * Method reads data from XML file and store it into Customer list using SAX 
	 * @param filename
	 * @return Customer list
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public List<Customer> readDataFromXml(String filename) throws IOException, ParserConfigurationException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		// if xml contains namespace 
//		factory.setNamespaceAware(true);
		
		try {
			SAXParser parser = factory.newSAXParser();
			parser.parse(filename, this);
		} catch (SAXException e) {
			System.out.println(e.getMessage());
		}
		
		return data;
	}
	
	/**
	 * Event handler for start document
	 * Initializes data list
	 */
	@Override
	public void startDocument() throws SAXException {
		data = new ArrayList<Customer>();
	}

	/**
	 * Event handler for end document
	 */
	@Override
	public void endDocument() throws SAXException {
		//System.out.println("End document");
	}
	
	/**
	 * Event handler for start element
	 * Creates customer, set id and add it into list
	 * Initializes currentText
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
		currentElement = qName;
		// if xml contains namespace 
//		currentElement = localName;
		
		switch (currentElement) {
		case "customers":
			break;

		case "customer":
			customer = new Customer();
			String id = attributes.getValue(Customer.ID);
			customer.setId(Integer.parseInt(id));
			data.add(customer);
			break;

		default:
			currentText = new StringBuilder();
			break;
		}
	}
	
	/**
	 * Event handler for end element
	 * Sets values of customer fields using content set in character event handler
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (!currentElement.equals("customers")
				&& !currentElement.equals("customer")) {

			String content = currentText.toString();

			switch (currentElement) {
			case Customer.NAME:
				customer.setName(content);
				break;

			case Customer.PHONE:
				customer.setPhone(content);
				break;

			case Customer.ABOUT:
				customer.setAbout(content);
				break;

			case Customer.AGE:
				customer.setAge(Integer.parseInt(content));
				break;

			case Customer.ACTIVE:
				customer.setActive(Boolean.parseBoolean(content));
				break;

			case Customer.BALANCE:
				customer.setBalance(new BigDecimal(content));
				break;

			case Customer.JOINED:
				DateFormat df = new SimpleDateFormat(Customer.XMLDATEFORMAT);
				try {
					customer.setJoined(df.parse(content));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				break;

			default:
				break;
			}
		}
		
		currentElement = "";
	}
	
	/**
	 * Event handler for characters
	 * Gets the content of element
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if(currentText != null) {
			currentText.append(ch, start, length);
		}
	}
	
	/**
	 * Event handler for warning
	 */
	@Override
	public void warning(SAXParseException e) throws SAXException {
		System.out.println("Warning !!!");
	}
	
	/**
	 * Event handler for error
	 */
	@Override
	public void error(SAXParseException e) throws SAXException {
		System.out.println("Error !!!");
	}
	
	/**
	 * Event handler for fatal error
	 */
	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		System.out.println("Fatal Error !!!");
	}

}
