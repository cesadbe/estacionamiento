package com.ceiba.parking;

import org.springframework.stereotype.Component;

@Component
public final class ConfigValues {

	private ConfigValues() {
		
	}
	
	public enum TipoVehiculo {
		CARRO ("C", "Carro"),
		MOTO ("M", "Motocicleta"),
		BICI ("B", "BICICLETA");
		
		private final String tipo;
		private final String descripcion;
		TipoVehiculo(String tipo, String descripcion){
			this.tipo = tipo;
			this.descripcion = descripcion;
		}
		public String getTipo() {
			return tipo;
		}
		
		public String getDescripcion() {
			return descripcion;
		}
		
		public static TipoVehiculo getBytipo(String tipo){
			for(TipoVehiculo prop : values()){
		      if(prop.getTipo().equals(tipo)){
		        return prop;
		      }
		    }

		    throw new IllegalArgumentException(tipo + " is not a valid PropName");
		}
	
	}
	
	
	/**
	 * PATH URL
	 */
	public static final String API = "api";
	public static final String PATH_INGRESO = "/ingreso";
	public static final String PATH_CONSULTA_INDIVIDUAL = "/consulta/vehiculo";
	public static final String PATH_CONSULTA_TODOS = "/consulta";
	public static final String PATH_SALIDA = "/salida";
	
	/**
	 * CONFIGURACIONES
	 */
	
	public static final String VEHICULOS_PERMITIDOS = "C,M";
	public static final long MAX_MOTOS = 10;
	public static final long MAX_AUTOS = 20;
	
	
	public static final double VALOR_HORA_MOTO = 500D;
	public static final double VALOR_HORA_CARRO = 1000D;
	public static final double VALOR_DIA_MOTO = 4000D;
	public static final double VALOR_DIA_CARRO = 8000D;
	public static final double VALOR_EXTRA_MOTO = 2000D;
	
	/**
	 * MENSAJES DE EXCEPCIONES
	 */
	public static final String EXC_VEHICULO_NO_PERMITIDO = "Tipo de Vehiculo no permitido";
	public static final String EXC_EXCEDE_CUPO = "No existen celdas disponibles";
	public static final String EXC_NO_ES_DIA_HABIL = "No se puede ingresar porque no esta en un dia habil";
	public static final String EXC_INGRESO_YA_REGISTRADO = "EL vehiculo registra que ya fue ingresado";
	public static final String EXC_SALIDA_NO_REGISTRADO = "EL vehiculo no registra que fue ingresado";
	public static final String EXC_NO_PARAMETROS = "No ha ingresado parametros de busqueda";
}
