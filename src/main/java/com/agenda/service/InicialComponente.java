package com.agenda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.agenda.dto.PessoaDTO;
import com.agenda.model.Pessoa;
import com.agenda.model.UsuarioRole;

import jakarta.annotation.PostConstruct;

@Component
public class InicialComponente {
	
	@Autowired
	private PessoaService pessoaService;
	
	@PostConstruct
	public void init() {
		PessoaDTO pessoaDTO = new PessoaDTO();
		pessoaDTO.setUsuario("rafaelcn1");
		pessoaDTO.setSenha("123");
		String senhaCriptografada = new BCryptPasswordEncoder().encode(pessoaDTO.getSenha());
		pessoaDTO.setSenha(senhaCriptografada);
		pessoaDTO.setRole(UsuarioRole.ADMIN);
		pessoaDTO.setNome("Rafael Cunha");
		Pessoa criarPessoa = this.pessoaService.criarPessoa(pessoaDTO);
		System.out.println(criarPessoa);
	}

}
