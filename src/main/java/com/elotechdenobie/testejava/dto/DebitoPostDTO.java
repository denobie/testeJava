package com.elotechdenobie.testejava.dto;

import com.elotechdenobie.testejava.entities.Debito;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class DebitoPostDTO {

    private Long id = 0L;
    private LocalDate dataLancamento;
    @JsonProperty("pessoa")
    private Long pessoa;
    @NotNull
    @Valid
    private List<DebitoParcelaDTO> parcelas = new ArrayList<>();

   public static Debito toEntity(DebitoPostDTO debitoDTO){
       Debito debito = new Debito();
       debito.setDataLancamento(debitoDTO.getDataLancamento());

        return debito;
   }
}
