package com.ceiba.parking.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.parking.ConfigValues;
import com.ceiba.parking.entity.Aparcamiento;
import com.ceiba.parking.model.Vehiculo;
import com.ceiba.parking.repository.AparcamientoRepository;
import com.ceiba.parking.service.IngresoService;
import com.ceiba.parking.util.BusinessException;

@Service
public class IngresoServiceImpl implements IngresoService {
	
	@Autowired
	AparcamientoRepository repository;

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
	
	public Aparcamiento entityConverter(Vehiculo veh, Date fechaIngreso) {
		Aparcamiento a = new Aparcamiento();
		
		a.setPlaca(veh.getPlaca());
		a.setFechaIngeso(fechaIngreso);
		a.setTipoVehiculo(veh.getTipo());
		a.setAltoCilindraje(veh.getCilindraje()>500);
		
		return a;
	}

}
