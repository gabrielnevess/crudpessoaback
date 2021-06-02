package com.example.crudpessoa.service;

import com.example.crudpessoa.exception.NotFoundException;
import com.example.crudpessoa.model.Pessoa;
import com.example.crudpessoa.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa salvarPessoa(Pessoa pessoa) {
        return this.pessoaRepository.save(pessoa);
    }

    public Pessoa atualizarPessoa(Pessoa p) throws NotFoundException {

        Pessoa pessoa = this.pessoaRepository.findById(p.getPessoaId())
                                             .orElseThrow(() -> new NotFoundException(String.format("Pessoa não encontrada com o ID :: %d", p.getPessoaId())));

        pessoa.setNome(p.getNome());
        pessoa.setEmail(p.getEmail());
        pessoa.setDataNascimento(p.getDataNascimento());
        pessoa.setSexo(p.getSexo());
        pessoa.setCelular(p.getCelular());
        pessoa.setTelefone(p.getTelefone());

        return pessoa;
    }

    public void deletarPessoa(Integer pessoaId) throws NotFoundException {
        Pessoa pessoa = this.pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new NotFoundException(String.format("Pessoa não encontrada com o ID :: %d", pessoaId)));
        this.pessoaRepository.delete(pessoa);
    }

    public Pessoa buscarPeloId(Integer pessoaId) throws NotFoundException {
        return this.pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new NotFoundException(String.format("Pessoa não encontrada com o ID :: %d", pessoaId)));
    }

    public Page<Pessoa> listarPessoas(int page, int size) {
        return this.pessoaRepository.findAll(PageRequest.of(page, size));
    }

}
