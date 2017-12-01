package com.asms.messagemgmt.response;

import java.util.List;

import com.asms.common.response.SuccessResponse;
import com.asms.messagemgmt.entity.Message;
import com.asms.usermgmt.request.UserBasicDetails;

public class MessageSuccessResponse extends SuccessResponse{
	
	
	
	private List<UserBasicDetails> userBasicDetails;
	
	private List<MessageDetails> messageDetails;
	
	private List<Message> messages;
	
	

	public List<UserBasicDetails> getUserBasicDetails() {
		return userBasicDetails;
	}

	public void setUserBasicDetails(List<UserBasicDetails> userDetails) {
		this.userBasicDetails = userDetails;
	}

	public List<MessageDetails> getMessageDetails() {
		return messageDetails;
	}

	public void setMessageDetails(List<MessageDetails> messageDetails) {
		this.messageDetails = messageDetails;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}



	



	
	

}
