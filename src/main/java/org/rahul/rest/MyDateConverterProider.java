package org.rahul.rest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Calendar;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

import sun.util.resources.cldr.aa.CalendarData_aa_ER;

@Provider
public class MyDateConverterProider implements ParamConverterProvider {

	@Override
	public <T> ParamConverter<T> getConverter(
			final Class<T> rawType, Type genericType, Annotation[] annotations) {
		// TODO Auto-generated method stub
	
		if(rawType.getName().equals(MyDate.class.getName()))
		{
			return new ParamConverter<T>()
					{
					Calendar c=Calendar.getInstance();
						@Override
						public T fromString(String value) {
							if(value.equalsIgnoreCase("tomorrow"))	
							{
								c.add(Calendar.DATE,1);
							}
							else if(value.equalsIgnoreCase("yesterday"))	
							{
								c.add(Calendar.DATE,-1);
								
							}
							MyDate date=new MyDate();
							date.setDate(c.get(Calendar.DATE));
							date.setMonth(c.get(Calendar.MONTH));
							date.setYear(c.get(Calendar.YEAR));
							System.out.println(date);
							return rawType.cast(date);
						}

						@Override
						public String toString(T m) {
							// TODO Auto-generated method stub
							return m.toString();
						}				
				
					};
		}
		
		return null;
	}

	

	

}
