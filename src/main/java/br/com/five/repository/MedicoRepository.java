package br.com.five.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.five.entities.Medico;
import br.com.five.entities.Paciente;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

	@Query("SELECT m FROM Medico m JOIN m.atendimentos a WHERE a.dataAtendimento BETWEEN :startDate AND :endDate")
	List<Medico> findByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
	
	@Query("SELECT m FROM Medico m JOIN m.atendimentos a WHERE a.paciente = :paciente")
	List<Medico> medicoByPaciente(@Param("paciente") Paciente paciente);

}
