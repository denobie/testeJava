package com.elotechdenobie.testejava.enumerable;

import lombok.Getter;

@Getter
public enum TipoPessoa {
    FISICA("F", "Fisica", CheckPessoaFisica.class),
    JURIDICA("J", "Juridica", CheckPessoaJuridica.class);

    private final String value;
    private final String descricao;
    private final Class<?> group;

    TipoPessoa(String value, String descricao, Class<?> group){
        this.value = value;
        this.descricao = descricao;
        this.group = group;
    }

    public static TipoPessoa fromValue(String value) {
        if (TipoPessoa.FISICA.getValue().equals(value)) {
            return FISICA;
        } else if (TipoPessoa.JURIDICA.getValue().equals(value)) {
            return JURIDICA;
        }

        return null;
    }
}
