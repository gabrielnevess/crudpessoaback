package com.example.crudpessoa.service;

import com.example.crudpessoa.exception.NotFoundException;
import com.example.crudpessoa.model.Estado;
import com.example.crudpessoa.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado salvarEstado(Estado estado) {
        return this.estadoRepository.save(estado);
    }

    public Estado atualizarEstado(Estado p) throws NotFoundException {

        Estado estado = this.estadoRepository.findById(p.getEstadoId())
                .orElseThrow(() -> new NotFoundException(String.format("Estado não encontrado com o ID :: %d", p.getEstadoId())));
        estado.setNome(p.getNome());

        return estado;
    }

    public void deletarEstado(Integer estadoId) throws NotFoundException {
        Estado estado = this.estadoRepository.findById(estadoId)
                .orElseThrow(() -> new NotFoundException(String.format("Estado não encontrado com o ID :: %d", estadoId)));
        this.estadoRepository.delete(estado);
    }

    public Estado buscarPeloId(Integer estadoId) throws NotFoundException {
        return this.estadoRepository.findById(estadoId)
                .orElseThrow(() -> new NotFoundException(String.format("Estado não encontrado com o ID :: %d", estadoId)));
    }

    public Page<Estado> listarEstados(int page, int size) {
        return this.estadoRepository.findAll(PageRequest.of(page, size));
    }

}
