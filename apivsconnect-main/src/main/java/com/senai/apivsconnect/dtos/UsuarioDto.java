package com.senai.apivsconnect.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDto(
        @NotBlank String nome,
        @NotBlank @Email(message = "o email deve estar em um formato valido") String email,
        @NotBlank String senha,
        String endereco,
        String cep,
        String tipo_usuario,
        String url_img


) {
}
