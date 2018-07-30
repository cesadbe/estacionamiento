package com.ceiba.parking.dominio.testdatabuilder;

import com.ceiba.parking.model.Vehiculo;

public class VehiculoTestDataBuilder {
	
	private static final String PLACA_DEFECTO = "ASD123";
	private static final String TIPO_DEFECTO = "C";
	private static final int CILINDRAJE_DEFECTO = 1599;
	
	private String placa;
	private String tipo;
	private int cilindraje;
	
	public VehiculoTestDataBuilder() {
		this.placa = PLACA_DEFECTO;
		this.tipo = TIPO_DEFECTO;
		this.cilindraje = CILINDRAJE_DEFECTO;
	}	
	
	public Vehiculo build() {
		return new Vehiculo(this.placa, this.tipo, this.cilindraje);
	}
	
	public VehiculoTestDataBuilder conPlaca(String placa_) {
		this.placa = placa_;
		return this;
	}

	public VehiculoTestDataBuilder deTipo(String tipo_) {
		this.tipo = tipo_;
		return this;
	}
	
	public VehiculoTestDataBuilder conCilindraje(int cilindraje_) {
		this.cilindraje = cilindraje_;
		return this;
	}
}
