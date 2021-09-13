package com.meylium.elsch.controller;

import com.meylium.elsch.model.User;
import com.meylium.elsch.repo.elastic.UsersRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class UsersController {
    private final UsersRepo usersRepo;

    public UsersController(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

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
