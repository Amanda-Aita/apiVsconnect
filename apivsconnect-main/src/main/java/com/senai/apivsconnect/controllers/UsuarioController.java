package com.senai.apivsconnect.controllers;

import com.senai.apivsconnect.dtos.UsuarioDto;
import com.senai.apivsconnect.models.UsuarioModel;
import com.senai.apivsconnect.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.Delimiter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/usuarios", produces = {"application/json"})
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listarUsuarios() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findAll());
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Object> exibirUsuario(@PathVariable(value = "idUsuario") UUID id) {
        Optional<UsuarioModel> usuarioBuscado = usuarioRepository.findById(id);

        if (usuarioBuscado.isEmpty()) {
            // Retornar usuario não encontrado
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuarioBuscado.get());
    }


    @PostMapping
    public ResponseEntity<Object> cadastrarUsuario(@RequestBody @Valid UsuarioDto usuarioDto) {
        if (usuarioRepository.findByEmail(usuarioDto.email()) != null) {
            // Nao pode cadastrar
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Esse email já está cadastrado!");

        }
        UsuarioModel usuario = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDto, usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<Object> editarUsuario(@PathVariable(value = "idUsuario") UUID id, @RequestBody @Valid UsuarioDto usuarioDto) {
    Optional<UsuarioModel> usuarioBuscado = usuarioRepository.findById(id);

    if (usuarioBuscado.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuario nao encontrado");
    }

    UsuarioModel usuario = usuarioBuscado.get();

    BeanUtils.copyProperties(usuarioDto, usuario);

    return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario)) ;
    }

}

    @DeleteMapping ("\{idusuario}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "idUsuario") UUID id) {
    Optional<UsuarioModel> usuarioBuscado = usuarioRepository.findById(id);

    if (usuarioBuscado.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND.body("usuario nao encontrado");
    }

    usuarioRepository.delete(usuarioBuscado.get());

     return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuario nao encontrado");

    }

    }

