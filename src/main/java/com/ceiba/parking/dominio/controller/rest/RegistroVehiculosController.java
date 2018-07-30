package com.ceiba.parking.dominio.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.parking.ConfigValues;
import com.ceiba.parking.dominio.controller.rest.dto.IngresoReq;
import com.ceiba.parking.dominio.controller.rest.dto.IngresoResp;
import com.ceiba.parking.service.IngresoService;
import com.ceiba.parking.util.BusinessException;

@RestController
@RequestMapping(ConfigValues.API)
public class RegistroVehiculosController {
	
	Logger logger = LoggerFactory.getLogger(RegistroVehiculosController.class);
	
	@Autowired
	IngresoService ingService;
	
	@RequestMapping(value = ConfigValues.PATH_INGRESO, method = RequestMethod.POST)
	public IngresoResp ingresarVehiculo(@RequestBody IngresoReq request) {
		
		IngresoResp response = new IngresoResp();
		
		try {
			response.setTicket(ingService.ingresar(request.getVehiculo(), request.getFechaIngreso()));			
		}catch(BusinessException b) {
			logger.info("BusinessException catched: " + b.getMessage());
			response.setMessage(b.getMessage());
		}		
		
		return response;
		
	}

}
