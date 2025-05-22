package com.abimael.creditoapi.dto;
import io.swagger.v3.oas.annotations.media.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Representa um crédito constituído. Contém atributos como número do crédito, NFS-e,
 * valores e indicadores fiscais.
 */
@Schema(
        name = "Credito",
        description = "Objeto Credito"
)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreditoDto {

    @Schema(
            description = "Número identificador do crédito constituído"
    )
    private String numeroCredito;

    @Schema(
            description = "Número da nota fiscal de serviço eletrônica (NFS-e) vinculada ao crédito"
    )
    private String numeroNfse;

    @Schema(
            description = "Data em que o crédito foi constituído"
    )
    private LocalDate dataConstituicao;

    @Schema(
            description = "Valor do ISSQN apurado para o crédito"
    )
    private BigDecimal valorIssqn;

    @Schema(
            description = "Tipo do crédito, como ISSQN ou Outros"
    )
    private String tipoCredito;

    @Schema(
            description = "Indica se o contribuinte é optante pelo Simples Nacional (Sim ou Não)"
    )
    private String simplesNacional;

    @Schema(
            description = "Alíquota utilizada na apuração do crédito"
    )
    private BigDecimal aliquota;

    @Schema(
            description = "Valor total faturado na operação"
    )
    private BigDecimal valorFaturado;

    @Schema(
            description = "Valor total de deduções aplicadas"
    )
    private BigDecimal valorDeducao;

    @Schema(
            description = "Base de cálculo final utilizada para cálculo do ISSQN"
    )
    private BigDecimal baseCalculo;
}

