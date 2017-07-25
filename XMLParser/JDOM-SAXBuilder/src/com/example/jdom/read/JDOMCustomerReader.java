package com.example.jdom.read;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

import com.example.model.Customer;

/**
 * Class: JDOM (SAXBuilder) Customer XML file Reader
 * @author Arti
 *
 */
public class JDOMCustomerReader {

	/**
	 * Method reads data from XML file and store it into Customer list using JDOM 
	 * @param filename
	 * @param filter: XPath query
	 * @return list of Customer
	 */
	public List<Customer> readDataFromXml(String filename, String filter) {
		
		List<Customer> data = new ArrayList<>();
		
		File file = new File(filename);
		SAXBuilder builder = new SAXBuilder();
		Document doc = null;
		
		try {
			doc = builder.build(file);
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
			return null;
		}

//		Element root = doc.getRootElement();
//		List<Element> custElements = root.getChildren("customer");
		
		XPathFactory xfactory = XPathFactory.instance();
		XPathExpression<Element> exp = xfactory.compile(filter, Filters.element());
		List<Element> custElements = exp.evaluate(doc);
		
		for (Element ce : custElements) {
			
			Customer customer = new Customer();
			data.add(customer);
			
//			String id = ce.getAttributeValue(Customer.ID);
			
			Attribute att = ce.getAttribute(Customer.ID);
			try {
				customer.setId(att.getIntValue());
			} catch (DataConversionException e1) {
				System.out.println("Error occurred while getting integer value of id attribute !!!");
			}

			customer.setName(ce.getChildText(Customer.NAME));
			customer.setPhone(ce.getChildText(Customer.PHONE));
			customer.setAbout(ce.getChildText(Customer.ABOUT));
			customer.setAge(Integer.parseInt(ce.getChildText(Customer.AGE)));
			customer.setBalance(ce.getChildText(Customer.BALANCE));
			customer.setActive(ce.getChildText(Customer.ACTIVE));
			
			DateFormat df = new SimpleDateFormat(Customer.XMLDATEFORMAT);
			try {
				customer.setJoined(df.parse(ce.getChildText(Customer.JOINED)));
			} catch (ParseException e) {
				System.out.println("Error occurred while getting joined date !!!");
			}
		}
		
		return data;
	}
}
