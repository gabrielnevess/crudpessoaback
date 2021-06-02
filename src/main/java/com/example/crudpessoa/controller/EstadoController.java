package com.example.crudpessoa.controller;

import com.example.crudpessoa.exception.BadRequestException;
import com.example.crudpessoa.exception.NotFoundException;
import com.example.crudpessoa.model.Estado;
import com.example.crudpessoa.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @PostMapping(value = "/salvarEstado")
    public ResponseEntity<Estado> salvarEstado(@Valid @RequestBody Estado estado) {
        return new ResponseEntity<Estado>(this.estadoService.salvarEstado(estado), HttpStatus.CREATED);
    }

    @PutMapping(value = "/atualizarEstado")
    public ResponseEntity<Estado> atualizarEstado(@Valid @RequestBody Estado estado) throws NotFoundException {
        return new ResponseEntity<Estado>(this.estadoService.atualizarEstado(estado), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletarEstado/{estadoId}")
    public ResponseEntity<Estado> deletarEstado(@PathVariable(value = "estadoId") Integer estadoId) throws NotFoundException, BadRequestException {
        this.estadoService.deletarEstado(estadoId);
        return new ResponseEntity<Estado>(HttpStatus.OK);
    }

    @GetMapping(value = "/buscarEstadoId/{estadoId}")
    public ResponseEntity<Estado> buscarEstadoId(@PathVariable(value = "estadoId") Integer estadoId) throws NotFoundException {
        return new ResponseEntity<Estado>(this.estadoService.buscarEstadoId(estadoId), HttpStatus.OK);
    }

    @GetMapping(value = "/listarEstados")
    public ResponseEntity<Page<Estado>> listarEstados(@RequestParam(value = "offset", defaultValue = "0") int page,
                                                      @RequestParam(value = "limit", defaultValue = "10") int size) {
        return new ResponseEntity<Page<Estado>>(this.estadoService.listarEstados(page, size), HttpStatus.OK);
    }

}
