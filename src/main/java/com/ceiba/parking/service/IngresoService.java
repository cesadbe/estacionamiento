package com.ceiba.parking.service;

import java.util.Date;

import com.ceiba.parking.model.Vehiculo;
import com.ceiba.parking.util.BusinessException;

public interface IngresoService {
	
	public String ingresar(Vehiculo veh, Date fechaIngreso) throws BusinessException;

}
