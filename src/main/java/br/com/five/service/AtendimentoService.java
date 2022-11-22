package br.com.five.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.five.dto.AtendimentoDTO;
import br.com.five.entities.Atendimento;
import br.com.five.repository.AtendimentoRepository;

@Service
public class AtendimentoService {

	@Autowired
	private AtendimentoRepository atendimentoRepository;

	public AtendimentoDTO insert(AtendimentoDTO Atendimento) {
		atendimentoRepository.save(Atendimento.toEntity());
		return Atendimento;
	}
	
	public List<AtendimentoDTO> findByPeriod(LocalDate startDate, LocalDate endDate){
		List<Atendimento> findByDate = atendimentoRepository.findByPeriod(startDate, endDate);
		return findByDate.stream().map(x -> new AtendimentoDTO(x)).collect(Collectors.toList());
	}

	public AtendimentoDTO findById(Long id) {
		Optional<Atendimento> findById = atendimentoRepository.findById(id);
		AtendimentoDTO atendimento = new AtendimentoDTO();
		return atendimento.AtendimentoDTO(findById.get());
	}

	public List<AtendimentoDTO> findAll() {
		List<Atendimento> findAll = atendimentoRepository.findAll();
		return findAll.stream().map(x -> new AtendimentoDTO(x)).collect(Collectors.toList());
	}

	public AtendimentoDTO update(Long id, AtendimentoDTO atendimentoDTO) {
		AtendimentoDTO findById = findById(id);
		findById.setId(id);
		findById.setDataAtendimento(atendimentoDTO.getDataAtendimento());
		findById.setMedico(atendimentoDTO.getMedico());
		findById.setPaciente(atendimentoDTO.getPaciente());
		findById.setAtivo(atendimentoDTO.isAtivo());
		findById.setObservacao(atendimentoDTO.getObservacao());
		return findById;

	}

}
