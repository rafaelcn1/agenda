package com.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.agenda.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	UserDetails findByLogin(String login);
}
