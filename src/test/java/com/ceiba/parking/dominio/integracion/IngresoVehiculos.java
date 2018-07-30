package com.ceiba.parking.dominio.integracion;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import com.ceiba.parking.ConfigValues;
import com.ceiba.parking.dominio.controller.rest.dto.IngresoReq;
import com.ceiba.parking.dominio.controller.rest.dto.IngresoResp;
import com.ceiba.parking.dominio.testdatabuilder.VehiculoTestDataBuilder;
import com.ceiba.parking.model.Vehiculo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IngresoVehiculos {
	
	@Autowired
    private TestRestTemplate restTemplate;
	
    @Test
	public void ingresaVehiculoTipoBicicleta() {
    	Vehiculo bici = new VehiculoTestDataBuilder().
				deTipo(ConfigValues.TipoVehiculo.BICI.getTipo()).
				conPlaca("").
				conCilindraje(0).
				build();
    	Date lunesSeisAgosto2018 = new GregorianCalendar(2018, Calendar.AUGUST, 6).getTime();
		
    	IngresoReq request = new IngresoReq(bici, lunesSeisAgosto2018);
    	ResponseEntity<IngresoResp> responseEntity = restTemplate.postForEntity("/api/ingreso", request, IngresoResp.class);
        IngresoResp response = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            
		boolean generoTicket = !StringUtils.isEmpty(response.getTicket());
		
		assertFalse(generoTicket);
	}
	
	
	@Test
    public void ingresaVehiculoTipoMotoParqueadero_1_Vacio() {
		Vehiculo moto = new VehiculoTestDataBuilder().
				deTipo(ConfigValues.TipoVehiculo.MOTO.getTipo()).
				conPlaca("JDR10B").
				conCilindraje(124).
				build();
		Date lunesSeisAgosto2018 = new GregorianCalendar(2018, Calendar.AUGUST, 6).getTime();
		
		IngresoReq request = new IngresoReq(moto, lunesSeisAgosto2018);		
        ResponseEntity<IngresoResp> responseEntity = restTemplate.postForEntity("/api/ingreso", request, IngresoResp.class);
        IngresoResp response = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        
        boolean generoTicket = !StringUtils.isEmpty(response.getTicket());
		assertTrue(generoTicket);
    }

	@Test
	public void ingresaVehiculoTipoCarroParqueadero_1_Vacio() {
		Vehiculo carro = new VehiculoTestDataBuilder().
				deTipo(ConfigValues.TipoVehiculo.CARRO.getTipo()).
				conPlaca("BXP873").
				conCilindraje(1599).
				build();
		Date lunesSeisAgosto2018 = new GregorianCalendar(2018, Calendar.AUGUST, 6).getTime();
		
		IngresoReq request = new IngresoReq(carro, lunesSeisAgosto2018);
        ResponseEntity<IngresoResp> responseEntity = restTemplate.postForEntity("/api/ingreso", request, IngresoResp.class);
        IngresoResp response = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        
        boolean generoTicket = !StringUtils.isEmpty(response.getTicket());
		assertTrue(generoTicket);
	}	
	
	@Test
	public void ingresaVehiculoTipoMotoParqueadero_2_Lleno() {		
		
		Vehiculo moto = new VehiculoTestDataBuilder().
				deTipo(ConfigValues.TipoVehiculo.MOTO.getTipo()).
				conCilindraje(124).
				build();
		IngresoReq request;
		Date lunesSeisAgosto2018 = new GregorianCalendar(2018, Calendar.AUGUST, 6).getTime();
		
		for(int i = 0; i< ConfigValues.MAX_MOTOS; i++) {			
			moto.setPlaca("ASD" + i + "A");
			request = new IngresoReq(moto, lunesSeisAgosto2018);
			restTemplate.postForEntity("/api/ingreso", request, IngresoResp.class);
		}

		moto.setPlaca("ASD" + ConfigValues.MAX_MOTOS + "A");
		request = new IngresoReq(moto, lunesSeisAgosto2018);
		ResponseEntity<IngresoResp> responseEntity = restTemplate.postForEntity("/api/ingreso", request, IngresoResp.class);
		
        IngresoResp response = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            
		boolean generoTicket = !StringUtils.isEmpty(response.getTicket());		
		assertFalse(generoTicket);
		
		assertEquals(ConfigValues.EXC_EXCEDE_CUPO, response.getMessage());
		
	}
	
	@Test
	public void ingresaVehiculoTipoCarroParqueadero_2_Lleno() {
		Vehiculo carro = new VehiculoTestDataBuilder().
				deTipo(ConfigValues.TipoVehiculo.CARRO.getTipo()).
				conCilindraje(1599).
				build();
		
		IngresoReq request;
		Date lunesSeisAgosto2018 = new GregorianCalendar(2018, Calendar.AUGUST, 6).getTime();
		
		for(int i = 0; i< ConfigValues.MAX_AUTOS; i++) {
			carro.setPlaca("BXP0" + i);
			request = new IngresoReq(carro, lunesSeisAgosto2018);
			restTemplate.postForEntity("/api/ingreso", request, IngresoResp.class);
		}
		
		carro.setPlaca("BXP" + ConfigValues.MAX_AUTOS);
		request = new IngresoReq(carro, lunesSeisAgosto2018);
		ResponseEntity<IngresoResp> responseEntity = restTemplate.postForEntity("/api/ingreso", request, IngresoResp.class);
		
        IngresoResp response = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            
		boolean generoTicket = !StringUtils.isEmpty(response.getTicket());		
		assertFalse(generoTicket);
		
		assertEquals(ConfigValues.EXC_EXCEDE_CUPO, response.getMessage());
	}
	
		
	@Test
	public void ingresaVehiculoPlacaABC123Martes() {
		Vehiculo veh = new VehiculoTestDataBuilder().
				conPlaca("ABC123").
				build();
		Date martesSieteAgosto2018 = new GregorianCalendar(2018, Calendar.AUGUST, 7).getTime();
		
		IngresoReq request = new IngresoReq(veh, martesSieteAgosto2018);		
        ResponseEntity<IngresoResp> responseEntity = restTemplate.postForEntity("/api/ingreso", request, IngresoResp.class);
        IngresoResp response = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        
        boolean generoTicket = !StringUtils.isEmpty(response.getTicket());
        assertFalse(generoTicket);		
		assertEquals(ConfigValues.EXC_NO_ES_DIA_HABIL, response.getMessage());
	}
	
	@Test
	public void ingresaVehiculoPlacaABC123Domingo() {
		Vehiculo veh = new VehiculoTestDataBuilder().
				conPlaca("ABC123").
				build();
		Date domingoCincoAgosto2018 = new GregorianCalendar(2018, Calendar.AUGUST, 5).getTime();
		
		IngresoReq request = new IngresoReq(veh, domingoCincoAgosto2018);		
        ResponseEntity<IngresoResp> responseEntity = restTemplate.postForEntity("/api/ingreso", request, IngresoResp.class);
        IngresoResp response = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        
        boolean generoTicket = !StringUtils.isEmpty(response.getTicket());
        assertTrue(generoTicket);
	}
	
	@Test
	public void ingresaVehiculoPlacaABC123Lunes() {
		Vehiculo veh = new VehiculoTestDataBuilder().
				conPlaca("ABC123").
				build();
		Date lunesSeisAgosto2018 = new GregorianCalendar(2018, Calendar.AUGUST, 6).getTime();
		
		IngresoReq request = new IngresoReq(veh, lunesSeisAgosto2018);		
        ResponseEntity<IngresoResp> responseEntity = restTemplate.postForEntity("/api/ingreso", request, IngresoResp.class);
        IngresoResp response = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        
        boolean generoTicket = !StringUtils.isEmpty(response.getTicket());
        assertTrue(generoTicket);
	}
	
	@Test
	public void ingresaVehiculoPlacaABC123Viernes() {
		Vehiculo veh = new VehiculoTestDataBuilder().
				conPlaca("ABC123").
				build();
		Date viernesTresAgosto2018 = new GregorianCalendar(2018, Calendar.AUGUST, 3).getTime();
		
		IngresoReq request = new IngresoReq(veh, viernesTresAgosto2018);		
        ResponseEntity<IngresoResp> responseEntity = restTemplate.postForEntity("/api/ingreso", request, IngresoResp.class);
        IngresoResp response = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        
        boolean generoTicket = !StringUtils.isEmpty(response.getTicket());
        assertFalse(generoTicket);		
		assertEquals(ConfigValues.EXC_NO_ES_DIA_HABIL, response.getMessage());
	}

}
