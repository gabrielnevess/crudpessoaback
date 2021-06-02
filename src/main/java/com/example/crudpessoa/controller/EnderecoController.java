package com.example.crudpessoa.controller;

import com.example.crudpessoa.exception.NotFoundException;
import com.example.crudpessoa.model.Endereco;
import com.example.crudpessoa.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping(value = "/salvarEndereco")
    public ResponseEntity<Endereco> salvarEndereco(@Valid @RequestBody Endereco endereco) {
        return new ResponseEntity<Endereco>(this.enderecoService.salvarEndereco(endereco), HttpStatus.CREATED);
    }

    @PutMapping(value = "/atualizarEndereco")
    public ResponseEntity<Endereco> atualizarEndereco(@Valid @RequestBody Endereco endereco) throws NotFoundException {
        return new ResponseEntity<Endereco>(this.enderecoService.atualizarEndereco(endereco), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletarEndereco/{enderecoId}")
    public ResponseEntity<Endereco> deletarEndereco(@PathVariable(value = "enderecoId") Integer enderecoId) throws NotFoundException {
        this.enderecoService.deletarEndereco(enderecoId);
        return new ResponseEntity<Endereco>(HttpStatus.OK);
    }

    @GetMapping(value = "/buscarEnderecoId/{enderecoId}")
    public ResponseEntity<Endereco> buscarEnderecoId(@PathVariable(value = "enderecoId") Integer enderecoId) throws NotFoundException {
        return new ResponseEntity<Endereco>(this.enderecoService.buscarEnderecoId(enderecoId), HttpStatus.OK);
    }

    @GetMapping(value = "/listarEnderecos")
    public ResponseEntity<Page<Endereco>> listarEnderecos(@RequestParam(value = "offset", defaultValue = "0") int page,
                                                          @RequestParam(value = "limit", defaultValue = "10") int size) {
        return new ResponseEntity<Page<Endereco>>(this.enderecoService.listarEnderecos(page, size), HttpStatus.OK);
    }

}
