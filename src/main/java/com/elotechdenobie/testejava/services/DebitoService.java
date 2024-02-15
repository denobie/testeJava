package com.elotechdenobie.testejava.services;

import com.elotechdenobie.testejava.dto.DebitoDTO;
import com.elotechdenobie.testejava.dto.DebitoParcelaDTO;
import com.elotechdenobie.testejava.dto.DebitoPostDTO;
import com.elotechdenobie.testejava.dto.DebitoResumoDTO;
import com.elotechdenobie.testejava.entities.Debito;
import com.elotechdenobie.testejava.entities.DebitoParcela;
import com.elotechdenobie.testejava.entities.Pessoa;
import com.elotechdenobie.testejava.enumerable.SituacaoParcela;
import com.elotechdenobie.testejava.exceptions.RestException;
import com.elotechdenobie.testejava.exceptions.ValidationException;
import com.elotechdenobie.testejava.repositories.DebitoRepository;
import com.elotechdenobie.testejava.repositories.PessoaRepository;
import com.elotechdenobie.testejava.specifications.DebitoSpecifications;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class DebitoService {
    private final DebitoRepository debitoRepository;
    private final PessoaRepository pessoaRepository;
    public DebitoDTO insert(DebitoPostDTO debitoPostDTO){
        log.info("Iniciando inserção de um novo débito");

        Pessoa pessoa = this.pessoaRepository.findById(debitoPostDTO.getPessoa())
                            .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, String.format("Pessoa '%d' não encontrada.", debitoPostDTO.getPessoa())));

        Debito debitoToSave = DebitoPostDTO.toEntity(debitoPostDTO);
        debitoToSave.setPessoa(pessoa);

        addParcelasIntoDebito(debitoToSave, debitoPostDTO);

        Debito debitoSaved = this.debitoRepository.save(debitoToSave);

        log.info(String.format("Finalizado inserção de um novo débito id '%d'", debitoSaved.getId()));
        return DebitoDTO.fromEntity(debitoSaved);
    }

    public void delete(Long id){
        log.info(String.format("Iniciando exclusão do débito '%d'", id));

        Debito debitoToDelete = this.debitoFound(id);

        this.debitoRepository.delete(debitoToDelete);

        log.info(String.format("Finalizado exclusão do débito '%d'", id));
    }

    public DebitoDTO insertParcelaIntoDebito(Long id, DebitoPostDTO debitoPostDTO){
        log.info(String.format("Iniciando inserção de nova parcela no débito '%d'", id));

        if (!debitoPostDTO.getId().equals(id)) {
            throw new ValidationException(String.format("Id da seção '%d' diferente do id do Request '%d'", id, debitoPostDTO.getId()));
        }

        Debito debito = debitoFound(debitoPostDTO.getId());

        addParcelasIntoDebito(debito, debitoPostDTO);

        log.info(String.format("Finalizado inserção de nova parcela no débito '%d'", id));
        return DebitoDTO.fromEntity(this.debitoRepository.save(debito));
    }

    private void addParcelasIntoDebito(Debito debitoToSave, DebitoPostDTO debitoPostDTO) {
        debitoPostDTO.getParcelas().stream().map(
                debitoParcelaDTO -> {
                    DebitoParcela debitoParcelaToSave = DebitoParcelaDTO.toEntity(debitoParcelaDTO, debitoToSave, SituacaoParcela.ABERTA);
                    debitoParcelaToSave.setParcela(debitoToSave.getNextParcela());

                    return debitoParcelaToSave;
                }
        ).forEach(debitoToSave.getDebitoParcelas()::add);
    }

    public DebitoDTO findById(Long id){
        return DebitoDTO.fromEntity(debitoFound(id));
    }

    public Page<DebitoDTO> findAll(Pageable pageable){
        return this.debitoRepository.findAll(pageable).map(DebitoDTO::fromEntity);
    }

    public Page<DebitoDTO> findAllByPessoa(Pageable pageable, Long id){
        List<DebitoDTO> allDebitosDTO = this.debitoRepository.findAllByPessoaId(id)
                                            .stream().map(DebitoDTO::fromEntity).toList();

        List<DebitoDTO> listDebitosDTO = allDebitosDTO.stream()
                                                      .skip(pageable.getOffset())
                                                      .limit(pageable.getPageSize()).toList();

        return new PageImpl<>(listDebitosDTO, pageable, allDebitosDTO.size());
    }

    public Page<DebitoDTO> findAllByPriodoDataLancamento(Pageable pageable, LocalDate dataInicial, LocalDate dataFinal){

        dataFinal = Objects.isNull(dataFinal) ? dataInicial : dataFinal;
        //Como não vai dar tempo para alterar, aqui eu mudaria para buscar a data de lancamento da parcela.
        List<DebitoDTO> allDebitosDTO = this.debitoRepository.findAllByDataLancamentoBetween(dataInicial, dataFinal)
                                            .stream().map(DebitoDTO::fromEntity).toList();

        List<DebitoDTO> listDebitosDTO = allDebitosDTO.stream()
                                                      .skip(pageable.getOffset())
                                                      .limit(pageable.getPageSize()).toList();

        return new PageImpl<>(listDebitosDTO, pageable, allDebitosDTO.size());
    }

    public DebitoDTO prorrogaParcela(Long id, Long parcela, LocalDate novaDataVencimento) {
        log.info(String.format("Iniciando prorrogação da Data de Vencimento da parcela '%d' do débito '%d'", parcela, id));

        Debito debitoToUpdate = debitoFound(id);

        DebitoParcela debitoParcelaToUpdate = debitoToUpdate.getDebitoParcelas().stream()
                .filter(debitoParcela -> debitoParcela.getParcela().equals(parcela))
                .findFirst().orElseThrow(() -> new ValidationException(String.format("Parcela '%d' não encontrada para o Débito '%d'", parcela, debitoToUpdate.getId())));

        debitoParcelaToUpdate.setDataVencimento(novaDataVencimento);

        this.debitoRepository.save(debitoToUpdate);

        log.info(String.format("Finalizado prorrogação da Data de Vencimento da parcela '%d' do débito '%d'", parcela, id));
        return DebitoDTO.fromEntity(debitoToUpdate);
    }

    public DebitoResumoDTO getValorTotalDebitosLancados(){
        return DebitoResumoDTO.of(this.debitoRepository.findValorTotalDebitosLancados(), null, null);
    }

    public DebitoResumoDTO getValorTotalDebitosPagos(){
        return DebitoResumoDTO.of(null, null, this.debitoRepository.findValorTotalDebitosPagos());
    }

    public DebitoResumoDTO getValorTotalDebitosCancelados(){
        return DebitoResumoDTO.of(null, this.debitoRepository.findValorTotalDebitosCancelados(), null);
    }

    public DebitoResumoDTO getResumoDebitos(){
        return DebitoResumoDTO.of(this.debitoRepository.findValorTotalDebitosLancados(),
                                  this.debitoRepository.findValorTotalDebitosCancelados(),
                                  this.debitoRepository.findValorTotalDebitosPagos());
    }

    private Debito debitoFound(Long id){
        return this.debitoRepository.findById(id).orElseThrow(() -> new ValidationException(String.format("Débito '%d' não encontrado.", id)));
    }

    //Aqui eu criaria um novo método e endpoint para que fosse possível movimentar todas as parcelas EM ABERTO do débito, percorrendo a lista checando se está EM ABERTO e fazendo o pagamento.
    public void movimentaParcela(Long id, Long parcela, SituacaoParcela situacaoParcela) {
        log.info(String.format("Iniciando movimentação da parcela '%d' do débito '%d' para '%s'", parcela, id, situacaoParcela.getDescricao()));

        Debito debitoToUpdate = debitoFound(id);

        DebitoParcela debitoParcelaToUpdate = debitoToUpdate.getDebitoParcelas().stream()
                .filter(debitoParcela -> debitoParcela.getParcela().equals(parcela))
                .findFirst().orElseThrow(() -> new ValidationException(String.format("Parcela '%d' não encontrada para o Débito '%d'", parcela, debitoToUpdate.getId())));

        if (debitoParcelaToUpdate.getSituacaoParcela().equals(SituacaoParcela.ABERTA)) {
            if (situacaoParcela.equals(SituacaoParcela.PAGA)) {
                debitoParcelaToUpdate.setDataPagamento(LocalDate.now());
            } else {
                debitoParcelaToUpdate.setDataCancelamento(LocalDate.now());
            }
        }
        else {
            throw new ValidationException(String.format("Para movimentar a parcela para '%s' ela precisa estar EM ABERTO. Situação atual '%s'", situacaoParcela.getDescricao(), debitoParcelaToUpdate.getSituacaoParcela().getDescricao()));
        }

        debitoParcelaToUpdate.setSituacaoParcela(situacaoParcela);

        this.debitoRepository.save(debitoToUpdate);

        log.info(String.format("Finalizado movimentação da parcela '%d' do débito '%d' para '%s'", parcela, id, situacaoParcela.getDescricao()));
    }

    public Page<DebitoDTO> findByCustomCriteria(LocalDate dataInicial, LocalDate dataFinal, String nome, String cpfCnpj, Pageable pageable){

        dataFinal = Objects.isNull(dataFinal) ? dataInicial : dataFinal;

        Specification<Debito> specification = Specification.where(DebitoSpecifications.byDataLancamentoBetween(dataInicial, dataFinal));

        if (StringUtils.isNotBlank(nome)){
            specification = specification.and(DebitoSpecifications.byNome(nome));
        }

        if (StringUtils.isNotBlank(cpfCnpj)){
            specification = specification.and(DebitoSpecifications.byCpfCnpj(cpfCnpj));
        }

        List<DebitoDTO> allDebitoDTOS = this.debitoRepository.findAll(specification, pageable).stream().map(DebitoDTO::fromEntity).toList();

        List<DebitoDTO> listDebitosDTO = allDebitoDTOS.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize()).toList();

        return new PageImpl<>(listDebitosDTO, pageable, allDebitoDTOS.size());
    }
}
