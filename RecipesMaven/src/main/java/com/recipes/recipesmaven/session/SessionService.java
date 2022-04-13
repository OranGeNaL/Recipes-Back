package com.recipes.recipesmaven.session;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;

    public String saveSession(SessionId sessionId) {
        return sessionRepository.save(sessionId).getSession();
    }

    public String getEmailBySession(String session) {
        if (sessionRepository.findBySession(session) == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return sessionRepository.findBySession(session).getNameUser();
    }
}
