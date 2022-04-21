package com.recipes.recipesmaven.recipe;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    Iterable<Recipe> findAllByNameContainsIgnoreCaseOrderByDateDesc(String name);

    Iterable<Recipe> findAllByCategoryIgnoreCaseOrderByDateDesc(String category);

    Iterable<Recipe> findAllByOrderByDateAsc();

    Iterable<Recipe> findAllByOrderByViewsAsc();

    Iterable<Recipe> findAllByAuthorIgnoreCase(String author);

    Iterable<Recipe> findAllByOrderByLikesDesc();

}
