package com.agenda.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.agenda.repository.PessoaRepository;
import com.agenda.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
//OncePerRequestFilter que dizer que só será executado uma vez
public class SecurityFilter extends OncePerRequestFilter {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = this.recoveryToken(request);
		if(token != null) {
			String usuario = this.tokenService.validateToken(token);			
			//Validando o token para pegar o subject
			UserDetails userDetails = this.pessoaRepository.findByUsuario(usuario);
			
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			//VErificações da autenticação do usuário
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			//SAlvar a autenticação para o sprint security usar depois
			
		}
		filterChain.doFilter(request, response); //VAi chamar o proximo
		
	}

	private String recoveryToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization"); //Pegando header Autorization
		
		if(header != null) {
			return header.replace("Bearer ", ""); //Vou substituir o Bearer por " " para pegar apenas o token
		} else {
			return null;
		}
		
	}

}
