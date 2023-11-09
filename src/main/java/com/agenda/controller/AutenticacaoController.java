package com.agenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agenda.dto.AutenticacaoDTO;
import com.agenda.dto.RegisterDTO;
import com.agenda.model.Usuario;
import com.agenda.repository.PessoaRepository;
import com.agenda.repository.UsuarioRepository;

@Controller // definem que esta classe é um controlador
@RequestMapping("/auth") // os métodos mapeados dentro dela serão acessíveis a partir do caminho base
							// "/auth"
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authenticationManager; // responsável por lidar com a autenticação de usuários,
															// pegarar da classe SecurityConfig

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AutenticacaoDTO dado) {
		UsernamePasswordAuthenticationToken usernameSenha = new UsernamePasswordAuthenticationToken(dado.userName(),
				dado.senha()); // cria um objeto autenticação em que o nome de usuário e a senha são
								// encapsulados em um objeto para serem verificados.
		this.authenticationManager.authenticate(usernameSenha); // verifica as credenciais (nome de usuário e senha) e,
																// se forem válidas,
		return ResponseEntity.ok().build(); // Se a autenticação for bem-sucedida, o método responde com um status HTTP
											// 200 (OK)

	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterDTO data) {
		if (this.pessoaRepository.findByUsuario(data.userName()) != null) {
			return ResponseEntity.badRequest().build();
		} else {
			String senhaCriptografada = new BCryptPasswordEncoder().encode(data.senha()); //Criptografar a senha
			Usuario novoUsuario = new Usuario(data.userName(), data.senha(), data.role());
			this.usuarioRepository.save(novoUsuario);
			return ResponseEntity.ok().build();
		}

	}
}
