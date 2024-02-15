package com.elotechdenobie.testejava.specifications;

import com.elotechdenobie.testejava.entities.Debito;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class DebitoSpecifications {
    public static Specification<Debito> byDataLancamentoBetween(LocalDate dataInicial, LocalDate dataFinal) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("dataLancamento"), dataInicial, dataFinal);
    }

    public static Specification<Debito> byNome(String nome) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("pessoa").get("nome"), "%" + nome + "%");
    }

    public static Specification<Debito> byCpfCnpj(String cpfCnpj) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("pessoa").get("cpfCnpj"), "%" + cpfCnpj + "%");
    }
}
