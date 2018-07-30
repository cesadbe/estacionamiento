package com.ceiba.parking.dominio.unitaria;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ceiba.parking.ConfigValues;
import com.ceiba.parking.dominio.testdatabuilder.VehiculoTestDataBuilder;
import com.ceiba.parking.model.Vehiculo;
import com.ceiba.parking.service.impl.IngresoServiceImpl;

@SpringBootTest
public class ValidacionesIngresoPorTipo {
	
	@Test
	public void vehiculoTipoMotocicleta(){
		
		IngresoServiceImpl serviceIngreso = new IngresoServiceImpl();
		
		Vehiculo veh = new VehiculoTestDataBuilder().
				deTipo(ConfigValues.TipoVehiculo.MOTO.getTipo()).
				build();
		
		boolean esPermitidoIngreso = serviceIngreso.esTipoVehiculoPermitido(veh);		
		assertTrue(esPermitidoIngreso);
				
	}
	
	@Test
	public void vehiculoTipoAutomovil(){
		
		IngresoServiceImpl serviceIngreso = new IngresoServiceImpl();
		
		Vehiculo veh = new VehiculoTestDataBuilder().
				deTipo(ConfigValues.TipoVehiculo.CARRO.getTipo()).
				build();
		
		boolean esPermitidoIngreso = serviceIngreso.esTipoVehiculoPermitido(veh);		
		assertTrue(esPermitidoIngreso);
				
	}
	
	@Test
	public void vehiculoTipoBicicleta(){
		
		IngresoServiceImpl serviceIngreso = new IngresoServiceImpl();
		
		Vehiculo veh = new VehiculoTestDataBuilder().
				deTipo(ConfigValues.TipoVehiculo.BICI.getTipo()).
				build();
		
		boolean esPermitidoIngreso = serviceIngreso.esTipoVehiculoPermitido(veh);		
		assertFalse(esPermitidoIngreso);
				
	}

}
