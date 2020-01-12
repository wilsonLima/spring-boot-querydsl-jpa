package br.com.exemplo.jpa.querydsl.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.querydsl.core.annotations.Config;

@Entity
@Table(name = "tb_consulta")
@Config(listAccessors = true, entityAccessors = true)
public class Consulta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "cd_medico")
	private Medico medico;

	@ManyToOne
	@JoinColumn(name = "cd_paciente")
	private Paciente paciente;

	@Column(name = "data_hora_consulta")
	private LocalDateTime dataHoraConsulta;

	@Column(name = "status_consulta")
	@Enumerated(EnumType.STRING)
	private StatusConsulta status;

	private BigDecimal valor;

	private String descricao;

	public Consulta() {
		super();
	}

	public LocalDateTime getDataHoraConsulta() {
		return dataHoraConsulta;
	}

	public String getDescricao() {
		return descricao;
	}

	public Long getId() {
		return id;
	}

	public Medico getMedico() {
		return medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public StatusConsulta getStatus() {
		return status;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setDataHoraConsulta(final LocalDateTime dataHoraConsulta) {
		this.dataHoraConsulta = dataHoraConsulta;
	}

	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setMedico(final Medico medico) {
		this.medico = medico;
	}

	public void setPaciente(final Paciente paciente) {
		this.paciente = paciente;
	}

	public void setStatus(final StatusConsulta status) {
		this.status = status;
	}

	public void setValor(final BigDecimal valor) {
		this.valor = valor;
	}

}
