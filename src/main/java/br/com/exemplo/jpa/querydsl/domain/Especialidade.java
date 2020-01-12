package br.com.exemplo.jpa.querydsl.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.querydsl.core.annotations.Config;

@Entity
@Table(name = "tb_especialidade")
@Config(listAccessors = true, entityAccessors = true)
public class Especialidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	Especialidade() {
		super();
	}

	public Especialidade(final String nome) {
		super();
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

}
