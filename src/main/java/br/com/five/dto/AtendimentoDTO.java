package br.com.five.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.five.entities.Atendimento;
import br.com.five.entities.Medico;
import br.com.five.entities.Paciente;

public class AtendimentoDTO {

	private Long id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAtendimento;
	
	
	private Medico medico;
	private Paciente paciente;
	private String observacao;
	private boolean ativo;

	public AtendimentoDTO() {
		super();
	}

	public AtendimentoDTO(Long id, LocalDate dataAtendimento, Medico medico, Paciente paciente, String observacao,
			boolean ativo) {
		super();
		this.id = id;
		this.dataAtendimento = dataAtendimento;
		this.medico = medico;
		this.paciente = paciente;
		this.observacao = observacao;
		this.ativo = ativo;
	}

	public AtendimentoDTO(Atendimento atd) {
		id = atd.getId();
		dataAtendimento = atd.getDataAtendimento();
		medico = atd.getMedico();
		paciente = atd.getPaciente();
		observacao = atd.getObservacao();
		ativo = atd.isAtivo();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(LocalDate dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
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

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Atendimento toEntity() {
		return new Atendimento(id, observacao, dataAtendimento, medico, paciente, ativo);
	}

	public AtendimentoDTO AtendimentoDTO(Atendimento atendimento) {
		return new AtendimentoDTO(atendimento.getId(), atendimento.getDataAtendimento(), atendimento.getMedico(), atendimento.getPaciente(), atendimento.getObservacao(), atendimento.isAtivo());
	}
}
