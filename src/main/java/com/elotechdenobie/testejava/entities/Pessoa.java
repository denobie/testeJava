package com.elotechdenobie.testejava.entities;

import com.elotechdenobie.testejava.enumerable.CheckPessoaFisica;
import com.elotechdenobie.testejava.enumerable.CheckPessoaJuridica;
import com.elotechdenobie.testejava.enumerable.TipoPessoa;
import com.elotechdenobie.testejava.enumerable.converter.TipoPessoaEnumConverter;
import com.elotechdenobie.testejava.validation.PessoaGroupSequenceProvider;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import java.io.Serializable;

@Entity
@Table(name = "pessoa")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@GroupSequenceProvider(PessoaGroupSequenceProvider.class)
public class Pessoa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    @NotBlank
    private String nome;

    @Length(max = 20)
    @Column(name = "cpf_cnpj")
    @NotNull(message = "CPF/CNPJ é obrigatório")
    @CPF(groups = CheckPessoaFisica.class)
    @CNPJ(groups = CheckPessoaJuridica.class)
    private String cpfCnpj;

    @Column(name = "tipopessoa", length = 1)
    @NotNull(message = "TipoPessoa é obrigatório")
    @Convert(converter = TipoPessoaEnumConverter.class)
    private TipoPessoa tipoPessoa;

    public Pessoa merge(Pessoa pessoaToMerge){
        this.setNome(pessoaToMerge.getNome());
        this.setCpfCnpj(pessoaToMerge.getCpfCnpj());
        this.setTipoPessoa(pessoaToMerge.getTipoPessoa());

        return this;
    }

}
