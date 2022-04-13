package com.recipes.recipesmaven.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public Map<String, String> registerUser(@Valid @RequestBody User user) {
        return Map.of("id", userService.registerUser(user));
    }

    @GetMapping
    public Map<String, String> logInUser(@Valid @RequestBody String session) {
        return Map.of("email", userService.loginUserBySession(session));
    }

    @ExceptionHandler(DuplicateUsernameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void duplicateUsernameExceptionHandler() {
    }
}
