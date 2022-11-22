package br.com.five.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.five.entities.Medico;
import br.com.five.entities.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

	@Query("SELECT p FROM Paciente p JOIN p.atendimentos a WHERE a.medico = :medico")
	List<Paciente> pacienteByMed(@Param("medico") Medico medico);
	
	
	 
	
}
