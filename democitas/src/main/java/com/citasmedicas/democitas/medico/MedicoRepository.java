package com.citasmedicas.democitas.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long>{

	Page<Medico> findAllByActivoTrue(Pageable paginacion);

}
