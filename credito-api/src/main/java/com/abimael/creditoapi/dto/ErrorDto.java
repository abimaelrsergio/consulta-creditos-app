package com.abimael.creditoapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name = "Error",
        description = "Respostas de erro"
)
public class ErrorDto {

    @Schema(
            description = "API que foi chamada pelo cliente"
    )
    private  String api;

    @Schema(
            description = "Código do erro"
    )
    private HttpStatus code;

    @Schema(
            description = "Mensagem de erro"
    )
    private  String message;

    @Schema(
            description = "Tempo em que o erro aconteceu"
    )
    private LocalDateTime localDateTime;

}

