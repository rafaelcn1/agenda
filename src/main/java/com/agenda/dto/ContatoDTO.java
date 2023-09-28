package com.agenda.dto;

import com.agenda.model.Pessoa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContatoDTO {

	private Integer id;
	private String telefone;
	private String email;

	private Pessoa pessoa;

}
