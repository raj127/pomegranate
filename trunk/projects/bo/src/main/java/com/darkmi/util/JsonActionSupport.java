package com.darkmi.util;

import java.util.ArrayList;
import java.util.List;

public abstract class JsonActionSupport extends BaseAction {

	private static final long serialVersionUID = -4101156893679325144L;
	
	/**
	 * FOR JSON
	 * indicate request success or fail
	 */
	private boolean success=true;   
	
	
	/**
	 * FOR JSON
	 * pass  messages to client
	 */
	private List<String> messages=new ArrayList<String>();
	
	/**
	 * FOR JSON
	 * add one error message and set success to false
	 * @param errorMsg
	 */
	public void addJsonError(String errorMsg){
		success=false;
		messages.add(errorMsg);
	}
	
	/**
	 * FOR JSON
	 * add one normal message
	 * @param errorMsg
	 */
	public void addJsonMessage(String message){
		messages.add(message);
	}

	public boolean isSuccess() {
		return success;
	}

	/**
	 * FOR JSON
	 * set json response flag, success or fail
	 * @param success
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

}
