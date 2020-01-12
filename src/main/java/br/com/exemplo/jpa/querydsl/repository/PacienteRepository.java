package br.com.exemplo.jpa.querydsl.repository;

import java.util.List;

import br.com.exemplo.jpa.querydsl.domain.Paciente;
import br.com.exemplo.jpa.querydsl.domain.Sexo;

public interface PacienteRepository {

	long atualizarStatus(Long idPaciente, final Boolean b);

	Paciente buscaPorId(final Long id);

	long count();

	boolean existemConsultasParaOPaciente(final Long idPaciente);

	List<String> listaPacientesPorSexo(final Sexo sex);

	long remover(final Long id);

}
