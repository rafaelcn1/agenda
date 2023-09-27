package com.agenda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agenda.dto.PessoaDTO;
import com.agenda.model.Pessoa;
import com.agenda.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@PostMapping
	public ResponseEntity<Pessoa> criarPessoa(@RequestBody PessoaDTO pessoaDTO) {
		Pessoa novaPessoa = this.pessoaService.criarPessoa(pessoaDTO);
		return new ResponseEntity<Pessoa>(novaPessoa, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Pessoa>> listarTodasPessoas() {
		List<Pessoa> listarTodasPessoas = this.pessoaService.listarTodasPessoas();
		return new ResponseEntity<List<Pessoa>>(listarTodasPessoas, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> buscarPessoaPorId(@PathVariable Integer id) throws Exception {
		Pessoa pessoa = this.pessoaService.buscarPessoaPorId(id);
		return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Pessoa> editarPessoa(@RequestBody PessoaDTO pessoaDTO) throws Exception {
		Pessoa pessoaEditada = this.pessoaService.editarPessoa(pessoaDTO);
		return new ResponseEntity<Pessoa>(pessoaEditada, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public void deletarPessoa(@PathVariable Integer id) throws Exception {
		this.pessoaService.deletarPessoa(id);
	}
}
