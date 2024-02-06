package com.elotechdenobie.testejava.services;

import com.elotechdenobie.testejava.dto.PessoaDTO;
import com.elotechdenobie.testejava.entities.Pessoa;
import com.elotechdenobie.testejava.exceptions.DataBaseException;
import com.elotechdenobie.testejava.exceptions.RestException;
import com.elotechdenobie.testejava.exceptions.ValidationException;
import com.elotechdenobie.testejava.repositories.PessoaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    public PessoaDTO findById(Long id){
        return PessoaDTO.fromEntity(foundPessoa(id));
    }

    public Page<PessoaDTO> findAll(Pageable pageable){
        return this.pessoaRepository.findAll(pageable).map(PessoaDTO::fromEntity);
    }

    public PessoaDTO insert(PessoaDTO pessoaDTO){
        log.info(String.format("Iniciando inserção da Pessoa '%s'", pessoaDTO.getNome()));

        Pessoa pessoaFound = this.pessoaRepository.findByCpfCnpj(pessoaDTO.getCpfCnpj());

        if (pessoaFound != null){
            throw new ValidationException(String.format("CPF/CNPJ Já cadastrado para a Pessoa '%d'", pessoaFound.getId()));
        }

        Pessoa pessoaSaved = this.pessoaRepository.save(PessoaDTO.toEntity(pessoaDTO));

        log.info(String.format("Finalizando inserção da Pessoa '%s' id '%d'", pessoaDTO.getNome(), pessoaSaved.getId()));

        return PessoaDTO.fromEntity(pessoaSaved);
    }

    public void delete(Long idDelete) {
        try {
            this.pessoaRepository.deleteById(foundPessoa(idDelete).getId());
        }
        catch (DataIntegrityViolationException e){
            throw new DataBaseException(e.getMessage());
        }

    }

    public PessoaDTO update(Long idUpdate, PessoaDTO requestPessoaDTO){
        log.info(String.format("Iniciando atualização da Pessoa '%d'", idUpdate));

        if (!requestPessoaDTO.getId().equals(idUpdate)){
            throw new ValidationException(String.format("Id da seção '%d' diferente do id do Request '%d'", idUpdate, requestPessoaDTO.getId()));
        }

        Pessoa pessoaSaved = this.pessoaRepository.save(prepareToMerge(PessoaDTO.toEntity(requestPessoaDTO)));

        log.info(String.format("Fim atualização da Pessoa '%d'", idUpdate));
        return PessoaDTO.fromEntity(pessoaSaved);
    }

    private Pessoa prepareToMerge(Pessoa pessoaToSave){
        Pessoa pessoaFound = foundPessoa(pessoaToSave.getId());

        return pessoaFound.merge(pessoaToSave);
    }

    private Pessoa foundPessoa(Long id){
        return this.pessoaRepository.findById(id)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, String.format(String.format("Pessoa '%d' não encontrada", id))));
    }
}
