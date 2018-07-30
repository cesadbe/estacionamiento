package com.ceiba.parking.entity;

import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name = "tbl_aparcamiento")
public class Aparcamiento {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Basic
    private String placa;
    
	@Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngeso;
	
	@Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSalida;
	
	@Basic
    private Double valorPagar;
	
	@Basic
    private Boolean altoCilindraje;
	
	@Basic
	private String tipoVehiculo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Date getFechaIngeso() {
		return fechaIngeso;
	}

	public void setFechaIngeso(Date fechaIngeso) {
		this.fechaIngeso = fechaIngeso;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Double getValorPagar() {
		return valorPagar;
	}

	public void setValorPagar(Double valorPagar) {
		this.valorPagar = valorPagar;
	}

	public Boolean getAltoCilindraje() {
		return altoCilindraje;
	}

	public void setAltoCilindraje(Boolean altoCilindraje) {
		this.altoCilindraje = altoCilindraje;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

}
