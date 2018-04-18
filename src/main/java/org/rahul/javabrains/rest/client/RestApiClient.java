package org.rahul.javabrains.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.rahul.javabrains.messenger.model.Message;



public class RestApiClient {

	public static void main(String[] args) {
		
		Client client=ClientBuilder.newClient();
		
		WebTarget baseTarget=client.target("http://localhost:8082/advanced-jaxrs/webapi/");
		WebTarget messageTarget=baseTarget.path("messages");
		WebTarget singleMessageTarget=messageTarget.path("{messageId}");
		
	
		Message m1=singleMessageTarget.resolveTemplate("messageId",1).request(MediaType.APPLICATION_JSON).get(Message.class);
		
		Message m2=singleMessageTarget.resolveTemplate("messageId",2).request(MediaType.APPLICATION_JSON).get(Message.class);
		
		System.out.println(m1.getMessage());
		System.out.println(m2.getMessage());
		
		Message newMessage=new Message(5,"My new Message","RM");
		Response postResponse = messageTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(newMessage));
	
		Message msg=postResponse.readEntity(Message.class);
		System.out.println(msg);
		
		
		
	}

}
