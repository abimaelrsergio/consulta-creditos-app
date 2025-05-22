package com.abimael.creditoapi.kafka;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for CreditoKafkaPublisher.
 *
 * @author Abimael Sergio
 */
class CreditoKafkaPublisherTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    private CreditoKafkaPublisher publisher;

    @Captor
    private ArgumentCaptor<String> messageCaptor;

    /**
     * Inicializa o contexto de teste.
     *
     * <p>Chama MockitoAnnotations.openMocks(this) para inicializar
     * os mocks e inicializa o objeto publisher com o mock
     * de um KafkaTemplate.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        publisher = new CreditoKafkaPublisher(kafkaTemplate);
    }

    /**
     * Deve publicar uma mensagem no tópico 'consultas_credito' quando consultar por
     * número de crédito.
     *
     * Verifica se o {@link CreditoKafkaPublisher} publica uma mensagem no tópico
     * 'consultas_credito' com o conteúdo da consulta de crédito por número de
     * crédito, quando chamar o método
     * {@link CreditoKafkaPublisher#publicarConsultaNumeroCredito(String)}.
     */
    @Test
    void devePublicarMensagemQuandoConsultaPorNumeroCredito() {
        String numeroCredito = "999";

        publisher.publicarConsultaNumeroCredito(numeroCredito);

        verify(kafkaTemplate).send(eq("consultas_credito"), messageCaptor.capture());
        assertTrue(messageCaptor.getValue().contains(numeroCredito));
    }

    /**
     * Deve publicar uma mensagem no tópico 'consultas_credito' quando consultar por
     * número de NFS-e.
     *
     * Verifica se o {@link CreditoKafkaPublisher} publica uma mensagem no tópico
     * 'consultas_credito' com o conteúdo da consulta de crédito por número de
     * NFS-e, quando chamar o método
     * {@link CreditoKafkaPublisher#publicarConsultaNumeroNfse(String)}.
     */
    @Test
    void devePublicarMensagemQuandoConsultaPorNumeroNfse() {
        String numeroNfse = "456789";

        publisher.publicarConsultaNumeroNfse(numeroNfse);

        verify(kafkaTemplate).send(eq("consultas_credito"), messageCaptor.capture());
        assertTrue(messageCaptor.getValue().contains(numeroNfse));
    }
}
