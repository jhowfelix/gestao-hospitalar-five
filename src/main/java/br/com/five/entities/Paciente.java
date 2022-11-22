package br.com.five.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.five.enums.Sexo;

@Entity
@Table(name = "tb_paciente")
public class Paciente extends Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
	private List<Atendimento> atendimentos = new ArrayList<>();

	public Paciente() {
		super();
	}

	public Paciente(String nome, String cpf, LocalDate dataNascimento, Sexo sexo, Long id) {
		super(nome, cpf, dataNascimento, sexo);
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
