package com.agenda.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
	public ResponseEntity<Pessoa> criarPessoa(@RequestBody PessoaDTO pessoaDTO) throws ParseException {
		Pessoa novoUsuario = this.pessoaService.criarPessoa(pessoaDTO);
		return new ResponseEntity<Pessoa>(novoUsuario, HttpStatus.CREATED);

	}

}
