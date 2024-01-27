package com.elotechdenobie.testejava.entities;

import com.elotechdenobie.testejava.enumerable.CheckPessoaFisica;
import com.elotechdenobie.testejava.enumerable.CheckPessoaJuridica;
import com.elotechdenobie.testejava.enumerable.TipoPessoa;
import com.elotechdenobie.testejava.validation.PessoaValidationGroupsQuery;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "pessoa")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Pessoa implements PessoaValidationGroupsQuery, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    @NotNull(message = "Nome é obrigatório")
    private String nome;

    @Length(max = 20)
    @CPF(groups = CheckPessoaFisica.class)
    @CNPJ(groups = CheckPessoaJuridica.class)
    @NotNull(message = "CPF/CNPJ é obrigatório")
    private String cpfCnpj;

    @Column(length = 1)
    @NotNull(message = "TipoPessoa é obrigatório")
    private TipoPessoa tipoPessoa;


    public Pessoa merge(Pessoa pessoaToMerge){
        this.setNome(pessoaToMerge.getNome());
        this.setCpfCnpj(pessoaToMerge.getCpfCnpj());
        this.setTipoPessoa(pessoaToMerge.getTipoPessoa());

        return this;
    }

    @Override
    public List<Class<?>> queryValidationsGroup() {
      return Arrays.asList(tipoPessoa.getValidationGroup());
    }
}
