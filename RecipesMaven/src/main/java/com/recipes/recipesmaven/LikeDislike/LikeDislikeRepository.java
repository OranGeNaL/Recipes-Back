package com.recipes.recipesmaven.LikeDislike;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LikeDislikeRepository extends CrudRepository<LikeDislikeDTO, Long> {
    Optional<LikeDislikeDTO> findByIdRecipe(Long idRecipe);
}
