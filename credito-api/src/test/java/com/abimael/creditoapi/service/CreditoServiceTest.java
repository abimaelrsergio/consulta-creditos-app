package com.abimael.creditoapi.service;

import com.abimael.creditoapi.dto.CreditoDto;
import com.abimael.creditoapi.entity.Credito;
import com.abimael.creditoapi.exception.ResourceNotFoundException;
import com.abimael.creditoapi.kafka.CreditoKafkaPublisher;
import com.abimael.creditoapi.repository.CreditoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Classe de testes unitários para CreditoService.
 *
 * @author Abimael Sergio
 */
class CreditoServiceTest {

    @Mock
    private CreditoRepository repository;

    @Mock
    private CreditoKafkaPublisher publisher;

    @InjectMocks
    private CreditoService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }


    /**
     * Deve retornar lista de creditos quando numero de NFS-e for valido
     *
     * Verifica se a lista de creditos associados a um numero de NFS-e e retornada
     * corretamente pelo servico, e se o evento de consulta e publicado no
     * Kafka.
     */
    @Test
    void deveRetornarListaDeCreditosQuandoNumeroNfseValido() {
        String nfse = "123456";
        List<Credito> lista = List.of(new Credito(1L, "123", nfse, LocalDate.now(), BigDecimal.TEN,
                "ISSQN", true, BigDecimal.ONE, BigDecimal.TEN, BigDecimal.ONE, BigDecimal.TEN));
        when(repository.findByNumeroNfse(nfse)).thenReturn(lista);

        List<CreditoDto> result = service.getCreditoByNfse(nfse);

        assertEquals(1, result.size());
        verify(publisher).publicarConsultaNumeroNfse(nfse);
    }


    /**
     * Deve lancar IllegalArgumentException quando numero de NFS-e for nulo
     *
     * Verifica se o servico lanca uma excecao do tipo
     * IllegalArgumentException quando o numero de NFS-e for nulo.
     */
    @Test
    void deveLancarExcecaoQuandoNumeroNfseNulo() {
        assertThrows(IllegalArgumentException.class, () -> service.getCreditoByNfse(null));
    }

    /**
     * Deve lancar ResourceNotFoundException quando nao encontrar NFS-e
     *
     * Verifica se o servico lanca uma excecao do tipo
     * ResourceNotFoundException quando o numero de NFS-e nao for encontrado.
     */
    @Test
    void deveLancarResourceNotFoundQuandoNaoEncontrarNfse() {
        when(repository.findByNumeroNfse("000")).thenReturn(Collections.emptyList());
        assertThrows(ResourceNotFoundException.class, () -> service.getCreditoByNfse("000"));
    }

    /**
     * Deve retornar credito por numero de credito
     *
     * Verifica se o servico retorna o credito correto quando informado o
     * numero de credito, e se o evento de consulta e publicado no
     * Kafka.
     */
    @Test
    void deveRetornarCreditoPorNumeroCredito() {
        String numeroCredito = "999";
        Credito c = new Credito(1L, numeroCredito, "789", LocalDate.now(), BigDecimal.TEN,
                "ISSQN", true, BigDecimal.ONE, BigDecimal.TEN, BigDecimal.ONE, BigDecimal.TEN);
        when(repository.findByNumeroCredito(numeroCredito)).thenReturn(Optional.of(c));

        CreditoDto dto = service.getCreditoByNumeroCredito(numeroCredito);

        assertEquals(numeroCredito, dto.getNumeroCredito());
        verify(publisher).publicarConsultaNumeroCredito(numeroCredito);
    }

    /**
     * Deve lancar IllegalArgumentException quando numero de credito for invalido
     *
     * Verifica se o servico lanca uma excecao do tipo
     * IllegalArgumentException quando o numero de credito for nulo ou vazio.
     */
    @Test
    void deveLancarIllegalArgumentExceptionSeNumeroCreditoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> service.getCreditoByNumeroCredito(""));
    }

    /**
     * Deve lancar ResourceNotFoundException quando nao encontrar credito por numero
     *
     * Verifica se o servico lanca uma excecao do tipo
     * ResourceNotFoundException quando o numero de credito nao for encontrado.
     */
    @Test
    void deveLancarResourceNotFoundSeCreditoNaoExistir() {
        when(repository.findByNumeroCredito("notfound")).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.getCreditoByNumeroCredito("notfound"));
    }
}
