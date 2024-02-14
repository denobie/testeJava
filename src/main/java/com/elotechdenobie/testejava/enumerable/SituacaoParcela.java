package com.elotechdenobie.testejava.enumerable;

import com.elotechdenobie.testejava.exceptions.ValidationException;
import lombok.Getter;

@Getter
public enum SituacaoParcela {

    ABERTA(1L, "EM ABERTO"),
    PAGA(2L, "PAGA"),
    CANCELADA(3L, "CANCELADA");

    private final Long value;

    private final String descricao;

    SituacaoParcela(Long value, String descricao) {
        this.value = value;
        this.descricao = descricao;
    }

    public static SituacaoParcela fromValue(Long value){
        return switch (value.intValue()) {
            case 1 -> ABERTA;
            case 2 -> PAGA;
            case 3 -> CANCELADA;
            default -> throw new ValidationException(String.format("Situação Parcela '%d' não encontrada.", value));
        };
    }
}
