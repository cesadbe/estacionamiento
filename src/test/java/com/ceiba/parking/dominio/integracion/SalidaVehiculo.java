package com.ceiba.parking.dominio.integracion;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import com.ceiba.parking.ConfigValues;
import com.ceiba.parking.controller.rest.dto.IngresoReq;
import com.ceiba.parking.controller.rest.dto.IngresoResp;
import com.ceiba.parking.controller.rest.dto.SalidaReq;
import com.ceiba.parking.controller.rest.dto.SalidaResp;
import com.ceiba.parking.dominio.testdatabuilder.VehiculoTestDataBuilder;
import com.ceiba.parking.model.Vehiculo;
import com.ceiba.parking.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SalidaVehiculo {

	@Autowired
    private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	@Before
	public void limpiarParqueados() {
		Util.borrarIngresos(port);
	}
	
    @Test
	public void salidaCarro1Hora() {
    			
    	String placa = "BXP878";
    	Date fechaSalida = new Date();
    	String ticketGenerado;
    	Double valorCancelarRecibido;
    	Double valorCancelarEsperado = 0d;
    	
    	Vehiculo carro = new VehiculoTestDataBuilder().
				deTipo(ConfigValues.TipoVehiculo.CARRO.getTipo()).
				conPlaca(placa).
				build();
    	
    	IngresoReq request = new IngresoReq(carro, null);
		ResponseEntity<IngresoResp> responseEntity = restTemplate.postForEntity("/api/ingreso", request, IngresoResp.class);
		IngresoResp responseIngreso = responseEntity.getBody();
		ticketGenerado = responseIngreso.getTicket();
		
		SalidaReq requestSalida = new SalidaReq(placa, ticketGenerado, fechaSalida);
		ResponseEntity<SalidaResp> responseSalidaEntity = restTemplate.postForEntity("/api/salida", requestSalida, SalidaResp.class);
		SalidaResp responseSalida = responseSalidaEntity.getBody();
		valorCancelarRecibido = responseSalida.getValorPagar();
    	    			
		boolean hayValorCancelarCalculado = valorCancelarEsperado.compareTo(valorCancelarRecibido)==0;
		assertTrue(hayValorCancelarCalculado);

    }
    
    
    @Test
	public void salidaCarroNoRegistrado() {
    			
    	String placa = "BXP878";
    	Date fechaSalida = new Date();

		
		SalidaReq requestSalida = new SalidaReq(placa, null, fechaSalida);
		ResponseEntity<SalidaResp> responseSalidaEntity = restTemplate.postForEntity("/api/salida", requestSalida, SalidaResp.class);
		SalidaResp responseSalida = responseSalidaEntity.getBody();
		String valorCancelarRecibido = "" + responseSalida.getValorPagar();
    	    			
		boolean hayValorCancelarRecibido = StringUtils.isEmpty(valorCancelarRecibido);
		assertTrue(!hayValorCancelarRecibido);

    }

}
