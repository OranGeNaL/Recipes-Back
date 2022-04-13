package com.recipes.recipesmaven.recipe;

import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    Iterable<Recipe> findAllByNameContainsIgnoreCaseOrderByDateDesc(String name);

    Iterable<Recipe> findAllByCategoryIgnoreCaseOrderByDateDesc(String category);
}
