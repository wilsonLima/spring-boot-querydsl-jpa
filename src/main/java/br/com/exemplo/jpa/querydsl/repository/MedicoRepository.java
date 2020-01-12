package br.com.exemplo.jpa.querydsl.repository;

import java.time.LocalDate;
import java.util.List;

import br.com.exemplo.jpa.querydsl.domain.Medico;
import br.com.exemplo.jpa.querydsl.domain.Sexo;

public interface MedicoRepository {

	List<String> listaEspecialidadesDeMedicosPorNomeMedicoOuCrm(String parametro);

	List<Medico> listaMedicosdPorEspecialidadeESexo(final String nomeEspecialidade, final Sexo sexo);

	List<String> listaNomesDeMedicosdPorDataRegistroEspecialidade(final LocalDate inicio, final LocalDate fim, final String nomeEspecialidade);

	List<String> listaNomesDeMedicosNaoDemitidos();

	List<String> listaNomesDeMedicosOrdenadosECaixaAlta();

}
