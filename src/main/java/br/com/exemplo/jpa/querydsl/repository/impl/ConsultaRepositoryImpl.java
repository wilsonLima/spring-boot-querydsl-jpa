package br.com.exemplo.jpa.querydsl.repository.impl;

import static br.com.exemplo.jpa.querydsl.domain.QConsulta.consulta;
import static br.com.exemplo.jpa.querydsl.domain.QMedico.medico;
import static br.com.exemplo.jpa.querydsl.domain.QPaciente.paciente;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;

import br.com.exemplo.jpa.querydsl.domain.Consulta;
import br.com.exemplo.jpa.querydsl.query.IQueryFactoryHelper;
import br.com.exemplo.jpa.querydsl.repository.ConsultaRepository;

@Repository
public class ConsultaRepositoryImpl implements ConsultaRepository {

	private final IQueryFactoryHelper queryHelper;

	@Inject
	public ConsultaRepositoryImpl(final IQueryFactoryHelper queryHelper) {
		super();
		this.queryHelper = queryHelper;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean existemConsultasParaOPaciente(final Long idPaciente) {
		if (Objects.isNull(idPaciente)) {
			return false;
		}
		final long count = queryHelper.factory() //
				.select() //
				.from(consulta) //
				.where(consulta.paciente().id.eq(idPaciente)) //
				.fetchCount();

		return count > 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Consulta> listaConsultasDoPaciente(final Long idPaciente) {
		if (Objects.isNull(idPaciente)) {
			return new ArrayList<>();
		}
		return queryHelper.factory() //
				.select(consulta) //
				.from(consulta) //
				.join(consulta.paciente(), paciente) //
				.where(paciente.id.eq(idPaciente)) //
				.fetch();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Consulta> listaConsultasDoPacienteEMedico(final Long idPaciente, final Long idMedico) {
		if (Objects.isNull(idPaciente) || Objects.isNull(idMedico)) {
			return new ArrayList<>();
		}
		final BooleanBuilder restricoes = new BooleanBuilder();
		restricoes.and(consulta.paciente().id.eq(idPaciente));
		restricoes.and(consulta.medico().id.eq(idMedico));
		return queryHelper.factory() //
				.select(consulta) //
				.from(consulta) //
				.where(restricoes) //
				.fetch();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, BigDecimal> somaValoresDeConsultasPorMedico() {
		final Map<String, BigDecimal> result = new HashMap<>();
		final List<Tuple> tuples = queryHelper.factory() //
				.select(medico.nome, consulta.valor.sum()) //
				.from(medico, consulta) //
				.where(consulta.medico().id.eq(medico.id)) //
				.groupBy(medico.nome, consulta.valor) //
				.fetch();
		tuples.stream().forEach(t -> result.put(t.get(medico.nome), t.get(consulta.valor.sum())));
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BigDecimal somaValoresDeTodasConsultas() {
		return queryHelper.factory() //
				.select(consulta.valor.sum()) //
				.from(consulta) //
				.fetchOne();
	}

}
