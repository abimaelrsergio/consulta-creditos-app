package com.abimael.creditoapi.mapper;

import com.abimael.creditoapi.dto.CreditoDto;
import com.abimael.creditoapi.entity.Credito;

/**
 * Classe utilitária responsável por mapear entidades {@link Credito} para objetos {@link CreditoDto}.
 *
 * <p>Esta classe aplica o padrão Factory Method de forma estática, centralizando a criação
 * de objetos DTO a partir de entidades do domínio. A utilização de um mapper dedicado promove
 * a separação de responsabilidades (princípio SOLID - SRP)</p>
 *
 * @author Abimael
 */
public class CreditoMapper {

    private CreditoMapper() {
        // evita instanciação
    }

    /**
     * Cria um CreditoDto a partir de uma entidade Credito.
     *
     * @param credito entidade de origem
     * @return DTO com os dados mapeados
     */
    public static CreditoDto toDto(Credito credito) {
        return new CreditoDto(
                credito.getNumeroCredito(),
                credito.getNumeroNfse(),
                credito.getDataConstituicao(),
                credito.getValorIssqn(),
                credito.getTipoCredito(),
                credito.isSimplesNacional() ? "Sim" : "Não",
                credito.getAliquota(),
                credito.getValorFaturado(),
                credito.getValorDeducao(),
                credito.getBaseCalculo()
        );
    }
}
