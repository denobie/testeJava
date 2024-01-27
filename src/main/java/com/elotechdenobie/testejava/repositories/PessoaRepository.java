package com.elotechdenobie.testejava.repositories;

import com.elotechdenobie.testejava.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
