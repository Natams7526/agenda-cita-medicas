package com.citasmedicas.democitas.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

	Page<Paciente> findAllByActivoTrue(Pageable paginacion);

}
