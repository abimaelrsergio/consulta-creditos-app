package com.abimael.creditoapi.service;

import com.abimael.creditoapi.dto.CreditoDto;
import com.abimael.creditoapi.entity.Credito;
import com.abimael.creditoapi.repository.CreditoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CreditoService {
    private final CreditoRepository creditoRepository;

    public CreditoService(CreditoRepository creditoRepository){
        this.creditoRepository = creditoRepository;
    }

    /**
     * Busca os credito(s) associados a um determinado numero de NFS-e.
     *
     * @param numeroNfse número da NFS-e
     * @return lista de créditos associados
     */
    public List<CreditoDto> getCreditoByNfse(String numeroNfse) {
        List<Credito> creditoList = creditoRepository.findByNumeroNfse(numeroNfse);
        return creditoList.stream().map(CreditoDto::new).toList();
    }

    /**
     * Busca um {@link Credito} associado a um determinado número de crédito.
     *
     * @param numeroCredito número do crédito
     * @return um {@link Credito} associado com o número de crédito
     */
    public CreditoDto getCreditoByNumeroCredito(String numeroCredito) {
        Credito credito = creditoRepository.findByNumeroCredito(numeroCredito);
        return new CreditoDto(credito);
    }
}
