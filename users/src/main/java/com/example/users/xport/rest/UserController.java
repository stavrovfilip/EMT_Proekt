package com.example.users.xport.rest;

import com.example.users.domain.models.User;
import com.example.users.domain.models.UserId;
import com.example.users.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class UserController {

    /*
    Rest Controller za korisnici, kade sme zavisni od userService.
    Imame dva GET mapinzi, edniot za pobaruvanje na eden korisnik spored id, a drugiot za listanje na site korisnici.
     */

    private final UserService userService;

    @GetMapping
    public List<User> findAll() {
        return this.userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable UserId id) {
        User user = this.userService.findById(id);

        if(user!=null)
            return ResponseEntity.ok().body(user);
        return ResponseEntity.notFound().build();
    }
}
