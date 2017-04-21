package org.sri.java.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.sri.java.messenger.database.DatabaseClass;
import org.sri.java.messenger.model.Message;

public class MessageService {
	
	//Test
	
	private Map<Long,Message> messages = DatabaseClass.getMessages();

	public List<Message> getAllMessage()
	{
		Message m1=new Message(1L, "Hello World!", "Sri");
		Message m2=new Message(2L,"Hello Jersey!","Sri");
		Message m3=new Message(3L,"Hello Java!","Jack");
		List<Message> l1=new ArrayList<>();
		l1.add(m1);
		l1.add(m2);
		l1.add(m3);
		return l1;
	}
	
}
