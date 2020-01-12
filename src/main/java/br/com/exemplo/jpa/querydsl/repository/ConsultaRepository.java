package br.com.exemplo.jpa.querydsl.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import br.com.exemplo.jpa.querydsl.domain.Consulta;

public interface ConsultaRepository {

	boolean existemConsultasParaOPaciente(Long idPaciente);

	List<Consulta> listaConsultasDoPaciente(final Long idPaciente);

	List<Consulta> listaConsultasDoPacienteEMedico(final Long idPaciente, final Long idMedico);

	Map<String, BigDecimal> somaValoresDeConsultasPorMedico();

	BigDecimal somaValoresDeTodasConsultas();

}
