package com.example.crudpessoa.repository;

import com.example.crudpessoa.model.Endereco;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EnderecoRepository extends PagingAndSortingRepository<Endereco, Integer> {
}
