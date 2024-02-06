package com.elotechdenobie.testejava.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "debito")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Debito implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pessoa", referencedColumnName = "id")
    private Pessoa pessoa;

    @Column(name = "datalancamento")
    @PastOrPresent(message = "Data de Lançamento não pode ser superior a Data Atual.")
    private LocalDate dataLancamento;

    @OneToMany(mappedBy = "debito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DebitoParcela> debitoParcela = new ArrayList<>();
}
