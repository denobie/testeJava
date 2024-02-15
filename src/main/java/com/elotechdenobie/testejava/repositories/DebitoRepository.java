package com.elotechdenobie.testejava.repositories;

import com.elotechdenobie.testejava.entities.Debito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface DebitoRepository extends JpaRepository<Debito, Long>, JpaSpecificationExecutor<Debito> {

    List<Debito> findAllByPessoaId(Long id);

    List<Debito> findAllByDataLancamentoBetween(LocalDate dataInicial, LocalDate dataFinal);

    @Query(value = "select coalesce(sum(valor), 0) from DebitoParcela")
    BigDecimal findValorTotalDebitosLancados();

    @Query(value = "select coalesce(sum(valor), 0) from DebitoParcela where situacaoParcela = 2")
    BigDecimal findValorTotalDebitosPagos();

    @Query(value = "select coalesce(sum(valor), 0) from DebitoParcela where situacaoParcela = 3")
    BigDecimal findValorTotalDebitosCancelados();

}
