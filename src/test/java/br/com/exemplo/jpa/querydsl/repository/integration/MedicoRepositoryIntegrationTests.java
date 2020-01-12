package br.com.exemplo.jpa.querydsl.repository.integration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import br.com.exemplo.jpa.querydsl.SpringBootQuerydslJpaApplication;
import br.com.exemplo.jpa.querydsl.domain.Medico;
import br.com.exemplo.jpa.querydsl.domain.Sexo;
import br.com.exemplo.jpa.querydsl.repository.MedicoRepository;

@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@SpringBootTest(classes = SpringBootQuerydslJpaApplication.class)
public class MedicoRepositoryIntegrationTests {

	@Inject
	private MedicoRepository medicoRepository;

	@Test
	public void testListaEspecialidadesDeMedicosPorNomeMedicoOuCrm() {
		assertAll( //
				() -> assertEquals(Arrays.asList("Oftalmologista"), medicoRepository.listaEspecialidadesDeMedicosPorNomeMedicoOuCrm("62799")), //
				() -> assertEquals(Arrays.asList("Oftalmologista"), medicoRepository.listaEspecialidadesDeMedicosPorNomeMedicoOuCrm("Silviana Santos")) //
		);
	}

	@Test
	public void testListaMedicosdPorEspecialidadeESexo() {
		final List<Medico> lista = medicoRepository.listaMedicosdPorEspecialidadeESexo("Clínico Geral", Sexo.MASCULINO);
		assertAll( //
				() -> assertEquals(1, lista.size()), //
				() -> assertTrue(lista.stream().allMatch(d -> "Junior Fontes".equals(d.getNome()))) //
		);
	}

	@Test
	public void testListaNomesDeMedicosdPorDataRegistroEspecialidade() {
		assertEquals(Arrays.asList("Angélica Araujo"), medicoRepository.listaNomesDeMedicosdPorDataRegistroEspecialidade(LocalDate.of(2000, 02, 13), LocalDate.of(2005, 05, 20), "Clínico Geral"));
	}

	@Test
	public void testListaNomesDeMedicosNaoDemitidos() {
		assertEquals(Arrays.asList("Angélica Araujo", "Mario Oliveira", "Fabio Mattos", "João Augusto", "Silviana Santos"), medicoRepository.listaNomesDeMedicosNaoDemitidos());
	}

	@Test
	public void testListaNomesDeMedicosOrdenadosECaixaAlta() {
		assertEquals(Arrays.asList("ANGÉLICA ARAUJO", "FABIO MATTOS", "JOÃO AUGUSTO", "JUNIOR FONTES", "MARIO OLIVEIRA", "SILVIANA SANTOS"), medicoRepository.listaNomesDeMedicosOrdenadosECaixaAlta());
	}

}
