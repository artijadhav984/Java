package com.example.dom.parse;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.example.model.Customer;

/**
 * Class: Document Object Model Customer XML file Reader
 * @author Arti
 *
 */
public class DOMCustomerReader {
	
	/**
	 * Method reads data from XML file and store it into Customer list using DOM 
	 * @param filename
	 * @param filter: XPath query
	 * @return list of Customer
	 * @throws XPathExpressionException
	 */
	public List<Customer> readDataFromXml(String filename, String filter) throws XPathExpressionException {
		
		List<Customer> data = new ArrayList<>();

		File xmlFile = new File(filename);
		Document doc = null;
		NodeList list;
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(xmlFile);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		
		if(filter != null && !filter.isEmpty()) {
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			XPathExpression expression = xpath.compile(filter);
			list = (NodeList) expression.evaluate(doc, XPathConstants.NODESET);
		}
		else
		{
			Element root = doc.getDocumentElement();
			list = root.getElementsByTagName("customer");

			// list = doc.getElementsByTagName("customer");
		}
		
		for (int i = 0; i < list.getLength(); i++) {
			Customer customer = new Customer();
			data.add(customer);
			
			Element custElement = (Element) list.item(i);
			
			customer.setId(Integer.parseInt(custElement.getAttribute(Customer.ID)));
			customer.setName(getTextFromElement(custElement, Customer.NAME));
			customer.setPhone(getTextFromElement(custElement, Customer.PHONE));
			customer.setAbout(getTextFromElement(custElement, Customer.ABOUT));
			customer.setAge(Integer.parseInt(getTextFromElement(custElement, Customer.AGE)));
			customer.setBalance(getTextFromElement(custElement, Customer.BALANCE));
			customer.setActive(getTextFromElement(custElement, Customer.ACTIVE));
			
			String joined = getTextFromElement(custElement, Customer.JOINED);
			DateFormat df = new SimpleDateFormat(Customer.XMLDATEFORMAT);
			try {
				customer.setJoined(df.parse(joined));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
		
		return data;
	}
	
	/**
	 * Method gets the text from child element
	 * @param parentElement
	 * @param childElementName
	 * @return Text content of child element
	 */
	private String getTextFromElement(Element parentElement, String childElementName) {
		Element node = (Element) parentElement.getElementsByTagName(childElementName).item(0);
		String content = node.getTextContent();
		return content;
	}
	
}
