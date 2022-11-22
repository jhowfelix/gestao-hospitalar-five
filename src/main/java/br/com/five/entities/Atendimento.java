package br.com.five.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Table(name = "tb_atendimento")
@Entity
public class Atendimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String observacao;

	private LocalDate dataAtendimento;
	 
	@ManyToOne
	private Medico medico;
	
	@ManyToOne
	private Paciente paciente;
	private boolean ativo;

	public Atendimento() {
		super();
	}

	public Atendimento(Long id, String observacao, LocalDate dataAtendimento, Medico medico, Paciente paciente,
			boolean ativo) {
		super();
		this.id = id;
		this.observacao = observacao;
		this.dataAtendimento = dataAtendimento;
		this.medico = medico;
		this.paciente = paciente;
		this.ativo = ativo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public LocalDate getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(LocalDate dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}
	
	
	
	

}
