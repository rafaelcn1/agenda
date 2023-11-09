package com.agenda.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.agenda.dto.PessoaDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pessoa implements Serializable, UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;

	@Column(unique = true, nullable = false)
	private String usuario;

	@Column(nullable = false)
	private String senha;

	@JsonIgnore
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private List<Contato> contatos = new ArrayList<Contato>();

	private UsuarioRole role;

	public Pessoa(PessoaDTO pessoaDTO) {
		this.id = pessoaDTO.getId();
		this.nome = pessoaDTO.getNome();
		this.dataNascimento = pessoaDTO.getDataNascimento();
		this.usuario = pessoaDTO.getUsuario();
		this.senha = pessoaDTO.getSenha();
		this.contatos = pessoaDTO.getContatos();
		this.role = pessoaDTO.getRole();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { // Metodo para saber qual a função do usuário tem
		if (this.role == UsuarioRole.ADMIN) {
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER")); 
		} else {
			return List.of(new SimpleGrantedAuthority("ROLE_USER")); // se não, apenas user
		}
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return usuario; // Representa o usuario
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
