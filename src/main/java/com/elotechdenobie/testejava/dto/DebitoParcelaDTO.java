package com.elotechdenobie.testejava.dto;

import com.elotechdenobie.testejava.entities.Debito;
import com.elotechdenobie.testejava.entities.DebitoParcela;
import com.elotechdenobie.testejava.enumerable.SituacaoParcela;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class DebitoParcelaDTO {
    private Long parcela;
    @Positive(message = "Valor deve ser maior que zero")
    @Valid
    private BigDecimal valor;
    private LocalDate dataLancamento;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private LocalDate dataCancelamento;
    private Long situacaoParcela;
    private String descricaoSituacaoParcela;

    public static DebitoParcelaDTO fromEntity(DebitoParcela debitoParcela){
        return DebitoParcelaDTO.builder()
                .parcela(debitoParcela.getParcela())
                .valor(debitoParcela.getValor())
                .dataLancamento(debitoParcela.getDataLancamento())
                .dataVencimento(debitoParcela.getDataVencimento())
                .dataPagamento(debitoParcela.getDataPagamento())
                .dataCancelamento(debitoParcela.getDataCancelamento())
                .situacaoParcela(debitoParcela.getSituacaoParcela().getValue())
                .descricaoSituacaoParcela(debitoParcela.getSituacaoParcela().getDescricao())
                .build();
    }

    public static DebitoParcela toEntity(DebitoParcelaDTO debitoParcelaDTO, Debito debito, SituacaoParcela situacaoParcela){
        return DebitoParcela.builder()
                .parcela(debitoParcelaDTO.getParcela())
                .valor(debitoParcelaDTO.getValor())
                .dataLancamento(debitoParcelaDTO.getDataLancamento())
                .dataVencimento(debitoParcelaDTO.getDataVencimento())
                .dataPagamento(debitoParcelaDTO.getDataPagamento())
                .dataCancelamento(debitoParcelaDTO.getDataCancelamento())
                .situacaoParcela(situacaoParcela)
                .debito(debito)
                .build();
    }
}
