package com.abimael.creditoapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class CorsConfig {

    /**
     * Configura as definições de CORS para a aplicação.
     *
     * Este método define um bean do tipo {@link WebMvcConfigurer} que personaliza o mapeamento de CORS.
     * Ele permite requisições de outras origens vindas de "http://localhost:4200" para dar suporte ao frontend em Angular.
     * Todos os métodos HTTP e cabeçalhos são permitidos para requisições CORS.
     *
     * @return uma instância de {@link WebMvcConfigurer} com configurações de CORS personalizadas
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200") // <-- libera para Angular
                        .allowedMethods("*")
                        .allowedHeaders("*");
            }
        };
    }
}
