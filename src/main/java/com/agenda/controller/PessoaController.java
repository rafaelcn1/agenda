package com.agenda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.agenda.service.TokenService;

@RestController
@RequestMapping("/pessoas")
@CrossOrigin(origins = "http://localhost:4200") // Evitar o erro de blocked by CORS policy
public class PessoaController {
	
	@Autowired
	private TokenService tokenService;

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private AuthenticationManager authenticationManager; // responsável por lidar com a autenticação de usuários,
															// pegarar da classe SecurityConfig

	@PostMapping
	public ResponseEntity<Pessoa> criarPessoa(@RequestBody PessoaDTO pessoaDTO) {
		if (this.pessoaService.buscarPessoaPorUsuario(pessoaDTO.getUsuario()) != null) {
			return ResponseEntity.badRequest().build();
		} else {
			String senhaCriptografada = new BCryptPasswordEncoder().encode(pessoaDTO.getSenha());
			pessoaDTO.setSenha(senhaCriptografada);
			
			Pessoa novaPessoa = this.pessoaService.criarPessoa(pessoaDTO);
			System.out.println(novaPessoa);
			return new ResponseEntity<Pessoa>(novaPessoa, HttpStatus.CREATED);
		}
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

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody PessoaDTO pessoaDTO) {
		
		UsernamePasswordAuthenticationToken usernameSenha = new UsernamePasswordAuthenticationToken(
				pessoaDTO.getUsuario(), pessoaDTO.getSenha());
		// cria um objeto autenticação em que o nome de usuário e a senha são
		// encapsulados em um objeto para serem verificados.
		Authentication authenticate = this.authenticationManager.authenticate(usernameSenha);
		// verifica as credenciais (nome de usuário e senha) e,se forem válidas,
		
		String token = this.tokenService.generateToken((Pessoa) authenticate.getPrincipal());
		//PEgando o objeto principal e fazendo um cast para dizer que é uma pessoa
		
		
		return ResponseEntity.ok(token); // Se a autenticação for bem-sucedida, o método responde com um status HTTP
											// 200 (OK) e joga o token no corpo

	}

	/*
	 * @GetMapping("/usuario") public ResponseEntity<Pessoa>
	 * buscarPessoaPorUsuario(@RequestBody PessoaDTO pessoaDTO) throws Exception {
	 * Pessoa pessoa =
	 * this.pessoaService.buscarPessoaPorUsuario(pessoaDTO.getUsuario()); return new
	 * ResponseEntity<Pessoa>(pessoa, HttpStatus.OK); }
	 */
}
