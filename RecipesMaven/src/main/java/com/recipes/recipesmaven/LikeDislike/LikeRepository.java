package com.recipes.recipesmaven.LikeDislike;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LikeRepository extends CrudRepository<LikeDTO, Long> {
    Optional<LikeDTO> findByIdRecipe(Long idRecipe);
}
