package com.ceiba.parking.dominio.controller.rest.dto;

import java.util.Date;

import com.ceiba.parking.model.Vehiculo;

public class IngresoReq {

	Vehiculo vehiculo;
	Date fechaIngreso;
	
	
	public IngresoReq() {
		super();
	}
	
	public IngresoReq(Vehiculo vehiculo, Date fechaIngreso) {
		super();
		this.vehiculo = vehiculo;
		this.fechaIngreso = fechaIngreso;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}


	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}


	public Date getFechaIngreso() {
		return fechaIngreso;
	}


	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}	
	
	

}
