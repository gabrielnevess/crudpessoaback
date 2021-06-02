package com.example.crudpessoa.repository;

import com.example.crudpessoa.model.Pessoa;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PessoaRepository extends PagingAndSortingRepository<Pessoa, Integer> {
    public Optional<Pessoa> findByEmail(String email);
}
