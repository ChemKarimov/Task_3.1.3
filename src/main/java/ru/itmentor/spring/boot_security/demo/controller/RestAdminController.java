package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.RoleService;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RestAdminController {
    private final UserService userService;
    private final RoleService roleService;

    public RestAdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/allUsers")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("/allRoles")
    public ResponseEntity<Iterable<Role>> getAllRoles() {
        return ResponseEntity.ok().body(roleService.findAll());
    }

    @GetMapping("/userById/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @GetMapping("/userByName/{name}")
    public ResponseEntity<Optional<User>> getUserByName(@PathVariable String name) {
        return ResponseEntity.ok().body(userService.findByName(name));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>("Пользователь с id = " + id + " удален.", HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            userService.save(user);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(user);
        }
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        try {
            userService.updateUser(user);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(user);
        }
        return ResponseEntity.ok().body(user);
    }

}