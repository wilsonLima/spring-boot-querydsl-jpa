package br.com.exemplo.jpa.querydsl.repository.integration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.inject.Inject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import br.com.exemplo.jpa.querydsl.SpringBootQuerydslJpaApplication;
import br.com.exemplo.jpa.querydsl.repository.PacienteRepository;

@DisplayName("Teste Atualizar e Remover Paciente")
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@SpringBootTest(classes = SpringBootQuerydslJpaApplication.class)
public class PacienteRepositoryAtualizarERemoverIntegrationTests {

	@Inject
	private PacienteRepository pacienteRepository;

	@Test
	@DisplayName("Teste Atualizar Status do Paciente")
	public void testAtualizarStatus() {
		final Long idPaciente = Long.valueOf(2l);
		assertAll( //
				() -> assertTrue(pacienteRepository.buscaPorId(idPaciente).isAtivo()), //
				() -> assertEquals(1l, pacienteRepository.atualizarStatus(idPaciente, Boolean.FALSE)), //
				() -> assertFalse(pacienteRepository.buscaPorId(idPaciente).isAtivo()) //
		);
	}

	@Test
	@DisplayName("Teste remover paciente")
	public void testRemover() {
		assertEquals(11l, pacienteRepository.count());
		pacienteRepository.remover(11l);
		assertEquals(10l, pacienteRepository.count());
	}
}
