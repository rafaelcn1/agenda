package com.agenda.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.agenda.model.Pessoa;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenService {

	// Valor que irá pegar do arquivo properties
	@Value("${api.security.token.secret}")
	private String secret;

	// Metodo para gerar o token, recebendo uma pessoa
	public String generateToken(Pessoa pessoa) {
		
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret); 
			//Algorito que será usado, no caso o HMAC256, secret tem que ser unico, por isso gaurdado no properties
			
			//GEração do token
			String token = JWT.create()
					.withIssuer("auth-api") //Quem criou, usamos o nome "auth-api", mas poder ser qualquer nome
					.withSubject(pessoa.getUsuario()) //Quem irá receber o token, no caso o usuário
					.withExpiresAt(generateExpiration()) //Tempo de expração
					.sign(algorithm); //assinar o token com o algoritimo que escolhemos
			
			return token;
			
		} catch (JWTCreationException e) {
			throw new RuntimeException("Erro ao gerar o token!", e);
		}
	}
	
	//Metodo para validação do token
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			
			return JWT.require(algorithm)
					.withIssuer("auth-api")//Quem criou, usamos o nome "auth-api", mas poder ser qualquer nome
					.build() //Construir, montando o que tem
					.verify(token) //VErifica o token
					.getSubject(); //PEgar o subject criado, no caso da linha 32
		} catch (JWTVerificationException e) {
			return " "; //dizer que nao tem nenhuma usuário
		}
	}

	//Metodo para gerar a expiraação do token
	private Instant generateExpiration() {
		return LocalDateTime //Trabalhar com data e hora
				.now() //Hora agora
				.plusHours(2) // Adicionar mais 2h
				.toInstant(ZoneOffset.of("-03:00")); //Fuso horario Brasil
	}

}
