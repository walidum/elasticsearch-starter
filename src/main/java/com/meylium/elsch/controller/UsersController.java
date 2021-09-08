package com.meylium.elsch.controller;

import com.meylium.elsch.model.User;
import com.meylium.elsch.repo.elastic.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UsersController {
    @Autowired
    private UsersRepo usersRepo;

    @GetMapping(path = "all")
    public ResponseEntity<Iterable<User>> allUsers() {
        var users = this.usersRepo.findAll();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping(path = "all")
    public ResponseEntity deleteAll() {
        this.usersRepo.deleteAll();
        return ResponseEntity.ok("all users deleted !");
    }
}
