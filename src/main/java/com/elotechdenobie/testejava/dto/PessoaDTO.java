package com.elotechdenobie.testejava.dto;

import com.elotechdenobie.testejava.entities.Pessoa;
import com.elotechdenobie.testejava.enumerable.TipoPessoa;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PessoaDTO {
    private Long id;
    private String nome;
    private String cpfCnpj;
    private String tipoPessoa;

    public static PessoaDTO fromEntity(Pessoa pessoa) {
        return PessoaDTO.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .cpfCnpj(pessoa.getCpfCnpj())
                .tipoPessoa(pessoa.getTipoPessoa().getValue())
                .build();
    }

    public static Pessoa toEntity(PessoaDTO pessoaDTO) {
        return Pessoa.builder()
                .id(pessoaDTO.getId())
                .nome(pessoaDTO.getNome())
                .cpfCnpj(pessoaDTO.getCpfCnpj())
                .tipoPessoa(TipoPessoa.fromValue(pessoaDTO.getTipoPessoa()))
                .build();
    }
}
