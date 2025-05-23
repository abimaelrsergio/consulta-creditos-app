package com.abimael.creditoapi.kafka;

import com.abimael.creditoapi.dto.CreditoDto;
import lombok.extern.slf4j.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Responsável por publicar eventos de consulta de crédito no Kafka.
 *
 * Publica mensagens no tópico 'consultas_credito' sempre que uma consulta for realizada,
 * simulando uma estratégia de auditoria ou log distribuído.
 */
@Slf4j
@Component
public class CreditoKafkaPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * @param kafkaTemplate injetado como Singleton pelo Spring, conforme configuração em KafkaProducerConfig
     */
    public CreditoKafkaPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Publica a consulta de crédito por número de crédito em formato simplificado.
     *
     * @param numeroCredito número do crédito consultado
     */
    public void publicarConsultaNumeroCredito(String numeroCredito) {
        String mensagem = "Consulta realizada para crédito: " + numeroCredito;
        try {
            kafkaTemplate.send("consultas_credito", mensagem);
        } catch (Exception e) {
            log.error("Erro ao publicar no Kafka: {}", mensagem, e);
        }
    }

    /**
     * Publica a consulta de crédito por NFS-e em formato simplificado.
     *
     * @param numeroNfse número da NFS-e consultada
     */
    public void publicarConsultaNumeroNfse(String numeroNfse) {
        String mensagem = "Consulta realizada para NFS-e: " + numeroNfse;
        try {
            kafkaTemplate.send("consultas_credito", mensagem);
        } catch (Exception e) {
            log.error("Erro ao publicar no Kafka: {}", mensagem, e);
        }
    }
}

