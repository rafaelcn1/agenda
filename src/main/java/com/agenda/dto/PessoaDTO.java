package com.agenda.dto;

import java.util.Date;

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

}
