package com.utils;

public class Message {

	private boolean valid;
	
	private String description;
	
	public Message(boolean valid,String description) {
		this.valid = valid;
		this.description = description;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
