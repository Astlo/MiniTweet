package tinyTwitter;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Message {
	
	private @Id Long msgId;
	
	private @Index String sender;
	
	private String message;
	
	private @Index List<String> receivers = new ArrayList<String>();

	private @Index long timestamp;
	
	public Message() {}
	
	public Message(String message, List<String> liste, String sender) {
		this.message = message;
		this.receivers = liste;
		this.sender = sender;
		this.timestamp = System.currentTimeMillis();
	}
	
	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public void addReceivers(String e) {
		if(!this.receivers.contains(e))
		{
			this.receivers.add(e);
		}
	}
	

	public void removeReceivers(String e)
	{
		this.receivers.remove(e);
	}
	
	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getReceivers() {
		return receivers;
	}

	public void setReceivers(List<String> receivers) {
		this.receivers = receivers;
	}
	

}