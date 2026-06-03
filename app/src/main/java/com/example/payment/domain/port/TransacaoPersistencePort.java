package com.example.payment.domain.port;

import com.example.payment.domain.entity.TransacaoPixQueryRequest;
import com.example.payment.domain.entity.TransacaoPixRequest;
import com.example.payment.domain.entity.TransacaoPixUpdateRequest;
import com.example.payment.domain.entity.TransacaoPixResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Port que define o contrato de persistência de transações PIX.
 * Representa a saída hexagonal - qualquer adapter de persistência deve implementar este contrato.
 * Localizado no domain, completamente desacoplado de frameworks.
 */
public interface TransacaoPersistencePort {

    /**
     * Salva uma nova transação PIX.
     *
     * @param request dados da transação
     * @return código da transação criada
     */
    String save(TransacaoPixRequest request);

    /**
     * Deleta uma transação PIX.
     *
     * @param id código da transação
     */
    void delete(String id);

    /**
     * Atualiza uma transação PIX.
     *
     * @param id código da transação
     * @param request dados da atualização
     */
    void update(String id, TransacaoPixUpdateRequest request);

    /**
     * Busca uma transação PIX por ID.
     *
     * @param id código da transação
     * @return dados da transação
     */
    TransacaoPixResponse getById(String id);

    /**
     * Busca transações PIX com filtros opcionais.
     *
     * @param request critérios de busca
     * @param pageable paginação
     * @return página de transações
     */
    Page<TransacaoPixResponse> query(TransacaoPixQueryRequest request, Pageable pageable);
}
