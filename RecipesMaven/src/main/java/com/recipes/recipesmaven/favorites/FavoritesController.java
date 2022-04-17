package com.recipes.recipesmaven.favorites;

import com.recipes.recipesmaven.recipe.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Iterator;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
@Validated
public class FavoritesController {

    private final FavoritesService favoritesService;

    @PostMapping
    public void postFavorite(@RequestBody @Valid Favorite favorite) {
        favoritesService.saveFavorite(favorite);
    }
    @GetMapping
    public ResponseEntity<Iterator<Recipe>> getAllRecipesByAuthor(@RequestParam(name = "sesID") String sesID) {
        return ResponseEntity.ok(favoritesService.getAllByAuthor(sesID));
    }
}
