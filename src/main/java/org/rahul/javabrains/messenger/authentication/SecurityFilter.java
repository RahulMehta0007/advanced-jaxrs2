package org.rahul.javabrains.messenger.authentication;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;
@Provider
public class SecurityFilter implements ContainerRequestFilter{

	private static final String AUTHORIZATION_HEADER="Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX="Basic ";
	private static final String SECURED_URI_PREFIX="secured";
	
	@Override
	public void filter(ContainerRequestContext request) throws IOException {
		// TODO Auto-generated method stub
		if(request.getUriInfo().getPath().contains(SECURED_URI_PREFIX))
		{
			List<String> list = request.getHeaders().get(AUTHORIZATION_HEADER);
		
		if(list!=null&&list.size()>=0)
		{
			String authHeader=list.get(0);
			authHeader=authHeader.replace(AUTHORIZATION_HEADER_PREFIX,"");
			String decodeString=Base64.decodeAsString(authHeader);
			StringTokenizer token=new StringTokenizer(decodeString,":");
			String userName=token.nextToken();
			String password=token.nextToken();
			
			if(userName.equals("user")&&password.equals("password"))
			return;
		
		}
		
		Response unAuthorizedStatus=Response.status(Response.Status.UNAUTHORIZED).entity("User Cannot Access The Resource").build();
		request.abortWith(unAuthorizedStatus);
	}
	}
}
