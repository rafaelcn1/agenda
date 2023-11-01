package com.agenda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agenda.dto.ContatoDTO;
import com.agenda.model.Contato;
import com.agenda.model.Pessoa;
import com.agenda.service.ContatoService;
import com.agenda.service.PessoaService;

@RestController
@RequestMapping("/contatos")
@CrossOrigin(origins = "http://localhost:4200") //Evitar o erro de blocked by CORS policy
public class ContatoController {

	@Autowired
	private ContatoService contatoService;

	@Autowired
	private PessoaService pessoaService;

	@PostMapping
	public ResponseEntity<Contato> cadastrarContato(@RequestBody ContatoDTO contatoDTO) throws Exception {
		Contato contato = new Contato(contatoDTO);
		Pessoa pessoa = this.pessoaService.buscarPessoaPorId(contato.getPessoa().getId());
		contato.setPessoa(pessoa);
		Contato cadastrarContato = this.contatoService.cadastrarContato(contato);
		return new ResponseEntity<Contato>(cadastrarContato, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Contato>> listarTodosContatos() {
		List<Contato> contatos = this.contatoService.listarTodosContatos();
		return new ResponseEntity<List<Contato>>(contatos, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Contato> buscarContatoId(@PathVariable Integer id) {
		Contato contato = this.contatoService.buscarContatoId(id);
		return new ResponseEntity<Contato>(contato, HttpStatus.OK);
	}

	@GetMapping("/pessoa/{id}")
	public ResponseEntity<List<Contato>> listarContatosPessoa(@PathVariable Integer id) throws Exception {
		Pessoa pessoa = this.pessoaService.buscarPessoaPorId(id);
		List<Contato> contatos = this.contatoService.listarContatosPessoa(pessoa);
		return new ResponseEntity<List<Contato>>(contatos, HttpStatus.OK);
	}

	@PutMapping()
	public ResponseEntity<Contato> editarContato(@RequestBody ContatoDTO contatoDTO) throws Exception {
		Contato contatoEditado = new Contato(contatoDTO);
		contatoEditado.setId(contatoDTO.getId());
		Contato contato = this.contatoService.editarContato(contatoEditado);
		return new ResponseEntity<Contato>(contato, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public void deletarContato(@PathVariable Integer id) {
		this.contatoService.deletarContato(id);
	}

}
