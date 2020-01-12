package br.com.exemplo.jpa.querydsl.repository.integration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import br.com.exemplo.jpa.querydsl.domain.Paciente;
import br.com.exemplo.jpa.querydsl.domain.Sexo;
import br.com.exemplo.jpa.querydsl.repository.PacienteRepository;

@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@SpringBootTest(classes = SpringBootQuerydslJpaApplication.class)
public class PacienteRepositoryIntegrationTests {

	@Inject
	private PacienteRepository pacienteRepository;

	@Test
	public void testBuscaPorId() {
		final Paciente paciente = pacienteRepository.buscaPorId(1l);
		assertAll( //
				() -> assertNotNull(paciente), //
				() -> assertEquals("Maria Silva", paciente.getNome()), //
				() -> assertEquals(Sexo.FEMININO, paciente.getSexo()), //
				() -> assertEquals(LocalDate.of(2017, 06, 10), paciente.getDataRegistro()) //
		);
	}

	@Test
	public void testCount() {
		assertEquals(11l, pacienteRepository.count());
	}

	@Test
	public void testExistemConsultasParaOPaciente() {
		assertAll( //
				() -> assertTrue(pacienteRepository.existemConsultasParaOPaciente(1l)), //
				() -> assertFalse(pacienteRepository.existemConsultasParaOPaciente(11l)) //
		);
	}

	@Test
	public void testListaPacientesPorSexo() {
		final List<String> nomesPorSexo = pacienteRepository.listaPacientesPorSexo(Sexo.MASCULINO);
		assertAll( //
				() -> assertNotNull(nomesPorSexo), //
				() -> assertEquals(5, nomesPorSexo.size()), //
				() -> assertEquals(Arrays.asList("Marcos Augusto", "Jorge Araujo", "Fabio Moraes", "João Cesar Silva", "Julio Fonseca"), nomesPorSexo) //
		);
	}

}
