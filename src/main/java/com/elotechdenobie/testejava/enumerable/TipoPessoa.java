package com.elotechdenobie.testejava.enumerable;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoPessoa {
    FISICA("F", CheckPessoaFisica.class),
    JURIDICA("J", CheckPessoaJuridica.class);

    private final String value;
    private final Class<?> validationGroup;

    TipoPessoa(String value, Class<?> validationGroup) {
        this.value = value;
        this.validationGroup = validationGroup;
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

    public Class<?> getValidationGroup() {
        return validationGroup;
    }

    public String getDescricao() {

        return this.name();
    }
}
