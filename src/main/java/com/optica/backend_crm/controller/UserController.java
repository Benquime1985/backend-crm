package com.optica.backend_crm.controller;


import com.optica.backend_crm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.optica.backend_crm.models.User;
import org.springframework.security.core.Authentication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public Map<String, String> getMyInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> info = new HashMap<>();
        if (auth.getPrincipal() instanceof User user) {
            info.put("name", user.getNameEmployee());
            info.put("role", user.getAuthorities().toString());
        } else {
            // Fallback por si el usuario es anónimo o hubo un error
            info.put("email", auth.getName());
            info.put("role", auth.getAuthorities().toString());
        }

        return info;
    }

    @GetMapping("/all")
    public List<User> listUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User userDetails) {
        return userService.updateUser(id, userDetails);
    }
}
