package com.abimael.creditoapi.controller;

import com.abimael.creditoapi.dto.*;
import com.abimael.creditoapi.service.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.*;
import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Controlador REST responsável por expor os endpoints de consulta de créditos constituídos.
 *
 * Disponibiliza operações para buscar créditos com base no número da NFS-e ou pelo número do crédito.
 * Esta classe atua como interface entre o cliente e a camada de serviço {@link CreditoService}.
 *
 * Os endpoints expõem os dados no formato JSON e seguem o padrão RESTful.
 *
 * Exemplos de uso:
 * - GET /api/creditos/{numeroNfse}
 * - GET /api/creditos/credito/{numeroCredito}
 *
 * @author Abimael
 */
@Tag(
        name = "CreditoController REST API responsável por expor os endpoints de consulta de créditos constituídos",
        description = "Disponibiliza operações para buscar créditos com base no número da NFS-e ou pelo número do crédito."
)
@Slf4j
@RequestMapping(path = "/api/creditos", produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
public class CreditoController {

    private final CreditoService creditoService;

    public CreditoController(CreditoService creditoService) {
        this.creditoService = creditoService;
    }

    /**
     * Manipula requisições GET para obter uma lista de créditos associados ao número de NFS-e informado.
     *
     * @param numeroNfse o número da NFS-e para o qual os créditos devem ser recuperados
     * @return um ResponseEntity contendo uma lista de objetos CreditoDto associados ao número de NFS-e fornecido
     */
    @Operation(
            summary = "Recupera os creditos associados a NFS-e",
            description = "Manipula requisições GET para obter uma lista de créditos associados ao número de NFS-e informado"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "404", description = "O recurso solicitado não existe", content = @Content(
                    schema = @Schema(implementation = ErrorDto.class)
            )),
            @ApiResponse(responseCode = "400", description = "O cliente enviou dados inválidos", content = @Content(
                    schema = @Schema(implementation = ErrorDto.class)
            ))
    }
    )
    @GetMapping("/{numeroNfse}")
    public ResponseEntity<List<CreditoDto>> getCreditoByNfse(@PathVariable String numeroNfse) {
        log.debug("CreditoController.getCreditoByNfse: {}", numeroNfse);
        return ResponseEntity.ok(creditoService.getCreditoByNfse(numeroNfse));
    }


    /**
     * Manipula requisições GET para obter os detalhes de um crédito específico associado ao número de crédito informado.
     *
     * @param numeroCredito o número do crédito para o qual os detalhes devem ser recuperados
     * @return um ResponseEntity contendo um objeto CreditoDto com os detalhes do crédito associado ao número informado
     */
    @Operation(
            summary = "Recupera os creditos associados ao número de crédito",
            description = "Manipula requisições GET para obter os detalhes de um crédito específico associado ao número de crédito informado."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "404", description = "O recurso solicitado não existe", content = @Content(
                    schema = @Schema(implementation = ErrorDto.class)
            )),
            @ApiResponse(responseCode = "400", description = "O cliente enviou dados inválidos", content = @Content(
                    schema = @Schema(implementation = ErrorDto.class)
            ))
    }
    )
    @GetMapping("/credito/{numeroCredito}")
    public ResponseEntity<CreditoDto> getCreditoByNumeroCredito(@PathVariable String numeroCredito) {
        log.debug("CreditoController.getCreditoByNumeroCredito: {}", numeroCredito);
        return ResponseEntity.ok(creditoService.getCreditoByNumeroCredito(numeroCredito));
    }
}
