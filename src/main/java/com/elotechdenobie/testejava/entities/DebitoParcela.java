package com.elotechdenobie.testejava.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "debitoparcela")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class DebitoParcela implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "debito", referencedColumnName = "id")
    @NotNull
    private Debito debito;

    @NotNull
    private Long parcela;

    @NotNull
    @Builder.Default
    private BigDecimal valor = BigDecimal.ZERO;

    @NotNull(message = "Data de Lançamento é obrigatória")
    @Column(name = "datalancamento")
    @PastOrPresent
    private LocalDate dataLancamento;

    @NotNull(message = "Data de Vencimento é obrigatória")
    @Column(name = "datavencimento")
    @FutureOrPresent
    private LocalDate dataVencimento;

    @Column(name = "datapagamento")
    private LocalDate dataPagamento;

    @Column(name = "datacancelamento")
    private LocalDate dataCancelamento;

    @ManyToOne
    @JoinColumn(name = "situacao", referencedColumnName = "id")
    private SituacaoParcela situacao;
}
