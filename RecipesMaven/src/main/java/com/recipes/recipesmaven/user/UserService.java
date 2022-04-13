package com.recipes.recipesmaven.user;

import com.recipes.recipesmaven.session.SessionId;
import com.recipes.recipesmaven.session.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SessionService sessionService;
    private final PasswordEncoder passwordEncoder;

    public String registerUser(User user) {
        if (userRepository.existsById(user.getEmail())) {
            throw new DuplicateUsernameException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return sessionService.saveSession(new SessionId(user.getEmail()));
    }

    public void changeLogin(String oldLogin, String newLogin) {

    }

    public String loginUserBySession(String session) {
        return sessionService.getEmailBySession(session);
    }
}
