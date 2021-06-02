package com.example.crudpessoa.controller;

import com.example.crudpessoa.exception.NotFoundException;
import com.example.crudpessoa.model.Pessoa;
import com.example.crudpessoa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping(value = "/salvarPessoa")
    public ResponseEntity<Pessoa> salvarPessoa(@Valid @RequestBody Pessoa pessoa) {
        return new ResponseEntity<Pessoa>(this.pessoaService.salvarPessoa(pessoa), HttpStatus.CREATED);
    }

    @PutMapping(value = "/atualizarPessoa")
    public ResponseEntity<Pessoa> atualizarPessoa(@Valid @RequestBody Pessoa pessoa) throws NotFoundException {
        return new ResponseEntity<Pessoa>(this.pessoaService.atualizarPessoa(pessoa), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletarPessoa/{pessoaId}")
    public ResponseEntity<Pessoa> deletarPessoa(@PathVariable(value = "pessoaId") Integer pessoaId) throws NotFoundException {
        this.pessoaService.deletarPessoa(pessoaId);
        return new ResponseEntity<Pessoa>(HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPessoaId/{pessoaId}")
    public ResponseEntity<Pessoa> buscarPessoaId(@PathVariable(value = "pessoaId") Integer pessoaId) throws NotFoundException {
        return new ResponseEntity<Pessoa>(this.pessoaService.buscarPessoaId(pessoaId), HttpStatus.OK);
    }

    @GetMapping(value = "/listarPessoas")
    public ResponseEntity<Page<Pessoa>> listarPessoas(@RequestParam(value = "offset", defaultValue = "0") int page,
                                                      @RequestParam(value = "limit", defaultValue = "10") int size) {
        return new ResponseEntity<Page<Pessoa>>(this.pessoaService.listarPessoas(page, size), HttpStatus.OK);
    }

}
