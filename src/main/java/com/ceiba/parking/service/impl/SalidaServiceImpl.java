package com.ceiba.parking.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ceiba.parking.ConfigValues;
import com.ceiba.parking.controller.rest.dto.SalidaReq;
import com.ceiba.parking.controller.rest.dto.SalidaResp;
import com.ceiba.parking.dominio.CalculadoraPago;
import com.ceiba.parking.entity.Aparcamiento;
import com.ceiba.parking.repository.AparcamientoRepository;
import com.ceiba.parking.service.ConsultaService;
import com.ceiba.parking.service.SalidaService;
import com.ceiba.parking.util.BusinessException;

@Service
public class SalidaServiceImpl implements SalidaService {
		
	@Autowired
	AparcamientoRepository repository;
	
	@Autowired
	ConsultaService consultaService;
	
	@Autowired
	CalculadoraPago calculadora;

	@Override
	public SalidaResp despachar(SalidaReq req, Date fechaSalida) throws BusinessException {
		
		Aparcamiento vehiculo = consultaService.obtenerEntity(req.getPlaca(), req.getTicket());
		
		if( vehiculo==null || StringUtils.isEmpty(vehiculo.getId()) ) {
			throw new BusinessException(ConfigValues.EXC_SALIDA_NO_REGISTRADO);
		}
		
		double valorPagar = calculadora.calcularPago(consultaService.entityConverter(vehiculo), fechaSalida);
		
		vehiculo.setValorPagar(valorPagar);
		vehiculo.setFechaSalida(fechaSalida);
		
		Aparcamiento vehiculoActualizado = repository.save(vehiculo);
		
		
		return new SalidaResp(
				vehiculoActualizado.getPlaca(),
				"" + vehiculoActualizado.getId(),
				vehiculoActualizado.getValorPagar(),
				vehiculoActualizado.getFechaSalida()
		);

	}
		

}
