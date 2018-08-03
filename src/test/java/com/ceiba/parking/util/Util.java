package com.ceiba.parking.util;

import java.util.Date;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.ceiba.parking.dominio.controller.rest.dto.ConsultaResp;
import com.ceiba.parking.dominio.controller.rest.dto.SalidaReq;
import com.ceiba.parking.dominio.controller.rest.dto.SalidaResp;

public class Util {

	public static void borrarIngresos(int port) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<ConsultaResp>> response = restTemplate.exchange(
		  "http://localhost:" + port + "/api/consulta",
		  HttpMethod.GET,
		  null,
		  new ParameterizedTypeReference<List<ConsultaResp>>(){});
		List<ConsultaResp> vehiculos = response.getBody();
		
		for(ConsultaResp v : vehiculos) {
			SalidaReq requestSalida = new SalidaReq(v.getPlaca(), v.getTicket(), new Date());
			ResponseEntity<SalidaResp> responseSalidaEntity = restTemplate.postForEntity("http://localhost:" + port + "/api/salida", requestSalida, SalidaResp.class);
			SalidaResp responseSalida = responseSalidaEntity.getBody();
			System.out.println(responseSalida.getValorPagar());
		}
	}
	
}
