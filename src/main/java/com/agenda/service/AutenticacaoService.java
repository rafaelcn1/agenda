package com.agenda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.agenda.repository.PessoaRepository;

/**
 * para que o Spring me identifique que essa classe representa um serviço da
 * nossa aplicação a gente vai ter que colocar a notação de service aqui em cima
 * da classe e para que o spinning Security identifique que essa classe é o
 * nosso autorization service que ele vai ter que chamar de forma automática e
 * implementar a classe UserDetailsService
 */
@Service
public class AutenticacaoService implements UserDetailsService{
	
	@Autowired
	PessoaRepository pessoaRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return pessoaRepository.findByUsuario(username);
	}
	
	

}
