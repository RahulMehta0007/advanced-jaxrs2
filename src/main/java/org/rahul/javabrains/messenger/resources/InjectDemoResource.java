package org.rahul.javabrains.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path(value = "/inject")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

@GET
@Path("annotations")
public String getParamValues(@MatrixParam("matrixParam") String matrixParam,
							@CookieParam("cookieParam") String cookieParam,
							@HeaderParam("headerParam") String headerParam)
{
return "[ Matrix Param : "+matrixParam+" , cookieParam is : "+cookieParam+
		" Header Param is : "+headerParam+" ]";	
}

@GET
@Path("context")
public String getParamUsingContext(@Context UriInfo uriInfo,@Context HttpHeaders headers)
{
	String path=uriInfo.getAbsolutePath().toString();
	return "Path : "+path+" ,Header : "+ headers.getHeaderString("Accept");	
	
}


	
}
