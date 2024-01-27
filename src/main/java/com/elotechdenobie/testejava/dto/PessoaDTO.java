package com.elotechdenobie.testejava.dto;

import com.elotechdenobie.testejava.entities.Pessoa;
import com.elotechdenobie.testejava.enumerable.TipoPessoa;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PessoaDTO {
    private Long id;
    private String nome;
    private String cpfCnpj;
    private TipoPessoa tipoPessoa;

    public static PessoaDTO fromEntity(Pessoa pessoa) {
        return PessoaDTO.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .cpfCnpj(pessoa.getCpfCnpj())
                .tipoPessoa(pessoa.getTipoPessoa())
                .build();
    }

    public static Pessoa toEntity(PessoaDTO pessoaDTO) {
        return Pessoa.builder()
                .id(pessoaDTO.getId())
                .nome(pessoaDTO.getNome())
                .cpfCnpj(pessoaDTO.getCpfCnpj())
                .tipoPessoa(pessoaDTO.getTipoPessoa())
                .build();
    }
}
