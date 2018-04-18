package org.rahul.javabrains.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;


import org.rahul.javabrains.messenger.model.ErrorMessage;

public class GenericExceptionMapper implements ExceptionMapper <Throwable>{


	@Override
	public Response toResponse(Throwable ex) {
		ErrorMessage msg=new ErrorMessage(ex.getMessage(),500,"http://javabrains.org");
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(msg).build();
	}
	
}
