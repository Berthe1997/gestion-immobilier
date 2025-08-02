package com.news.controllers;

public class Message {

	private String message = ""; // message to send
	private int type = 0; // message type (info, warning or error)
	private String msgColor = ""; // corresponding color STRING By message type for the view

	public Message() {
		this("", 0, "black");
	}

	public Message(String message, int type, String msgColor) {
		this.message = message;
		this.type = type;
		this.msgColor = msgColor;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getMsgColor() {
		return msgColor;
	}

	public void setMsgColor(String msgColor) {
		this.msgColor = msgColor;
	}

}
