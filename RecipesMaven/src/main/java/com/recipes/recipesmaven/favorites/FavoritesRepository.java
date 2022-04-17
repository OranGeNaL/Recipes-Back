package com.recipes.recipesmaven.favorites;

import org.springframework.data.repository.CrudRepository;

public interface FavoritesRepository extends CrudRepository<Favorite, Long> {
    Iterable<Favorite> findAllBySesAuthorIgnoreCaseOrderById(String sesAuthor);
}
