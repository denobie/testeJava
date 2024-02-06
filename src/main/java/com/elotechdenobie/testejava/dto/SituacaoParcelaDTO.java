package com.elotechdenobie.testejava.dto;

import com.elotechdenobie.testejava.entities.SituacaoParcela;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SituacaoParcelaDTO {
    private Long id;
    private String descricao;
    private String tipo;

    public static SituacaoParcelaDTO fromEntity(SituacaoParcela situacaoParcela){
        return SituacaoParcelaDTO.builder()
                .id(situacaoParcela.getId())
                .descricao(situacaoParcela.getDescricao())
                .tipo(situacaoParcela.getTipo())
                .build();
    }

    public static SituacaoParcela toEntity(SituacaoParcelaDTO situacaoParcelaDTO){
        return SituacaoParcela.builder()
                .id(situacaoParcelaDTO.getId())
                .descricao(situacaoParcelaDTO.getDescricao())
                .tipo(situacaoParcelaDTO.getTipo())
                .build();
    }
}
