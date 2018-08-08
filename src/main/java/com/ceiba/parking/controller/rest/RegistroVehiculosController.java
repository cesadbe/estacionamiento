package com.ceiba.parking.controller.rest;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.parking.ConfigValues;
import com.ceiba.parking.controller.rest.dto.IngresoReq;
import com.ceiba.parking.controller.rest.dto.IngresoResp;
import com.ceiba.parking.controller.rest.dto.SalidaReq;
import com.ceiba.parking.controller.rest.dto.SalidaResp;
import com.ceiba.parking.service.IngresoService;
import com.ceiba.parking.service.SalidaService;
import com.ceiba.parking.util.BusinessException;

@RestController
@RequestMapping(ConfigValues.API)
public class RegistroVehiculosController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RegistroVehiculosController.class);
	
	@Autowired
	IngresoService ingService;
	
	@Autowired
	SalidaService salidaService;
	
	@RequestMapping(value = ConfigValues.PATH_INGRESO, method = RequestMethod.POST)
	public IngresoResp ingresarVehiculo(@RequestBody IngresoReq request) {
		
		IngresoResp response = new IngresoResp();
		procesarHoraIngreso(request);
		try {
			response.setTicket(ingService.ingresar(request.getVehiculo(), request.getFechaIngreso()));			
		}catch(BusinessException b) {
			LOGGER.info("BusinessException catched={}", b);
			response.setMessage(b.getMessage());
		}		
		return response;		
	}

	private void procesarHoraIngreso(IngresoReq request) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(request.getFechaIngreso()!=null?request.getFechaIngreso():new Date());
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		request.setFechaIngreso(calendar.getTime());
	}
	
	@RequestMapping(value = ConfigValues.PATH_SALIDA, method = RequestMethod.POST)
	public SalidaResp despacharVehiculo(@RequestBody SalidaReq request) {
		
		SalidaResp response = new SalidaResp();
		procesarHoraSalida(request);
		try {
			response = salidaService.despachar(request, request.getFechaSalida());
		}catch(BusinessException b) {
			LOGGER.info("BusinessException catched={}", b);
			response.setMessage(b.getMessage());
		}		
		return response;		
	}
	
	private void procesarHoraSalida(SalidaReq request) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(request.getFechaSalida()!=null?request.getFechaSalida():new Date());
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		request.setFechaSalida(calendar.getTime());
	}
	

}
