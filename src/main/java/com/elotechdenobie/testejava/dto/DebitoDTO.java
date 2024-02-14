package com.elotechdenobie.testejava.dto;

import com.elotechdenobie.testejava.entities.Debito;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class DebitoDTO {
    private Long id;
    private LocalDate dataLancamento;
    @JsonProperty("pessoa")
    private PessoaDTO pessoaDTO;
    @JsonProperty("parcela")
    @Builder.Default
    private List<DebitoParcelaDTO> debitoParcelaDTO = new ArrayList<>();

    public static DebitoDTO fromEntity(Debito debito){
        return DebitoDTO.builder()
                .id(debito.getId())
                .dataLancamento(debito.getDataLancamento())
                .pessoaDTO(PessoaDTO.fromEntity(debito.getPessoa()))
                .debitoParcelaDTO(debito.getDebitoParcelas().stream().map(DebitoParcelaDTO::fromEntity).toList())
                .build();
    }
}
