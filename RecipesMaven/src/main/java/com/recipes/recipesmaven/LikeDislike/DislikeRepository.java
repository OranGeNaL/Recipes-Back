package com.recipes.recipesmaven.LikeDislike;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DislikeRepository extends CrudRepository<DisLikeDTO, Long> {
    Optional<DisLikeDTO> findByIdRecipe(Long idRecipe);
}
