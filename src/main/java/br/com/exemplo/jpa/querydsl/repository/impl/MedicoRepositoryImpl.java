package br.com.exemplo.jpa.querydsl.repository.impl;

import static br.com.exemplo.jpa.querydsl.domain.QMedico.medico;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;

import br.com.exemplo.jpa.querydsl.domain.Medico;
import br.com.exemplo.jpa.querydsl.domain.Sexo;
import br.com.exemplo.jpa.querydsl.query.IQueryFactoryHelper;
import br.com.exemplo.jpa.querydsl.repository.MedicoRepository;

@Repository
public class MedicoRepositoryImpl implements MedicoRepository {

	private final IQueryFactoryHelper queryHelper;

	@Inject
	public MedicoRepositoryImpl(final IQueryFactoryHelper queryHelper) {
		super();
		this.queryHelper = queryHelper;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> listaEspecialidadesDeMedicosPorNomeMedicoOuCrm(final String parametro) {
		if (Objects.isNull(parametro) || parametro.isEmpty()) {
			return new ArrayList<>();
		}
		final BooleanBuilder restricoes = new BooleanBuilder();
		restricoes.and(medico.nome.like(parametro)).or(medico.crm.like(parametro));
		return queryHelper.factory() //
				.select(medico.especialidade().nome) //
				.from(medico) //
				.where(restricoes) //
				.fetch();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Medico> listaMedicosdPorEspecialidadeESexo(final String nomeEspecialidade, final Sexo sexo) {
		if (Objects.isNull(sexo) || Objects.isNull(nomeEspecialidade) || nomeEspecialidade.isEmpty()) {
			return new ArrayList<>();
		}
		return queryHelper.factory() //
				.select(medico) //
				.from(medico) //
				.where(medico.sexo.eq(sexo), //
						medico.especialidade().nome.equalsIgnoreCase(nomeEspecialidade)) //
				.fetch();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> listaNomesDeMedicosdPorDataRegistroEspecialidade(final LocalDate inicio, final LocalDate fim, final String nomeEspecialidade) {
		if (Objects.isNull(inicio) || Objects.isNull(fim) || Objects.isNull(nomeEspecialidade) || nomeEspecialidade.isEmpty()) {
			return new ArrayList<>();
		}
		final BooleanBuilder restricoes = new BooleanBuilder();
		restricoes.and(medico.dataRegistro.between(inicio, fim)).and(medico.especialidade().nome.equalsIgnoreCase(nomeEspecialidade));
		return queryHelper.factory() //
				.select(medico.nome) //
				.from(medico) //
				.where(restricoes) //
				.fetch();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> listaNomesDeMedicosNaoDemitidos() {
		return queryHelper.factory() //
				.select(medico.nome) //
				.from(medico) //
				.where(medico.dataDemissao.isNull()) //
				.fetch();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> listaNomesDeMedicosOrdenadosECaixaAlta() {
		return queryHelper.factory() //
				.select(medico.nome.toUpperCase()) //
				.from(medico) //
				.orderBy(medico.nome.asc()) //
				.fetch();
	}

}
