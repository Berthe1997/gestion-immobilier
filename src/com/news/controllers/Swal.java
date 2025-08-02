package com.news.controllers;

public class Swal {
	private String message = ""; // message to send
	private String type = ""; // message type (info, warning or error)
	private String swal = ""; // corresponding color STRING By message type for the view
	
	public Swal() {
		this("", "", "");
	}

	public Swal(String message, String type, String swal) {
		this.message = message;
		this.type = type;
		this.swal = swal;
	}
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSwal() {
		return swal;
	}
	public void setSwal(String swal) {
		this.swal = swal;
	}
	
}
