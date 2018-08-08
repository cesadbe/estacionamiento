package com.ceiba.parking.dominio.integracion;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.ceiba.parking.ConfigValues;
import com.ceiba.parking.controller.rest.dto.ConsultaResp;
import com.ceiba.parking.controller.rest.dto.IngresoReq;
import com.ceiba.parking.controller.rest.dto.IngresoResp;
import com.ceiba.parking.dominio.testdatabuilder.VehiculoTestDataBuilder;
import com.ceiba.parking.model.Vehiculo;
import com.ceiba.parking.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConsultaVehiculos {
	
	@LocalServerPort
	private int port;
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@Before
	public void limpiarParqueados() {
		Util.borrarIngresos(port);
	}
	
    @Test
	public void consultaAutomovilParqueadoPorPlaca() {
    			
    	String placa = "BXP873";
    	Date fechaActual = new Date();
    	String ticketGenerado;
    	
    	Vehiculo carro = new VehiculoTestDataBuilder().
				deTipo(ConfigValues.TipoVehiculo.CARRO.getTipo()).
				conPlaca(placa).
				build();
    	
    	IngresoReq request = new IngresoReq(carro, fechaActual);
		ResponseEntity<IngresoResp> responseEntity = restTemplate.postForEntity("/api/ingreso", request, IngresoResp.class);
		IngresoResp responseIngreso = responseEntity.getBody();
		ticketGenerado = responseIngreso.getTicket();
    	    	
		ConsultaResp response = restTemplate.getForObject("/api/consulta/vehiculo?placa="+placa, ConsultaResp.class);
		
		boolean estaVehiculoRegistrado = (placa.equalsIgnoreCase(response.getPlaca()) && ticketGenerado.equalsIgnoreCase(response.getTicket()) ) ;
		assertTrue(estaVehiculoRegistrado);		

    }
    
    @Test
	public void consultaAutomovilParqueadoPorTicket() {
    			
    	String placa = "BXP8734";
    	Date fechaActual = new Date();
    	String ticketGenerado;
    	
    	Vehiculo carro = new VehiculoTestDataBuilder().
				deTipo(ConfigValues.TipoVehiculo.CARRO.getTipo()).
				conPlaca(placa).
				build();
    	
    	IngresoReq request = new IngresoReq(carro, fechaActual);
		ResponseEntity<IngresoResp> responseEntity = restTemplate.postForEntity("/api/ingreso", request, IngresoResp.class);
		IngresoResp responseIngreso = responseEntity.getBody();
		ticketGenerado = responseIngreso.getTicket();
    	    	
		ConsultaResp response = restTemplate.getForObject("/api/consulta/vehiculo?ticket="+ticketGenerado, ConsultaResp.class);
		
		boolean estaVehiculoRegistrado = (placa.equalsIgnoreCase(response.getPlaca()) && ticketGenerado.equalsIgnoreCase(response.getTicket()) ) ;
		assertTrue(estaVehiculoRegistrado);		

    }    
    
    @Test
	public void consultaAutomovilNoParqueado() {
    	String placa = "LDC873";    	
    	
		ConsultaResp response = restTemplate.getForObject("/api/consulta/vehiculo?placa="+placa, ConsultaResp.class);
		
		boolean estaVehiculoRegistrado = !( StringUtils.isEmpty(response.getTicket()) && StringUtils.isEmpty(response.getPlaca()) ) ;
		assertFalse(estaVehiculoRegistrado);		

    }
    
    @Test
	public void consultaAutomovilNoParametros() {    	
    	
		ConsultaResp response = restTemplate.getForObject("/api/consulta/vehiculo", ConsultaResp.class);
		
		boolean hayMensaje = ( ConfigValues.EXC_NO_PARAMETROS.equalsIgnoreCase(response.getMessage()) );
		assertTrue(hayMensaje);		

    }
    
    @Test
	public void consultaTotalVehiculosParqueados() {
       	Date fechaActual = new Date();
    	
    	Vehiculo carro = new VehiculoTestDataBuilder().
				deTipo(ConfigValues.TipoVehiculo.CARRO.getTipo()).
				build();    	
    	IngresoReq request;
    	IngresoResp responseIngreso;
    	ResponseEntity<IngresoResp> responseEntity;
    	
    	carro.setPlaca("BXP873");
    	request = new IngresoReq(carro, fechaActual);
    	responseEntity = restTemplate.postForEntity("/api/ingreso", request, IngresoResp.class);
		responseIngreso = responseEntity.getBody();
		System.out.println(responseIngreso);		

		carro.setPlaca("DSD123");
		request = new IngresoReq(carro, fechaActual);
    	responseEntity = restTemplate.postForEntity("/api/ingreso", request, IngresoResp.class);
		responseIngreso = responseEntity.getBody();
		System.out.println(responseIngreso);		
		
		carro.setPlaca("BXP873");
		request = new IngresoReq(carro, fechaActual);
    	responseEntity = restTemplate.postForEntity("/api/ingreso", request, IngresoResp.class);
		responseIngreso = responseEntity.getBody();
		System.out.println(responseIngreso);		
		
		carro.setPlaca("DSD111");
		request = new IngresoReq(carro, fechaActual);
    	responseEntity = restTemplate.postForEntity("/api/ingreso", request, IngresoResp.class);
		responseIngreso = responseEntity.getBody();
		System.out.println(responseIngreso);		
				
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<ConsultaResp>> response = restTemplate.exchange(
		  "http://localhost:" + port + "/api/consulta",
		  HttpMethod.GET,
		  null,
		  new ParameterizedTypeReference<List<ConsultaResp>>(){});
		List<ConsultaResp> vehiculos = response.getBody();
		
		assertTrue(vehiculos.size()==3);		

    }
    

}
