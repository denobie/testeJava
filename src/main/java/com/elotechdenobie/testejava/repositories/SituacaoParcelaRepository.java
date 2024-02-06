package com.elotechdenobie.testejava.repositories;

import com.elotechdenobie.testejava.entities.SituacaoParcela;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SituacaoParcelaRepository extends JpaRepository<SituacaoParcela, Long> {
}
