package com.abimael.creditoapi.controller;

import com.abimael.creditoapi.dto.*;
import com.abimael.creditoapi.service.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping(path = "/api/creditos", produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
public class CreditoController {

    private final CreditoService creditoService;

    public CreditoController(CreditoService creditoService) {
        this.creditoService = creditoService;
    }

    @GetMapping("/{numeroNfse}")
    public ResponseEntity<List<CreditoDto>> getCreditoByNfse(@PathVariable String numeroNfse) {
        return ResponseEntity.ok(creditoService.getCreditoByNfse(numeroNfse));
    }

    @GetMapping("/credito/{numeroCredito}")
    public ResponseEntity<CreditoDto> getCreditoByNumeroCredito(@PathVariable String numeroCredito) {
        return ResponseEntity.ok(creditoService.getCreditoByNumeroCredito(numeroCredito));
    }
}
