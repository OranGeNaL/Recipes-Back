package com.recipes.recipesmaven.favorites;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritesRepository extends CrudRepository<Favorite, Long> {
    Iterable<Favorite> findAllBySesAuthorIgnoreCaseOrderById(String sesAuthor);
}
