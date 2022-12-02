package br.com.five.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.five.dto.PacienteDTO;
import br.com.five.service.PacienteService;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

	@Autowired
	private PacienteService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<PacienteDTO> findById(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(service.findById(id));
	}
	  @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	  public ResponseEntity<InputStreamResource> gerarPdfPacientes() {
	    return service.gerarPdfPacientes();
	  }

	@GetMapping
	public ResponseEntity<List<PacienteDTO>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping("/bymed/{id}")
	public ResponseEntity<List<PacienteDTO>> pacienteByMed(@PathVariable("id") long id) {
		return ResponseEntity.ok().body(service.pacienteByMed(id));
	}

	@PostMapping("/create")
	public ResponseEntity<PacienteDTO> insert(@RequestBody @Valid PacienteDTO PacienteDTO) {
		PacienteDTO paciente = service.insert(PacienteDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(PacienteDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(paciente);
	}

	@PutMapping("/editar/{id}")
	public ResponseEntity<PacienteDTO> update(@PathVariable("id") Long id,
			@RequestBody @Valid PacienteDTO PacienteDTO) {
		PacienteDTO update = service.update(id, PacienteDTO);
		return ResponseEntity.ok().body(update);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}

}
