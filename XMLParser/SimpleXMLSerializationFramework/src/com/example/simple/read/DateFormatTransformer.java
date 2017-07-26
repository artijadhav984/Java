package com.example.simple.read;

import java.text.DateFormat;
import java.util.Date;

import org.simpleframework.xml.transform.Transform;

/**
 * Class: To handle date format transform
 *
 */
public class DateFormatTransformer implements Transform<Date> {
	
	private DateFormat df;
	
	public DateFormatTransformer() {
		
	}
	
	public DateFormatTransformer(DateFormat df) {
		this.df = df;
	}

	@Override
	public Date read(String dateValue) throws Exception {
		return df.parse(dateValue);
	}

	@Override
	public String write(Date date) throws Exception {
		return df.format(date);
	}

}
