package org.rahul.javabrains.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.rahul.javabrains.messenger.model.ErrorMessage;


@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {


	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorMessage msg=new ErrorMessage(ex.getMessage(),404,"http://javabrains.org");
		return Response.status(Status.NOT_FOUND).entity(msg).build();
		
	}

}
