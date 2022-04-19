package com.recipes.recipesmaven.users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;

    @PostMapping
    public Map<String,String> registerUser(@Valid @RequestBody User user) {
        return Map.of("sesID", userService.registerUser(user));
    }

    @GetMapping
    public ResponseEntity<Map<String,String>> validate(@RequestParam(name = "sesID") String sesID) {
        if (userService.validateUser(sesID) == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(Map.of("email",userService.validateUser(sesID)));
        }
    }

    @GetMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
        String sesID = userService.login(email,password);
        if (sesID == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(Map.of("sesID",sesID));
        }
    }
}