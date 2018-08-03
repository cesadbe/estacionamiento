package com.ceiba.parking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ceiba.parking.entity.Aparcamiento;

public interface AparcamientoRepository extends JpaRepository<Aparcamiento, Long>{
	
	@Query("SELECT COUNT(1) FROM Aparcamiento a WHERE a.fechaSalida IS NULL and a.tipoVehiculo = :tipoVehiculo")
	Long vehiculosParqueadosSegunTipo(@Param("tipoVehiculo") String tipoVehiculo);

	@Query("SELECT a from Aparcamiento a where a.placa = :placa and a.fechaSalida IS NULL")
	Optional<Aparcamiento> findByPlaca(@Param("placa")String placa);
	
	@Query("SELECT a from Aparcamiento a where a.id = :ticket and a.fechaSalida IS NULL")
	Optional<Aparcamiento> findByTicket(@Param("ticket")String ticket);
	
	@Query("SELECT a from Aparcamiento a where a.fechaSalida IS NULL")
	List<Aparcamiento> findAllParqueados();

}
