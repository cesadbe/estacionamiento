package com.ceiba.parking.service;

import java.util.Date;

import com.ceiba.parking.controller.rest.dto.SalidaReq;
import com.ceiba.parking.controller.rest.dto.SalidaResp;
import com.ceiba.parking.util.BusinessException;

public interface SalidaService {
	
	public SalidaResp despachar(SalidaReq req, Date fechaSalida) throws BusinessException;

}
