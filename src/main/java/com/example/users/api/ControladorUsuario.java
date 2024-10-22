package com.example.users.api;

import com.example.users.CriaEAtualiza.AtualizaUsuario;
import com.example.users.CriaEAtualiza.CriaUsuario;
import com.example.users.model.User;
import com.example.users.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class ControladorUsuario {
    private final UserService userService;

    public ControladorUsuario(UserService userService) {
        this.userService = userService;
    }

    // Get All Users
    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Get User by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        Optional<User> userOpt = userService.getUserById(id);
        return userOpt.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseThrow(() -> new com.example.users.exception.UsuarioNaoEncontrado(id));
    }

    // Create User
    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody CriaUsuario CriaUsuario) {
        User createdUser = userService.create(CriaUsuario);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // Update User
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Integer id, @Valid @RequestBody AtualizaUsuario AtualizaUsuario) {
        User updatedUser = userService.update(id, AtualizaUsuario);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
