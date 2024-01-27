package com.elotechdenobie.testejava.enumerable;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoPessoa {
    FISICA("F", CheckPessoaFisica.class),
    JURIDICA("J", CheckPessoaJuridica.class);

    private final String value;
    private final Class<?> group;

    TipoPessoa(String value, Class<?> group) {
        this.value = value;
        this.group = group;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    public static TipoPessoa fromString(String value) {

        if ("F".equals(value)) {
            return FISICA;
        } else if ("J".equals(value)) {
            return JURIDICA;
        }

        return null;

    }

    public Class<?> getGroup() {
        return group;
    }

    public String getDescricao() {

        return this.name();
    }
}
