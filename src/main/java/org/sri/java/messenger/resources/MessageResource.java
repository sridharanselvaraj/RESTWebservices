package org.sri.java.messenger.resources;

import java.net.URI;
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
import javax.ws.rs.core.UriInfo;

import org.sri.java.messenger.model.Message;
import org.sri.java.messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	MessageService messageService=new MessageService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getJsonMessage(@BeanParam MessageFilterBean filterBean)
	{
		System.out.println("JSON Method Called");
		if(filterBean.getYear() > 0)
		{
			return messageService.getAllMessageForYear(filterBean.getYear());
		}
		if(filterBean.getStart() >=0 && filterBean.getSize() >0)
		{
			return messageService.getAllMessagePaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessage();
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Message> getXMLMessage(@BeanParam MessageFilterBean filterBean)
	{
		System.out.println("Xml Method Called");
		if(filterBean.getYear() > 0)
		{
			return messageService.getAllMessageForYear(filterBean.getYear());
		}
		if(filterBean.getStart() >=0 && filterBean.getSize() >0)
		{
			return messageService.getAllMessagePaginated(filterBean.getStart(), filterBean.getSize());
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
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id,@Context UriInfo  uriInfo)
	{
		
		Message message= messageService.getMessage(id);
	  
		message.addLink(getUriForSelf(uriInfo, message),"self");
		message.addLink(getUriforProfile(uriInfo, message),"profile");
		return message;
	}
	
	private String getUriforProfile(UriInfo uriInfo,Message message)
	{
		URI uri= uriInfo.getBaseUriBuilder()
				.path(ProfileResource.class)
				.path(message.getAuthor())
				.build();
		return uri.toString();		
				
	}

	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri =	uriInfo.getBaseUriBuilder()
		.path(MessageResource.class)
		.path(Long.toString(message.getId()))
		.build()
		.toString();
		return uri;
	}
	
	

	
	
/*	
	public String getMessage()
	{
		return "Hello World!";
	}
*/
}
