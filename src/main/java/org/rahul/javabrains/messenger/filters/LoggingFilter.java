package org.rahul.javabrains.messenger.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
@Provider
public class LoggingFilter implements ContainerResponseFilter,ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext request) throws IOException {
		System.out.println("Request Filter :");
		System.out.println("Headers :"+request.getHeaders());
		
	}

	@Override
	public void filter(ContainerRequestContext arg0, ContainerResponseContext response) throws IOException {
		System.out.println("Response Filters ");
		System.out.println("Response Headers "+response.getHeaders());
		
	}

}
