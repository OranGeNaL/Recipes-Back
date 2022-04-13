package com.recipes.recipesmaven.session;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends CrudRepository<SessionId, Long> {
    SessionId findBySession(String session);
}
