package com.abimael.creditoapi.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuração de Kafka Producer no padrão Singleton via Spring.
 * Garante que o KafkaTemplate seja instanciado uma única vez pela aplicação.
 */
@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    /**
     * Cria um bean ProducerFactory utilizado para produzir mensagens para o Kafka.
     *
     * Configura o ProducerFactory com as propriedades necessárias, incluindo os serializadores
     * de chave e valor do tipo String e o endereço do servidor Kafka.
     *
     * @return uma instância de ProducerFactory configurada para mensagens de chave e valor do tipo String.
     */
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }

    /**
     * Cria um bean KafkaTemplate utilizado para enviar mensagens para o Kafka.
     *
     * @return uma instância de KafkaTemplate configurada para mensagens de chave e valor do tipo String.
     */
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
