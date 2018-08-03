package com.ceiba.parking.dominio.controller.rest.dto;

import java.util.Date;

public class ConsultaResp {

	private String placa;
	private String codTipo;
	private String tipo;
	private Date fechaIngreso;
	private String ticket;
	private boolean altoCilindraje = false;
	
	private String message;
	
	public ConsultaResp() {
		super();
	}

	public ConsultaResp(String placa, String codTipo, Date fechaIngreso, String ticket) {
		super();
		this.placa = placa;
		this.codTipo = codTipo;
		this.fechaIngreso = fechaIngreso;
		this.ticket = ticket;
	}	
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getCodTipo() {
		return codTipo;
	}
	public void setCodTipo(String codTipo) {
		this.codTipo = codTipo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
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
	public boolean isAltoCilindraje() {
		return altoCilindraje;
	}
	public void setAltoCilindraje(boolean altoCilindraje) {
		this.altoCilindraje = altoCilindraje;
	}
	
}
