package com.elotechdenobie.testejava.dto;

import com.elotechdenobie.testejava.entities.Pessoa;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PessoaDTO {
    private Long id;
    @NotNull
    @NotEmpty
    private String nome;

    public static PessoaDTO fromEntity(Pessoa pessoa) {
        return PessoaDTO.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .build();
    }

    public static Pessoa toEntity(PessoaDTO pessoaDTO) {
        return Pessoa.builder()
                .id(pessoaDTO.getId())
                .nome(pessoaDTO.getNome())
                .build();
    }
}
