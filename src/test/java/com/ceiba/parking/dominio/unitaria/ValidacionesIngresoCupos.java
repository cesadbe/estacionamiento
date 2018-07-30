package com.ceiba.parking.dominio.unitaria;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.ceiba.parking.ConfigValues;
import com.ceiba.parking.dominio.testdatabuilder.VehiculoTestDataBuilder;
import com.ceiba.parking.model.Vehiculo;
import com.ceiba.parking.repository.AparcamientoRepository;
import com.ceiba.parking.service.impl.IngresoServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ValidacionesIngresoCupos {
	
	@Mock
	IngresoServiceImpl serviceIngreso;
	
	AparcamientoRepository repo;
	
	@Test
	public void ingresaMotoParqueaderoVacio() {
		IngresoServiceImpl serviceIngreso = new IngresoServiceImpl();
		
		Vehiculo veh = new VehiculoTestDataBuilder().
				deTipo(ConfigValues.TipoVehiculo.MOTO.getTipo()).
				build();
		
		repo = Mockito.mock(AparcamientoRepository.class);
		when(repo.vehiculosParqueadosSegunTipo("M")).thenReturn(0l);
		
		ReflectionTestUtils.setField(serviceIngreso, "repository", repo);
		
		boolean esPermitidoIngreso = serviceIngreso.hayCupoDisponible(veh);		
		assertTrue(esPermitidoIngreso);
	}
	
	@Test
	public void ingresaMotoParqueaderoLleno() {
		IngresoServiceImpl serviceIngreso = new IngresoServiceImpl();
		
		Vehiculo veh = new VehiculoTestDataBuilder().
				deTipo(ConfigValues.TipoVehiculo.MOTO.getTipo()).
				build();
		
		long maximaCapacidadMotos = ConfigValues.MAX_MOTOS;
		
		repo = Mockito.mock(AparcamientoRepository.class);
		when(repo.vehiculosParqueadosSegunTipo("M")).thenReturn(maximaCapacidadMotos);
		
		ReflectionTestUtils.setField(serviceIngreso, "repository", repo);
		
		boolean esPermitidoIngreso = serviceIngreso.hayCupoDisponible(veh);		
		assertFalse(esPermitidoIngreso);
	}
	
	@Test
	public void ingresaCarroParqueaderoVacio() {
		IngresoServiceImpl serviceIngreso = new IngresoServiceImpl();
		
		Vehiculo veh = new VehiculoTestDataBuilder().
				deTipo(ConfigValues.TipoVehiculo.CARRO.getTipo()).
				build();
		
		repo = Mockito.mock(AparcamientoRepository.class);
		when(repo.vehiculosParqueadosSegunTipo("C")).thenReturn(0l);
		
		ReflectionTestUtils.setField(serviceIngreso, "repository", repo);
		
		boolean esPermitidoIngreso = serviceIngreso.hayCupoDisponible(veh);		
		assertTrue(esPermitidoIngreso);		
	}
	
	@Test
	public void ingresaCarroParqueaderoLleno() {
		IngresoServiceImpl serviceIngreso = new IngresoServiceImpl();
		
		Vehiculo veh = new VehiculoTestDataBuilder().
				deTipo(ConfigValues.TipoVehiculo.CARRO.getTipo()).
				build();
		
		long maximaCapacidadCarros = ConfigValues.MAX_AUTOS;
		
		repo = Mockito.mock(AparcamientoRepository.class);
		when(repo.vehiculosParqueadosSegunTipo("C")).thenReturn(maximaCapacidadCarros);
		
		ReflectionTestUtils.setField(serviceIngreso, "repository", repo);
		
		boolean esPermitidoIngreso = serviceIngreso.hayCupoDisponible(veh);		
		assertFalse(esPermitidoIngreso);	
	}


}
