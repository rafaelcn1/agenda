package com.agenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenda.model.Contato;
import com.agenda.model.Pessoa;
import com.agenda.repository.ContatoRepository;

@Service
public class ContatoService {

	@Autowired
	private ContatoRepository contatoRepository;

	public Contato cadastrarContato(Contato contato) {
		return this.contatoRepository.save(contato);
	}

	public List<Contato> listarTodosContatos() {
		return this.contatoRepository.findAll();
	}

	public Contato buscarContatoId(Integer id) {
		return this.contatoRepository.findById(id).get();
	}

	public List<Contato> listarContatosPessoa(Pessoa pessoa) {
		List<Contato> contatos = this.contatoRepository.findByPessoa(pessoa);
		return contatos;
	}

	public Contato editarContato(Contato contato) {
		return this.contatoRepository.save(contato);
	}

	public void deletarContato(Integer id) {
		Contato contato = this.buscarContatoId(id);
		this.contatoRepository.delete(contato);		
	}
}
