package com.agenda.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.agenda.model.Contato;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {

	private Integer id;
	private String nome;
	private Date dataNascimento;
	private String usuario;
	private String senha;

	private List<Contato> contatos = new ArrayList<Contato>();

}
