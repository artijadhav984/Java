package com.example.dom.create;

import java.io.File;
import java.io.StringWriter;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import com.example.dataprovider.DataProvider;
import com.example.model.Customer;

public class CreateXMLWithDOM {

	public static void main(String[] args) throws ParserConfigurationException {

		List<Customer> data = DataProvider.getData(DataProvider.SMALL);
		DOMCustomerCreator creator = new DOMCustomerCreator();
		Document doc = creator.createXMLDoc(data);
		
		outputToString(doc);
		outputAsFile(doc, "./output/customers.xml");
		
	}
	
	/**
	 * Prints XML document content using Transformer
	 * @param doc: XML document
	 */
	private static void outputToString(Document doc) {
		
		DOMSource source = new DOMSource(doc);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		
		try {
			Transformer transformer = getTransformer();
			transformer.transform(source, result);
		} catch (TransformerFactoryConfigurationError | TransformerException e) {
			e.printStackTrace();
		}
		
		String xmlString = writer.toString();
		System.out.println(xmlString);
		
	}
	
	/**
	 * Gets Transformer object and set it's properties to indent(format) content
	 * @return transformer
	 * @throws TransformerFactoryConfigurationError
	 * @throws TransformerConfigurationException
	 */
	private static Transformer getTransformer() throws TransformerFactoryConfigurationError, TransformerConfigurationException {
		
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		return transformer;
		
	}
	
	/**
	 * Saves document content into a file using Transformer
	 * @param doc
	 * @param filename
	 */
	private static void outputAsFile(Document doc, String filename) {
		
		try {
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filename));
			getTransformer().transform(source, result);
		} catch (TransformerException | TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}
		
	}

}
