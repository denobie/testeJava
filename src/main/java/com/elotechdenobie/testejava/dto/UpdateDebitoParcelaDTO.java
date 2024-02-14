package com.elotechdenobie.testejava.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateDebitoParcelaDTO {

    @FutureOrPresent
    @Valid
    private LocalDate novaDataVencimento;
}
