package com.example.crudpessoa.service;

import com.example.crudpessoa.exception.BadRequestException;
import com.example.crudpessoa.exception.NotFoundException;
import com.example.crudpessoa.model.Estado;
import com.example.crudpessoa.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

    private final static String ESTADO_NAO_ENCONTRADO = "Estado não encontrado com o ID :: %d";
    private final static String ESTADO_EM_USO = "Você não pode deletar o estado de %s, pois está em uso.";

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado salvarEstado(Estado estado) {
        return this.estadoRepository.save(estado);
    }

    public Estado atualizarEstado(Estado p) throws NotFoundException {

        Estado estado = this.estadoRepository.findById(p.getEstadoId())
                .orElseThrow(() -> new NotFoundException(String.format(ESTADO_NAO_ENCONTRADO, p.getEstadoId())));
        estado.setNome(p.getNome());

        return estado;
    }

    public void deletarEstado(Integer estadoId) throws NotFoundException, BadRequestException {
        Estado estado = null;
        try {
           estado = this.estadoRepository.findById(estadoId)
                    .orElseThrow(() -> new NotFoundException(String.format(ESTADO_NAO_ENCONTRADO, estadoId)));
            this.estadoRepository.delete(estado);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new BadRequestException(String.format(ESTADO_EM_USO, estado.getNome()));
        }
    }

    public Estado buscarEstadoId(Integer estadoId) throws NotFoundException {
        return this.estadoRepository.findById(estadoId)
                .orElseThrow(() -> new NotFoundException(String.format(ESTADO_NAO_ENCONTRADO, estadoId)));
    }

    public Page<Estado> listarEstados(int page, int size) {
        return this.estadoRepository.findAll(PageRequest.of(page, size));
    }

}
