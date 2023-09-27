package com.agenda.service;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenda.dto.PessoaDTO;
import com.agenda.model.Pessoa;
import com.agenda.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa criarPessoa(PessoaDTO pessoaDTO) throws ParseException {
		Pessoa novaPessoa = new Pessoa(pessoaDTO);
		return this.pessoaRepository.save(novaPessoa);
	}

	

}
