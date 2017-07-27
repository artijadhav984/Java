package com.example.xmlpull.parse;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.example.model.Customer;

/**
 * Class:  XmlPullParser Customer XML file Reader
 * @author Arti
 *
 */
public class XmlPullParserCustomerReader {
	
	/**
	 * Method reads data from XML file and store it into Customer list using  XmlPullParser
	 * @param filename
	 * @param filter: XPath query
	 * @return list of Customer
	 * @throws XmlPullParserException 
	 * @throws IOException 
	 * @throws XPathExpressionException
	 */
	public List<Customer> readDataFromXml(String filename, String filter) throws XmlPullParserException, IOException {
		
		List<Customer> data = null;
		String currentTag = "";
		String currentText = "";
		Customer customer = null;
		
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		factory.setNamespaceAware(true);
		XmlPullParser xpp = factory.newPullParser();
		
		xpp.setInput(new FileReader(new File(filename)));
		int eventType = xpp.getEventType();
		
		while(eventType != XmlPullParser.END_DOCUMENT) {
			if(eventType == XmlPullParser.START_DOCUMENT) {
				data = new ArrayList<Customer>();
			} else if(eventType == XmlPullParser.START_TAG) {
				currentTag = xpp.getName();
				
				if (currentTag.equalsIgnoreCase("customer")) {
					customer = new Customer();
					
					String id = xpp.getAttributeValue("", Customer.ID);
					customer.setId(Integer.parseInt(id));
					data.add(customer);
				}
				
			} else if(eventType == XmlPullParser.END_TAG) {
				switch (currentTag) {
				case Customer.NAME:
					customer.setName(currentText);
					break;

				case Customer.PHONE:
					customer.setPhone(currentText);
					break;

				case Customer.ABOUT:
					customer.setAbout(currentText);
					break;

				case Customer.AGE:
					customer.setAge(Integer.parseInt(currentText));
					break;

				case Customer.ACTIVE:
					customer.setActive(Boolean.parseBoolean(currentText));
					break;

				case Customer.BALANCE:
					customer.setBalance(new BigDecimal(currentText));
					break;

				case Customer.JOINED:
					DateFormat df = new SimpleDateFormat(Customer.XMLDATEFORMAT);
					try {
						customer.setJoined(df.parse(currentText));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					break;

				default:
					break;
				}
				
				currentTag = "";
				
			} else if(eventType == XmlPullParser.TEXT) {
				currentText = xpp.getText();
			}
			
			eventType = xpp.next();
		}
		
		return data;
	}

}
