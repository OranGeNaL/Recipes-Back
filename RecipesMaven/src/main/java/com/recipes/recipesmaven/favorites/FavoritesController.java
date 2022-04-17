package com.recipes.recipesmaven.favorites;

import com.recipes.recipesmaven.FileUploadUtil;
import com.recipes.recipesmaven.recipe.Recipe;
import com.recipes.recipesmaven.recipe.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
@Validated
public class FavoritesController {

    private final FavoritesService favoritesService;

    @PostMapping
    public Map<String, Long> postRecipe(String sesID, Long idRecipe) {
        return Map.of("id", favoritesService.saveFavorite(sesID, idRecipe));
    }
    @GetMapping
    public ResponseEntity<Iterable<Favorite>> getAllRecipesByAuthor(@RequestParam(name = "sesID") String sesID) {
        return ResponseEntity.ok(favoritesService.getAllByAuthor(sesID));
    }
}
