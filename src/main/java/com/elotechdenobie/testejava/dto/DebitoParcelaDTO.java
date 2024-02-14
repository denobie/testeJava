package com.elotechdenobie.testejava.dto;

import com.elotechdenobie.testejava.entities.Debito;
import com.elotechdenobie.testejava.entities.DebitoParcela;
import com.elotechdenobie.testejava.entities.SituacaoParcela;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Data
@Builder
public class DebitoParcelaDTO {
    private Long parcela;
    @Positive
    private BigDecimal valor = BigDecimal.ZERO;
    private LocalDate dataLancamento;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private LocalDate dataCancelamento;
    @JsonProperty("situacaoParcela")
    private SituacaoParcelaDTO situacaoParcela;

    public static DebitoParcelaDTO fromEntity(DebitoParcela debitoParcela){
        return DebitoParcelaDTO.builder()
                .parcela(debitoParcela.getParcela())
                .valor(debitoParcela.getValor())
                .dataLancamento(debitoParcela.getDataLancamento())
                .dataVencimento(debitoParcela.getDataVencimento())
                .dataPagamento(debitoParcela.getDataPagamento())
                .dataCancelamento(debitoParcela.getDataCancelamento())
                .situacaoParcela(SituacaoParcelaDTO.fromEntity(debitoParcela.getSituacao()))
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
                .situacao(situacaoParcela)
                .debito(debito)
                .build();
    }
}
