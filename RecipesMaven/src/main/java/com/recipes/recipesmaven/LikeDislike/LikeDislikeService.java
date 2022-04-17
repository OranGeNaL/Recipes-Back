package com.recipes.recipesmaven.LikeDislike;

import com.recipes.recipesmaven.recipe.RecipeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeDislikeService {

    private final LikeDislikeRepository likeDislikeRepository;

    public void likePost(Long idRecipe) {
        LikeDislikeDTO temp = likeDislikeRepository.findByIdRecipe(idRecipe).orElseThrow(RecipeNotFoundException::new);
        temp.setLikes(temp.getLikes() + 1);
        likeDislikeRepository.save(temp);
    }

    public void dislikePost(Long idRecipe) {
        LikeDislikeDTO temp = likeDislikeRepository.findByIdRecipe(idRecipe).orElseThrow(RecipeNotFoundException::new);
        temp.setDislikes(temp.getDislikes() + 1);
        likeDislikeRepository.save(temp);
    }

    public void save(Long idRecipe) {
        LikeDislikeDTO save = new LikeDislikeDTO();
        save.setIdRecipe(idRecipe);
        save.setLikes(0L);
        save.setDislikes(0L);
        likeDislikeRepository.save(save);
    }
}
