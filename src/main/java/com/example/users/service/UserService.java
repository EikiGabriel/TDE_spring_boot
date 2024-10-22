package com.example.users.service;

import com.example.users.CriaEAtualiza.AtualizaUsuario;
import com.example.users.CriaEAtualiza.CriaUsuario;
import com.example.users.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User create(CriaUsuario CriaUsuario);
    User update(Integer id, AtualizaUsuario AtualizaUsuario);
    void delete(Integer id);
    Optional<User> getUserById(Integer id);
    List<User> getAll();
}
