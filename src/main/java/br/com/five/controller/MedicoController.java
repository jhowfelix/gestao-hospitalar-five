package br.com.five.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.five.dto.MedicoDTO;
import br.com.five.service.MedicoService;

@RestController
@RequestMapping("/medico")
public class MedicoController {
	
	@Autowired
	private MedicoService service;
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MedicoDTO> findById(@PathVariable("id") Long id){
		return ResponseEntity.ok().body(service.findById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<MedicoDTO>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping("/periodo")
	public ResponseEntity<List<MedicoDTO>> findByPeriod(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
		return ResponseEntity.ok().body(service.findByPeriod(startDate, endDate));
	}
	
	@GetMapping("/byPaciente/{id}")
	public ResponseEntity<List<MedicoDTO>> medicoByPaciente(@PathVariable("id") long id){
		return ResponseEntity.ok().body(service.medicoByPaciente(id));
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id")Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	@PostMapping("/create")
	public ResponseEntity<MedicoDTO> insert(@RequestBody @Valid MedicoDTO medicoDTO){
		MedicoDTO med = service.insert(medicoDTO);
	     URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(medicoDTO.getId())
	                .toUri();
		return ResponseEntity.created(uri).body(med);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<MedicoDTO> update(@PathVariable("id") Long id, @RequestBody @Valid MedicoDTO medicoDTO){
		MedicoDTO update = service.update(id, medicoDTO);
		return ResponseEntity.ok().body(update);
	}

}
