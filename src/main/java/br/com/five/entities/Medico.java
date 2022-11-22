package br.com.five.entities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.five.enums.Sexo;


@Entity
@Table(name = "tb_medico")
public class Medico extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @Column(name = "crm")
	private String crm;
    
    

    @OneToMany(mappedBy = "medico", fetch = FetchType.LAZY)
    private List<Atendimento> atendimentos = new ArrayList<>();

	public Medico() {
		super();
	}

	public Medico(String nome, String cpf, LocalDate dataNascimento, Sexo sexo, Long id, String crm) {
		super(nome, cpf, dataNascimento, sexo);
		this.id = id;
		this.crm = crm;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}
	
	

}
