package com.ceiba.parking.controller.rest.dto;

public class IngresoResp {
	
	String ticket;
	String message;
	
	public IngresoResp() {
		super();
		this.ticket = "";
		this.message = "";
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
