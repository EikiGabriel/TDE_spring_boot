package com.example.users.service;

import com.example.users.CriaEAtualiza.AtualizaUsuario;
import com.example.users.CriaEAtualiza.CriaUsuario;
import com.example.users.exception.UsuarioNaoEncontrado;
import com.example.users.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final List<User> userList;

    public UserServiceImpl() {
        userList = new ArrayList<>();
        User user1 = new User(1, 21, "Maria", "maria@gmail.com");
        User user2 = new User(2, 22, "João", "joao@gmail.com");
        User user3 = new User(3, 23, "José", "jose@gmail.com");
        User user4 = new User(4, 24, "Luiz", "luiz@gmail.com");
        User user5 = new User(5, 25, "Joice", "joice@gmail.com");
        userList.addAll(List.of(user1, user2, user3, user4, user5));
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return userList.stream().filter(user -> user.getId()==id).findFirst();
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(userList);
    }

    @Override
    public User create(CriaUsuario CriaUsuario) {
        User user = new User(CriaUsuario.id(), CriaUsuario.age(), CriaUsuario.name(), CriaUsuario.email());
        userList.add(user);
        return user;
    }

    @Override
    public User update(Integer id, AtualizaUsuario AtualizaUsuario) {
        User existingUser = getUserById(id).orElseThrow(() -> new UsuarioNaoEncontrado(id));
        existingUser.setName(AtualizaUsuario.name());
        existingUser.setEmail(AtualizaUsuario.email());
        existingUser.setAge(AtualizaUsuario.age());
        return existingUser;
    }

    @Override
    public void delete(Integer id) {
        User user = getUserById(id).orElseThrow(() -> new UsuarioNaoEncontrado(id));
        userList.remove(user);
    }
}
