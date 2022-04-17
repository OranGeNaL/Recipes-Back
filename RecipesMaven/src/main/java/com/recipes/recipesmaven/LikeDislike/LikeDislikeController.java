package com.recipes.recipesmaven.LikeDislike;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class LikeDislikeController {
    private final LikeDislikeService likeDislikeService;

    @PostMapping("/likes")
    public void likePost(@RequestParam Long idRecipe) {
        likeDislikeService.likePost(idRecipe);
    }

    @PostMapping("/dislike")
    public void dislikePost(@RequestParam Long idRecipe) {
        likeDislikeService.dislikePost(idRecipe);
    }
}
