package com.abimael.creditoapi.mapper;

import com.abimael.creditoapi.dto.CreditoDto;
import com.abimael.creditoapi.entity.Credito;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
* Classe de teste para o mapeamento de créditos.
 *
 * @author Abimael Sergio
*/
class CreditoMapperTest {

    /**
     * Verifica se o mapeamento para um {@link CreditoDto} mantem a informação de que o contribuinte
     * é optante pelo Simples Nacional.
     *
     * @author Abimael
     */
    @Test
    void deveMapearParaDtoComSimplesNacionalTrue() {
        Credito credito = new Credito(
                1L,
                "123",
                "456",
                LocalDate.of(2024, 5, 22),
                BigDecimal.valueOf(1000),
                "ISSQN",
                true,
                BigDecimal.valueOf(5),
                BigDecimal.valueOf(20000),
                BigDecimal.valueOf(3000),
                BigDecimal.valueOf(17000)
        );

        CreditoDto dto = CreditoMapper.toDto(credito);

        assertEquals("123", dto.getNumeroCredito());
        assertEquals("Sim", dto.getSimplesNacional());
    }

    /**
     * Verifica se o mapeamento para um {@link CreditoDto} mantem a informação de que o contribuinte
     * não optante pelo Simples Nacional.
     *
     * @author Abimael
     */
    @Test
    void deveMapearParaDtoComSimplesNacionalFalse() {
        Credito credito = new Credito(
                2L,
                "321",
                "654",
                LocalDate.of(2024, 5, 21),
                BigDecimal.valueOf(1200),
                "Outros",
                false,
                BigDecimal.valueOf(4.5),
                BigDecimal.valueOf(15000),
                BigDecimal.valueOf(2500),
                BigDecimal.valueOf(12500)
        );

        CreditoDto dto = CreditoMapper.toDto(credito);

        assertEquals("321", dto.getNumeroCredito());
        assertEquals("Não", dto.getSimplesNacional());
    }
}
