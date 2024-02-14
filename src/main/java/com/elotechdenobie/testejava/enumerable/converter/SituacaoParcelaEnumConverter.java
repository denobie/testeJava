package com.elotechdenobie.testejava.enumerable.converter;

import com.elotechdenobie.testejava.enumerable.SituacaoParcela;
import jakarta.persistence.AttributeConverter;

import java.util.Objects;

public class SituacaoParcelaEnumConverter implements AttributeConverter<SituacaoParcela, Long> {

    @Override
    public Long convertToDatabaseColumn(SituacaoParcela attribute) {
        if (Objects.isNull(attribute)) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public SituacaoParcela convertToEntityAttribute(Long dbData) {
        return SituacaoParcela.fromValue(dbData);
    }
}
