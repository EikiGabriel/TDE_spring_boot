package com.example.users.exception;

public class UsuarioNaoEncontrado extends RuntimeException {
    public UsuarioNaoEncontrado(Integer id) {
        super("Usuário não encontrado.");
    }
}
