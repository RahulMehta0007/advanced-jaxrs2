package org.rahul.javabrains.messenger.resources;

import java.util.List;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.rahul.javabrains.messenger.model.Comment;
import org.rahul.javabrains.messenger.services.CommentService;


@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

CommentService cs=new CommentService();
	
	
	@POST
	public Comment addComment(@PathParam("messageId")long messageId,Comment comment)
	{
		return cs.addComment(messageId, comment);
	
	}
	
	@PUT
	@Path("/{commentId}")
	public Comment updateComment(@PathParam("messageId")long messageId,
								Comment comment,@PathParam("commentId")long commentId)
	{	

		comment.setId(commentId);
		return cs.updateComment(messageId,comment);
	}
	
	@DELETE
	@Path("/{commentId}")
	public void removeComment(@PathParam("messageId")long messageId,
			@PathParam("commentId")long commentId)
	{
		cs.removeComment(messageId,commentId);
	}
	
	
	@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("messageId")long messageId,
			@PathParam("commentId")long commentId)
	{	
		return cs.getComment(messageId, commentId);
	
	}
	
	@GET
	public List<Comment> getAllComments(@PathParam("messageId")long messageId)
	{	
		return cs.getAllComments(messageId);
	
	}
	
	
	
	
	
	
	
}
