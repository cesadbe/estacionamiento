package com.ceiba.parking.dominio.controller.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.parking.ConfigValues;
import com.ceiba.parking.dominio.controller.rest.dto.ConsultaResp;
import com.ceiba.parking.service.ConsultaService;
import com.ceiba.parking.util.BusinessException;


@RestController
@RequestMapping(ConfigValues.API)
public class ConsultaVehiculosController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaVehiculosController.class);
	
	@Autowired
	ConsultaService consultaService;
	
	@RequestMapping(value = ConfigValues.PATH_CONSULTA_INDIVIDUAL, method = RequestMethod.GET)
	public ConsultaResp consultaVehiculo(@RequestParam(value = "placa", required=false) String placa, @RequestParam(value = "ticket", required=false) String ticket) {	
		ConsultaResp resp = new ConsultaResp();
		try {
			resp = consultaService.consultaVehiculo(placa, ticket);
		} catch (BusinessException e) {
			LOGGER.info("BusinessException catched={}", e);
			resp.setMessage(e.getMessage());
		} catch(Exception e) {
			LOGGER.info("RuntimeException catched={}", e);
			resp.setMessage(e.getMessage());
		}
		
		return resp;
		
	}
	
	@RequestMapping(value = ConfigValues.PATH_CONSULTA_TODOS, method = RequestMethod.GET)
	public List<ConsultaResp> consultaTodosVehiculos() {	
		return consultaService.obtenerTodos();		
	}
	
}
