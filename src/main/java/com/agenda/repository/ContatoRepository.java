package com.agenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agenda.model.Contato;
import com.agenda.model.Pessoa;

public interface ContatoRepository extends JpaRepository<Contato, Integer> {

	List<Contato> findByPessoa(Pessoa pessoa);

}
