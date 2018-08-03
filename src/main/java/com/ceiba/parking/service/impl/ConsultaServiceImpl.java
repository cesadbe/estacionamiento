package com.ceiba.parking.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ceiba.parking.ConfigValues;
import com.ceiba.parking.dominio.controller.rest.dto.ConsultaResp;
import com.ceiba.parking.entity.Aparcamiento;
import com.ceiba.parking.repository.AparcamientoRepository;
import com.ceiba.parking.service.ConsultaService;
import com.ceiba.parking.util.BusinessException;

@Service
public class ConsultaServiceImpl implements ConsultaService {
	
	@Autowired
	AparcamientoRepository repository;

	@Override
	public ConsultaResp consultaVehiculo(String placa, String ticket) throws BusinessException {		
		return entityConverter(obtenerEntity(placa, ticket));		
	}
	
	
	public Aparcamiento obtenerEntity (String placa, String ticket) throws BusinessException {
		Aparcamiento entity;		
		if(!StringUtils.isEmpty(ticket)) {
			Optional<Aparcamiento> registro = repository.findById(Long.valueOf(ticket));
			entity = registro.orElse(null);
		}else if(!StringUtils.isEmpty(placa)) {
			Optional<Aparcamiento> registro = repository.findByPlaca(placa);
			entity = registro.orElse(null);
		}else {
			throw new BusinessException(ConfigValues.EXC_NO_PARAMETROS);
		}		
		return entity; 
	}
		
	
	public ConsultaResp entityConverter(Aparcamiento entity) {
		ConsultaResp response = new ConsultaResp();
		
		if(entity!=null) {
			response.setPlaca(entity.getPlaca());
			response.setTicket(""+entity.getId());
			response.setCodTipo(entity.getTipoVehiculo());
			response.setFechaIngreso(entity.getFechaIngeso());
			response.setTipo(ConfigValues.TipoVehiculo.getBytipo(entity.getTipoVehiculo()).getDescripcion());
			response.setAltoCilindraje(entity.getAltoCilindraje());
		}
		
		return response;
	}


	@Override
	public List<ConsultaResp> obtenerTodos() {
		
		List<Aparcamiento> vehiculosParqueados = repository.findAllParqueados();
		List<ConsultaResp> vehiculosParqueadosResponse = new ArrayList<>();
		for(Aparcamiento a : vehiculosParqueados ) {
			vehiculosParqueadosResponse.add(entityConverter(a));
		}
		
		return vehiculosParqueadosResponse;
	}

}
