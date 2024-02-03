package com.elotechdenobie.testejava.repositories;

import com.elotechdenobie.testejava.entities.Debito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DebitoRepository extends JpaRepository<Debito, Long> {
    List<Debito> findAllByPessoaId(Long id);

    List<Debito> findAllByDataLancamentoBetween(LocalDate dataInicial, LocalDate dataFinal);
}
