package br.com.five.dto;

import java.time.LocalDate;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.five.entities.Medico;
import br.com.five.enums.Sexo;

public class MedicoDTO {
	
	private Long id;
	private String nome;

	@CPF
	private String cpf;
	
    @JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
    
	private Sexo sexo;
	
	@Pattern(regexp = "[0-9]{6}.[A-Z]{2}")
	private String crm;
	public MedicoDTO() {
		super();
	}
	public MedicoDTO(Long id, String nome, String cpf, LocalDate dataNascimento, Sexo sexo, String crm) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.crm = crm;
	}
	
	public MedicoDTO(Medico medico) {
		id = medico.getId();
		nome = medico.getNome();
		cpf = medico.getCpf();
		dataNascimento = medico.getDataNascimento();
		sexo = medico.getSexo();
		crm = medico.getCrm();
	}
	
	public MedicoDTO MedicoDTO(Medico medico) {
		return new MedicoDTO(medico.getId(), medico.getNome(), medico.getCpf(), medico.getDataNascimento(), medico.getSexo(), medico.getCrm());
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
	public String getCrm() {
		return crm;
	}
	public void setCrm(String crm) {
		this.crm = crm;
	}
	
	
	public Medico toEntity() {
		return new Medico(nome, cpf, dataNascimento, sexo, id, crm );
	}
	

}
