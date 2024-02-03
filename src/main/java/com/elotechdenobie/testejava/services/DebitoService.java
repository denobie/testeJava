package com.elotechdenobie.testejava.services;

import com.elotechdenobie.testejava.dto.DebitoDTO;
import com.elotechdenobie.testejava.entities.Debito;
import com.elotechdenobie.testejava.exceptions.ValidationException;
import com.elotechdenobie.testejava.repositories.DebitoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DebitoService {
    private final DebitoRepository debitoRepository;

    public DebitoDTO findById(Long id){
        return DebitoDTO.fromEntity(debitoFound(id));
    }

    public Page<DebitoDTO> findAll(Pageable pageable){
        return this.debitoRepository.findAll(pageable).map(DebitoDTO::fromEntity);
    }

    public Page<DebitoDTO> findAllByPessoa(Pageable pageable, Long id){
        List<DebitoDTO> allDebitosDTO = this.debitoRepository.findAllByPessoaId(id).stream().map(DebitoDTO::fromEntity).toList();

        List<DebitoDTO> listDebitosDTO = allDebitosDTO.stream().skip(pageable.getOffset()).limit(pageable.getPageSize()).toList();

        return new PageImpl<>(listDebitosDTO, pageable, allDebitosDTO.size());
    }

    public Page<DebitoDTO> findAllByPriodoDataLancamento(Pageable pageable, LocalDate dataInicial, LocalDate dataFinal){

        List<DebitoDTO> allDebitosDTO = this.debitoRepository.findAllByDataLancamentoBetween(dataInicial, dataFinal)
                .stream().map(DebitoDTO::fromEntity).toList();

        List<DebitoDTO> listDebitosDTO = allDebitosDTO.stream().skip(pageable.getOffset()).limit(pageable.getPageSize()).toList();

        return new PageImpl<>(listDebitosDTO, pageable, allDebitosDTO.size());
    }

    private Debito debitoFound(Long id){
        return this.debitoRepository.findById(id).orElseThrow(() -> new ValidationException(String.format("Débito não encontrado para o '%d'.", id)));
    }
}
