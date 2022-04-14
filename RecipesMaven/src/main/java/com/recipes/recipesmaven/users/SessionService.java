package com.recipes.recipesmaven.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;

    public String saveSession(String email) {
        Session ses = new Session(UUID.randomUUID().toString(), email);
        sessionRepository.save(ses);
        return ses.getSessID();
    }

    public String validateUser(String sesID) {
        Iterable<Session> iter = sessionRepository.findAll();
        for (Session it : iter) {
            if (it.getSessID().equals(sesID)) {
                return it.getEmail();
            }
        }
        return null;
    }
}
