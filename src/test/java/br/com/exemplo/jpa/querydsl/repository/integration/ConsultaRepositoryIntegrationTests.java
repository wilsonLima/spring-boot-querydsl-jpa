package br.com.exemplo.jpa.querydsl.repository.integration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import br.com.exemplo.jpa.querydsl.SpringBootQuerydslJpaApplication;
import br.com.exemplo.jpa.querydsl.domain.Consulta;
import br.com.exemplo.jpa.querydsl.repository.ConsultaRepository;

@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@SpringBootTest(classes = SpringBootQuerydslJpaApplication.class)
public class ConsultaRepositoryIntegrationTests {

	@Inject
	private ConsultaRepository consultaRepository;

	@Test
	public void testExistemConsultasParaOPaciente() {
		assertTrue(consultaRepository.existemConsultasParaOPaciente(Long.valueOf(1l)));
	}

	@Test
	public void testListaConsultasDoPaciente() {
		final List<Consulta> lista = consultaRepository.listaConsultasDoPaciente(Long.valueOf(10l));

		assertAll( //
				() -> assertNotNull(lista), //
				() -> assertEquals(1, lista.size()), //
				() -> assertEquals(Long.valueOf(2l), lista.get(0).getId()), //
				() -> assertEquals(Long.valueOf(10l), lista.get(0).getPaciente().getId()) //

		);
	}

	@Test
	public void testListaConsultasDoPacienteEMedico() {
		final List<Consulta> lista = consultaRepository.listaConsultasDoPacienteEMedico(Long.valueOf(10l), Long.valueOf(1l));
		assertAll( //
				() -> assertNotNull(lista), //
				() -> assertEquals(1, lista.size()), //
				() -> assertEquals(Long.valueOf(2l), lista.get(0).getId()), //
				() -> assertEquals(Long.valueOf(1l), lista.get(0).getMedico().getId()), //
				() -> assertEquals(Long.valueOf(10l), lista.get(0).getPaciente().getId()) //
		);
	}

	@Test
	public void testSomaValoresDeConsultasPorMedico() {
		final Map<String, BigDecimal> result = consultaRepository.somaValoresDeConsultasPorMedico();
		assertAll( //
				() -> assertNotNull(result), //
				() -> assertEquals(new BigDecimal("200.0"), result.get("Angélica Araujo")), //
				() -> assertEquals(new BigDecimal("100.0"), result.get("Junior Fontes")), //
				() -> assertEquals(new BigDecimal("150.0"), result.get("Mario Oliveira")), //
				() -> assertEquals(new BigDecimal("90.0"), result.get("Fabio Mattos")), //
				() -> assertEquals(new BigDecimal("120.0"), result.get("Silviana Santos")), //
				() -> assertEquals(new BigDecimal("150.0"), result.get("João Augusto")) //
		);
	}

	@Test
	public void testSomaValoresDeTodasConsultas() {
		final BigDecimal soma = consultaRepository.somaValoresDeTodasConsultas();
		assertAll( //
				() -> assertNotNull(soma), //
				() -> assertEquals(new BigDecimal("810.0"), soma) //
		);
	}

}
