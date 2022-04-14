package com.recipes.recipesmaven.recipe;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recipe")
@RequiredArgsConstructor
@Validated
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }

    @PostMapping("/new")
    public Map<String, Long> postRecipe(@Valid @RequestBody Recipe recipe) {
        return Map.of("id", recipeService.saveRecipe(recipe));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipeById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRecipe(@PathVariable Long id, @Valid @RequestBody Recipe recipe) {
        recipeService.updateRecipeById(id, recipe);
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    @GetMapping("/author")
    public ResponseEntity<Iterable<Recipe>> getAllRecipes(@RequestParam String login) {
        return ResponseEntity.ok(recipeService.getAllRecipesByAuthor(login));
    }



    @GetMapping("/orderBy/date")
    public ResponseEntity<Iterable<Recipe>> getAllRecipesOrderByDate() {
        return ResponseEntity.ok(recipeService.getAllRecipesOrderByDate());
    }

    @GetMapping("/orderBy/views")
    public ResponseEntity<Iterable<Recipe>> getAllRecipesOrderByViews() {
        return ResponseEntity.ok(recipeService.getAllRecipesOrderByViews());
    }

    @GetMapping("/fragment/{id}")
    public Map<String,String> getMainInformation(@PathVariable Long id) {
        List<String> list = recipeService.getRecipeByIdMainInformation(id);
        return Map.of("title", list.get(0),
                "main-photo", list.get(1));
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Recipe>> searchRecipe(@RequestParam Map<String, String> params) {
        if (params.size() == 1 && params.containsKey("category")) {
            return ResponseEntity.ok(recipeService.getAllRecipesByCategory(params.get("category")));
        } else if (params.size() == 1 && params.containsKey("name")) {
            return ResponseEntity.ok(recipeService.getAllRecipesContainingName(params.get("name")));
        }
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(RecipeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void recipeNotFoundExceptionHandler() {
    }
}