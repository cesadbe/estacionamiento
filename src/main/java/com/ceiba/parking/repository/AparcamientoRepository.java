package com.ceiba.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ceiba.parking.entity.Aparcamiento;

public interface AparcamientoRepository extends JpaRepository<Aparcamiento, Long>{
	
	@Query("SELECT COUNT(1) FROM Aparcamiento a WHERE a.fechaSalida IS NULL and a.tipoVehiculo = :tipoVehiculo")
	Long vehiculosParqueadosSegunTipo(@Param("tipoVehiculo") String tipoVehiculo);

}
