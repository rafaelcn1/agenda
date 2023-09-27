package com.agenda.model;

import java.io.Serializable;
import java.util.Date;

import com.agenda.dto.PessoaDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pessoa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Date dataNascimento;

	@Column(unique = true, nullable = false)
	private String usuario;

	@Column(unique = true, nullable = false)
	private String senha;

	public Pessoa(PessoaDTO pessoaDTO) {
		this.id = pessoaDTO.getId();
		this.nome = pessoaDTO.getNome();
		this.dataNascimento = pessoaDTO.getDataNascimento();
		this.usuario = pessoaDTO.getUsuario();
		this.senha = pessoaDTO.getSenha();
	}

}
