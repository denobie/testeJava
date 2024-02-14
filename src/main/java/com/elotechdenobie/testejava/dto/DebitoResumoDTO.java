package com.elotechdenobie.testejava.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DebitoResumoDTO {
    private BigDecimal valorTotalLancado;
    private BigDecimal valorTotalCancelado;
    private BigDecimal valorTotalPago;

    public static DebitoResumoDTO of(BigDecimal valorTotalLancado, BigDecimal valorTotalCancelado, BigDecimal valorTotalPago){
        DebitoResumoDTO debitoResumoDTO = new DebitoResumoDTO();

        debitoResumoDTO.setValorTotalLancado(valorTotalLancado);
        debitoResumoDTO.setValorTotalPago(valorTotalPago);
        debitoResumoDTO.setValorTotalCancelado(valorTotalCancelado);

        return debitoResumoDTO;
    }

}
