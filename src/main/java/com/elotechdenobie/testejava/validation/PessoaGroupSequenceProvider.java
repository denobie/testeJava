package com.elotechdenobie.testejava.validation;

import com.elotechdenobie.testejava.entities.Pessoa;
import com.elotechdenobie.testejava.enumerable.CheckPessoaFisica;
import com.elotechdenobie.testejava.enumerable.CheckPessoaJuridica;
import jakarta.validation.GroupSequence;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;
import com.elotechdenobie.testejava.enumerable.TipoPessoa;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@GroupSequence({ CheckPessoaFisica.class, CheckPessoaJuridica.class, Pessoa.class })
public class PessoaGroupSequenceProvider implements DefaultGroupSequenceProvider<Pessoa> {
    @Override
    public List<Class<?>> getValidationGroups(Pessoa pessoa){
        if (pessoa != null && pessoa.getTipoPessoa() != null) {
            if (TipoPessoa.FISICA.equals(pessoa.getTipoPessoa())) {
                return Arrays.asList(CheckPessoaFisica.class, Pessoa.class);
            } else if (TipoPessoa.JURIDICA.equals(pessoa.getTipoPessoa())) {
                return Arrays.asList(CheckPessoaJuridica.class, Pessoa.class);
            }
        }
        return Collections.singletonList(Pessoa.class);
    }

    protected boolean isPessoaSelected(Pessoa pessoa){
        return pessoa != null && pessoa.getTipoPessoa() != null;
    }
}
