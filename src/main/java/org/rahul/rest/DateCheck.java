package org.rahul.rest;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Produces(MediaType.TEXT_PLAIN)
@Path("messageBodyWriterCheck")
public class DateCheck {
	
	//To demonstrate MessageBodyWriter
	
	@GET

	public Date getDate()
	{
		
		return new Date();
		
	}
	
	
}
