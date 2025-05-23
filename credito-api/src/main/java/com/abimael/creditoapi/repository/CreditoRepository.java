package com.abimael.creditoapi.repository;

import com.abimael.creditoapi.entity.Credito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface CreditoRepository extends JpaRepository<Credito, Long> {
    /**
     * Busca uma lista de {@link Credito} asssociados a um determinado número de NFS-e.
     *
     * @param numeroNfse número da NFS-e
     * @return uma lista de {@link Credito} associados com uma NFS-e
     */
    List<Credito> findByNumeroNfse(String numeroNfse);

    /**
     * Busca um {@link Credito} associado a um determinado número de crédito.
     *
     * @param numeroCredito número do crédito
     * @return um {@link Credito} associado com o número de crédito
     */
    Optional<Credito> findByNumeroCredito(String numeroCredito);
}
