package com.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.agenda.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
	
	//Optional<Pessoa> findByUsuario(String usuario);
	
	UserDetails findByUsuario(String usuario);

}
