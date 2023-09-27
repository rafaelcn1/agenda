package com.agenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenda.dto.PessoaDTO;
import com.agenda.model.Pessoa;
import com.agenda.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa criarPessoa(PessoaDTO pessoaDTO) {
		Pessoa novaPessoa = new Pessoa(pessoaDTO);
		return this.pessoaRepository.save(novaPessoa);
	}

	public List<Pessoa> listarTodasPessoas() {
		return this.pessoaRepository.findAll();
	}

	public Pessoa buscarPessoaPorId(Integer id) throws Exception {
		return this.pessoaRepository.findById(id).get();

	}

	public Pessoa editarPessoa(PessoaDTO pessoaDTO) throws Exception {
		Pessoa pessoaEditada = this.buscarPessoaPorId(pessoaDTO.getId());
		pessoaEditada = new Pessoa(pessoaDTO);
		return this.pessoaRepository.save(pessoaEditada);
	}

}
