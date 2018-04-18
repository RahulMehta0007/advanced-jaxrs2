package org.rahul.javabrains.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class InvocationDemo {

	public static void main(String[] args) {
	
		InvocationDemo obj=new InvocationDemo();
		Invocation invocation=obj.prepareRequestForMessageByYear(2018);
		Response response=invocation.invoke();
		System.out.println(response.getStatus());
		
		

	}

	
	public Invocation prepareRequestForMessageByYear(int year)
	{
		
		Client client=ClientBuilder.newClient();
		
		Invocation invocation = client.target("http://localhost:8082/advanced-jaxrs/webapi/").
				path("messages")
				.queryParam("year",year)
				.request(MediaType.APPLICATION_JSON).buildGet();
			return invocation;
		
		
	}
	
}
