package com.ceiba.parking.dominio.unitaria;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ceiba.parking.dominio.testdatabuilder.VehiculoTestDataBuilder;
import com.ceiba.parking.model.Vehiculo;
import com.ceiba.parking.service.impl.IngresoServiceImpl;

@SpringBootTest
public class ValidacionesIngresoPorDia {
	
	@Test
	public void vehiculoPlacaASD123IngresaDiaLunes(){
		
		IngresoServiceImpl serviceIngreso = new IngresoServiceImpl();
		
		Vehiculo veh = new VehiculoTestDataBuilder().
				conPlaca("ASD123").
				conCilindraje(124).
				build();
		Date lunesSeisAgosto2018 = new GregorianCalendar(2018, Calendar.AUGUST, 6).getTime();
		
		boolean esPermitidoIngreso = serviceIngreso.esDiaHabil(veh, lunesSeisAgosto2018);
		
		assertTrue(esPermitidoIngreso);
				
	}
	
	@Test
	public void vehiculoPlacaBSD123IngresaDiaLunes(){
		
		IngresoServiceImpl serviceIngreso = new IngresoServiceImpl();
		
		Vehiculo veh = new VehiculoTestDataBuilder().
				conPlaca("BSD123").
				conCilindraje(124).
				build();
		Date lunesSeisAgosto2018 = new GregorianCalendar(2018, Calendar.AUGUST, 6).getTime();
		
		boolean esPermitidoIngreso = serviceIngreso.esDiaHabil(veh, lunesSeisAgosto2018);
		
		assertTrue(esPermitidoIngreso);
				
	}
	
	@Test
	public void vehiculoPlacaASD123IngresaDiaDomingo(){
		
		IngresoServiceImpl serviceIngreso = new IngresoServiceImpl();
		
		Vehiculo veh = new VehiculoTestDataBuilder().
				conPlaca("ASD123").
				conCilindraje(124).
				build();
		Date domingoCincoAgosto2018 = new GregorianCalendar(2018, Calendar.AUGUST, 5).getTime();
		
		boolean esPermitidoIngreso = serviceIngreso.esDiaHabil(veh, domingoCincoAgosto2018);
		
		assertTrue(esPermitidoIngreso);
				
	}
	
	@Test
	public void vehiculoPlacaBSD123IngresaDiaDomingo(){
		
		IngresoServiceImpl serviceIngreso = new IngresoServiceImpl();
		
		Vehiculo veh = new VehiculoTestDataBuilder().
				conPlaca("BSD123").
				conCilindraje(124).
				build();
		Date domingoCincoAgosto2018 = new GregorianCalendar(2018, Calendar.AUGUST, 5).getTime();
		
		boolean esPermitidoIngreso = serviceIngreso.esDiaHabil(veh, domingoCincoAgosto2018);
		
		assertTrue(esPermitidoIngreso);
				
	}
	
	@Test
	public void vehiculoPlacaASD123NoIngresaDiaMartes(){
		
		IngresoServiceImpl serviceIngreso = new IngresoServiceImpl();
		
		Vehiculo veh = new VehiculoTestDataBuilder().
				conPlaca("ASD123").
				conCilindraje(124).
				build();
		Date martesSieteAgosto2018 = new GregorianCalendar(2018, Calendar.AUGUST, 7).getTime();
		
		boolean esPermitidoIngreso = serviceIngreso.esDiaHabil(veh, martesSieteAgosto2018);
		
		assertFalse(esPermitidoIngreso);
				
	}
	
	@Test
	public void vehiculoPlacaBSD123IngresaDiaMartes(){
		
		IngresoServiceImpl serviceIngreso = new IngresoServiceImpl();
		
		Vehiculo veh = new VehiculoTestDataBuilder().
				conPlaca("BSD123").
				conCilindraje(124).
				build();
		Date martesSieteAgosto2018 = new GregorianCalendar(2018, Calendar.AUGUST, 7).getTime();
		
		boolean esPermitidoIngreso = serviceIngreso.esDiaHabil(veh, martesSieteAgosto2018);
		
		assertTrue(esPermitidoIngreso);
				
	}

}
