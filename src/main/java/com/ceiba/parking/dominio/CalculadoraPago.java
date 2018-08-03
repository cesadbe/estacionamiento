package com.ceiba.parking.dominio;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.ceiba.parking.ConfigValues;
import com.ceiba.parking.dominio.controller.rest.dto.ConsultaResp;

@Component
public class CalculadoraPago {
	
	Map<String, Double> valoresHora;
	Map<String, Double> valoresDia;
	Double valorExtraAltoCilindraje;
	

	public CalculadoraPago() {
		super();
		valoresHora = new HashMap<>();
		valoresHora.put(ConfigValues.TipoVehiculo.CARRO.getTipo(), ConfigValues.VALOR_HORA_CARRO);
		valoresHora.put(ConfigValues.TipoVehiculo.MOTO.getTipo(), ConfigValues.VALOR_HORA_MOTO);
		
		valoresDia = new HashMap<>();
		valoresDia.put(ConfigValues.TipoVehiculo.CARRO.getTipo(), ConfigValues.VALOR_DIA_CARRO);
		valoresDia.put(ConfigValues.TipoVehiculo.MOTO.getTipo(), ConfigValues.VALOR_DIA_MOTO);
		
		valorExtraAltoCilindraje = ConfigValues.VALOR_EXTRA_MOTO;
	}


	public Double calcularPago(ConsultaResp vehiculo, Date fechaSalida) {
		
		long diffInMillies = Math.abs(fechaSalida.getTime() - vehiculo.getFechaIngreso().getTime());
		long minutosTotalParqueado = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
		long horasTotalParqueado = minutosTotalParqueado/60;
		long fraccionHoraParqueado = minutosTotalParqueado%60;
		
		int diasParqueado = (int) (horasTotalParqueado/24); 
		int horasParqueado = (int) (horasTotalParqueado%24);
		
		if(horasParqueado>8) {
			horasParqueado = 0;
			diasParqueado++;
		}
		
		if(fraccionHoraParqueado>0) {
			horasParqueado++;
		}
		
		double valorPagar = diasParqueado * valoresDia.get(vehiculo.getCodTipo());
		valorPagar += horasParqueado * valoresHora.get(vehiculo.getCodTipo());
		
		if(vehiculo.isAltoCilindraje()) {
			valorPagar += 2000;
		}
		
		return valorPagar;
	}
	

}
