package com.agenda.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration // indicar para Spring que essa é uma classe te confuração
@EnableWebSecurity // habilita aqui a configuração do webscurity e configurar dentro dessa classe
public class SecurityConfig {

	// Array contendo os caminhos públicos que não exigem autenticação.
	private static final String[] PUBLIC_MATCHERS = { "/h2/**" };

	@Autowired
	SecurityFilter securityFilter;

	/**
	 * Este método configura uma cadeia de filtros de segurança para uma aplicação
	 * web. Uma cadeia de filtros de segurança permite definir as configurações de
	 * segurança para proteger os recursos da aplicação.
	 */

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.csrf(csrf -> csrf
				//.ignoringRequestMatchers(new MvcRequestMatcher(new HandlerMappingIntrospector(), "/h2/**"))
				.disable())
				// desativa a proteção contra ataques CSRF (Cross-Site Request Forgery) para a
				// aplicação. Isso permite que as solicitações HTTP sejam enviadas sem a
				// necessidade de tokens CSRF.
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				// session.sessionCreationPolicy não deve criar ou gerenciar sessões de usuário.
				// Isso é comum em aplicações
				// RESTful, onde a autenticação é baseada em tokens, como JWT (JSON Web Tokens)
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(HttpMethod.POST, "/pessoas/login").permitAll()
						.requestMatchers(HttpMethod.POST, "/pessoas").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/pessoas").hasRole("ADMIN")
						.anyRequest().authenticated())
				// Quem não for admim pode acessar outras pagina, exceto "/pessoas"
				// .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
				// Adicionar um filtro antes da validação do authorizeHttpRequests acima
				.build();

	}
	


	// Metodo para ser usado na classe AutenticacaoController
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	// MEtodo para criptografar as senhas
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
