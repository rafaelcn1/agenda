package com.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agenda.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
