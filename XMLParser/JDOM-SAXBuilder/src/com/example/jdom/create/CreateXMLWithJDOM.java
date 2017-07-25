package com.example.jdom.create;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.example.dataprovider.DataProvider;
import com.example.model.Customer;

public class CreateXMLWithJDOM {

	public static void main(String[] args) throws IOException {

		List<Customer> data = DataProvider.getData(DataProvider.SMALL);

		JDOMCustomerCreator creator = new JDOMCustomerCreator();
		Document doc = creator.createXMLDocument(data);
		List<Element> list = doc.getRootElement().getChildren();
		System.out.println("retrieved " + list.size());
		
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		String xmlString = outputter.outputString(doc);
		System.out.println(xmlString);
		
		FileWriter writer = new FileWriter(new File("./output/customers.xml"));
		outputter.output(doc, writer);
		
	}

}
