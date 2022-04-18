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

    @PostMapping("/like")
    public void setLike(@RequestParam Long idRecipe, @RequestParam String sesID) {
        likeDislikeService.likePost(idRecipe, sesID);
    }

    @PostMapping("/dislike")
    public void setDislike(@RequestParam Long idRecipe, @RequestParam String sesID) {
        likeDislikeService.dislikePost(idRecipe, sesID);
    }
}
