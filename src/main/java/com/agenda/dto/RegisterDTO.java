package com.agenda.dto;

import com.agenda.model.UsuarioRole;

public record RegisterDTO(String userName, String senha, UsuarioRole role) {

}
