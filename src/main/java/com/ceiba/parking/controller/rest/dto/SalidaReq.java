package com.ceiba.parking.controller.rest.dto;

import java.util.Date;

public class SalidaReq {

	private String placa;
	private String ticket;
	private Date fechaSalida;
		
	public SalidaReq() {
		super();
	}

	public SalidaReq(String placa, String ticket, Date fechaSalida) {
		super();
		this.placa = placa;
		this.ticket = ticket;
		this.fechaSalida = fechaSalida;
	}
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public Date getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
		
}
