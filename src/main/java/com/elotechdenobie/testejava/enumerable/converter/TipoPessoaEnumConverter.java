package com.elotechdenobie.testejava.enumerable.converter;

import com.elotechdenobie.testejava.enumerable.TipoPessoa;
import jakarta.persistence.AttributeConverter;

import java.util.Objects;

public class TipoPessoaEnumConverter implements AttributeConverter<TipoPessoa, String> {

    @Override
    public String convertToDatabaseColumn(TipoPessoa attribute) {
        if (Objects.isNull(attribute)) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public TipoPessoa convertToEntityAttribute(String dbData) {
        return TipoPessoa.fromValue(dbData);
    }
}
