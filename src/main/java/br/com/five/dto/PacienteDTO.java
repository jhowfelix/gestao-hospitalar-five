package br.com.five.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.five.entities.Paciente;
import br.com.five.enums.Sexo;

public class PacienteDTO {

	private Long id;

	private String nome;

	@CPF
	private String cpf;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	private Sexo sexo;

	public PacienteDTO() {
		super();
	}

	public PacienteDTO(Long id, String nome, String cpf, LocalDate dataNascimento, Sexo sexo) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
	}

	public PacienteDTO(Paciente paciente) {
		id = paciente.getId();
		nome = paciente.getNome();
		cpf = paciente.getCpf();
		dataNascimento = paciente.getDataNascimento();
		sexo = paciente.getSexo();
	}

	public PacienteDTO toDTO(Paciente paciente) {
		return new PacienteDTO(paciente.getId(), paciente.getNome(), paciente.getCpf(), paciente.getDataNascimento(),
				paciente.getSexo());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Paciente toEntity() {
		return new Paciente(nome, cpf, dataNascimento, sexo, id);
	}
}
