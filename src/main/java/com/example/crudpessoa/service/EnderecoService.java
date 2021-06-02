package com.example.crudpessoa.service;

import com.example.crudpessoa.exception.NotFoundException;
import com.example.crudpessoa.model.Endereco;
import com.example.crudpessoa.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    private final static String ENDERECO_NAO_ENCONTRADO = "Endereco não encontrado com o ID :: %d";

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco salvarEndereco(Endereco endereco) {
        return this.enderecoRepository.save(endereco);
    }

    public Endereco atualizarEndereco(Endereco p) throws NotFoundException {

        Endereco endereco = this.enderecoRepository.findById(p.getEnderecoId())
                .orElseThrow(() -> new NotFoundException(String.format(ENDERECO_NAO_ENCONTRADO, p.getEnderecoId())));

        endereco.setPessoa(endereco.getPessoa());
        endereco.setEstado(endereco.getEstado());
        endereco.setLogradouro(endereco.getLogradouro());
        endereco.setNumero(endereco.getNumero());
        endereco.setComplemento(endereco.getComplemento());
        endereco.setBairro(endereco.getBairro());
        endereco.setCep(endereco.getCep());
        endereco.setCidade(endereco.getCidade());

        return endereco;
    }

    public void deletarEndereco(Integer enderecoId) throws NotFoundException {
        Endereco endereco = this.enderecoRepository.findById(enderecoId)
                .orElseThrow(() -> new NotFoundException(String.format(ENDERECO_NAO_ENCONTRADO, enderecoId)));
        this.enderecoRepository.delete(endereco);
    }

    public Endereco buscarEnderecoId(Integer enderecoId) throws NotFoundException {
        return this.enderecoRepository.findById(enderecoId)
                .orElseThrow(() -> new NotFoundException(String.format(ENDERECO_NAO_ENCONTRADO, enderecoId)));
    }

    public Page<Endereco> listarEnderecos(int page, int size) {
        return this.enderecoRepository.findAll(PageRequest.of(page, size));
    }

}
