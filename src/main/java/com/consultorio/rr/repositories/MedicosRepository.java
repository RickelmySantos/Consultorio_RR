package com.consultorio.rr.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.consultorio.rr.dominio.medicos.Medicos;

public interface MedicosRepository extends JpaRepository<Medicos, Long>{

	Page<Medicos> findAllByAtivoTrue(Pageable paginacao);

}
