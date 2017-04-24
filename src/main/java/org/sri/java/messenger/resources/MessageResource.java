package org.sri.java.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.sri.java.messenger.model.Message;
import org.sri.java.messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	MessageService messageService=new MessageService();
	
	@GET
	public List<Message> getMessage(@QueryParam("year") int year,
									@QueryParam("start") int start,
									@QueryParam("size") int size)
	{
		if(year > 0)
		{
			return messageService.getAllMessageForYear(year);
		}
		if(start >=0 && size >0)
		{
			return messageService.getAllMessagePaginated(start, size);
		}
		return messageService.getAllMessage();
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId")long id,Message messages)
	{
		messages.setId(id);
		return messageService.updateMessage(messages);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId")long id,Message messages)
	{
		messageService.removeMessage(id);
	}
	
	@POST
	public Message addMessage(Message message)
	{
		return messageService.addMessage(message);
	}
	
	@GET
	@Path("/{messageID}")
	public Message test(@PathParam("messageID") long id)
	{
		return messageService.getMessage(id);
	}
	
	
	
	
/*	
	public String getMessage()
	{
		return "Hello World!";
	}
*/
}
