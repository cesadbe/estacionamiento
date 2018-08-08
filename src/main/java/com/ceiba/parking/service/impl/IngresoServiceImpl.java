package com.ceiba.parking.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ceiba.parking.ConfigValues;
import com.ceiba.parking.controller.rest.dto.ConsultaResp;
import com.ceiba.parking.entity.Aparcamiento;
import com.ceiba.parking.model.Vehiculo;
import com.ceiba.parking.repository.AparcamientoRepository;
import com.ceiba.parking.service.ConsultaService;
import com.ceiba.parking.service.IngresoService;
import com.ceiba.parking.util.BusinessException;

@Service
public class IngresoServiceImpl implements IngresoService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(IngresoServiceImpl.class);
	
	@Autowired
	AparcamientoRepository repository;
	
	@Autowired
	ConsultaService consultaService;

	@Override
	public String ingresar(Vehiculo veh, Date fechaIngreso) throws BusinessException {
		
		if(! esTipoVehiculoPermitido(veh)) {
			throw new BusinessException(ConfigValues.EXC_VEHICULO_NO_PERMITIDO);
		}
		
		if(! hayCupoDisponible(veh)) {
			throw new BusinessException(ConfigValues.EXC_EXCEDE_CUPO);
		}
		
		if(! esDiaHabil(veh, fechaIngreso)) {
			throw new BusinessException(ConfigValues.EXC_NO_ES_DIA_HABIL);
		}
		
		if( estaVehiculoYaRegistrado(veh) ) {
			throw new BusinessException(ConfigValues.EXC_INGRESO_YA_REGISTRADO);
		}
		
		Aparcamiento a = entityConverter(veh, fechaIngreso);
		repository.save(a);
		
		return ""+a.getId();
	}
	
	
	public boolean esTipoVehiculoPermitido(Vehiculo veh) {		
		return ConfigValues.VEHICULOS_PERMITIDOS.contains(veh.getTipo());
	}
	
	public boolean hayCupoDisponible(Vehiculo veh) {
		long vehiculosParqueadosTipo = repository.vehiculosParqueadosSegunTipo(veh.getTipo());
		
		long maximoVehiculosTipo = 0;
		if("M".equalsIgnoreCase(veh.getTipo())) {
			maximoVehiculosTipo = ConfigValues.MAX_MOTOS;
		}else if("C".equalsIgnoreCase(veh.getTipo())) {
			maximoVehiculosTipo = ConfigValues.MAX_AUTOS;
		} 
		
		return vehiculosParqueadosTipo < maximoVehiculosTipo;
	}
	
	public boolean esDiaHabil(Vehiculo veh, Date fechaIngreso) {
		if(veh.getPlaca().startsWith("A")) {			
			Calendar c = Calendar.getInstance();
			c.setTime(fechaIngreso);
			int day = c.get(Calendar.DAY_OF_WEEK);
			
			return (Calendar.MONDAY==day || Calendar.SUNDAY==day);
		}else {			
			return true;
		}
	}
	
	public boolean estaVehiculoYaRegistrado(Vehiculo veh) {
		try {
			ConsultaResp vehiculo = consultaService.consultaVehiculo(veh.getPlaca(), null);
			return !StringUtils.isEmpty(vehiculo.getTicket());
		} catch (BusinessException e) {
			LOGGER.info("BusinessException ConsultaVehiculo catched={}", e);
		}
		return false;
	}
	
	public Aparcamiento entityConverter(Vehiculo veh, Date fechaIngreso) {
		Aparcamiento a = new Aparcamiento();
		
		a.setPlaca(veh.getPlaca());
		a.setFechaIngeso(fechaIngreso);
		a.setTipoVehiculo(veh.getTipo());
		a.setAltoCilindraje(veh.getCilindraje()>500 && ConfigValues.TipoVehiculo.MOTO.getTipo().equalsIgnoreCase(veh.getTipo()));
		
		return a;
	}

}
