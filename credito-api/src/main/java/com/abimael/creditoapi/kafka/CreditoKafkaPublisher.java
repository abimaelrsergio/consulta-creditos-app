package com.abimael.creditoapi.kafka;

import com.abimael.creditoapi.dto.CreditoDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Responsável por publicar eventos de consulta de crédito no Kafka.
 *
 * Publica mensagens no tópico 'consultas_credito' sempre que uma consulta for realizada,
 * simulando uma estratégia de auditoria ou log distribuído.
 */
@Component
public class CreditoKafkaPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public CreditoKafkaPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Publica a consulta de crédito em formato simplificado.
     *
     * @param dto DTO com os dados do crédito consultado
     */
    public void publicarConsulta(CreditoDto dto) {
        String mensagem = "Consulta realizada para crédito: " + dto.getNumeroCredito()
                + ", NFS-e: " + dto.getNumeroNfse();
        kafkaTemplate.send("consultas_credito", mensagem);
    }
}

