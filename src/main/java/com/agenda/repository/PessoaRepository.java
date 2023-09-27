package com.agenda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agenda.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
	
	Optional<Pessoa> findByUsuario(String usuario);

}
