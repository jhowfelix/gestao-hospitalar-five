package br.com.five.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.five.dto.MedicoDTO;

import br.com.five.entities.Medico;
import br.com.five.entities.Paciente;
import br.com.five.repository.AtendimentoRepository;
import br.com.five.repository.MedicoRepository;
import br.com.five.repository.PacienteRepository;
import ch.qos.logback.core.status.Status;

@Service
public class MedicoService {

	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;

	
	@Autowired
	private AtendimentoRepository atendimentoRepository;
	
	
	
	public void delete(Long id) {
		if(atendimentoRepository.existsByMedicoId(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Impossivel deletar medico com atendimento");
		}else {
			
			medicoRepository.deleteById(id);
		}
	}
	
	public MedicoDTO insert(MedicoDTO medico) {
		medicoRepository.save(medico.toEntity());
		return medico;
	}
	
	public List<MedicoDTO> medicoByPaciente(Long id){
		Optional<Paciente> findById = pacienteRepository.findById(id);
		List<Medico> medicoByPaciente = medicoRepository.medicoByPaciente(findById.get());
		return medicoByPaciente.stream().map(x -> new MedicoDTO(x)).collect(Collectors.toList());
		
	}

	public MedicoDTO findById(Long id) {
		Optional<Medico> findById = medicoRepository.findById(id);
		MedicoDTO med = new MedicoDTO();
		return med.MedicoDTO(findById.get());
	}

	public List<MedicoDTO> findByPeriod(LocalDate startDate, LocalDate endDate) {
		List<Medico> findByPeriod = medicoRepository.findByPeriod(startDate, endDate);
		return findByPeriod.stream().map(x -> new MedicoDTO(x)).collect(Collectors.toList());
	}

	public List<MedicoDTO> findAll() {
		List<Medico> findAll = medicoRepository.findAll();
		return findAll.stream().map(x -> new MedicoDTO(x)).collect(Collectors.toList());
	}

	public MedicoDTO update(Long id, MedicoDTO medicoDTO) {
		MedicoDTO findById = findById(id);
		findById.setId(id);
		findById.setNome(medicoDTO.getNome());
		findById.setCpf(medicoDTO.getCpf());
		findById.setCrm(medicoDTO.getCrm());
		findById.setDataNascimento(medicoDTO.getDataNascimento());
		medicoRepository.save(findById.toEntity());
		return findById;

	}


}
