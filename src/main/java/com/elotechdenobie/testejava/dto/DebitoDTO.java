package com.elotechdenobie.testejava.dto;

import com.elotechdenobie.testejava.entities.Debito;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class DebitoDTO {
    private Long id;
    private LocalDate dataLancamento;
    @JsonProperty("pessoa")
    private PessoaDTO pessoaDTO;

    public static DebitoDTO fromEntity(Debito debito){
        return DebitoDTO.builder()
                .id(debito.getId())
                .dataLancamento(debito.getDataLancamento())
                .pessoaDTO(PessoaDTO.fromEntity(debito.getPessoa()))
                .build();
    }

   public static Debito toEntity(DebitoDTO debitoDTO){
        return Debito.builder()
                .id(debitoDTO.getId())
                .dataLancamento(debitoDTO.getDataLancamento())
                .pessoa(PessoaDTO.toEntity(debitoDTO.getPessoaDTO()))
                .build();
   }
}
