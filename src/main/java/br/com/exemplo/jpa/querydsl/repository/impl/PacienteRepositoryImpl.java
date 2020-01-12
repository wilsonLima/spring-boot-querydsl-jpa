package br.com.exemplo.jpa.querydsl.repository.impl;

import static br.com.exemplo.jpa.querydsl.domain.QConsulta.consulta;
import static br.com.exemplo.jpa.querydsl.domain.QPaciente.paciente;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.exemplo.jpa.querydsl.domain.Paciente;
import br.com.exemplo.jpa.querydsl.domain.Sexo;
import br.com.exemplo.jpa.querydsl.query.IQueryFactoryHelper;
import br.com.exemplo.jpa.querydsl.repository.PacienteRepository;

@Repository
public class PacienteRepositoryImpl implements PacienteRepository {

	private final IQueryFactoryHelper queryHelper;

	@Inject
	public PacienteRepositoryImpl(final IQueryFactoryHelper queryHelper) {
		super();
		this.queryHelper = queryHelper;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public long atualizarStatus(final Long idPaciente, final Boolean status) {
		if (Objects.isNull(idPaciente) || Objects.isNull(status)) {
			return 0l;
		}
		return queryHelper.factory() //
				.update(paciente) //
				.set(paciente.ativo, status) //
				.where(paciente.id.eq(idPaciente)) //
				.execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Paciente buscaPorId(final Long id) {
		if (Objects.isNull(id)) {
			return null;
		}
		return queryHelper.factory() //
				.select(paciente) //
				.from(paciente) //
				.where(paciente.id.eq(id)) //
				.fetchOne();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() {
		return queryHelper.factory() //
				.select() //
				.from(paciente) //
				.fetchCount();
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
				.from(paciente) //
				.where(paciente.id.eq(idPaciente), //
						queryHelper.factory().select() //
								.from(consulta) //
								.where(consulta.paciente().id.eq(paciente.id)) //
								.exists()) //
				.fetchCount();
		return count > 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> listaPacientesPorSexo(final Sexo sexo) {
		if (Objects.isNull(sexo)) {
			return new ArrayList<>();
		}
		return queryHelper.factory() //
				.select(paciente.nome) //
				.from(paciente) //
				.where(paciente.sexo.eq(sexo)) //
				.fetch();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public long remover(final Long id) {
		if (Objects.isNull(id)) {
			return 0l;
		}
		return queryHelper.factory() //
				.delete(paciente) //
				.where(paciente.id.eq(id)) //
				.execute();
	}

}
