package com.example.jdom.create;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.jdom2.CDATA;
import org.jdom2.Document;
import org.jdom2.Element;

import com.example.model.Customer;

/**
 * Class: JDOM Customer XML file Creator
 * @author Arti
 *
 */
public class JDOMCustomerCreator {

	/**
	 * Method reads data from Customer list and create & store it into XML file using JDOM 
	 * @param data
	 * @return XML document
	 */
	public Document createXMLDocument(List<Customer> data) {
		
		Document doc = new Document();
		Element root = new Element("customers");
		doc.addContent(root);
		
		for (Customer customer : data) {
			Element custElement = new Element("customer");
			root.addContent(custElement);
			
			custElement.setAttribute(Customer.ID, Integer.toString(customer.getId()));
			
			addChildElement(custElement, Customer.NAME, customer.getName());
			addChildElement(custElement, Customer.PHONE, customer.getPhone());
			addChildElement(custElement, Customer.AGE, Integer.toString(customer.getAge()));
			
			Element about = addChildElement(custElement, Customer.ABOUT, "");
			CDATA cdata = new CDATA(customer.getAbout());
			about.addContent(cdata);
			
			addChildElement(custElement, Customer.BALANCE, customer.getBalance().toString());
			addChildElement(custElement, Customer.ACTIVE, Boolean.toString(customer.getActive()));
			
			DateFormat df = new SimpleDateFormat(Customer.XMLDATEFORMAT);
			addChildElement(custElement, Customer.JOINED, df.format(customer.getJoined()));
			
		}
		
		return doc;

	}
	
	/**
	 * Adds child element and set it's text
	 * @param parent element
	 * @param elementName to create child element
	 * @param textValue
	 * @return child element
	 */
	private Element addChildElement(Element parent, String elementName, String textValue) {
		Element child = new Element(elementName);
		child.setText(textValue);
		parent.addContent(child);
		return child;
	}

}
