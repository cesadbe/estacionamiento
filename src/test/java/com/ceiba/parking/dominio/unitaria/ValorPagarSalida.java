package com.ceiba.parking.dominio.unitaria;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StringUtils;

import com.ceiba.parking.ConfigValues;
import com.ceiba.parking.dominio.CalculadoraPago;
import com.ceiba.parking.dominio.controller.rest.dto.ConsultaResp;

public class ValorPagarSalida {
	
	Date fechaIngreso;
	
	@Before
	public void init() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		fechaIngreso = calendar.getTime();
	}

	@Test
	public void valorCarroFraccionHora() {
		int minutosSuma = 43;
		Date fechaSalida  = agregaMinutosAFecha(fechaIngreso, minutosSuma);
		Double valorPagar;
		Double valorPagarEsperado = 1000D;
		ConsultaResp carro = new ConsultaResp(
				"CSD123",
				ConfigValues.TipoVehiculo.CARRO.getTipo(),
				fechaIngreso,
				"1"
		);
		CalculadoraPago calculadora = new CalculadoraPago();
		valorPagar = calculadora.calcularPago(carro, fechaSalida);
				
		boolean hayValorCancelarCalculado = !StringUtils.isEmpty(valorPagar);
		assertTrue(hayValorCancelarCalculado);
		assertTrue(valorPagarEsperado.compareTo(valorPagar)==0);
	}
	
	@Test
	public void valorCarro1HoraExacta() {
		int minutosSuma = 60;
		Date fechaSalida  = agregaMinutosAFecha(fechaIngreso, minutosSuma);
		Double valorPagar;
		Double valorPagarEsperado = 1000D;
		ConsultaResp carro = new ConsultaResp(
				"CSD123",
				ConfigValues.TipoVehiculo.CARRO.getTipo(),
				fechaIngreso,
				"1"
		);
		CalculadoraPago calculadora = new CalculadoraPago();
		valorPagar = calculadora.calcularPago(carro, fechaSalida);
				
		boolean hayValorCancelarCalculado = !StringUtils.isEmpty(valorPagar);
		assertTrue(hayValorCancelarCalculado);
		assertTrue(valorPagarEsperado.compareTo(valorPagar)==0);
	}
	
	@Test
	public void valorCarro1Hora1Minuto() {
		int minutosSuma = 61;
		Date fechaSalida  = agregaMinutosAFecha(fechaIngreso, minutosSuma);
		Double valorPagar;
		Double valorPagarEsperado = 2000D;
		ConsultaResp carro = new ConsultaResp(
				"CSD123",
				ConfigValues.TipoVehiculo.CARRO.getTipo(),
				fechaIngreso,
				"1"
		);
		CalculadoraPago calculadora = new CalculadoraPago();
		valorPagar = calculadora.calcularPago(carro, fechaSalida);
				
		boolean hayValorCancelarCalculado = !StringUtils.isEmpty(valorPagar);
		assertTrue(hayValorCancelarCalculado);
		assertTrue(valorPagarEsperado.compareTo(valorPagar)==0);
	}
	
	@Test
	public void valorCarro2Horas59Minutos() {
		int minutosSuma = 179;
		Date fechaSalida  = agregaMinutosAFecha(fechaIngreso, minutosSuma);
		Double valorPagar;
		Double valorPagarEsperado = 3000D;
		ConsultaResp carro = new ConsultaResp(
				"CSD123",
				ConfigValues.TipoVehiculo.CARRO.getTipo(),
				fechaIngreso,
				"1"
		);
		CalculadoraPago calculadora = new CalculadoraPago();
		valorPagar = calculadora.calcularPago(carro, fechaSalida);
				
		boolean hayValorCancelarCalculado = !StringUtils.isEmpty(valorPagar);
		assertTrue(hayValorCancelarCalculado);
		assertTrue(valorPagarEsperado.compareTo(valorPagar)==0);
	}
	
	@Test
	public void valorCarro1Dia3Horas() {
		int minutosSuma = 1620;
		Date fechaSalida  = agregaMinutosAFecha(fechaIngreso, minutosSuma);
		Double valorPagar;
		Double valorPagarEsperado = 11000D;
		ConsultaResp carro = new ConsultaResp(
				"CSD123",
				ConfigValues.TipoVehiculo.CARRO.getTipo(),
				fechaIngreso,
				"1"
		);
		CalculadoraPago calculadora = new CalculadoraPago();
		valorPagar = calculadora.calcularPago(carro, fechaSalida);
				
		boolean hayValorCancelarCalculado = !StringUtils.isEmpty(valorPagar);
		assertTrue(hayValorCancelarCalculado);
		assertTrue(valorPagarEsperado.compareTo(valorPagar)==0);
	}
	
	@Test
	public void valorCarro1Dia20Horas() {
		int minutosSuma = 2640;
		Date fechaSalida  = agregaMinutosAFecha(fechaIngreso, minutosSuma);
		Double valorPagar;
		Double valorPagarEsperado = 16000D;
		ConsultaResp carro = new ConsultaResp(
				"CSD123",
				ConfigValues.TipoVehiculo.CARRO.getTipo(),
				fechaIngreso,
				"1"
		);
		CalculadoraPago calculadora = new CalculadoraPago();
		valorPagar = calculadora.calcularPago(carro, fechaSalida);
				
		boolean hayValorCancelarCalculado = !StringUtils.isEmpty(valorPagar);
		assertTrue(hayValorCancelarCalculado);
		assertTrue(valorPagarEsperado.compareTo(valorPagar)==0);
	}
	
	@Test
	public void valorCarro2Dias8Horas() {
		int minutosSuma = 3360;
		Date fechaSalida  = agregaMinutosAFecha(fechaIngreso, minutosSuma);
		Double valorPagar;
		Double valorPagarEsperado = 24000D;
		ConsultaResp carro = new ConsultaResp(
				"CSD123",
				ConfigValues.TipoVehiculo.CARRO.getTipo(),
				fechaIngreso,
				"1"
		);
		CalculadoraPago calculadora = new CalculadoraPago();
		valorPagar = calculadora.calcularPago(carro, fechaSalida);
				
		boolean hayValorCancelarCalculado = !StringUtils.isEmpty(valorPagar);
		assertTrue(hayValorCancelarCalculado);
		assertTrue(valorPagarEsperado.compareTo(valorPagar)==0);
	}
	
	
	
	
	@Test
	public void valorMotoBajoCilindrajeFraccionHora() {
		int minutosSuma = 43;
		Date fechaSalida  = agregaMinutosAFecha(fechaIngreso, minutosSuma);
		Double valorPagar;
		Double valorPagarEsperado = 500D;
		ConsultaResp moto = new ConsultaResp(
				"JDR10B",
				ConfigValues.TipoVehiculo.MOTO.getTipo(),
				fechaIngreso,
				"1"
		);
		CalculadoraPago calculadora = new CalculadoraPago();
		valorPagar = calculadora.calcularPago(moto, fechaSalida);
				
		boolean hayValorCancelarCalculado = !StringUtils.isEmpty(valorPagar);
		assertTrue(hayValorCancelarCalculado);
		assertTrue(valorPagarEsperado.compareTo(valorPagar)==0);
	}
	
	@Test
	public void valorMotoBajoCilindraje1HoraExacta() {
		int minutosSuma = 60;
		Date fechaSalida  = agregaMinutosAFecha(fechaIngreso, minutosSuma);
		Double valorPagar;
		Double valorPagarEsperado = 500D;
		ConsultaResp moto = new ConsultaResp(
				"JR10B",
				ConfigValues.TipoVehiculo.MOTO.getTipo(),
				fechaIngreso,
				"1"
		);
		CalculadoraPago calculadora = new CalculadoraPago();
		valorPagar = calculadora.calcularPago(moto, fechaSalida);
				
		boolean hayValorCancelarCalculado = !StringUtils.isEmpty(valorPagar);
		assertTrue(hayValorCancelarCalculado);
		assertTrue(valorPagarEsperado.compareTo(valorPagar)==0);
	}
	
	@Test
	public void valorMotoBajoCilindraje1Hora1Minuto() {
		int minutosSuma = 61;
		Date fechaSalida  = agregaMinutosAFecha(fechaIngreso, minutosSuma);
		Double valorPagar;
		Double valorPagarEsperado = 1000D;
		ConsultaResp moto = new ConsultaResp(
				"JDR10B",
				ConfigValues.TipoVehiculo.MOTO.getTipo(),
				fechaIngreso,
				"1"
		);
		CalculadoraPago calculadora = new CalculadoraPago();
		valorPagar = calculadora.calcularPago(moto, fechaSalida);
				
		boolean hayValorCancelarCalculado = !StringUtils.isEmpty(valorPagar);
		assertTrue(hayValorCancelarCalculado);
		assertTrue(valorPagarEsperado.compareTo(valorPagar)==0);
	}
	
	@Test
	public void valorMotoBajoCilindraje2Horas59Minutos() {
		int minutosSuma = 179;
		Date fechaSalida  = agregaMinutosAFecha(fechaIngreso, minutosSuma);
		Double valorPagar;
		Double valorPagarEsperado = 1500D;
		ConsultaResp moto = new ConsultaResp(
				"JDR10B",
				ConfigValues.TipoVehiculo.MOTO.getTipo(),
				fechaIngreso,
				"1"
		);
		CalculadoraPago calculadora = new CalculadoraPago();
		valorPagar = calculadora.calcularPago(moto, fechaSalida);
				
		boolean hayValorCancelarCalculado = !StringUtils.isEmpty(valorPagar);
		assertTrue(hayValorCancelarCalculado);
		assertTrue(valorPagarEsperado.compareTo(valorPagar)==0);
	}
	
	@Test
	public void valorMotoBajoCilindraje1Dia3Horas() {
		int minutosSuma = 1620;
		Date fechaSalida  = agregaMinutosAFecha(fechaIngreso, minutosSuma);
		Double valorPagar;
		Double valorPagarEsperado = 5500D;
		ConsultaResp moto = new ConsultaResp(
				"JDR10B",
				ConfigValues.TipoVehiculo.MOTO.getTipo(),
				fechaIngreso,
				"1"
		);
		CalculadoraPago calculadora = new CalculadoraPago();
		valorPagar = calculadora.calcularPago(moto, fechaSalida);
				
		boolean hayValorCancelarCalculado = !StringUtils.isEmpty(valorPagar);
		assertTrue(hayValorCancelarCalculado);
		assertTrue(valorPagarEsperado.compareTo(valorPagar)==0);
	}
	
	@Test
	public void valorMotoBajoCilindraje1Dia20Horas() {
		int minutosSuma = 2640;
		Date fechaSalida  = agregaMinutosAFecha(fechaIngreso, minutosSuma);
		Double valorPagar;
		Double valorPagarEsperado = 8000D;
		ConsultaResp moto = new ConsultaResp(
				"JDR10B",
				ConfigValues.TipoVehiculo.MOTO.getTipo(),
				fechaIngreso,
				"1"
		);
		CalculadoraPago calculadora = new CalculadoraPago();
		valorPagar = calculadora.calcularPago(moto, fechaSalida);
				
		boolean hayValorCancelarCalculado = !StringUtils.isEmpty(valorPagar);
		assertTrue(hayValorCancelarCalculado);
		assertTrue(valorPagarEsperado.compareTo(valorPagar)==0);
	}
	
	@Test
	public void valorMotoBajoCilindraje2Dias8Horas() {
		int minutosSuma = 3360;
		Date fechaSalida  = agregaMinutosAFecha(fechaIngreso, minutosSuma);
		Double valorPagar;
		Double valorPagarEsperado = 12000D;
		ConsultaResp moto = new ConsultaResp(
				"JDR10B",
				ConfigValues.TipoVehiculo.MOTO.getTipo(),
				fechaIngreso,
				"1"
		);
		CalculadoraPago calculadora = new CalculadoraPago();
		valorPagar = calculadora.calcularPago(moto, fechaSalida);
				
		boolean hayValorCancelarCalculado = !StringUtils.isEmpty(valorPagar);
		assertTrue(hayValorCancelarCalculado);
		assertTrue(valorPagarEsperado.compareTo(valorPagar)==0);
	}
	
	
	
	
	@Test
	public void valorMotoAltoCilindrajeFraccionHora() {
		int minutosSuma = 43;
		Date fechaSalida  = agregaMinutosAFecha(fechaIngreso, minutosSuma);
		Double valorPagar;
		Double valorPagarEsperado = 2500D;
		ConsultaResp moto = new ConsultaResp(
				"JDR10B",
				ConfigValues.TipoVehiculo.MOTO.getTipo(),
				fechaIngreso,
				"1"
		);
		moto.setAltoCilindraje(true);
		CalculadoraPago calculadora = new CalculadoraPago();
		valorPagar = calculadora.calcularPago(moto, fechaSalida);
				
		boolean hayValorCancelarCalculado = !StringUtils.isEmpty(valorPagar);
		assertTrue(hayValorCancelarCalculado);
		assertTrue(valorPagarEsperado.compareTo(valorPagar)==0);
	}
	
	@Test
	public void valorAltoCilindraje1HoraExacta() {
		int minutosSuma = 60;
		Date fechaSalida  = agregaMinutosAFecha(fechaIngreso, minutosSuma);
		Double valorPagar;
		Double valorPagarEsperado = 2500D;
		ConsultaResp moto = new ConsultaResp(
				"JR10B",
				ConfigValues.TipoVehiculo.MOTO.getTipo(),
				fechaIngreso,
				"1"
		);
		moto.setAltoCilindraje(true);
		CalculadoraPago calculadora = new CalculadoraPago();
		valorPagar = calculadora.calcularPago(moto, fechaSalida);
				
		boolean hayValorCancelarCalculado = !StringUtils.isEmpty(valorPagar);
		assertTrue(hayValorCancelarCalculado);
		assertTrue(valorPagarEsperado.compareTo(valorPagar)==0);
	}
	
	@Test
	public void valorAltoCilindraje1Hora1Minuto() {
		int minutosSuma = 61;
		Date fechaSalida  = agregaMinutosAFecha(fechaIngreso, minutosSuma);
		Double valorPagar;
		Double valorPagarEsperado = 3000D;
		ConsultaResp moto = new ConsultaResp(
				"JDR10B",
				ConfigValues.TipoVehiculo.MOTO.getTipo(),
				fechaIngreso,
				"1"
		);
		moto.setAltoCilindraje(true);
		CalculadoraPago calculadora = new CalculadoraPago();
		valorPagar = calculadora.calcularPago(moto, fechaSalida);
				
		boolean hayValorCancelarCalculado = !StringUtils.isEmpty(valorPagar);
		assertTrue(hayValorCancelarCalculado);
		assertTrue(valorPagarEsperado.compareTo(valorPagar)==0);
	}
	
	@Test
	public void valorAltoCilindraje2Horas59Minutos() {
		int minutosSuma = 179;
		Date fechaSalida  = agregaMinutosAFecha(fechaIngreso, minutosSuma);
		Double valorPagar;
		Double valorPagarEsperado = 3500D;
		ConsultaResp moto = new ConsultaResp(
				"JDR10B",
				ConfigValues.TipoVehiculo.MOTO.getTipo(),
				fechaIngreso,
				"1"
		);
		moto.setAltoCilindraje(true);
		CalculadoraPago calculadora = new CalculadoraPago();
		valorPagar = calculadora.calcularPago(moto, fechaSalida);
				
		boolean hayValorCancelarCalculado = !StringUtils.isEmpty(valorPagar);
		assertTrue(hayValorCancelarCalculado);
		assertTrue(valorPagarEsperado.compareTo(valorPagar)==0);
	}
	
	@Test
	public void valorAltoCilindraje1Dia3Horas() {
		int minutosSuma = 1620;
		Date fechaSalida  = agregaMinutosAFecha(fechaIngreso, minutosSuma);
		Double valorPagar;
		Double valorPagarEsperado = 7500D;
		ConsultaResp moto = new ConsultaResp(
				"JDR10B",
				ConfigValues.TipoVehiculo.MOTO.getTipo(),
				fechaIngreso,
				"1"
		);
		moto.setAltoCilindraje(true);
		CalculadoraPago calculadora = new CalculadoraPago();
		valorPagar = calculadora.calcularPago(moto, fechaSalida);
				
		boolean hayValorCancelarCalculado = !StringUtils.isEmpty(valorPagar);
		assertTrue(hayValorCancelarCalculado);
		assertTrue(valorPagarEsperado.compareTo(valorPagar)==0);
	}
	
	@Test
	public void valorAltoCilindraje1Dia20Horas() {
		int minutosSuma = 2640;
		Date fechaSalida  = agregaMinutosAFecha(fechaIngreso, minutosSuma);
		Double valorPagar;
		Double valorPagarEsperado = 10000D;
		ConsultaResp moto = new ConsultaResp(
				"JDR10B",
				ConfigValues.TipoVehiculo.MOTO.getTipo(),
				fechaIngreso,
				"1"
		);
		moto.setAltoCilindraje(true);
		CalculadoraPago calculadora = new CalculadoraPago();
		valorPagar = calculadora.calcularPago(moto, fechaSalida);
				
		boolean hayValorCancelarCalculado = !StringUtils.isEmpty(valorPagar);
		assertTrue(hayValorCancelarCalculado);
		assertTrue(valorPagarEsperado.compareTo(valorPagar)==0);
	}
	
	@Test
	public void valorAltoCilindraje2Dias8Horas() {
		int minutosSuma = 3360;
		Date fechaSalida  = agregaMinutosAFecha(fechaIngreso, minutosSuma);
		Double valorPagar;
		Double valorPagarEsperado = 14000D;
		ConsultaResp moto = new ConsultaResp(
				"JDR10B",
				ConfigValues.TipoVehiculo.MOTO.getTipo(),
				fechaIngreso,
				"1"
		);
		moto.setAltoCilindraje(true);
		CalculadoraPago calculadora = new CalculadoraPago();
		valorPagar = calculadora.calcularPago(moto, fechaSalida);
				
		boolean hayValorCancelarCalculado = !StringUtils.isEmpty(valorPagar);
		assertTrue(hayValorCancelarCalculado);
		assertTrue(valorPagarEsperado.compareTo(valorPagar)==0);
	}
	
	
	private Date agregaMinutosAFecha(Date fechaInicial, int minutos) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaInicial);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		calendar.add(Calendar.MINUTE, minutos);
		
		return calendar.getTime();
	}

}
