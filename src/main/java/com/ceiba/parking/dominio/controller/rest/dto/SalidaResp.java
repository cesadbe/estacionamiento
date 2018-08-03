package com.ceiba.parking.dominio.controller.rest.dto;

import java.util.Date;

public class SalidaResp {
	
	private String placa;
	private String ticket;
	private Double valorPagar;
	private Date fechaSalida;
	private String message;
	
	public SalidaResp() {
		super();
	}
	
	public SalidaResp(String placa, String ticket, Double valorPagar, Date fechaSalida) {
		super();
		this.placa = placa;
		this.ticket = ticket;
		this.valorPagar = valorPagar;
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
	public Double getValorPagar() {
		return valorPagar;
	}
	public void setValorPagar(Double valorPagar) {
		this.valorPagar = valorPagar;
	}
	public Date getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
