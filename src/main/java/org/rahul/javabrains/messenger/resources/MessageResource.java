package org.rahul.javabrains.messenger.resources;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

import org.rahul.javabrains.messenger.model.Message;
import org.rahul.javabrains.messenger.resources.beans.MessageFilterBean;
import org.rahul.javabrains.messenger.services.MessageService;



@Path(value = "/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value= {  MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
public class MessageResource {

	MessageService ms=new MessageService();
	
	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean messageFilterBean)
	{	
		if(messageFilterBean.getYear()>0)
		{
		return ms.getAllMessagesForYear(messageFilterBean.getYear());
		}
		if(messageFilterBean.getStart()>=0 && messageFilterBean.getSize() >0)
		{
			return ms.getAllMessagesPaginated(messageFilterBean.getStart(), messageFilterBean.getSize());
		}
		return ms.getAllMessages();
		 
	}
	
	@POST
	public Response addMessage(Message message,@Context UriInfo uriInfo)
	{	Message msg=ms.addMessage(message);
		String id=String.valueOf(msg.getId());
	URI uri=uriInfo.getAbsolutePathBuilder().path(id).build();
		return Response.created(uri).entity(msg).build();
		
		//return ms.addMessage(message);
	
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(Message message,@PathParam("messageId")long messageId)
	{	message.setId(messageId);
		return ms.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void removeMessage(@PathParam("messageId")long messageId)
	{
		ms.removeMessage(messageId);
	}
	
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId")long messageId,@Context UriInfo uriInfo)
	{	Message msg= ms.getMessage(messageId);
		// http://localhost:8080/messenger/webapi/messages
		msg.addLink(getUriForSelf(uriInfo,msg),"self");
		msg.addLink(getUriForProfile(uriInfo,msg),"profile");
		msg.addLink(getUriForComments(uriInfo,msg),"comments");
		return msg;
	}

	@Path("/{messageId}/comments")
	public CommentResource getCommentResource()
	{	return new CommentResource();
	}
	
	private String getUriForSelf(UriInfo uriInfo,Message msg) {
		String URI=	uriInfo.getBaseUriBuilder().
				path(MessageResource.class).
				path(Long.toString(msg.getId())) 
						.build().toString();
		return URI;
	}
	
	private String getUriForProfile(UriInfo uriInfo,Message msg) {
		String URI=	uriInfo.getBaseUriBuilder().
				path(ProfileResource.class).
				path(msg.getAuthor()).
				build().toString();
		return URI;
	}
	
	private String getUriForComments(UriInfo uriInfo,Message msg) {
		/*String URI=	uriInfo.getAbsolutePathBuilder().
				path(MessageResource.class).
				path(Long.toString(msg.getId())).
				build().toString();
		return URI;*/
		String URI=uriInfo.getBaseUriBuilder().path(MessageResource.class).path(MessageResource.class,"getCommentResource").path(CommentResource.class).resolveTemplate("messageId",msg.getId()).build().toString();
		
	return URI;	
	}
	
	
}
