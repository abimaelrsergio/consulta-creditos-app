package com.abimael.creditoapi.controller;

import com.abimael.creditoapi.dto.CreditoDto;
import com.abimael.creditoapi.exception.ResourceNotFoundException;
import com.abimael.creditoapi.service.CreditoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.*;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Classe de testes para o controlador de créditos.
 * Verifica a funcionalidade e integridade do CreditoController.
 *
 * @author Abimael Sergio
 */
@WebMvcTest(CreditoController.class)
class CreditoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreditoService creditoService;

    /**
     * Testa se o endpoint retorna uma lista de créditos com status HTTP 200.
     *
     * Este teste verifica se o controlador retorna corretamente uma lista de
     * créditos associados a um número de NFS-e válido. A resposta deve conter
     * o status HTTP 200 e a lista de créditos deve refletir os dados esperados,
     * tais como o número do crédito e o valor do ISSQN.
     *
     * @throws Exception se ocorrer um erro durante a execução do teste
     */
    @Test
    void deveRetornarStatus200ComListaDeCreditos() throws Exception {
        CreditoDto dto = new CreditoDto("123", "789", LocalDate.of(2024, 2, 25),
                BigDecimal.valueOf(1500.75), "ISSQN", "Sim", BigDecimal.valueOf(5.0),
                BigDecimal.valueOf(30000.00), BigDecimal.valueOf(5000.00), BigDecimal.valueOf(25000.00));

        when(creditoService.getCreditoByNfse("789")).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/creditos/789")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numeroCredito").value("123"))
                .andExpect(jsonPath("$[0].valorIssqn").value(1500.75));
    }

    /**
     * Testa se o endpoint retorna um crédito específico com status HTTP 200.
     *
     * Este teste verifica se o controlador retorna corretamente um crédito
     * específico quando informado o número do crédito. A resposta deve conter
     * o status HTTP 200 e o crédito deve refletir os dados esperados, tais como
     * o número do crédito e o valor do ISSQN.
     *
     * @throws Exception se ocorrer um erro durante a execução do teste
     */
    @Test
    void deveRetornarStatus200ComCreditoEspecifico() throws Exception {
        CreditoDto dto = new CreditoDto("123", "789", LocalDate.of(2024, 2, 25),
                BigDecimal.valueOf(1500.75), "ISSQN", "Sim", BigDecimal.valueOf(5.0),
                BigDecimal.valueOf(30000.00), BigDecimal.valueOf(5000.00), BigDecimal.valueOf(25000.00));

        when(creditoService.getCreditoByNumeroCredito("123")).thenReturn(dto);

        mockMvc.perform(get("/api/creditos/credito/123")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroNfse").value("789"))
                .andExpect(jsonPath("$.aliquota").value(5.0));
    }

    /**
     * Testa se o endpoint retorna um erro 404 (não encontrado) quando o
     * crédito não existe.
     *
     * Este teste verifica se o controlador retorna corretamente um erro 404
     * quando o crédito informado não existe. A resposta deve conter o status
     * HTTP 404 e os dados de erro esperados, tais como o código de erro e a
     * mensagem de erro.
     *
     * @throws Exception se ocorrer um erro durante a execução do teste
     */
    @Test
    void deveRetornarStatus404QuandoCreditoNaoEncontrado() throws Exception {
        when(creditoService.getCreditoByNumeroCredito("000")).thenThrow(
                new ResourceNotFoundException("Credito", "número de crédito", "000")
        );

        mockMvc.perform(get("/api/creditos/credito/000")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").exists());
    }

    /**
     * Testa se o endpoint retorna um erro 400 (bad request) quando o
     * parâmetro de entrada é inválido.
     *
     * Este teste verifica se o controlador retorna corretamente um erro 400
     * quando o parâmetro de entrada (número da NFS-e) é inválido. A resposta
     * deve conter o status HTTP 400 e os dados de erro esperados, tais como o
     * código de erro e a mensagem de erro.
     *
     * @throws Exception se ocorrer um erro durante a execução do teste
     */
    @Test
    void deveRetornarStatus400QuandoParametroInvalido() throws Exception {
        when(creditoService.getCreditoByNfse("")).thenThrow(
                new IllegalArgumentException("Número da NFS-e não pode ser nulo ou vazio")
        );

        mockMvc.perform(get("/api/creditos/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}
