package br.com.five.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.five.dto.PacienteDTO;
import br.com.five.entities.Medico;
import br.com.five.entities.Paciente;
import br.com.five.repository.AtendimentoRepository;
import br.com.five.repository.MedicoRepository;
import br.com.five.repository.PacienteRepository;

@Service
public class PacienteService {
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private AtendimentoRepository atendimentoRepository;
	
	
	
	
	public PacienteDTO insert(PacienteDTO pacientedto) {
		pacienteRepository.save(pacientedto.toEntity());
		return pacientedto;
	}
	
	
	public void delete(Long id) {
		if(atendimentoRepository.existsByPacienteId(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Impossivel deletar paciente com atendimento");
		}else {
			pacienteRepository.deleteById(id);
		}
	}
	
	public PacienteDTO findById(Long id) {
		Optional<Paciente> findById = pacienteRepository.findById(id);
		PacienteDTO paciente = new PacienteDTO();
		return paciente.toDTO(findById.get());
	}
	public List<PacienteDTO> findAll(){
		List<Paciente> findAll = pacienteRepository.findAll();
		return findAll.stream().map(x -> new PacienteDTO(x)).collect(Collectors.toList());
	}
	
	public List<PacienteDTO> pacienteByMed(Long id){
		
		Optional<Medico> findById = medicoRepository.findById(id);
		
		List<Paciente> pacienteByMed = pacienteRepository.pacienteByMed(findById.get());
		return pacienteByMed.stream().map(x -> new PacienteDTO(x)).collect(Collectors.toList());
		
	}
	
	
	public PacienteDTO update(Long id, PacienteDTO pacienteDTO) {
		PacienteDTO findByID = findById(id);
		findByID.setId(id);
		findByID.setNome(pacienteDTO.getNome());
		findByID.setCpf(pacienteDTO.getCpf());
		findByID.setDataNascimento(pacienteDTO.getDataNascimento());
		pacienteRepository.save(findByID.toEntity());
		return findByID;
	}
	

}
