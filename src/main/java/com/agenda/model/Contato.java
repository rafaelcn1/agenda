package com.agenda.model;

import java.io.Serializable;

import com.agenda.dto.ContatoDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Contato implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String telefone;
	private String email;

	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;

	@Override
	public String toString() {
		return "Contato [id=" + id + ", telefone=" + telefone + ", email=" + email + "]";
	}

	public Contato(ContatoDTO contatoDTO) {
		this.telefone = contatoDTO.getTelefone();
		this.email = contatoDTO.getEmail();
		this.pessoa = contatoDTO.getPessoa();
	}

}
