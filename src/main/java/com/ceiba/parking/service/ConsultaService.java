package com.ceiba.parking.service;

import java.util.List;

import com.ceiba.parking.dominio.controller.rest.dto.ConsultaResp;
import com.ceiba.parking.entity.Aparcamiento;
import com.ceiba.parking.util.BusinessException;

public interface ConsultaService {

	public ConsultaResp consultaVehiculo(String placa, String ticket) throws BusinessException;
	
	public Aparcamiento obtenerEntity (String placa, String ticket) throws BusinessException;
	
	public ConsultaResp entityConverter(Aparcamiento entity);
	
	public List<ConsultaResp> obtenerTodos();
}
