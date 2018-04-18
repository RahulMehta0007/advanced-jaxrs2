package org.rahul.javabrains.messenger.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.rahul.javabrains.messenger.database.DataBaseClass;
import org.rahul.javabrains.messenger.exception.DataNotFoundException;
import org.rahul.javabrains.messenger.model.Message;

public class MessageService {

	private Map<Long,Message> messages=DataBaseClass.getMessages();
	
	public MessageService() {
		
		messages.put( 1L,new Message(1,"Hello :) ","Rahul Mehta"));
		messages.put( 2L,new Message(2,"Hey  ","Rahul Mehta"));
	}
	
	
	public  List <Message> getAllMessages()
	{
	
		return new ArrayList<Message>(messages.values());
	}
	
	
	public  List <Message> getAllMessagesForYear(int year)
	{
	List <Message> messagesForYear=new ArrayList<Message>();
	Calendar cal=Calendar.getInstance();
	for(Message msg:messages.values())
	{
		cal.setTime(msg.getCreated());
	if((cal.get(Calendar.YEAR))==year)
	{
		messagesForYear.add(msg);
	}	
		
	}
	return messagesForYear;	
	}
	
	
	public  List <Message> getAllMessagesPaginated(int start,int size)
	{
		List <Message> list=new ArrayList<Message>(messages.values());
		if(start+size> list.size()) return new ArrayList<Message>();
		return list.subList(start,start+size);
		
		
	}
	
	
	
	
	public Message getMessage(Long id)
	{
		Message msg=messages.get(id);;
		
		if(msg==null)
		{
			throw new DataNotFoundException(id);
		}
		return msg;
	}
	
	public Message addMessage(Message message)
	{
		message.setId(messages.size()+1);
		messages.put(message.getId(),message);
		return message;
		
	}
	public Message updateMessage(Message message)
	{
		if(message.getId()<0)
			return null;
		else 
		{	messages.put(message.getId(),message);
			return message;
		}
	}
	
	public Message removeMessage(Long id)
	{	
		return messages.remove(id);
	}
	
	
}
