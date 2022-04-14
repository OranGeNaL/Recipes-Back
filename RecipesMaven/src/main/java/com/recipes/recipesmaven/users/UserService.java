package com.recipes.recipesmaven.users;

import com.recipes.recipesmaven.recipe.RecipeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SessionService sessionService;


    public String registerUser(User user) {
        if (userRepository.existsById(user.getEmail())) {
            throw new DuplicateUsernameException();
        }
        userRepository.save(user);
        return sessionService.saveSession(user.getEmail());
    }

    public String validateUser(String sesID) {
        return sessionService.validateUser(sesID);
    }

    public String login(String email, String password) {
        User user = userRepository.findById(email).orElseThrow(RecipeNotFoundException::new);
        if (user.getPassword().equals(password)) {
            return sessionService.saveSession(email);
        }
        throw new RecipeNotFoundException();
    }
}