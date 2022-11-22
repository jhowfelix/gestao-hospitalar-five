package br.com.five.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.five.dto.AtendimentoDTO;
import br.com.five.service.AtendimentoService;

@RestController
@RequestMapping("/atendimento")
public class AtendimentoController {

	@Autowired
	private AtendimentoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<AtendimentoDTO> findById(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(service.findById(id));
	}
	
	@GetMapping("/periodo")
	public ResponseEntity<List<AtendimentoDTO>> findByPeriod(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
		return ResponseEntity.ok().body(service.findByPeriod(startDate, endDate));
	}


	@GetMapping
	public ResponseEntity<List<AtendimentoDTO>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@PostMapping("/create")
	public ResponseEntity<AtendimentoDTO> insert(@RequestBody AtendimentoDTO AtendimentoDTO) {
		AtendimentoDTO Atendimento = service.insert(AtendimentoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(AtendimentoDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(Atendimento);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<AtendimentoDTO> update(@PathVariable("id") Long id,
			@RequestBody AtendimentoDTO AtendimentoDTO) {
		AtendimentoDTO update = service.update(id, AtendimentoDTO);
		return ResponseEntity.ok().body(update);
	}

}
