package org.rahul.javabrains.messenger.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.rahul.javabrains.messenger.database.DataBaseClass;
import org.rahul.javabrains.messenger.model.Comment;
import org.rahul.javabrains.messenger.model.ErrorMessage;
import org.rahul.javabrains.messenger.model.Message;

public class CommentService {

private Map<Long,Message> messages=DataBaseClass.getMessages();
	
	public CommentService() {
	
	}
	
	public  List <Comment> getAllComments(long messageId)
	{
		Map <Long,Comment> comments=messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
		
	}
	
	public Comment getComment(long messageId,long commentId)
	{	
		ErrorMessage errmsg=new ErrorMessage("Not Found",404,"http://javabrains.org");
		Response response= Response.status(Status.NOT_FOUND).entity(errmsg).build();
		
		
		Message msg=messages.get(messageId);
		if(msg==null)
		{
		throw new WebApplicationException(Status.NOT_FOUND);
		}
		Map <Long,Comment> comments=messages.get(messageId).getComments();
		Comment com=comments.get(commentId);
		if(com==null)
		{
			throw new NotFoundException(response);
		}
		return com;
	}
	
	
	
	public Comment addComment(long messageId ,Comment comment)
	{	
		Map <Long,Comment> comments=messages.get(messageId).getComments();
		comment.setId(comments.size()+1);
		comments.put(comment.getId(),comment);
		return comment;
		
	}
	public Comment updateComment(long messageId,Comment comment)
	{	
		Map <Long,Comment> comments=messages.get(messageId).getComments();
		if(comment.getId()<0)
			return null;
		else 
		{	comments.put(comment.getId(),comment);
			return comment;
		}
	}
	
	public Comment removeComment(long messageId,long commentId)
	{	
		Map <Long,Comment> comments=messages.get(messageId).getComments();
		return comments.remove(commentId);
	}
	
	
	
}
