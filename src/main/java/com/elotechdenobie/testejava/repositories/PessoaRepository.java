package com.elotechdenobie.testejava.repositories;

import com.elotechdenobie.testejava.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Pessoa findByCpfCnpj(String cpfCnpj);
}
