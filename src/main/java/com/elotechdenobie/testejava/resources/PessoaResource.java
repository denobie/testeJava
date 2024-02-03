package com.elotechdenobie.testejava.resources;

import com.elotechdenobie.testejava.dto.PessoaDTO;
import com.elotechdenobie.testejava.entities.Pessoa;
import com.elotechdenobie.testejava.services.PessoaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/pessoa")
@RequiredArgsConstructor
public class PessoaResource {
    private final PessoaService pessoaService;

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.pessoaService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<PessoaDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(this.pessoaService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> insert(@RequestBody @Valid PessoaDTO pessoaRequestDTO) {
        return ResponseEntity.created(URI.create("")).body(this.pessoaService.insert(pessoaRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.pessoaService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDTO> update(@PathVariable Long id, @RequestBody @Valid PessoaDTO pessoaRequestDTO) {
        return ResponseEntity.ok(this.pessoaService.update(id, pessoaRequestDTO));
    }
}
