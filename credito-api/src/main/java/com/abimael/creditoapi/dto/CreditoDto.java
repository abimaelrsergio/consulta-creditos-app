package com.abimael.creditoapi.dto;

import com.abimael.creditoapi.entity.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class CreditoDto {
    private String numeroCredito;
    private String numeroNfse;
    private LocalDate dataConstituicao;
    private BigDecimal valorIssqn;
    private String tipoCredito;
    private boolean simplesNacional;
    private BigDecimal aliquota;
    private BigDecimal valorFaturado;
    private BigDecimal valorDeducao;
    private BigDecimal baseCalculo;

    public CreditoDto(Credito credito) {
        this.numeroCredito = credito.getNumeroCredito();
        this.numeroNfse = credito.getNumeroNfse();
        this.dataConstituicao = credito.getDataConstituicao();
        this.valorIssqn = credito.getValorIssqn();
        this.tipoCredito = credito.getTipoCredito();
        this.simplesNacional = credito.isSimplesNacional();
        this.aliquota = credito.getAliquota();
        this.valorFaturado = credito.getValorFaturado();
        this.valorDeducao = credito.getValorDeducao();
        this.baseCalculo = credito.getBaseCalculo();
    }
}
