package com.ceiba.parking;

public class ConfigValues {

	private ConfigValues() {
		
	}
	
	public enum TipoVehiculo {
		CARRO ("C", "Carro"),
		MOTO ("M", "Motocicleta"),
		BICI ("B", "BICICLETA");
		
		private final String tipo;
		private final String descripcion;
		TipoVehiculo(String tipo_, String descripcion_){
			this.tipo = tipo_;
			this.descripcion = descripcion_;
		}
		public String getTipo() {
			return tipo;
		}
		
		public String getDescripcion() {
			return descripcion;
		}
	
	}
	
	
	/**
	 * PATH URL
	 */
	public static final String API = "api";
	public static final String PATH_INGRESO = "/ingreso";
	
	/**
	 * CONFIGURACIONES
	 */
	
	public static final String VEHICULOS_PERMITIDOS = "C,M";
	public static final long MAX_MOTOS = 10;
	public static final long MAX_AUTOS = 20;
	
		
	/**
	 * MENSAJES DE EXCEPCIONES
	 */
	public static final String EXC_VEHICULO_NO_PERMITIDO = "Tipo de Vehiculo no permitido";
	public static final String EXC_EXCEDE_CUPO = "No existen celdas disponibles";
	public static final String EXC_NO_ES_DIA_HABIL = "No se puede ingresar porque no está en un día hábil";
}
